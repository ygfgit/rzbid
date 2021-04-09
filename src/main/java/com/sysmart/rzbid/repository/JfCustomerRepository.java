package com.sysmart.rzbid.repository;

import com.sysmart.rzbid.domain.JfCustomer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JfCustomer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JfCustomerRepository extends JpaRepository<JfCustomer, Long> {}
