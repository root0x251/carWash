package com.bortn.carWash.repos;

import com.bortn.carWash.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    
    @Query(value = "SELECT u_name, u_phone, u_mail, car_number, \n" +
            "public.booking.b_date, public.booking.b_time, public.booking.is_done, public.booking.id\n" +
            "FROM public.user_info\n" +
            "JOIN public.booking\n" +
            "ON booking_id = public.booking.id\n" +
            "WHERE public.booking.b_date = ?1\n" +
            "AND public.booking.is_done = 'false'\n" +
            "ORDER BY public.booking.b_time ASC\n", nativeQuery = true)
    List<String> findJobForWasher(String date);


}
