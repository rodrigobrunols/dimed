package com.example.dimed.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dimed.model.Linha;

public interface LinhasRepository extends FrameworkCrudRepository<Linha, Long> {


  @Query("select l from Linha l where l.codigo = :codigo ")
  Optional<Linha> findByCodigo(@Param("codigo") String codigo);
  
  @Query(value = "SELECT l FROM Linha l")
  List<Linha> list();

}
