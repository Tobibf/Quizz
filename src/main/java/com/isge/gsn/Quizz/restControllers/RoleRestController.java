package com.isge.gsn.Quizz.restControllers;

import com.isge.gsn.Quizz.dto.RoleDTO;
import com.isge.gsn.Quizz.models.Role;
import com.isge.gsn.Quizz.services.RolesService;
import com.isge.gsn.Quizz.utils.DataMapping;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping
    ResponseEntity<List<RoleDTO>> findAll() {

        List<Role> roles = rolesService.roleList();

        List<RoleDTO> results = new ArrayList<>();
        for (Role role : roles) {
            results.add(DataMapping.toRoleDTO(role));
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDTO) {

        Role role = rolesService.saveRole(DataMapping.toRole(roleDTO));

        return new ResponseEntity<>(DataMapping.toRoleDTO(role), HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}")
    ResponseEntity<RoleDTO> read(@PathVariable long id) {

        RoleDTO roleDTO = DataMapping.toRoleDTO(rolesService.readRole(id));

        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<String> update(@RequestBody RoleDTO roleDTO) {

        String message = rolesService.updateRole(DataMapping.toRole(roleDTO));

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable long id) {

        String message = rolesService.deleteRole(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
