package com.example.dimed.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Interface base para funções de CRUD e operações genéricas
 *
 * @param <T>  Entidade que representa a tabela no banco de dados
 * @param <ID> Chave da tabela
 * 
 * @author rodrigo
 * @version 1.0.0
 * @since 1.0.0
 */
@NoRepositoryBean
public interface FrameworkCrudRepository<T, ID extends Serializable> extends CrudRepository<T, ID>, FrameworkRepository {

  default T findOne(ID id) {
    return findById(id).orElse(null);
  }

  default <S extends T> List<S> save(Iterable<S> entities) {
    return (List<S>) saveAll(entities);
  }

  default void delete(ID id) {
    deleteById(id);
  }
}
