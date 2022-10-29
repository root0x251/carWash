package com.bortn.carWash.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFullInfoDto {
    String name;
    String phone;
    String mail;
    String carNumber;
    String date;
    String time;
    String isDone;
    String bookingId;

    public UserFullInfoDto() {
    }

    public UserFullInfoDto(String name, String phone, String mail, String carNumber, String date, String time, String isDone, String bookingId) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.carNumber = carNumber;
        this.date = date;
        this.time = time;
        this.isDone = isDone;
        this.bookingId = bookingId;
    }
}
