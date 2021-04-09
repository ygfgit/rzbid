package com.sysmart.rzbid.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sysmart.rzbid.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BtJfJtJfmxTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BtJfJtJfmx.class);
        BtJfJtJfmx btJfJtJfmx1 = new BtJfJtJfmx();
        btJfJtJfmx1.setId(1L);
        BtJfJtJfmx btJfJtJfmx2 = new BtJfJtJfmx();
        btJfJtJfmx2.setId(btJfJtJfmx1.getId());
        assertThat(btJfJtJfmx1).isEqualTo(btJfJtJfmx2);
        btJfJtJfmx2.setId(2L);
        assertThat(btJfJtJfmx1).isNotEqualTo(btJfJtJfmx2);
        btJfJtJfmx1.setId(null);
        assertThat(btJfJtJfmx1).isNotEqualTo(btJfJtJfmx2);
    }
}
