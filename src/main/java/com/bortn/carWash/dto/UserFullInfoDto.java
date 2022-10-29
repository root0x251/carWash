package com.bortn.carWash.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFullInfoDto {
    String name;
    String carNumber;
    String date;
    String time;

    public UserFullInfoDto() {
    }

    public UserFullInfoDto(String name, String carNumber, String date, String time) {
        this.name = name;
        this.carNumber = carNumber;
        this.date = date;
        this.time = time;
    }
}
