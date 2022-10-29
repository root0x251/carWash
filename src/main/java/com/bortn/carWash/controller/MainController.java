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
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public String searchAllRegisteredDays(Model model) {

        // search all booking
        List<String> bookedCurrentDay;
        List<String> bookedTomorrow;      // +1 day
        List<String> bookedNextDay;       // +2 day

        // this is booking days
        bookedCurrentDay = bookingRepository.findAllTime(generateDays("dd.MM", 0));
        for (String s : bookedCurrentDay) {
            System.out.println(generateDays("dd.MM", 0) + " = " + s);
        }

        bookedTomorrow = bookingRepository.findAllTime(generateDays("dd.MM", 1));
        for (String s : bookedTomorrow) {
            System.out.println(generateDays("dd.MM", 1) + " = " + s);
        }

        bookedNextDay = bookingRepository.findAllTime(generateDays("dd.MM", 2));
        for (String s : bookedNextDay) {
            System.out.println(generateDays("dd.MM", 2) + " = " + s);
        }

//        // generate time
//        List<String> listWithTime = generateTime();
//        // array with listOfDays
//        List<String> listOfDays = new ArrayList<>();
//        listOfDays.add(generateDays("EEE dd.MM", 0));
//        listOfDays.add(generateDays("EEE dd.MM", 1));
//        listOfDays.add(generateDays("EEE dd.MM", 2));
//
//        // insert variable in html
//        model.addAttribute("days", listOfDays);
//        model.addAttribute("time", listWithTime);

        // generate time
        List<String> listWithTimeToday = generateTime();
        List<String> listWithTimeTomorrow = generateTime();
        List<String> listWithTimeNextDayAfterTomorrow = generateTime();

        listWithTimeToday.removeAll(new HashSet<>(bookedCurrentDay));
        listWithTimeTomorrow.removeAll(new HashSet<>(bookedTomorrow));
        listWithTimeNextDayAfterTomorrow.removeAll(new HashSet<>(bookedNextDay));

        // days example SUN 26.10
        String dayToday = generateDays("EEE dd.MM", 0);
        String dayTomorrow = generateDays("EEE dd.MM", 1);
        String dayAfterTomorrow = generateDays("EEE dd.MM", 2);


        // today
        model.addAttribute("daysToday", dayToday);
        model.addAttribute("timeToday", listWithTimeToday);

        // tomorrow
        model.addAttribute("daysTomorrow", dayTomorrow);
        model.addAttribute("timeTomorrow", listWithTimeTomorrow);

        // next day after tomorrow
        model.addAttribute("daysNext", dayAfterTomorrow);
        model.addAttribute("timeNext", listWithTimeNextDayAfterTomorrow);

        return "index";
    }

    @PostMapping
    public String sendInfo(@RequestParam("name") String name,
                           @RequestParam("number") String number,
                           @RequestParam("time") String time) {

        String[] editingDate = time.split(" ")[1].split("T");
        UserInfo userInfo = new UserInfo(name, number);
        Booking booking = new Booking(editingDate[1], editingDate[0]);
        bookingRepository.save(booking);
        userInfo.setBooking(booking);
        userInfoRepository.save(userInfo);

        return "redirect:/";
    }

    private String generateDays(String pattern, int whatIsDay) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(pattern);
        SimpleDateFormat dayOfWeek = new SimpleDateFormat("E", Locale.ENGLISH); // Пн

        if (dayOfWeek.format(calendar.getTime()).equals("Sat")) {
            whatIsDay += 2;
        } else if (dayOfWeek.format(calendar.getTime()).equals("Sun")) {
            whatIsDay += 1;
        }
        calendar.add(Calendar.DATE, whatIsDay);
        return date.format(calendar.getTime());
    }

    private List<String> generateTime() {
        List<String> list = new ArrayList<>();
        list.add("09:00");
        for (int i = 10; i < 17; i++) {
            list.add(i + ":00");
        }
        return list;
    }


}
