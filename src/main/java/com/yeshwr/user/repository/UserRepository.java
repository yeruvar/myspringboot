package com.yeshwr.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.yeshwr.user.model.User;

/**
 * @author yeshwr
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
