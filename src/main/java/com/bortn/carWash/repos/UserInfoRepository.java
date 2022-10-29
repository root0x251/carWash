package com.bortn.carWash.repos;

import com.bortn.carWash.entity.UserInfo;
import com.bortn.carWash.dto.UserFullInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {


    @Query(value = "SELECT name, car_number, public.booking.b_date, public.booking.b_time\n" +
            "    FROM public.user_info\n" +
            "    JOIN public.booking\n" +
            "    ON booking_id = public.booking.id\n" +
            "    WHERE public.booking.b_date = ?1\n" +
            "    ORDER BY public.booking.b_time ASC", nativeQuery = true)
    List<String> findUsersByDate(String date);


}
