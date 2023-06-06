package com.hotelmariot.controller.main;

import com.hotelmariot.model.Stats;
import com.hotelmariot.model.enums.Beds;
import com.hotelmariot.model.enums.Role;
import com.hotelmariot.model.enums.Type;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("beds", Beds.values());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesRoom(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("room", roomsRepo.getReferenceById(id));
    }

    protected void AddAttributesRoomsMy(Model model) {
        AddAttributes(model);
        model.addAttribute("rooms", getUser().getRooms());
    }

    protected void AddAttributesRoomAdd(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
    }

    protected void AddAttributesRoomEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("room", roomsRepo.getReferenceById(id));
    }

    protected void AddAttributesCatalog(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("rooms", roomsRepo.findAllByOrderByFreeDesc());
    }

    protected void AddAttributesCatalogSearch(Model model, Type type, Beds beds) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("bSelect", type);
        model.addAttribute("fSelect", beds);
        model.addAttribute("rooms", roomsRepo.findAllByDescription_TypeAndDescription_BedsOrderByFreeDesc(type, beds));
    }

    protected void AddAttributesStatistics(Model model) {
        AddAttributes(model);
        List<Stats> stats = statsRepo.findAll();
        int income = stats.stream().reduce(0, (i, s) -> i + (s.getDays() * s.getRoom().getPrice()), Integer::sum);
        model.addAttribute("statistics", stats);
        model.addAttribute("income", income);

        stats.sort(Comparator.comparing(Stats::getDays));
        Collections.reverse(stats);
        int[] topPriceNumber = new int[5];
        String[] topPriceName = new String[5];
        for (int i = 0; i < stats.size(); i++) {
            if (i == 5) break;
            topPriceName[i] = stats.get(i).getRoom().getName();
            topPriceNumber[i] = stats.get(i).getDays();
        }
        model.addAttribute("topPriceName", topPriceName);
        model.addAttribute("topPriceNumber", topPriceNumber);

    }
}
