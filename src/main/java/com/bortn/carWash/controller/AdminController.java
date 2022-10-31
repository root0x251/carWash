package com.bortn.carWash.controller;

import com.bortn.carWash.entity.UserInfo;
import com.bortn.carWash.repos.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController extends UserController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping()
    public String getAllOrders(Model model) {
        // days (example SUN 26.10)
        List<String> listWithDate = generateTime("dd.MM", 20);

        // current date
        model.addAttribute("today", userInfoRepository.findJobForWasher(listWithDate.get(0)));
        // tomorrow
        model.addAttribute("tomorrow", userInfoRepository.findJobForWasher(listWithDate.get(1)));
        // day after tomorrow
        model.addAttribute("dayAfterTomorrow", userInfoRepository.findJobForWasher(listWithDate.get(2)));
        return "admin";
    }

    @GetMapping("{id}")
    public String workIsDone(@PathVariable(value = "id") String bookingID) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(Long.valueOf(bookingID));
        UserInfo userInfo1 = userInfo.get();
        userInfo1.setDone(true);
        userInfoRepository.save(userInfo1);

        return "redirect:/admin";
    }


}
