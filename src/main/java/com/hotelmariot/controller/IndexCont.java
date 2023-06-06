package com.hotelmariot.controller;

import com.hotelmariot.controller.main.Attributes;
import com.hotelmariot.model.enums.Beds;
import com.hotelmariot.model.enums.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam Type type, @RequestParam Beds beds) {
        AddAttributesCatalogSearch(model, type, beds);
        return "catalog";
    }
}
