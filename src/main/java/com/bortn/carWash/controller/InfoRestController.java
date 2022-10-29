package com.bortn.carWash.controller;

import com.bortn.carWash.entity.UserInfo;
import com.bortn.carWash.repos.BookingRepository;
import com.bortn.carWash.repos.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e")
public class InfoRestController {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public void getAllBookingTime() {
        userInfoRepository.findAll().forEach(userInfo -> System.out.println(userInfo.toString()));
    }

}
