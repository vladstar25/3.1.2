package com.my.pr.controller;

import com.my.pr.model.Role;
import com.my.pr.model.User;
import com.my.pr.service.RoleService;
import com.my.pr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class RestAdminController {
    private final UserService userService;
    private final RoleService roleService;

    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('ADMIN,USER')")
    @PostMapping(value = "/user/add")
    public ResponseEntity<?> addUser(@ModelAttribute("user") User user, @RequestParam("role") String[] role) {
        System.out.println("User Add Button ACTIVATE!!!");
        Set<Role> roles = new HashSet<>();
        for(String str: role){
            roles.add(roleService.getRoleByName(str));
        }
        user.setRoles(roles);
        userService.add(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('ADMIN,USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@ModelAttribute("user") User user, @PathVariable("role") String[] role, @PathVariable("id") int id) {
        Set<Role> roles = new HashSet<>();
        for(String str: role){
            roles.add(roleService.getRoleByName(str));
        }
        user.setRoles(roles);
        userService.edit(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //OK
    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('ADMIN,USER')")
    @DeleteMapping("/remote/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        User user = userService.getById(id);
        this.userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('ADMIN,USER')")
//    @RequestMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        User user = userService.getById(id);
//        model.addAttribute("user", user);
//        return "update-user";
//    }

}
