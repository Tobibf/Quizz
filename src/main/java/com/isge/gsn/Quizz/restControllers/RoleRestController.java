package com.isge.gsn.Quizz.restControllers;

import com.isge.gsn.Quizz.dto.RoleDTO;
import com.isge.gsn.Quizz.models.Role;
import com.isge.gsn.Quizz.services.RolesService;
import com.isge.gsn.Quizz.utils.DataMapping;
import com.isge.gsn.Quizz.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/roles")
@Slf4j
@AllArgsConstructor
/*CRUD and Listing functions*/
public class RoleRestController {

    private final RolesService rolesService;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping
    ResponseEntity<List<RoleDTO>> findAll(@RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            List<Role> roles = rolesService.roleList();

            List<RoleDTO> results = new ArrayList<>();
            for (Role rol : roles) {
                results.add(DataMapping.toRoleDTO(rol));
            }

            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/add")
    ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            Role rol = rolesService.saveRole(DataMapping.toRole(roleDTO));

            return new ResponseEntity<>(DataMapping.toRoleDTO(rol), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/view/{id}")
    ResponseEntity<RoleDTO> read(@PathVariable long id, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            RoleDTO roleDTO = DataMapping.toRoleDTO(rolesService.readRole(id));

            return new ResponseEntity<>(roleDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PutMapping("/update")
    ResponseEntity<String> update(@RequestBody RoleDTO roleDTO, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            String message = rolesService.updateRole(DataMapping.toRole(roleDTO));

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable long id, @RequestHeader(value = "token", defaultValue = "") String token) {
        String role = jwtUtils.verifyJwt(token);

        if (role.equals("Admin")) {

            String message = rolesService.deleteRole(id);

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}
