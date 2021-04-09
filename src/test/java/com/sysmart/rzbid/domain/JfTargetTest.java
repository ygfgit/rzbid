package com.sysmart.rzbid.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sysmart.rzbid.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JfTargetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JfTarget.class);
        JfTarget jfTarget1 = new JfTarget();
        jfTarget1.setId(1L);
        JfTarget jfTarget2 = new JfTarget();
        jfTarget2.setId(jfTarget1.getId());
        assertThat(jfTarget1).isEqualTo(jfTarget2);
        jfTarget2.setId(2L);
        assertThat(jfTarget1).isNotEqualTo(jfTarget2);
        jfTarget1.setId(null);
        assertThat(jfTarget1).isNotEqualTo(jfTarget2);
    }
}
