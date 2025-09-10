package com.users.users.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.users.Utils.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones sobre usuarios")
public class UserController {

    private final List<UserDto> users = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    @Operation(summary = "Obtener usuarios")
    public List<UserDto> getUsers() {
        return users;
    }

    @PostMapping
    @Operation(summary = "Añadir usuario")
    public ResponseEntity<?> addUser(@RequestBody UserDto newUser) {
        boolean existEmail = users.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(newUser.getEmail()));
        if (newUser.getEmail() != null && newUser.getName() != null) {
            if (existEmail) {
                return ResponseEntity.status(409)
                        .body("El correo " + newUser.getEmail() + " ya se encuentra registrado.");
            } else {
                newUser.setId(idCounter.getAndIncrement());
                users.add(newUser);
                return ResponseEntity.status(201).body("Usuario registrado correctamente");
            }
        }else{
             return ResponseEntity.status(400).body("El nombre y correo no pueden estar vacíos.");
        }

    }

    @PutMapping("{id}")
    @Operation(summary = "Actualizar usuario")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto newUser) {
        for (UserDto user : users) {
            if (user.getId().equals(id)) {
                user.setName(newUser.getName() != null ? newUser.getName() : user.getName());
                user.setEmail(newUser.getEmail() != null ? newUser.getEmail() : user.getEmail());
                return ResponseEntity.status(200).body("Usuario actualizado correctamente.");
            } else {
                return ResponseEntity.status(404).body("Usuario con id " + id + " no encontrado.");
            }
        }
        return null;
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar usuario")
    public  ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean delete = users.removeIf(user -> user.getId().equals(id));
        if(delete){
            return ResponseEntity.status(200).body("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.status(404).body("Usuario con id " + id + " no encontrado.");
        }
    }
}
