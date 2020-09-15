package br.com.udemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.udemy.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
