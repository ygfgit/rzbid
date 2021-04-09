package com.sysmart.rzbid.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sysmart.rzbid.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JfCargoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JfCargo.class);
        JfCargo jfCargo1 = new JfCargo();
        jfCargo1.setId(1L);
        JfCargo jfCargo2 = new JfCargo();
        jfCargo2.setId(jfCargo1.getId());
        assertThat(jfCargo1).isEqualTo(jfCargo2);
        jfCargo2.setId(2L);
        assertThat(jfCargo1).isNotEqualTo(jfCargo2);
        jfCargo1.setId(null);
        assertThat(jfCargo1).isNotEqualTo(jfCargo2);
    }
}
