package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.goit.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by User on 11.10.2017.
 */
@Controller
@RequestMapping(value = "/storage")
public class StorageController {
    private final UserService userService;

    private final static Logger LOGGER = LoggerFactory.getLogger(StorageController.class);

    @Autowired
    public StorageController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/{id}/saveProfilePhoto")
    public String saveProfilePhoto(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
        String realPathtoUploads = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");
        System.out.println(realPathtoUploads);
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/user/personalAccount/{id}/edit";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(realPathtoUploads + "/WEB-INF/jpg/" + "profilePhoto"+id+".jpg");
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/user/personalAccount/{id}/edit";
    }

    @PostMapping("/{id}/savePersonalPagePhoto")
    public String savePersonalPagePhoto(@RequestParam("file") MultipartFile file,
                                        RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
        String realPathtoUploads = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");
        System.out.println(realPathtoUploads);
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/user/personalAccount/{id}/edit";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(realPathtoUploads + "/WEB-INF/jpg/" + "personalPagePhoto" + id + ".jpg");
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/personalAccount/{id}/edit";
    }

    @PostMapping("/{id}/saveProjectPhoto")
    public String saveProjectPhoto(@RequestParam("file") MultipartFile file,
                                        RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
        String realPathtoUploads = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");
        System.out.println(realPathtoUploads);
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/startup/{id}/edit";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(realPathtoUploads + "/WEB-INF/jpg/" + "projectPhoto" + id + ".jpg");
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/startup/{id}/edit";
    }


}
