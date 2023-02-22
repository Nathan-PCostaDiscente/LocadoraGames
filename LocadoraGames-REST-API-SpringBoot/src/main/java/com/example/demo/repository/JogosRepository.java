package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Jogos;

@Repository
public interface JogosRepository extends JpaRepository<Jogos, Long> {

}
