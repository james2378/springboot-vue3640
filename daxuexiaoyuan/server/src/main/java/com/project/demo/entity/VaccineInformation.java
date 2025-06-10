package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *疫苗信息：(VaccineInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VaccineInformation")
public class VaccineInformation implements Serializable {

    //VaccineInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_information_id")
    private Integer vaccine_information_id;
   // 疫苗名称
   @Basic
    private String vaccine_name;
   // 接种点
   @Basic
    private String inoculation_point;
   // 地址
   @Basic
    private String address;
   // 是否有苗
   @Basic
    private String whether_there_are_seedlings;
   // 上班时间
   @Basic
    private String work_shift;
   // 备注
   @Basic
    private String remarks;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
