package oss.att.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.att.inventoryservice.entities.Creator;

public interface CreatorRepository extends JpaRepository<Creator,String> {
}
