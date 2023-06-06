package com.hotelmariot.controller;

import com.hotelmariot.controller.main.Attributes;
import com.hotelmariot.model.Rooms;
import com.hotelmariot.model.RoomsDescription;
import com.hotelmariot.model.Stats;
import com.hotelmariot.model.Users;
import com.hotelmariot.model.enums.Beds;
import com.hotelmariot.model.enums.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/rooms")
public class RoomsCont extends Attributes {

    @GetMapping("/{id}")
    public String Room(Model model, @PathVariable Long id) {
        AddAttributesRoom(model, id);
        return "room";
    }

    @GetMapping("/add")
    public String RoomAdd(Model model) {
        AddAttributesRoomAdd(model);
        return "room_add";
    }

    @GetMapping("/edit/{id}")
    public String RoomEdit(Model model, @PathVariable Long id) {
        AddAttributesRoomEdit(model, id);
        return "room_edit";
    }

    @GetMapping("/delete/{id}")
    public String RoomDelete(@PathVariable Long id) {
        roomsRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/my")
    public String RoomMy(Model model) {
        AddAttributesRoomsMy(model);
        return "my_rooms";
    }

    @GetMapping("/return/{id}")
    public String RoomReturn(@PathVariable Long id) {
        Users user = getUser();
        Rooms room = roomsRepo.getReferenceById(id);
        room.setFree(true);
        user.removeRoom(room);
        usersRepo.save(user);
        return "redirect:/rooms/my";
    }

    @PostMapping("/rent/{id}")
    public String RoomRent(@PathVariable Long id, @RequestParam int days) {
        Rooms room = roomsRepo.getReferenceById(id);

        Users user = getUser();
        user.addRoom(room);
        usersRepo.save(user);

        room.setFree(false);
        room.getStats().setDays(room.getStats().getDays() + days);

        roomsRepo.save(room);

        return "redirect:/rooms/{id}";
    }

    @PostMapping("/add")
    public String RoomAddNew(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam int price, @RequestParam int number, @RequestParam Type type, @RequestParam String description, @RequestParam Beds beds, @RequestParam int floor) {
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String[] result_photos;
                String result_screenshot;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_screenshot = uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_screenshot));
                    result_photos[i] = result_screenshot;
                }
                Rooms room = new Rooms(name, price, true, result_photos);
                room.setStats(new Stats(room));
                room.setDescription(new RoomsDescription(type, beds, number, description, floor));
                roomsRepo.save(room);
            } catch (Exception e) {
                AddAttributesRoomAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "room_add";
            }
        } else {
            AddAttributesRoomAdd(model);
            model.addAttribute("message", "Ошибка, некорректный данные!");
            return "room_add";
        }
        return "redirect:/rooms/add";
    }

    @PostMapping("/edit/{id}")
    public String RoomEditOld(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam int price, @RequestParam int number, @RequestParam Type type, @RequestParam String description, @RequestParam Beds beds, @RequestParam int floor, @PathVariable Long id) {
        Rooms room = roomsRepo.getReferenceById(id);
        String[] result_photos;
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String result_photo;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_photo = uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_photo));
                    result_photos[i] = result_photo;
                }
                room.setPhotos(result_photos);
            } catch (Exception e) {
                AddAttributesRoomAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "room_edit";
            }
        }

        room.setName(name);
        room.setPrice(price);
        roomsRepo.save(room);

        RoomsDescription roomsDescription = room.getDescription();
        roomsDescription.setDescription(description);
        roomsDescription.setBeds(beds);
        roomsDescription.setType(type);
        roomsDescription.setNumber(number);
        roomsDescription.setFloor(floor);
        roomsDescriptionRepo.save(roomsDescription);

        return "redirect:/";
    }
}
