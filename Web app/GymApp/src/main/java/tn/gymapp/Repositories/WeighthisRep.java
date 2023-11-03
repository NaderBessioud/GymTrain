package tn.gymapp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.User;
import tn.gymapp.Entities.Weighthistory;

@Repository
public interface WeighthisRep extends CrudRepository<Weighthistory, Long> {
	
	
	List<Weighthistory> findByUserhwOrderByDateDesc(User user);

}
