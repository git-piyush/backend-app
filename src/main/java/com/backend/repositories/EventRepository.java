package com.backend.repositories;

import com.backend.entity.Event;
import com.backend.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDepartment(String department);

    List<Event> findByBookingStatus(BookingStatus bookingStatus);

    List<Event> findByCreatedBy(String title);
    // add other queries as needed
}
