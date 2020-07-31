package com.ad.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Create By  @林俊杰
 * 2020/7/31 21:02
 *
 * @version 1.0
 */
@Entity
@Data
public class AdInfo {
    @Id
    private String adId;

    /*广告标题(寻物启事，组队招聘，寒暑假工)*/
    private String adTitle;

    /*照片连接*/
    private String adPic;

    /*用户Id*/
    private String userId;

    /*联系方式*/
    private String adContact;

}
