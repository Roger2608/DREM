package com.drem.fileprocessing.repository;

import com.drem.fileprocessing.entities.Gastos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoRepository extends CrudRepository<Gastos,Integer> {

}
