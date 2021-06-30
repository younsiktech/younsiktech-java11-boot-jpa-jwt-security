package com.tech.younsik.controller;

import com.tech.younsik.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("controller.AdminController")
@RequestMapping("/admin-api")
public class AdminController {
    
    @Autowired
    private AdminService adminService;

}
