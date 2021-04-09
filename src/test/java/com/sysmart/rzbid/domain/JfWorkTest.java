package com.sysmart.rzbid.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sysmart.rzbid.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JfWorkTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JfWork.class);
        JfWork jfWork1 = new JfWork();
        jfWork1.setId(1L);
        JfWork jfWork2 = new JfWork();
        jfWork2.setId(jfWork1.getId());
        assertThat(jfWork1).isEqualTo(jfWork2);
        jfWork2.setId(2L);
        assertThat(jfWork1).isNotEqualTo(jfWork2);
        jfWork1.setId(null);
        assertThat(jfWork1).isNotEqualTo(jfWork2);
    }
}
