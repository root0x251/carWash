package com.bortn.carWash.controller;

import com.bortn.carWash.entity.Booking;
import com.bortn.carWash.entity.UserInfo;
import com.bortn.carWash.repos.BookingRepository;
import com.bortn.carWash.repos.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller
public class MainController {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public String helloWorld(Model model) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        List<String> currentDay;
        List<String> tomorrow;      // +1 day
        List<String> nextDay;       // +2 day

        currentDay = bookingRepository.findAllTime(format.format(calendar.getTime()));
        for (String s : currentDay) {
            System.out.println("0 " + s);
        }

        calendar.add(Calendar.DATE, 1);
        tomorrow = bookingRepository.findAllTime(format.format(calendar.getTime()));
        for (String s : tomorrow) {
            System.out.println("1 " + s);
        }

        calendar.add(Calendar.DATE, 1);
        nextDay = bookingRepository.findAllTime(format.format(calendar.getTime()));
        for (String s : nextDay) {
            System.out.println("2 " + s);
        }

        return "index";
    }

    @PostMapping
    public String sendInfo(@RequestParam("name") String name,
                           @RequestParam("number") String number,
                           @RequestParam("time") String time) {


        String[] editingDate = time.split("T");
        UserInfo userInfo = new UserInfo(name, number);
        Booking booking =new Booking(editingDate[1], editingDate[0]);
        bookingRepository.save(booking);
        userInfo.setBooking(booking);
        userInfoRepository.save(userInfo);

        return "redirect:/";
    }




}
