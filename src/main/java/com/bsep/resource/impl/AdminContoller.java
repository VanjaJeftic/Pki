package com.bsep.resource.impl;

import com.bsep.domain.Admin;
import com.bsep.domain.Certificate;
import com.bsep.repository.AdminRepository;
import com.bsep.repository.CertificateRepository;
import com.bsep.resource.Resource;
import com.bsep.service.IService;
import com.bsep.service.impl.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


@RestController
@RequestMapping("/login")
@CrossOrigin(origins="http://localhost:3000")
public class AdminContoller {

    private  AdminRepository adminRepository;

    public AdminContoller(AdminRepository groupRepository) {
        this.adminRepository = groupRepository;
    }

    @PostMapping("/loginData")
    ResponseEntity<Admin> createGroup(@Valid @RequestBody Admin group) throws URISyntaxException {
      System.out.println("caoooooooooo");
        Admin result = adminRepository.save(group);
        return ResponseEntity.created(new URI("/api/group/" + result.getId()))
                .body(result);
    }


}