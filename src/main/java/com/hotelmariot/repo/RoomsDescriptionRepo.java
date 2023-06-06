package com.hotelmariot.repo;

import com.hotelmariot.model.RoomsDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsDescriptionRepo extends JpaRepository<RoomsDescription, Long> {
}
