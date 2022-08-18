package com.example.obspringejercicios456.repository;

import com.example.obspringejercicios456.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILaptopRepository extends JpaRepository<Laptop, Long> {
}
