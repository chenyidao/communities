package com.community.cyd.dto;

import com.community.cyd.model.User;
import lombok.Data;

/**
 * 咨询订单
 * */
@Data
public class ConsultDTO {
    private Long consultFee;  //咨询费用
    private User user;
    /*private String outTradeNo;  //订单号
    private String subject;     //订单名称
    private String description; //商品描述*/
}
