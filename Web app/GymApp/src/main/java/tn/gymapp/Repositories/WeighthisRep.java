package tn.gymapp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.gymapp.Entities.Weighthistory;

@Repository
public interface WeighthisRep extends CrudRepository<Weighthistory, Long> {

}
