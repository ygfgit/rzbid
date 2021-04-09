package com.sysmart.rzbid.repository;

import com.sysmart.rzbid.domain.JfWork;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JfWork entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JfWorkRepository extends JpaRepository<JfWork, Long> {}
