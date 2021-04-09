package com.sysmart.rzbid.repository;

import com.sysmart.rzbid.domain.JfTank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JfTank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JfTankRepository extends JpaRepository<JfTank, Long> {}
