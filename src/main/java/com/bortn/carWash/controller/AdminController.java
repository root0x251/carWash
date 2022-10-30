package com.bortn.carWash.controller;

import com.bortn.carWash.dto.UserFullInfoDto;
import com.bortn.carWash.entity.Booking;
import com.bortn.carWash.repos.BookingRepository;
import com.bortn.carWash.repos.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController extends UserController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping()
    public String getAllOrders(Model model) {
        // days (example SUN 26.10)
        List<String> listWithDate = generateDays("dd.MM");

        // current date
        model.addAttribute("today", mappingUserToDTO(listWithDate.get(0)));
        // tomorrow
        model.addAttribute("tomorrow", mappingUserToDTO(listWithDate.get(1)));
        // day after tomorrow
        model.addAttribute("dayAfterTomorrow", mappingUserToDTO(listWithDate.get(2)));
        return "admin";
    }

    @GetMapping("{id}")
    public String workIsDone(@PathVariable(value = "id") String bookingID) {
        Optional<Booking> booking = bookingRepository.findById(Long.valueOf(bookingID));
        Booking booking1 = booking.get();
        booking1.setDone(true);
        bookingRepository.save(booking1);

        return "redirect:/admin";
    }

    private List<UserFullInfoDto> mappingUserToDTO(String whatIsDay) {
        List<UserFullInfoDto> userFullInfoDtoList = new ArrayList<>();
        for (String s : userInfoRepository.findJobForWasher(whatIsDay)) {
            String[] user = s.split(",");
            userFullInfoDtoList.add(new UserFullInfoDto(user[0], user[1], user[2], user[3], user[4], user[5], user[6], user[7]));
        }
        return userFullInfoDtoList;
    }

}
