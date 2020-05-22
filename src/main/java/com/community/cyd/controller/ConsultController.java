package com.community.cyd.controller;


import com.community.cyd.dto.ConsultDTO;
import com.community.cyd.model.User;
import com.community.cyd.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ConsultController {
    @Autowired
    private ConsultService consultService;

    /**
     * 咨询接口
     */
    @GetMapping("/getConsultList")
    public String consultList(Model model,
                              HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<ConsultDTO> consultDTOS = consultService.getConsultList(user.getId());
        model.addAttribute("consultDTOS", consultDTOS);
        return "consultList";
    }

    @GetMapping("/consult")
    public String consultPage(Model model,
                              @RequestParam(name = "consultant") Long consultantId,
                              @RequestParam(name = "consultee") Long consulteeId,
                              @RequestParam(name = "consultFee") String consultFee) {
        model.addAttribute("consultantId", consultantId);
        model.addAttribute("consulteeId",consulteeId);
        model.addAttribute("consultFee",consultFee);
        return "consult";
    }
}
