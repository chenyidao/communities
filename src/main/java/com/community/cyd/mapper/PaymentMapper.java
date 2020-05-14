package com.community.cyd.mapper;

import com.community.cyd.model.Payment;
import com.community.cyd.model.PaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PaymentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    long countByExample(PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    int deleteByExample(PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    int insert(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    int insertSelective(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    List<Payment> selectByExampleWithRowbounds(PaymentExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    List<Payment> selectByExample(PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    Payment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    int updateByExampleSelective(@Param("record") Payment record, @Param("example") PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    int updateByExample(@Param("record") Payment record, @Param("example") PaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    int updateByPrimaryKeySelective(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PAYMENT
     *
     * @mbg.generated Thu May 14 00:48:49 CST 2020
     */
    int updateByPrimaryKey(Payment record);
}