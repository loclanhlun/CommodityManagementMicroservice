package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    List<Agency> findByCode(String code);

    List<Agency> findAgenciesByName(String name);

    List<Agency> findAgenciesByStatus(int status);
}
