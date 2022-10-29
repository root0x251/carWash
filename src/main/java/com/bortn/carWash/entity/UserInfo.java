package com.bortn.carWash.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "user_info")
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

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public UserInfo() {
    }

    public UserInfo(String name, String phone, String mail, String carNumber) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.carNumber = carNumber;
    }
}
