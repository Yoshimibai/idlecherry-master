package com.sydney.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sydney.entity.Commodity;
import com.sydney.entity.User;
import com.sydney.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    UserCollectionService userCollectionService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    CommodityService commodityService;

    @Autowired
    UserService userService;

    @Autowired
    CommodityCommentService commodityCommentService;


    /*
     * 主页
     * */
    @RequestMapping(value = "/index")
    public String index(Model model,
                        HttpSession session,
                        @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                        @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        //最新发布
        List<Commodity> commodities = commodityService.findLatestCommodity();

        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/index?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);



        return "index";
    }


    @RequestMapping(value = "/commodities")
    public String getCommodities( Model model,
                                  HttpSession session,
                                  @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                                  @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getAllCommodities();
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/commodities?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return  "goods";
    }



    /*
    * 搜索
    * */
    @RequestMapping(value = "/search")
    public String searchCommodity(@PathParam("searchContent") String searchContent,
                                  Model model,
                                  HttpSession session,
                                  @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                                  @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getCommodityBySearch(searchContent);
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/search?searchContent=" + searchContent + "&";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return  "goods";
    }

    /*
    * Clothes category
    * */
    @RequestMapping(value = "/commodity/clothes")
    public String getClothes(Model model,
                             HttpSession session,
                             @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                             @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getCommodityByCategory(1);
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/commodity/clothes?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return "goods";
    }

    @RequestMapping(value = "/commodity/books")
    public String getBooks(Model model,
                           HttpSession session,
                           @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                           @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getCommodityByCategory(2);
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/commodity/books?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return "goods";
    }

    @RequestMapping(value = "/commodity/homeApplicance")
    public String getHomeApplicance(Model model,
                           HttpSession session,
                           @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                           @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getCommodityByCategory(3);
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/commodity/homeApplicance?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return "goods";
    }

    @RequestMapping(value = "/commodity/electronics")
    public String getElectronics(Model model,
                                 HttpSession session,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                                 @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getCommodityByCategory(4);
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/commodity/electronics?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return "goods";
    }

    @RequestMapping(value = "/commodity/sports")
    public String getSports(Model model,
                                    HttpSession session,
                                    @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                                    @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getCommodityByCategory(5);
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/commodity/sports?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return "goods";
    }

    @RequestMapping(value = "/commodity/healthyBeauty")
    public String getHealthyBeauty(Model model,
                                 HttpSession session,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                                 @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getCommodityByCategory(6);
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/commodity/healthyBeauty?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return "goods";
    }

    @RequestMapping(value = "/commodity/entertainment")
    public String getEntertainment(Model model,
                                 HttpSession session,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                                 @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getCommodityByCategory(7);
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/commodity/entertainment?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return "goods";
    }

    @RequestMapping(value = "/commodity/others")
    public String getOthers(Model model,
                                 HttpSession session,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNumber") Integer pageNumber,
                                 @RequestParam(defaultValue = "6", value = "pageSize") Integer pageSize){

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String userName = user.getFirstName() + " "+ user.getLastName();

        //如果登录了
        if(session.getAttribute("user") != null){
            model.addAttribute("userName", userName);
            model.addAttribute("userId", userId);
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        if(pageSize == null){
            pageSize = 6;
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Commodity> commodities = commodityService.getCommodityByCategory(8);
        PageInfo pageInfo = new PageInfo(commodities);

        //到当前页的请求
        String requestPath = "/idlecherry/commodity/others?";
        model.addAttribute("requestPath", requestPath);
        model.addAttribute("commodities", commodities);
        model.addAttribute("pageInfo", pageInfo);

        //从数据库中拿到图片的连接以作为首页商品略缩图展示
        //搞个map来放url
        HashMap<Integer,String> images = new HashMap<Integer,String>();
        for (Commodity commodity : commodities){
            if((commodity.getCommstatus() == 1) && (commodity.getImage() != null))
                images.put(commodity.getCommid(), commodity.getImage());
        }
        model.addAttribute("images", images);

        return "goods";
    }
}
