package com.hotelmariot.repo;

import com.hotelmariot.model.Rooms;
import com.hotelmariot.model.enums.Beds;
import com.hotelmariot.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepo extends JpaRepository<Rooms, Long> {
    List<Rooms> findAllByDescription_TypeAndDescription_BedsOrderByFreeDesc(Type type, Beds beds);

    List<Rooms> findAllByOrderByFreeDesc();
}
