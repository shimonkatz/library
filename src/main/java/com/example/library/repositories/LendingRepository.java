package com.example.library.repositories;

import com.example.library.entities.Lending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendingRepository extends JpaRepository<Lending,Long> {

}
