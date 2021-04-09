package com.sysmart.rzbid.repository;

import com.sysmart.rzbid.domain.JfWorkDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JfWorkDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JfWorkDetailsRepository extends JpaRepository<JfWorkDetails, Long> {}
