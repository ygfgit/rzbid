package com.sysmart.rzbid.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sysmart.rzbid.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JfCompanyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JfCompany.class);
        JfCompany jfCompany1 = new JfCompany();
        jfCompany1.setId(1L);
        JfCompany jfCompany2 = new JfCompany();
        jfCompany2.setId(jfCompany1.getId());
        assertThat(jfCompany1).isEqualTo(jfCompany2);
        jfCompany2.setId(2L);
        assertThat(jfCompany1).isNotEqualTo(jfCompany2);
        jfCompany1.setId(null);
        assertThat(jfCompany1).isNotEqualTo(jfCompany2);
    }
}
