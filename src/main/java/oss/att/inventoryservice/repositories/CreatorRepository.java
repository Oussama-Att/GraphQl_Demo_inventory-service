package oss.att.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.att.inventoryservice.entities.Creator;

import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator,String> {
    Optional<Creator> findByEmail(String email);
}
