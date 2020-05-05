package com.community.cyd.controller;


import com.community.cyd.dto.ConsultDTO;
import com.community.cyd.mapper.UserMapper;
import com.community.cyd.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConsultController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConsultService connsultService;
    @GetMapping("/consult")
    public String consult(Model model) {
        List<ConsultDTO> consultDTOS = connsultService.getConsult();
        model.addAttribute("consultDTOS", consultDTOS);
        return "consult";
    }
}
