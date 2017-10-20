package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.goit.services.ProjectService;
import ua.goit.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ua.goit.controllers.Validation.validateProject;
import static ua.goit.controllers.Validation.validateUser;

/**
 * Controller for uploading images
 */
@Controller
@RequestMapping(value = "/storage")
public class StorageController {
    private final UserService userService;
    private final ProjectService projectService;
    private final static Logger LOGGER = LoggerFactory.getLogger(StorageController.class);

    @Autowired
    public StorageController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    /**
     * Mapping for url ":/storage/{id}/saveProfilePhoto"
     * Method upload profile photo to realPathtoUploads + "/WEB-INF/jpg/" folder with "profilePhoto"+id+".jpg" name.
     * It's doesn't save name of profile photo to database. Profile photo name saves to database
     * in {@code update} method of {@link UserController}
     * @param file
     * @param redirectAttributes for out information on page
     * @param id the id of user from url
     * @return redirect link to personal account edit page
     * @throws Exception if file didn't uploaded
     */
    @PostMapping("/{id}/saveProfilePhoto")
    public String saveProfilePhoto(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @PathVariable("id") Long id) throws Exception {
        validateUser(id, userService);
        String realPathtoUploads = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");
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
            throw new Exception("File " + file.getOriginalFilename() + " didn't uploaded", e);
        }

        return "redirect:/user/personalAccount/{id}/edit";
    }

    /**
     * Mapping for url ":/storage/{id}/savePersonalPagePhoto"
     * Method upload profile photo to realPathtoUploads + "/WEB-INF/jpg/" folder with "personalPagePhoto"+id+".jpg" name.
     * It's doesn't save name of personal page photo to database. Personal page photo name saves to database
     * in {@code update} method of {@link UserController}
     * @param file
     * @param redirectAttributes for out information on page
     * @param id the id of user from url
     * @return redirect link to personal account edit page
     * @throws Exception if file didn't uploaded
     */
    @PostMapping("/{id}/savePersonalPagePhoto")
    public String savePersonalPagePhoto(@RequestParam("file") MultipartFile file,
                                        RedirectAttributes redirectAttributes, @PathVariable("id") Long id) throws Exception {
        validateUser(id, userService);
        String realPathtoUploads = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");
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
            throw new Exception("File " + file.getOriginalFilename() + " didn't uploaded", e);
        }
        return "redirect:/user/personalAccount/{id}/edit";
    }

    /**
     * Mapping for url ":/storage/{id}/saveProjectPhoto"
     * Method upload profile photo to realPathtoUploads + "/WEB-INF/jpg/" folder with "projectPhoto"+id+".jpg" name.
     * It's doesn't save name of project photo to database. Project photo name saves to database
     * in {@code update} method of {@link ProjectController}
     * @param file
     * @param redirectAttributes for out information on page
     * @param id the id of project from url
     * @return redirect link to startup edit page
     * @throws Exception if file didn't uploaded
     */
    @PostMapping("/{id}/saveProjectPhoto")
    public String saveProjectPhoto(@RequestParam("file") MultipartFile file,
                                        RedirectAttributes redirectAttributes, @PathVariable("id") Long id) throws Exception {
        validateProject(id, projectService);
        String realPathtoUploads = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");

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
            throw new Exception("File " + file.getOriginalFilename() + " didn't uploaded", e);
        }
        return "redirect:/startup/{id}/edit";
    }

    /**
     * Exception handler
     * @param ex exception for handling
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} for error page
     * and exception message
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        return new ModelAndView("/error", "exception", ex.getMessage());
    }
}
