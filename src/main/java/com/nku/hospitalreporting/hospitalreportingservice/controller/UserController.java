package com.nku.hospitalreporting.hospitalreportingservice.controller;

import com.nku.hospitalreporting.hospitalreportingservice.model.User;
import com.nku.hospitalreporting.hospitalreportingservice.exception.ResourceNotFoundException;
import com.nku.hospitalreporting.hospitalreportingservice.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author serdar
 */
@RestController
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("user/all")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return user;
    }

    @PostMapping("user/add")
    public ResponseEntity createUser(@Valid @RequestBody User user) throws ResourceNotFoundException {
        if(repository.findByFileId(user.getFileId()).size() > 0) {
            return ResponseEntity.ok("Bu dosya numarası ile daha önce kayıt yapılmıştır!");
        }
        return ResponseEntity.ok(repository.save(user));
    }

    @PutMapping("user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        user.setAddress(userDetails.getAddress());
        user.setBlood(userDetails.getBlood());
        user.setFileId(userDetails.getFileId());
        user.setName(userDetails.getName());
        user.setTcId(userDetails.getTcId());
        final User updatedUser = repository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("user/find/{searchText}")
    public List<User> searchUsers(@PathVariable(value = "searchText") String searchText) {
        return repository.searchUsers(searchText);
    }

    @GetMapping("user/fileid/{fileId}")
    public ResponseEntity getReportWithFileId(@PathVariable(value = "fileId") String fileId) throws ResourceNotFoundException {
        try {
            User user = repository.findByFileId(fileId).get(0);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

}
