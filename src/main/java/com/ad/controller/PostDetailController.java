package com.ad.controller;

import com.ad.VO.PostDetailsVO;
import com.ad.VO.ResultVO;
import com.ad.converter.CommentInfo2CommentDTOConverter;
import com.ad.config.MySessionContext;
import com.ad.converter.PostInfo2PostDTOConverter;
import com.ad.dto.CommentDTO;
import com.ad.dto.PostDTO;
import com.ad.enums.ScoreEnum;
import com.ad.pojo.*;
import com.ad.service.Impl.*;
import com.ad.utils.MyDateUtil;
import com.ad.utils.ResultVOUtil;
import jdk.internal.dynalink.support.LinkerServicesImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WenZhikun
 * @data 2020-08-23 19:42
 */
@Controller
@Slf4j
public class PostDetailController {

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TagLinkServiceImpl tagLinkService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    private RecommendServiceImpl recommendService;

    @RequestMapping(value = "/api/postdetails")
    @ResponseBody
    public ResultVO details(@RequestParam(value = "postId",required = false)int postId,
                            HttpServletRequest request){

        String sessionId = request.getHeader("Cookie").split("=")[1];
        UserInfo user_login = null;
        try {
            user_login = (UserInfo) MySessionContext.getSession(sessionId).getAttribute("userInfo");
        }catch (Exception e){
            log.error("用户cookie获取失败");
        }

        //创建帖子信息
        PostInfo postInfo = postService.findOneById(postId);
        PostDTO postDTO = new PostDTO();
        //贴主信息
        UserInfo userInfo = userService.findOneById(postInfo.getUserId());

        //通过postId获取tagLink
        List<TagLink>tagLinkList = tagLinkService.findByPostId(postId);
        System.out.println(tagLinkList.toString());
        List<TagInfo>tagInfoList = new ArrayList<>();
        if (tagLinkList!=null&&tagLinkList.size()>=1)
        for (int i=0;i<tagLinkList.size();i++){
            tagInfoList.add(tagService.findOneById(tagLinkList.get(i).getTagId()));
            System.out.println(tagInfoList.get(i).getTagId()+" : " +tagInfoList.get(i).getTagContent());
        }

        try {
            postDTO= PostInfo2PostDTOConverter.convert(postInfo,userInfo,tagInfoList);
        }catch (Exception e){
            log.error("话题DTO转换失败");
        }


        List<CommentInfo>commentInfoList = commentService.findByPostId(postId);
        List<CommentDTO>commentDTOList = new ArrayList<>();
        CommentDTO commentDTO = null;
        UserInfo userInfo1 = null;
        for (int i=0;i<commentInfoList.size();i++){
            userInfo1 = userService.findOneById(commentInfoList.get(i).getUserId());
            commentDTO = CommentInfo2CommentDTOConverter.convert(userInfo1,commentInfoList.get(i));
            commentDTOList.add(commentDTO);
        }
        PostDetailsVO postDetailsVO = new PostDetailsVO();
        postDetailsVO.setPostDTO(postDTO);
        postDetailsVO.setCommentDTOList(commentDTOList);
        try{
            if (recommendService.findOneByUserIdAndPostId(user_login.getUserId(),postId)==null){
                recommendService.addScore(user_login.getUserId(),0,postId,0);
            }else {
                recommendService.clickAgain(recommendService.findOneByUserIdAndPostId(user_login.getUserId(),postId).getScoreId());
            }
        }catch (Exception e){
            log.error("用户点击行为检测失败");
        }

        return ResultVOUtil.build(200,"success",postDetailsVO);
    }
}
