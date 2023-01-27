package com.isge.gsn.Quizz.services;

import com.isge.gsn.Quizz.models.Role;
import com.isge.gsn.Quizz.repositories.RolesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RolesService {
    private final RolesRepository rolesRepository;

    public List<Role> roleList() {
        try {
            return rolesRepository.findAll();
        }catch (Exception exception){
            log.info(exception.toString());
            return null;
        }

    }

    public Role saveRole(Role role) {
        try {
            return rolesRepository.save(role);
        } catch (Exception exception) {
            return null;
        }
    }

    public Role readRole(long id) {
        return rolesRepository.findById(id).orElse(null);
    }

    public String updateRole(Role oldRole) {
        try {
            if (rolesRepository.existsById(oldRole.getId())) {
                /**
                 * Get the oldUser to database and update attribute FullName
                 * */
                Role newRole = rolesRepository.findById(oldRole.getId()).orElse(null);
                newRole.setName(oldRole.getName());

                rolesRepository.save(newRole);
                return "Role " + newRole.getName() + " updated successfully";
            }
            return "Role not exist";
        } catch (Exception e) {
            return "Unsuccessful updating";
        }
    }

    public String deleteRole(long id) {
        try {
            rolesRepository.deleteById(id);
            return "Role deleted successfully";
        } catch (Exception e) {
            return "Unsuccessful deleting";
        }
    }

}
