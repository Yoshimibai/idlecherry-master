package com.sydney.controller;


import com.sydney.entity.Commodity;
import com.sydney.entity.User;
import com.sydney.entity.UserCollection;
import com.sydney.service.CommodityService;
import com.sydney.service.UserCollectionService;
import com.sydney.service.UserService;
import com.sydney.entity.*;
import com.sydney.service.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.CommonDataSource;

import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {

    //local path for pictures
    public static String IMG_PATH = "/Users/chinone/Desktop/idlecherry-master/src/main/resources/static/img/";

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private UserCollectionService userCollectionService;


    @Autowired
    NoticeService noticeService;

    private UserService userService;


    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Sign up an account
     * @return
     */
    @ResponseBody
    @RequestMapping("signup")
    public String signUp(HttpServletRequest request, Map<String, String> parameter, HttpSession session) {
        //1. validate validation code

        //2. receive data from frontend
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        System.out.println("get email: " + email);
        System.out.println("get password: " + password);
        System.out.println("get password: " + firstName);
        System.out.println("get password: " + lastName);

        User userDB = userService.getUserByEmail(email);

        //3. check if the unique email exists in database
        if(ObjectUtils.isEmpty(userDB)) {
            System.out.println("?????????????????????????????????????????????email???????????????");
            User user = new User();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);

            //use API to setup the default avatar
            String avatarUrl = "https://ui-avatars.com/api/?name="+ firstName + "+" +lastName;
            user.setProfile(avatarUrl);
            userService.signup(user);
            parameter.put("message", "????????????");
            parameter.put("status", "ok");
            System.out.println("????????????????????????????????????");
        }else{
            System.out.println("??????????????????????????????????????????????????????email????????????????????????????????????");
            parameter.put("message", "?????????????????????????????????????????????");
            parameter.put("status", "no");
        }

        //4. return the information to front-end
        return JSON.toJSONString(parameter);
    }

    /**
     * Log in the account
     * @param request
     * @param session
     * @param parameter
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public String logIn(HttpServletRequest request, HttpSession session, Map<String, String> parameter) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("login account:" + email);
        System.out.println("login pwd:" + password);

        User userDB = userService.getUserByEmail(email);

        if(!ObjectUtils.isEmpty(userDB) && userDB.getPassword().equals(password)) { //username and password both correct
            //store the user information
            session.setAttribute("user", userDB);
            parameter.put("message", "????????????");
            parameter.put("status", "ok");
            System.out.println("??????????????????");
        }else if(!ObjectUtils.isEmpty(userDB)) {    //password is not correct
            parameter.put("message", "???????????????????????????");
            parameter.put("status", "no");
            System.out.println("????????????");
        }else { //username is not correct
            parameter.put("message", "??????????????????????????????");
            parameter.put("status", "no");
            System.out.println("???????????????");
        }

        return JSON.toJSONString(parameter);
    }


    /**
     * log out
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,HttpSession session){
        System.out.println("????????????");

        request.getSession().removeAttribute("user");
        return "test";
    }

    @RequestMapping("/usercenter")
    public String userAdmin(HttpServletRequest request, HttpSession session){
        return "admin/userInfo";
    }

    @PostMapping("/update")
    public String updateUser(HttpServletRequest request, HttpSession session, MultipartFile file) throws Exception {
        User user = (User) session.getAttribute("user");
        String password = request.getParameter("password"); // get new password
        String mobilephone = request.getParameter("mobilephone"); // get new phone number
        String gender = request.getParameter("gender");
        String school = request.getParameter("university");
        String oldPassword = user.getPassword();
        String oldMobilephone = user.getMobilephone();
        String oldGender = user.getGender();
        String oldSchool = user.getSchool();
        System.out.println("password " + password);
        System.out.println("mobilephone " + mobilephone);

        //only update when needed
        boolean needUpdate = false;
        if (!password.equals(oldPassword)) {
            user.setPassword(password);
            needUpdate = true;
        }
        if (!mobilephone.equals(oldMobilephone)) {
            user.setMobilephone(mobilephone);
            needUpdate = true;
        }

        if(!gender.equals(oldGender)) {
            user.setGender(gender);
            needUpdate = true;
        }

        if(!school.equals(oldSchool)) {
            user.setSchool(school);
            needUpdate = true;
        }

        if(needUpdate) {
            userService.update(user);
        }

        try {
            //handle the image name
            if(!file.isEmpty()) {
                String imgName = file.getOriginalFilename();
                String hash = UUID.randomUUID().toString().replaceAll("-", ""); //generate unique local name
                String extension = FilenameUtils.getExtension(imgName);
                String localName = hash + "." + extension;
                String pathname = IMG_PATH + localName;
                System.out.println(imgName);
                System.out.println(hash);
                System.out.println(extension);
                System.out.println(localName);
                System.out.println(pathname);

                //transfer to local
                file.transferTo(new File(pathname));

                //safe delete old image
                String oldHostPath = user.getProfile();
                String[] temp = oldHostPath.split("/");
                String oldLocalPath = temp[temp.length - 1];
                File oldVersion = new File(IMG_PATH + oldLocalPath);
                if (oldVersion != null) {
                    oldVersion.delete();
                }

                //set hostpath for new image mapping with server url
                String hostPath = request.getScheme() + "://" + request.getServerName() + ":"
                        + request.getServerPort() + "/idlecherry/images/" + localName;
                user.setProfile(hostPath);
                System.out.println(hostPath);

                //update database
                userService.updateImg(user);

                return "redirect:/user/releaseNew";
            }

            return "redirect:/user/releaseNew";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/user/releaseNew";
        }
    }


    @PostMapping("/updateCommodity")
    public String updateCommodity(Commodity commodity,HttpSession session){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = df.format(new Date());
        commodity.setUpdatetime(currentTime);
        System.out.println(commodity);
        User userFromSession = (User) session.getAttribute("user");
        if (commodity.getUserid() == userFromSession.getId()){
            commodityService.updateByCommidSelective(commodity);
            return "redirect:/user/commodityManage/"+commodity.getUserid();

        }
        return "/index";
    }

    @RequestMapping("/commodityManage")
    public String userManageCommodity(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("user");
        int uid = user.getId();// get uid
        List<Commodity> myOwnCommodities = commodityService.selectCommodityByUserId(uid);
        model.addAttribute("myOwnCommodities",myOwnCommodities);

        return "admin/commodityManagement";

    }

    @RequestMapping("/collectionManage")
    public String userManageCollection(HttpServletRequest request, HttpSession session){
        return "admin/collectionManagement";
    }

    @RequestMapping("/offshelf/{commId}")
    public String offShelfCommodities(@PathVariable int commId, HttpSession session){

        System.out.println("aaaaaaaaaaaaaaaa");
        //??????????????????????????????????????????
        //??????session????????????session????????????userid???goodsid?????????userid?????????
        User userFromSession = (User) session.getAttribute("user");
        int currentUserId = userFromSession.getId();
        commodityService.queryCommodityByID(commId).getUserid();
        //???????????????userid???session??????userid??????????????????????????????
        if(currentUserId == commodityService.queryCommodityByID(commId).getUserid() ){
            commodityService.pullOffShelvesById(commId);
            return "redirect:/user/commodityManage/"+currentUserId;
        }else {
            //????????????????????????
            return "error/intercept";
        }
    }

    @RequestMapping("/putOnSell/{commId}")
        public String onSellCommodities(@PathVariable int commId,HttpSession session){
        User userFromSession = (User) session.getAttribute("user");
        int currentUserId = userFromSession.getId();
        //???????????????userid???session??????userid??????????????????????????????
        if(currentUserId == commodityService.queryCommodityByID(commId).getUserid() ){
            commodityService.putOnSaleById(commId);
            return "redirect:/user/commodityManage/"+currentUserId;
        }else {
            //????????????????????????
            return "error/intercept";
        }
    }

    @RequestMapping("/deleteCollection/{commId}")
    public String deleteCollection(@PathVariable int commId,HttpSession session){
        User userFromSession = (User) session.getAttribute("user");
        int currentUserId = userFromSession.getId();
        if (currentUserId == userCollectionService.selectByCommIDandUserID(commId,currentUserId).getUserid()){
            userCollectionService.deleteByCommIDandUserID(commId,currentUserId);
            return "redirect:/user/collectionManage/" + currentUserId;
        }else {
            //????????????????????????
            return "error/intercept";
        }
    }

    @RequestMapping(value = "/Notice")
    public String showNotice(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Notice> noticeList = noticeService.selectByUserID(user.getId());
        model.addAttribute("noticeList", noticeList);
        return "Notice";
    }

    @RequestMapping("/editMyCommodity")
    public String editCommodity(@PathVariable int commId,HttpSession session,Model model){

        return "admin/editMyCommodity";

    }

    /**
     * purely used to redirect to the desired page
     *
     */
    @RequestMapping("/releaseNew")
    public String releasePage(HttpServletRequest request, HttpSession session) {
        System.out.println("???????????????");
        return "admin/releaseNew";
    }


    /**
     * release new commodity
     * add the information to database
     */
    @PostMapping("/releaseRequest")
    public String releaseRequest(HttpServletRequest request, HttpSession session, MultipartFile file) throws Exception{

        try {
            //user input commodity text information here
            User user = (User) session.getAttribute("user");
            int uid = user.getId();// get uid
            String name = request.getParameter("name"); // get commodity name
            String description = request.getParameter("description"); // get description
            BigDecimal price = new BigDecimal(request.getParameter("price")); // get price
            int categoryID = Integer.parseInt(request.getParameter("category")); //get categoryID
            System.out.println("uid " + uid);
            System.out.println("category " + categoryID);
            System.out.println("??????????????????????????????");

            //handle the image name
            String imgName = file.getOriginalFilename();
            String hash = UUID.randomUUID().toString().replaceAll("-", ""); //generate unique local name
            String extension = FilenameUtils.getExtension(imgName);
            String localName = hash + "." + extension;
            String pathname = IMG_PATH + localName;
            System.out.println(imgName);
            System.out.println(hash);
            System.out.println(extension);
            System.out.println(localName);
            System.out.println(localName);

            //transfer to local
            file.transferTo(new File(pathname));

            //set hostPath for new image mapping with server url
            String hostPath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + "/idlecherry/images/" + localName;
            System.out.println(hostPath);

            //register it into database
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = df.format(new Date());
            Commodity commodity = new Commodity();
            commodity.setCommname(name); // set name
            commodity.setCommdesc(description); // set description
            commodity.setPrice(price); //set price
            commodity.setUpdatetime(currentTime); // set post time
            commodity.setImage(hostPath); // set image path
            commodity.setCategoryid(categoryID); //set category
            commodity.setCommstatus(1); // set to for sale
            commodity.setUserid(uid);  // set owner id
            commodityService.register(commodity);
            return "redirect:/user/releaseNew";

        } catch(Exception e) {
            //failed upload image
            e.printStackTrace();
            return "redirect:/user/releaseNew";
        }
    }



}

