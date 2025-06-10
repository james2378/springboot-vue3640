package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *销假申请：(LeaveApplication)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LeaveApplication")
public class LeaveApplication implements Serializable {

    //LeaveApplication编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_application_id")
    private Integer leave_application_id;
   // 请假单号
   @Basic
    private String leave_no;
   // 学号
   @Basic
    private Integer student_number;
   // 教师
   @Basic
    private Integer teacher;
   // 实际天数
   @Basic
    private String actual_days;
   // 销假凭证
   @Basic
    private String certificate_of_cancellation_of_leave;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
