package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *请假管理：(LeaveManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LeaveManagement")
public class LeaveManagement implements Serializable {

    //LeaveManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_management_id")
    private Integer leave_management_id;
   // 请假单号
   @Basic
    private String leave_no;
   // 专业
   @Basic
    private String major;
   // 班级名称
   @Basic
    private String class_name;
   // 教师
   @Basic
    private Integer teacher;
   // 请假类型
   @Basic
    private String leave_type;
   // 天数
   @Basic
    private String days;
   // 开始时间
   @Basic
    private Timestamp start_time;
   // 结束时间
   @Basic
    private Timestamp end_time;
   // 学号
   @Basic
    private Integer student_number;
   // 院系
   @Basic
    private String faculty;
   // 请假原因
   @Basic
    private String reason_for_leave;
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
