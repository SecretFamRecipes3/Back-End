package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipe.models.Recipe;
import com.lambdaschool.secretfamilyrecipe.models.Role;
import com.lambdaschool.secretfamilyrecipe.models.User;
import com.lambdaschool.secretfamilyrecipe.models.UserRoles;

import com.lambdaschool.secretfamilyrecipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleService roleService;

    @Autowired
    private HelperFunctions helperFunctions;

    public User findUserById(long id) throws ResourceNotFoundException {
        return userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found"));
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findByName(String name) {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null) {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public void delete(long id) {
        userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();
        if (user.getUserid() != 0) {
            userrepos.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername()
                .toLowerCase());
        newUser.setEmail(user.getEmail()
                .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());

//        newUser.getRecipes()
//                .clear();
//        for (Recipe r : user.getRecipes()) {
//            newUser.getRecipes().add(new Recipe(r.getTitle(), r.getSource(), r.getPreptime(), r.getInstruction(), newUser));
//        }

        newUser.getRoles()
                .clear();
        for (UserRoles ur : user.getRoles()) {
            Role addRole = roleService.findRoleById(ur.getRole()
                    .getRoleid());
            newUser.getRoles()
                    .add(new UserRoles(newUser, addRole));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user, long id) {
        User currentUser = findUserById(id);

        if (helperFunctions.isAuthorizedToMakeChanges(currentUser.getUsername())) {
            if (user.getUsername() != null) {
                currentUser.setUsername(user.getUsername().toLowerCase());
            }
            if (user.getEmail() != null) {
                currentUser.setEmail(user.getEmail().toLowerCase());
            }
            if (user.getPassword() != null) {
                currentUser.setPasswordNoEncrypt(user.getPassword());
            }
            if (user.getRoles().size() > 0) {
                currentUser.getRoles().clear();
                for (UserRoles ur : user.getRoles()) {
                    Role addRole = roleService.findRoleById(ur.getRole().getRoleid());
                    currentUser.getRoles().add(new UserRoles(currentUser, addRole));
                }
            }

            if (user.getRecipes().size() > 0) {
                currentUser.getRecipes().clear();
                for (Recipe r : user.getRecipes()) {
                    currentUser.getRecipes().add(new Recipe(r.getTitle(), r.getSource(), r.getPreptime(), r.getInstruction(), currentUser));
                }
            }

            return userrepos.save(currentUser);
        } else {
            throw
                    new ResourceNotFoundException("This user is not authorized to make changes");
        }
    }

    @Transactional
    @Override
    public void deleteAll() {
        userrepos.deleteAll();
    }
}
