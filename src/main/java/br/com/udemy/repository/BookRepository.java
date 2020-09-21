package br.com.udemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.udemy.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
