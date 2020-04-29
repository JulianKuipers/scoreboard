package com.juliankuipers.repositories;

import com.juliankuipers.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    Iterable<User> findAll();
}
