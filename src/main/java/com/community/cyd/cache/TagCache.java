package com.community.cyd.cache;

import com.community.cyd.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> getTags() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript","php","css","html","html5","java","node.js","python","c++","c"));
        tagDTOS.add(program);


        TagDTO frameWork = new TagDTO();
        frameWork.setCategoryName("平台框架");
        frameWork.setTags(Arrays.asList("spring","springboot","express","django","flask","koa","yii"));
        tagDTOS.add(frameWork);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","docker","apache","ubuntu","centos","tomcat","负载均衡","unix","hadoop","windows-server"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","mongodb","sql","oracle","sql server","postgresql","sqlite"));
        tagDTOS.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git","github","maven","ide","svn","vim","sublime-text","eclipse","visual-studio-code","jupyter-notebook"));
        tagDTOS.add(tool);
        return tagDTOS;
    }

    /**
     * 判断是否为非法标签
     * @return
     * **/
    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ',');
        List<TagDTO> tagDTOs = getTags();
        List<String> tagList = tagDTOs.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t->!tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
