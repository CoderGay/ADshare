package com.ad.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Create By  @林俊杰
 * 2020/7/31 21:01
 *
 * @version 1.0
 */
@Entity
@Data
public class CommentInfo {
    @Id
    private String commentId;

    /*帖子ID*/
    private String postId;

    /*用户Id*/
    private String userId;

    /*文件Id*/
    private String docId;

    /*评论内容:[评论类型为纯文本时不能为空]*/
    private String commentContent;

    /*评论类型：0为纯文本，1为附资源*/
    private int commentType;

    /*评论时间*/
    private Date commentTime;


}
