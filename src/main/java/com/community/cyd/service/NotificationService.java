package com.community.cyd.service;

import com.community.cyd.dto.NotificationDTO;
import com.community.cyd.dto.PaginationDTO;
import com.community.cyd.enums.NotificationStatusEnum;
import com.community.cyd.enums.NotificationTypeEnum;
import com.community.cyd.exception.CustomizeErrorCode;
import com.community.cyd.exception.CustomizeException;
import com.community.cyd.mapper.NotificationMapper;
import com.community.cyd.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 通知业务
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 获取带通知的分页列表
     * */
    public PaginationDTO getListByUserId(Long userId, Integer page, Integer size) {

        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage;

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiveEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPaginationDTO(totalPage, page); //设置返回前端显示的内容

        //问题：当page < 0 时 以及 page > totalPage时 查询不到questionList ???
        Integer offset = size * (page - 1);    //select * from question limit offset,size 获取偏移量
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiveEqualTo(userId);
        example.setOrderByClause("gmt_create desc");

        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<NotificationDTO> notificationDTOS = notifications.stream().map(n -> {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(n, notificationDTO);
            //设置通知类型的名字
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(n.getType()));
            return notificationDTO;
        }).collect(Collectors.toList());
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    /**
     * 获取未读通知数（status = 0）
     */
    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiveEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    /**
     * 更新通知状态
     */
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);

        /*通知未找到*/
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        /*判断当前登录的用户，是否是被通知的用户*/
        if (!Objects.equals(notification.getReceive(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        /*点击通知后，status变成已读*/
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        /*返回相应的通知DTO*/
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;

    }
}

