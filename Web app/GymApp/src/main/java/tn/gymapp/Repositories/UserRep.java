package tn.gymapp.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.User;

@Repository
public interface UserRep extends CrudRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
