package com.bortn.carWash.controller;

import com.bortn.carWash.dto.UserFullInfoDto;
import com.bortn.carWash.repos.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends UserController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping()
    public String getAllOrders(Model model) {
        // current date
        model.addAttribute("today", mappingUserToDTO(0));
        // tomorrow
        model.addAttribute("tomorrow", mappingUserToDTO(1));
        // day after tomorrow
        model.addAttribute("dayAfterTomorrow", mappingUserToDTO(2));
        return "admin";
    }

    private List<UserFullInfoDto> mappingUserToDTO(int whatIsDay) {
        List<UserFullInfoDto> userFullInfoDtoList = new ArrayList<>();
        for (String s : userInfoRepository.findUsersByDate(generateDays("dd.MM", whatIsDay))) {
            String[] user = s.split(",");
            userFullInfoDtoList.add(new UserFullInfoDto(user[0], user[1], user[2], user[3]));
        }
        return userFullInfoDtoList;
    }

}
