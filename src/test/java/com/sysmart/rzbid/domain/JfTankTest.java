package com.sysmart.rzbid.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sysmart.rzbid.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JfTankTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JfTank.class);
        JfTank jfTank1 = new JfTank();
        jfTank1.setId(1L);
        JfTank jfTank2 = new JfTank();
        jfTank2.setId(jfTank1.getId());
        assertThat(jfTank1).isEqualTo(jfTank2);
        jfTank2.setId(2L);
        assertThat(jfTank1).isNotEqualTo(jfTank2);
        jfTank1.setId(null);
        assertThat(jfTank1).isNotEqualTo(jfTank2);
    }
}
