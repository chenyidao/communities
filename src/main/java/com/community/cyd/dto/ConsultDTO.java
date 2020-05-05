package com.community.cyd.dto;

import com.community.cyd.model.User;
import lombok.Data;

@Data
public class ConsultDTO {
    private String outTradeNo;  //订单号
    private Long total_amount;  //付款金额
    private String subject;     //订单名称
    private String description; //商品描述
    private User user;
}
