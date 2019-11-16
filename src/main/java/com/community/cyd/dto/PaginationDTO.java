package com.community.cyd.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;  //上一页按钮 <
    private boolean showFirstPage; //第一页按钮 <<
    private boolean showNext; //下一页按钮 >
    private boolean showEndPage; //最后一页按钮 >>
    private Integer page;   //所在页号
    private Integer totalPage;  //总页数
    private List<Integer> pages;  //所要显示的页号数组

    public void setPaginationDTO(Integer totalPage, Integer page) {

        this.page = page;
        this.totalPage = totalPage;
        pages = new ArrayList<>();

        //如果没有数据或数据少于1页，则不显示下面的按钮
        if (totalPage <= 1) {
            return;
        }

        pages.add(page);

        //展示当前的与其前后3个（如果前面的页少于3则只显示有的，后面的也是如此）
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否展示上一页按钮  如果页数在第1页就不用显示
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页按钮  如果页数在最后一页就不用显示
        if (page.equals(totalPage)) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页按钮<<  (如果显示的页号列表中包含第一页 则不展示)
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页按钮 >>
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
