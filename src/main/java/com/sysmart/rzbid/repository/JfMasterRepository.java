package com.sysmart.rzbid.repository;

import com.sysmart.rzbid.domain.JfMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JfMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JfMasterRepository extends JpaRepository<JfMaster, Long> {}
