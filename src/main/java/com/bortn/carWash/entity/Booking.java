package com.bortn.carWash.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "b_time")
    private String time;
    @Column(name = "b_date")
    private String date;

    public Booking() {
    }

    public Booking(String time, String date) {
        this.time = time;
        this.date = date;
    }
}
