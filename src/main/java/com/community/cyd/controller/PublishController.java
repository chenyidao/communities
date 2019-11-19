package com.community.cyd.controller;

import com.community.cyd.dto.QuestionDTO;
import com.community.cyd.model.Question;
import com.community.cyd.model.User;
import com.community.cyd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 发布问题
 **/
@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    /**
     * 编辑
     **/
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        //回显到页面
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("id", id); //用于之后前端传回
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    /**
     * 发布
     **/
    @PostMapping("/publish")
    public String doPublish(    //接收前端参数，允许为空
                                @RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "description", required = false) String description,
                                @RequestParam(value = "tag", required = false) String tag,
                                @RequestParam(value = "id", required = false) Integer id,
                                HttpServletRequest request,
                                Model model) {

        //获取session
        User user = (User) request.getSession().getAttribute("user");

        //回显到页面
        model.addAttribute("tag", tag);
        model.addAttribute("description", description);
        model.addAttribute("title", title);

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        //问题：这里的user中的accountId为null？？？  未开启驼峰式命名配置
        //问题存在则更新，不存在则插入
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId()); //通过user的Id与question的creator关联
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
