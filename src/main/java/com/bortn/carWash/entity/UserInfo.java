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
    private String name;
    @Column(name = "car_number")
    private String carNumber;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public UserInfo() {
    }

    public UserInfo(String name, String carNumber) {
        this.name = name;
        this.carNumber = carNumber;
    }

}
