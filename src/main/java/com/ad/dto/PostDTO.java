package com.ad.dto;

import lombok.Data;

import java.util.Date;

/**
 * Create By  @林俊杰
 * 2020/8/18 23:30
 *
 * @version 1.0
 */
@Data
public class PostDTO {

    private Integer postId;

    /*标题*/
    private String title;

    /*内容*/
    private String content;

    /*帖主名称*/
    private Integer username;

    /*帖主头像*/
    private String avatarUrl;

    /*回复数量*/
    private Integer commentNum;

    /*标签内容*/
    private String tag;

    /*最近回复时间*/
    private Date updateTime;
}