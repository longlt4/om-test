package com.om.test.userservice.controller;

import com.om.test.userservice.exception.UserServiceException;
import com.om.test.userservice.model.User;
import com.om.test.userservice.config.UserModelAssembler;
import com.om.test.userservice.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserRepository repository;
    private final UserModelAssembler assembler;

    UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    ResponseEntity<?> create(@RequestBody User newUser) {
        EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/users/{id}")
    public EntityModel<?> one(@PathVariable Long id) {
        User user= repository.findById(id)
                .orElseThrow(()-> new UserServiceException("Could not found user id: " + id));
        return assembler.toModel(user);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = repository.findById(id)
                .map(u -> {
                   u.setUsername(user.getUsername());
                   u.setEmail(user.getEmail());
                   return repository.save(u);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return repository.save(user);
                });

        EntityModel<User> entityModel = assembler.toModel(updatedUser);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/users/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
        ResponseEntity.noContent().build();
    }
}
