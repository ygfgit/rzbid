package com.sysmart.rzbid.repository;

import com.sysmart.rzbid.domain.JfCompany;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JfCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JfCompanyRepository extends JpaRepository<JfCompany, Long> {}
