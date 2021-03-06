package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.*;
import com.ingesup.java.qcm.form.AddUserForm;
import com.ingesup.java.qcm.service.GradeService;
import com.ingesup.java.qcm.service.UserService;
import com.ingesup.java.qcm.util.ApplicationUrls;
import com.ingesup.java.qcm.util.ControllerUtil;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private static final String ALL_USERS_URL = ApplicationUrls.User.USERS_NAMESPACE.toString();

    private static final String ALL_USERS_VIEW = "user/list";
    private static final String ADD_USER_VIEW = "user/add";

    private static final String USER_TYPE_STUDENT = "student";
    private static final String USER_TYPE_TEACHER = "teacher";

    private final UserService userService;
    private final GradeService gradeService;
    private final MessageSource messageSource;

    public UserController(UserService userService, GradeService gradeService, MessageSource messageSource) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.messageSource = messageSource;
    }

    private List<Grade> getGrades() {
        return gradeService.getAll();
    }

    private Map<String, String> getUserTypes() {
        Map<String, String> userTypes = new HashMap<>();
        userTypes.put(USER_TYPE_STUDENT,
                messageSource.getMessage("user.type.student", null, LocaleContextHolder.getLocale()));
        userTypes.put(USER_TYPE_TEACHER,
                messageSource.getMessage("user.type.teacher", null, LocaleContextHolder.getLocale()));

        return userTypes;
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping
    public String allUsers() {
        return ALL_USERS_VIEW;
    }

    @ResponseBody
    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/json")
    public List<User> jsonAllUsers(
            @RequestParam
                    boolean showAdminUsers) {
        return showAdminUsers ? userService.getAll() : userService.getAllNonAdminUsers();
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/add")
    public String addUserView(Model model) {
        model.addAttribute("addUserForm", new AddUserForm());
        model.addAttribute("userTypes", getUserTypes());
        model.addAttribute("grades", getGrades());

        return ADD_USER_VIEW;
    }

    @Secured(value = "ROLE_ADMIN")
    @PostMapping("/add")
    public String addUser(
            @Valid
                    AddUserForm addUserForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return ADD_USER_VIEW;
        }

        String userType = addUserForm.getUserType();
        if (USER_TYPE_STUDENT.equals(userType)) {
            if (addUserForm.getGrade() == null) {
                bindingResult.reject("grade cannot be null");
            }
        }

        addUserFromForm(addUserForm);

        redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
                messageSource.getMessage("user.add.success", null, LocaleContextHolder.getLocale())));

        return ControllerUtil.redirect(ALL_USERS_URL);
    }

    private void addUserFromForm(AddUserForm addUserForm) {
        if (USER_TYPE_STUDENT.equals(addUserForm.getUserType())) {
            Student student = addUserForm.getStudent();
            student.addRole(RoleEnum.ROLE_STUDENT);
            userService.add(student);
        } else {
            Teacher teacher = addUserForm.getTeacher();
            teacher.addRole(RoleEnum.ROLE_TEACHER);
            userService.add(teacher);
        }
    }
}
