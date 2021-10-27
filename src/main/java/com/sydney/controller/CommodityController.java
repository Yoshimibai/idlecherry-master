package com.sydney.controller;

import com.alibaba.fastjson.JSONObject;
import com.sydney.entity.Commodity;
import com.sydney.entity.CommodityComment;
import com.sydney.entity.User;
import com.sydney.service.CommodityCommentService;
import com.sydney.service.CommodityService;
import com.sydney.service.UserService;
import com.sydney.entity.*;
import com.sydney.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class CommodityController {


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

    @RequestMapping(value = "/CommodityDetail")
    /*
    开发思路：商品主页面有很多商品，每个商品都有一个对应的超链接，点了这个超链接后
    根据商品id查找该商品的所有详细信息，跳转页面，展示到CommodityDetail.html中
     */
    public String showCommodityDetail(Model model, HttpSession session, Integer id) {

        Commodity commodity = commodityService.queryCommodityByID(id);
        /*
        前端通过commdity.xxx就能获取该commodity的所有信息
         */
        model.addAttribute("commodity", commodity);

        /*
        下面来获取该commodity的所有者的信息
        前端通过user.xxx就能获取该commodity的所有者的所有信息
         */
        User commodityOwner = userService.getUserById(commodity.getUserid());
        model.addAttribute("commodityOwner", commodityOwner);

        /*
        下面来获取该commodity的comment
        前端通过comment.xxx就能获取该commodity的comment的所有信息
        为什么是list？因为一件商品会有多个评论
        前端可以通过for each循环遍历这个list获取所有评论，
         */
        List<CommodityComment> commentList = commodityCommentService.selectCommentsByCommID(commodity.getCommid());
        model.addAttribute("commentList", commentList);


        //0显示收藏 1显示已收藏
        Integer showFavoriteButtonOrNot;
        /*
        判断用户是否登录，即使未登录，也会显示收藏按钮
        若登录，判断用户是否已经收藏了该商品
         */
        User user = (User) session.getAttribute("user");
        if(user == null){
            showFavoriteButtonOrNot = 0;
        }else{
            UserCollection userCollection = userCollectionService.selectByCommIDandUserID(commodity.getCommid(), user.getId());
            if(userCollection == null){
                showFavoriteButtonOrNot = 0;
            }else {
                showFavoriteButtonOrNot = 1;
            }
        }
        model.addAttribute("showFavoriteButtonOrNot", showFavoriteButtonOrNot);

        return "CommodityDetail";
    }

    @RequestMapping(value = "/FavoriteCommodity")
    /*
    商品详情页面进行商品收藏
    商品收藏功能 首先得判断用户是否登录以及用户是否收藏了该商品。
    如果用户没登录，点击收藏按钮，自动跳转到登录页面；
    如果用户登录且没收藏该商品，点击收藏按钮可收藏，并且把收藏变成已收藏；
    如果如果用户登录且已经收藏该商品，显示已收藏
     */
    public String favoriteCommodity(Model model, HttpSession session, Integer commid) {

        User user = (User) session.getAttribute("user");
        //如果用户未登录 自动跳转到登录页面
        if(user == null){
            return "logsign";
        }

        Integer userID = user.getId();
        Commodity commodity = commodityService.queryCommodityByID(commid);
        String commName = commodity.getCommname();
        String commDesc = commodity.getCommdesc();
        BigDecimal price = commodity.getPrice();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = df.format(new Date());

        UserCollection userCollection = new UserCollection();
        userCollection.setCommid(commid);
        userCollection.setUserid(userID);
        userCollection.setCommname(commName);
        userCollection.setCommdesc(commDesc);
        userCollection.setPrice(price);
        userCollection.setCollectiontime(currentTime);
        userCollectionService.insertSelective(userCollection);

        model.addAttribute("commodity", commodity);
        User commodityOwner = userService.getUserById(commodity.getUserid());
        model.addAttribute("commodityOwner", commodityOwner);
        List<CommodityComment> commentList = commodityCommentService.selectCommentsByCommID(commodity.getCommid());
        model.addAttribute("commentList", commentList);

        /*
        执行到这一步，用户肯定是收藏了该商品的
         */
        Integer showFavoriteButtonOrNot = 1;
        model.addAttribute("showFavoriteButtonOrNot", showFavoriteButtonOrNot);

        return "redirect:/CommodityDetail?id=" + commid;
    }

    @RequestMapping(value = "/deFavoriteCommodity")
    public String deFavourite(Model model, HttpSession session, Integer commid){
        User user = (User) session.getAttribute("user");
        //如果用户未登录 自动跳转到登录页面
        if(user == null){
            return "logsign";
        }

        Integer userID = user.getId();
        userCollectionService.deleteByCommIDandUserID(commid,userID);

        return "redirect:/CommodityDetail?id=" + commid;
    }


    @RequestMapping(value = "/PostComment")
    public String postComment(Model model, HttpSession session, Integer commid, String comment) {
        /*
        发表评论
        首先得判断用户是否登录，如果未登录，自动跳转到登录界面
         */
        User user = (User) session.getAttribute("user");
        //如果用户未登录 自动跳转到登录页面
        if(user == null){
            return "logsign";
        }

        CommodityComment commodityComment = new CommodityComment();
        commodityComment.setCommid(commid);
        commodityComment.setUserid(user.getId());
        commodityComment.setContent(comment);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = df.format(new Date());
        commodityComment.setCommenttime(currentTime);
        commodityCommentService.insertSelective(commodityComment);

        Commodity commodity = commodityService.queryCommodityByID(commid);
        model.addAttribute("commodity", commodity);
        User commodityOwner = userService.getUserById(commodity.getUserid());
        model.addAttribute("commodityOwner", commodityOwner);
        List<CommodityComment> commentList = commodityCommentService.selectCommentsByCommID(commodity.getCommid());
        model.addAttribute("commentList", commentList);

        //0显示收藏 1显示已收藏
        Integer showFavoriteButtonOrNot;
        UserCollection userCollection = userCollectionService.selectByCommIDandUserID(commodity.getCommid(), user.getId());
        if(userCollection == null){
            showFavoriteButtonOrNot = 0;
        }else {
            showFavoriteButtonOrNot = 1;
        }
        model.addAttribute("showFavoriteButtonOrNot", showFavoriteButtonOrNot);

        //向商品拥有者发送通知：商品被评论
        Notice notice = new Notice();
        notice.setNoticetypeid(1);
        notice.setUserid(commodity.getUserid());
        notice.setNoticetime(currentTime);
        String noticeContent = "您的商品" + commodity.getCommname() + "已被评论！快去看看吧";
        notice.setNoticecontent(noticeContent);
        noticeService.insertSelective(notice);

        return "redirect:/CommodityDetail?id=" + commid;
    }



}
