package com.sysmart.rzbid.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sysmart.rzbid.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JfWorkDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JfWorkDetails.class);
        JfWorkDetails jfWorkDetails1 = new JfWorkDetails();
        jfWorkDetails1.setId(1L);
        JfWorkDetails jfWorkDetails2 = new JfWorkDetails();
        jfWorkDetails2.setId(jfWorkDetails1.getId());
        assertThat(jfWorkDetails1).isEqualTo(jfWorkDetails2);
        jfWorkDetails2.setId(2L);
        assertThat(jfWorkDetails1).isNotEqualTo(jfWorkDetails2);
        jfWorkDetails1.setId(null);
        assertThat(jfWorkDetails1).isNotEqualTo(jfWorkDetails2);
    }
}
