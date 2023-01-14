package com.br.fsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.br.fsc.models.Book;

@Component
public interface BookRepository extends JpaRepository<Book, Long>{

}
