package com.bortn.carWash.controller;

import com.bortn.carWash.repos.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deleteAll")
public class MegaAdminRestController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping()
    public String dellAll() {
        int size = userInfoRepository.findAll().size();
        userInfoRepository.deleteAll();
        return "Done, " + size + " records were deleted.";
    }

}
