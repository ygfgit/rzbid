package com.sysmart.rzbid.repository;

import com.sysmart.rzbid.domain.JfTarget;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JfTarget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JfTargetRepository extends JpaRepository<JfTarget, Long> {}
