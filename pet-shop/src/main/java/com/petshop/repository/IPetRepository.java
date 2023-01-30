package com.petshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.model.Pet;

public interface IPetRepository extends JpaRepository<Pet, Long> {

}
