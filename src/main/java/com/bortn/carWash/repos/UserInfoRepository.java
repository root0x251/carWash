package com.bortn.carWash.repos;

import com.bortn.carWash.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query(value = "SELECT id, car_number, b_date, is_done, u_mail, u_name, u_phone, b_time\n" +
            "FROM public.full_user\n" +
            "WHERE b_date = ?1\n" +
            "AND is_done = 'false'\n" +
            "ORDER BY b_time ASC", nativeQuery = true)
    List<UserInfo> findJobForWasher(String date);

    @Query(value = "SELECT b_time FROM public.full_user WHERE b_date = ?1", nativeQuery = true)
    List<String> findNotBookingDate(String date);

    @Query(value = "SELECT EXISTS(SELECT * from public.full_user " +
            "WHERE b_date = ?1 AND b_time = ?2)", nativeQuery = true)
    boolean isExist(String date, String time);

}
