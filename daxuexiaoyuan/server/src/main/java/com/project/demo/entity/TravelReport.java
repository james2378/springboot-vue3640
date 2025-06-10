package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *出行报备：(TravelReport)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TravelReport")
public class TravelReport implements Serializable {

    //TravelReport编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_report_id")
    private Integer travel_report_id;
   // 学号
   @Basic
    private Integer student_number;
   // 院系
   @Basic
    private String faculty;
   // 专业
   @Basic
    private String major;
   // 班级名称
   @Basic
    private String class_name;
   // 体温
   @Basic
    private String temperature;
   // 行程码
   @Basic
    private String travel_code;
   // 健康码
   @Basic
    private String health_code;
   // 核酸报告
   @Basic
    private String nucleic_acid_report;
   // 外出时间
   @Basic
    private Timestamp out_time;
   // 行程
   @Basic
    private String trip;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
