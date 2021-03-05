package com.example.dimed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.dimed.model.Itinerario;

public interface ItinerariosRepository extends DimedCrudRepository<Itinerario, Long> {


  @Query(value = "SELECT i FROM Itinerario i")
  List<Itinerario> list();

}
