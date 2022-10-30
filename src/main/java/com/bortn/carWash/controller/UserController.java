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
public class UserController {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private BookingRepository bookingRepository;

    private int yesterday;

    @GetMapping("/")
    public String searchAllRegisteredDays(Model model) {

        // search all booking
        List<String> bookedCurrentDay;
        List<String> bookedTomorrow;      // +1 day
        List<String> bookedNextDay;       // +2 day

        // days (example SUN 26.10)
        List<String> listWithBookingDate = generateDays("dd.MM");

        // this is booking days
        bookedCurrentDay = bookingRepository.findNotBookingDate(listWithBookingDate.get(0));
        bookedTomorrow = bookingRepository.findNotBookingDate(listWithBookingDate.get(1));
        bookedNextDay = bookingRepository.findNotBookingDate(listWithBookingDate.get(2));


        // generate time
        List<String> listWithTimeToday = generateTime();
        List<String> listWithTimeTomorrow = generateTime();
        List<String> listWithTimeNextDayAfterTomorrow = generateTime();

        listWithTimeToday.removeAll(new HashSet<>(bookedCurrentDay));
        listWithTimeTomorrow.removeAll(new HashSet<>(bookedTomorrow));
        listWithTimeNextDayAfterTomorrow.removeAll(new HashSet<>(bookedNextDay));

        // days (example SUN 26.10)
        List<String> listWithDate = generateDays("EEE dd.MM");

        // today
        model.addAttribute("daysToday", listWithDate.get(0));
        model.addAttribute("timeToday", listWithTimeToday);

        // tomorrow
        model.addAttribute("daysTomorrow", listWithDate.get(1));
        model.addAttribute("timeTomorrow", listWithTimeTomorrow);

        // next day after tomorrow
        model.addAttribute("daysNext", listWithDate.get(2));
        model.addAttribute("timeNext", listWithTimeNextDayAfterTomorrow);


        return "index";
    }

    @PostMapping("/")
    public String sendInfo(@RequestParam("name") String name,
                           @RequestParam("phone") String phone,
                           @RequestParam("mail") String mail,
                           @RequestParam("carNumber") String carNumber,
                           @RequestParam("time") String time) {

        String[] editingDate = time.split(" ")[1].split("T");
        UserInfo userInfo = new UserInfo(name, phone, mail, carNumber);
        Booking booking = new Booking(editingDate[1], editingDate[0], false);
        bookingRepository.save(booking);
        userInfo.setBooking(booking);
        userInfoRepository.save(userInfo);

        return "redirect:/";
    }

    protected List<String> generateDays(String pattern) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat(pattern);
        SimpleDateFormat dayOfWeek = new SimpleDateFormat("E", Locale.ENGLISH); // Пн

        List<String> listWithDate = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            calendar.add(Calendar.DATE, i);
            System.out.println(dayOfWeek.format(calendar.getTime()));
            if (dayOfWeek.format(calendar.getTime()).equals("Sat")) {

            } else if (dayOfWeek.format(calendar.getTime()).equals("Sun")) {

            } else {
                listWithDate.add(date.format(calendar.getTime()));
            }
            calendar.add(Calendar.DATE, -i);
        }

        return listWithDate;
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
