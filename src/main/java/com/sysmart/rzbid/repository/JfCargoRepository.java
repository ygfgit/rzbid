package com.sysmart.rzbid.repository;

import com.sysmart.rzbid.domain.JfCargo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JfCargo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JfCargoRepository extends JpaRepository<JfCargo, Long> {}
