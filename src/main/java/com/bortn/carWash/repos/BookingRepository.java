package com.bortn.carWash.repos;

import com.bortn.carWash.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT b_time FROM public.booking WHERE b_date = ?1", nativeQuery = true)
    List<String> findNotBookingDate(String date);

}
