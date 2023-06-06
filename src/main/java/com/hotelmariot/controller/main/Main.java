package com.hotelmariot.controller.main;

import com.hotelmariot.model.Users;
import com.hotelmariot.repo.RoomsDescriptionRepo;
import com.hotelmariot.repo.RoomsRepo;
import com.hotelmariot.repo.StatsRepo;
import com.hotelmariot.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Main {

    @Autowired
    protected UsersRepo usersRepo;
    @Autowired
    protected RoomsRepo roomsRepo;
    @Autowired
    protected RoomsDescriptionRepo roomsDescriptionRepo;
    @Autowired
    protected StatsRepo statsRepo;
    @Value("${upload.img}")
    protected String uploadImg;

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }
}