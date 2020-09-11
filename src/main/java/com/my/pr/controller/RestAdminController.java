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

//    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('ADMIN,USER')")
//    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
//    public String addUser(@ModelAttribute("user") User user, @RequestParam("role") String[] role) {
//        Set<Role> roles = new HashSet<>();
//
//        for(String str: role){
//            roles.add(roleService.getRoleByName(str));
//        }
//        user.setRoles(roles);
//        userService.add(user);
//        return "redirect:/admin";
//    }
//
//    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('ADMIN,USER')")
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("role") String[] role) {
//        Set<Role> roles = new HashSet<>();
//        for(String str: role){
//            roles.add(roleService.getRoleByName(str));
//        }
//        user.setRoles(roles);
//        final boolean updated = userService.edit(user);
//        return updated
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }

    //OK
    @PreAuthorize(value = "hasAuthority('ADMIN') or hasAuthority('ADMIN,USER')")
    @GetMapping("/remote/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        System.out.println("OPS");
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
