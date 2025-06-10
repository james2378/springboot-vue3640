package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学生接种预约：(StudentVaccinationAppointment)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StudentVaccinationAppointment")
public class StudentVaccinationAppointment implements Serializable {

    //StudentVaccinationAppointment编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_vaccination_appointment_id")
    private Integer student_vaccination_appointment_id;
   // 疫苗名称
   @Basic
    private String vaccine_name;
   // 接种点
   @Basic
    private String inoculation_point;
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
   // 预约时间
   @Basic
    private Timestamp time_of_appointment;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
