package com.sysmart.rzbid.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sysmart.rzbid.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JfCustomerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JfCustomer.class);
        JfCustomer jfCustomer1 = new JfCustomer();
        jfCustomer1.setId(1L);
        JfCustomer jfCustomer2 = new JfCustomer();
        jfCustomer2.setId(jfCustomer1.getId());
        assertThat(jfCustomer1).isEqualTo(jfCustomer2);
        jfCustomer2.setId(2L);
        assertThat(jfCustomer1).isNotEqualTo(jfCustomer2);
        jfCustomer1.setId(null);
        assertThat(jfCustomer1).isNotEqualTo(jfCustomer2);
    }
}
