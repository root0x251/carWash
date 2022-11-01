package com.bortn.carWash.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "full_user")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "u_name")
    private String name;
    @Column(name = "u_phone")
    private String phone;
    @Column(name = "u_mail")
    private String mail;
    @Column(name = "car_number")
    private String carNumber;
    @Column(name = "b_time")
    private String time;
    @Column(name = "b_date")
    private String date;
    @Column(name = "is_done")
    private boolean isDone;

    public UserInfo() {
    }

    public UserInfo(String name, String phone, String mail, String carNumber, String time, String date, boolean isDone) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.carNumber = carNumber;
        this.time = time;
        this.date = date;
        this.isDone = isDone;
    }
}
