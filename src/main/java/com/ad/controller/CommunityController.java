package com.ad.controller;

import com.ad.VO.ResultVO;
import com.ad.dto.PostDTO;
import com.ad.enums.ClassificationEnum;
import com.ad.service.Impl.PostServiceImpl;
import com.ad.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Create By  @林俊杰
 * 2020/8/18 22:43
 *
 * @version 1.0
 */
@Slf4j
@Controller
public class CommunityController {
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("api/user/community")
    @ResponseBody
    public ResultVO community(@RequestParam("grade") int grade,
                              @RequestParam("classification") int classification,
                              @RequestParam("groupnum") int groupNum,
                              @RequestParam("groupsize") int groupSize,
                              HttpServletRequest request,
                              HttpServletResponse response){

        List<PostDTO> postDTOList = null;
        //TODO if (grade = xxx) if(classification = xxx)
        if(classification== ClassificationEnum.ORDER_BY_TIME.getCode()){
            //"新鲜"栏目社区
            postDTOList = postService.findListOrderByTime(groupNum,groupSize);
        }else if (classification == ClassificationEnum.ORDER_BY_MY.getCode()){
            //TODO "我的"栏目社区

        }else if (classification == ClassificationEnum.ORDER_BY_SYS.getCode()){
            //TODO "推荐"栏目社区

        }else{
            log.error("请求参数错误,请检查设置");
        }

        if(postDTOList.isEmpty()){
            log.error("获取社区信息失败");
            return ResultVOUtil.errorMsg("获取社区信息失败");
        }

        return ResultVOUtil.success(postDTOList);
    }
}
