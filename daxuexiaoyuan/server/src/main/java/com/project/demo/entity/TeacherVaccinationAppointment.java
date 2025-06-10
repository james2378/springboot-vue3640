package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *教师接种预约：(TeacherVaccinationAppointment)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TeacherVaccinationAppointment")
public class TeacherVaccinationAppointment implements Serializable {

    //TeacherVaccinationAppointment编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_vaccination_appointment_id")
    private Integer teacher_vaccination_appointment_id;
   // 疫苗名称
   @Basic
    private String vaccine_name;
   // 接种点
   @Basic
    private String inoculation_point;
   // 教师工号
   @Basic
    private Integer teacher_job_number;
   // 院系
   @Basic
    private String faculty;
   // 接种时间
   @Basic
    private Timestamp inoculation_time;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
