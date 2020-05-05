package com.community.cyd.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class PayController {
    private final String APP_ID = "2016102400749154";
    private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMer++XD1yxjaPIvRUoRQZCDg/JthcAZulQXtCHbtL9AGNomG7L/gxsVayoptkh88GqAbfMdiji1p1Npam3LllZBeQum0Le+BkJ2QkHW96X+aI0WyrlLa/aND4dLNzRMb4WftEvQJ/kzQ/ckFI/7uOd0JjNCzJQmEbvFkPqKpAYNshtA3Ofv7I3TsDwyOCeaNyPk+C4Q8YgL7uPnp7xlAz0wb/QXDIvj9HYLeJsstoa/Trprn7KJQltg+4EZz3pndXm3xpBhn64puFprFxjAIVLyu+2JiBF0uttze3ZQ3jjdbo7hfP5r7vjw6DGjyEHSBIHYeyJp/JEakXWdjfkL1fAgMBAAECggEAVZPhgyHF/Ulqqz/sADIwgB9VxILxgvsnykBdyiU6mEaJO7MUPmg2lm8mFaShbViecbuilA1VoCuga49Zr0T/XBjhkjU/gKNySxEEHEwiQTZyxh5uSQhLW8eKphDR0QKxA1Itk+5guz79sTj1NHc11tAoDS8XqXNanP7ccoGZVaXDp4LwJleDIiPmgz8zNXuOdxvbMKP5stSuR0kaR4dXopAhrspW305eJ8nOvQlDz4QzzoLHSika08cQIOtWlY1f909ZPIoHfLCvwg7qiWCAM2GjtVlgrmmuSHS6AgS+3nnlyaurtoYknVgQyR3R/kUTto+cUcJEU1Z25XFRdtTCAQKBgQDMRpO4o2EfnI4E/gEJzNVCfreUwioxMoIJVPDvvPAUPPIWBHbZzJPNbCaGVsyVQbnvkTOzeAfjsNzpCENbTjJRK28P97OPZBavrwM5QE8dO+vjzpNB6c1PTzZ053vhqyhAotfjOwsQjRalrDKA6z2y0TJ5ADozcFvgy8h3q9KkHwKBgQCwDNEhrubsN7qzX5GvfmcUHcE+w31fe8SvUKtulD/jAJnd+xlYrHecx0xph+YuMe3w0SKUV/vnFSQFNxsuIzVgfk4fXnMddj9JT3ptfrQFrghsSg9izRW8IYrTPEJScSB81T+lFqD6DTw5VJfKmeEH30mHY6CD6Eyo2bY34Yy+wQKBgQCsThvscmNKNtPUgix+B7kbDafsYpsURHZLSMqybbxUVdQdnkZiEJ3beI5GYpDOlVIxMVla2LRO6rBsH/ww2BS5Qtm7rkRXfyVjq0wczpHtJs4iqeysfHohm79jfJ6SC704SCOyF+uNWnTv6f/vnn28h5j7V6XAHdZiF4JQ7y4OIQKBgHCFEJlFr2Qkzlhc6cFPudQanGtgCN6hPXmLxrYbXKXauX0uQunw+VYj70u3xz+ZO+nh0QcdIOx2D5qhuOaJLFFrCMV12X9oF1TkzmvzQl4q+Ek2IJVWXODsU01lQ0Jr+YavyUjbqLGJCgfecFHWfgRJCzbUT0BflbeJxrP6nblBAoGAIO4PJEqgUdhHIJXzrHbvOaGgMzpH7WHHxAKE5KJO/sgw2kE3ivbBmFkhTQUesb+FlYPbn2DwvM7orIP5xBdEWw65tvrLWXpwM+jQzsVUw+5t0/J3Rn1KVxm699kYp6Bb18Guz2dDQIHDRAKVjTW8oXfJ8CrBCrxCvPjvjWGxe7Y=";
    private final String CHARSET = "UTF-8";
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAloBuJABX9N7cxmMFxpXVwdAPNHtsRAMcOUhDgLGkZ7cCfxBTo/d+Z9mEUQD7zabuP2B5/2wXBtWi1tVKJan2rkeWhbtW3ZNlmV2PoS0eAKlOE7YDSqmHe1OtOWajynHtu/2DcFzV3ajTRv1kDS4aF6ZPJxD8IqiwUWDcrtxEh7Vr3AMT68QiRgm17E4AZz9ZrZDKkJjO2kbbCYB89mbcg5qB1xdd0DJx3V2AcVMQp5bT8ctVay2UASitS73izx8duDPxNJcc9NaT5OstukHN9R2W09I9gvXoZw3mt2kjxQKtH9BJKqWoJua9d8fOye8znFokVs7oZomJwoMMcH3GdwIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://localhost:8887/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://127.0.0.1:8887/returnUrl";

    /**
     * 发起支付
     * */
    @RequestMapping(value = "/alipay", method = RequestMethod.GET)
    public void alipay(HttpServletResponse httpResponse,
                       @RequestParam(name = "no", required = false) String no,
                       @RequestParam(name = "amount", defaultValue = "0") Long amount,
                       @RequestParam(name = "subject", required = false) String sub,
                       @RequestParam(name = "desc", required = false) String desc
    ) throws IOException {
        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = no;
        //付款金额，必填
        String total_amount = Long.toString(amount);
        //订单名称，必填
        String subject = sub;
        //商品描述，可空
        String body = desc;
        request.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    /**
     * 支付成功后回调地址
     * */
    @RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
    @ResponseBody
    public String returnUrl(HttpServletRequest request, HttpServletResponse response)
            throws IOException, AlipayApiException {
        System.out.println("=================================同步回调=====================================");

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);//查看参数都有哪些
        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
        //验证签名通过
        if (signVerified) {
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("商户订单号=" + out_trade_no);
            System.out.println("支付宝交易号=" + trade_no);
            System.out.println("付款金额=" + total_amount);

            //支付成功，修复支付状态
//            payService.updateById(Integer.valueOf(out_trade_no));
            return "ok";//跳转付款成功页面
        } else {
            return "no";//跳转付款失败页面
        }

    }
}
