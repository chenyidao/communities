package com.community.cyd.controller;

import com.community.cyd.cache.HotTagCache;
import com.community.cyd.dto.PaginationDTO;
import com.community.cyd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/****
 * 首页
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    /**
     * 带分页、搜索条件、搜索标签
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "9") Integer size,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "search", required = false) String search) {   //page 页号   size 每页大小
        PaginationDTO paginationDTO = questionService.questionList(page, tag, size, search);   //获取问题列表
        List<String> hots = hotTagCache.getHots();
        model.addAttribute("pagination", paginationDTO);
        model.addAttribute("search", search); //当点击时上下页索引时，还是显示满足search条件的问题
        model.addAttribute("tag", tag);
        model.addAttribute("hots", hots); //热点问题
        return "index";
    }
}