package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    List<Agency> findByCode(String code);

    List<Agency> findAgenciesByName(String name);

    List<Agency> findAgenciesByStatus(int status);

    @Query(value = "select * from Agency a where a.name like %:name% and status = :status", nativeQuery = true)
    List<Agency> findAgenciesByLikeNameAndStatus(@Param("name") String name,@Param("status") int status);

    @Query(value = "select * from Agency a where a.name like %:name%", nativeQuery = true)
    List<Agency> findAgenciesByLikeName(@Param("name") String name);
}
