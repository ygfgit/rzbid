package com.sysmart.rzbid.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sysmart.rzbid.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JfMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JfMaster.class);
        JfMaster jfMaster1 = new JfMaster();
        jfMaster1.setId(1L);
        JfMaster jfMaster2 = new JfMaster();
        jfMaster2.setId(jfMaster1.getId());
        assertThat(jfMaster1).isEqualTo(jfMaster2);
        jfMaster2.setId(2L);
        assertThat(jfMaster1).isNotEqualTo(jfMaster2);
        jfMaster1.setId(null);
        assertThat(jfMaster1).isNotEqualTo(jfMaster2);
    }
}
