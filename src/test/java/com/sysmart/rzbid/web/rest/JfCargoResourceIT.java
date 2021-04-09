package com.sysmart.rzbid.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sysmart.rzbid.IntegrationTest;
import com.sysmart.rzbid.domain.JfCargo;
import com.sysmart.rzbid.repository.JfCargoRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link JfCargoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JfCargoResourceIT {

    private static final LocalDate DEFAULT_RQ = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RQ = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ZYGSDM = "AAAAAAAAAA";
    private static final String UPDATED_ZYGSDM = "BBBBBBBBBB";

    private static final String DEFAULT_HTH = "AAAAAAAAAA";
    private static final String UPDATED_HTH = "BBBBBBBBBB";

    private static final Long DEFAULT_ZYWTRID = 1L;
    private static final Long UPDATED_ZYWTRID = 2L;

    private static final String DEFAULT_ZYWTR = "AAAAAAAAAA";
    private static final String UPDATED_ZYWTR = "BBBBBBBBBB";

    private static final String DEFAULT_ZHWCHM = "AAAAAAAAAA";
    private static final String UPDATED_ZHWCHM = "BBBBBBBBBB";

    private static final String DEFAULT_HWMCH = "AAAAAAAAAA";
    private static final String UPDATED_HWMCH = "BBBBBBBBBB";

    private static final String DEFAULT_GQ = "AAAAAAAAAA";
    private static final String UPDATED_GQ = "BBBBBBBBBB";

    private static final Double DEFAULT_SHL = 1D;
    private static final Double UPDATED_SHL = 2D;

    private static final Double DEFAULT_SYL = 1D;
    private static final Double UPDATED_SYL = 2D;

    private static final String ENTITY_API_URL = "/api/jf-cargos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JfCargoRepository jfCargoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJfCargoMockMvc;

    private JfCargo jfCargo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfCargo createEntity(EntityManager em) {
        JfCargo jfCargo = new JfCargo()
            .rq(DEFAULT_RQ)
            .zygsdm(DEFAULT_ZYGSDM)
            .hth(DEFAULT_HTH)
            .zywtrid(DEFAULT_ZYWTRID)
            .zywtr(DEFAULT_ZYWTR)
            .zhwchm(DEFAULT_ZHWCHM)
            .hwmch(DEFAULT_HWMCH)
            .gq(DEFAULT_GQ)
            .shl(DEFAULT_SHL)
            .syl(DEFAULT_SYL);
        return jfCargo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfCargo createUpdatedEntity(EntityManager em) {
        JfCargo jfCargo = new JfCargo()
            .rq(UPDATED_RQ)
            .zygsdm(UPDATED_ZYGSDM)
            .hth(UPDATED_HTH)
            .zywtrid(UPDATED_ZYWTRID)
            .zywtr(UPDATED_ZYWTR)
            .zhwchm(UPDATED_ZHWCHM)
            .hwmch(UPDATED_HWMCH)
            .gq(UPDATED_GQ)
            .shl(UPDATED_SHL)
            .syl(UPDATED_SYL);
        return jfCargo;
    }

    @BeforeEach
    public void initTest() {
        jfCargo = createEntity(em);
    }

    @Test
    @Transactional
    void createJfCargo() throws Exception {
        int databaseSizeBeforeCreate = jfCargoRepository.findAll().size();
        // Create the JfCargo
        restJfCargoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfCargo)))
            .andExpect(status().isCreated());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeCreate + 1);
        JfCargo testJfCargo = jfCargoList.get(jfCargoList.size() - 1);
        assertThat(testJfCargo.getRq()).isEqualTo(DEFAULT_RQ);
        assertThat(testJfCargo.getZygsdm()).isEqualTo(DEFAULT_ZYGSDM);
        assertThat(testJfCargo.getHth()).isEqualTo(DEFAULT_HTH);
        assertThat(testJfCargo.getZywtrid()).isEqualTo(DEFAULT_ZYWTRID);
        assertThat(testJfCargo.getZywtr()).isEqualTo(DEFAULT_ZYWTR);
        assertThat(testJfCargo.getZhwchm()).isEqualTo(DEFAULT_ZHWCHM);
        assertThat(testJfCargo.getHwmch()).isEqualTo(DEFAULT_HWMCH);
        assertThat(testJfCargo.getGq()).isEqualTo(DEFAULT_GQ);
        assertThat(testJfCargo.getShl()).isEqualTo(DEFAULT_SHL);
        assertThat(testJfCargo.getSyl()).isEqualTo(DEFAULT_SYL);
    }

    @Test
    @Transactional
    void createJfCargoWithExistingId() throws Exception {
        // Create the JfCargo with an existing ID
        jfCargo.setId(1L);

        int databaseSizeBeforeCreate = jfCargoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJfCargoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfCargo)))
            .andExpect(status().isBadRequest());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJfCargos() throws Exception {
        // Initialize the database
        jfCargoRepository.saveAndFlush(jfCargo);

        // Get all the jfCargoList
        restJfCargoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jfCargo.getId().intValue())))
            .andExpect(jsonPath("$.[*].rq").value(hasItem(DEFAULT_RQ.toString())))
            .andExpect(jsonPath("$.[*].zygsdm").value(hasItem(DEFAULT_ZYGSDM)))
            .andExpect(jsonPath("$.[*].hth").value(hasItem(DEFAULT_HTH)))
            .andExpect(jsonPath("$.[*].zywtrid").value(hasItem(DEFAULT_ZYWTRID.intValue())))
            .andExpect(jsonPath("$.[*].zywtr").value(hasItem(DEFAULT_ZYWTR)))
            .andExpect(jsonPath("$.[*].zhwchm").value(hasItem(DEFAULT_ZHWCHM)))
            .andExpect(jsonPath("$.[*].hwmch").value(hasItem(DEFAULT_HWMCH)))
            .andExpect(jsonPath("$.[*].gq").value(hasItem(DEFAULT_GQ)))
            .andExpect(jsonPath("$.[*].shl").value(hasItem(DEFAULT_SHL.doubleValue())))
            .andExpect(jsonPath("$.[*].syl").value(hasItem(DEFAULT_SYL.doubleValue())));
    }

    @Test
    @Transactional
    void getJfCargo() throws Exception {
        // Initialize the database
        jfCargoRepository.saveAndFlush(jfCargo);

        // Get the jfCargo
        restJfCargoMockMvc
            .perform(get(ENTITY_API_URL_ID, jfCargo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jfCargo.getId().intValue()))
            .andExpect(jsonPath("$.rq").value(DEFAULT_RQ.toString()))
            .andExpect(jsonPath("$.zygsdm").value(DEFAULT_ZYGSDM))
            .andExpect(jsonPath("$.hth").value(DEFAULT_HTH))
            .andExpect(jsonPath("$.zywtrid").value(DEFAULT_ZYWTRID.intValue()))
            .andExpect(jsonPath("$.zywtr").value(DEFAULT_ZYWTR))
            .andExpect(jsonPath("$.zhwchm").value(DEFAULT_ZHWCHM))
            .andExpect(jsonPath("$.hwmch").value(DEFAULT_HWMCH))
            .andExpect(jsonPath("$.gq").value(DEFAULT_GQ))
            .andExpect(jsonPath("$.shl").value(DEFAULT_SHL.doubleValue()))
            .andExpect(jsonPath("$.syl").value(DEFAULT_SYL.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingJfCargo() throws Exception {
        // Get the jfCargo
        restJfCargoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJfCargo() throws Exception {
        // Initialize the database
        jfCargoRepository.saveAndFlush(jfCargo);

        int databaseSizeBeforeUpdate = jfCargoRepository.findAll().size();

        // Update the jfCargo
        JfCargo updatedJfCargo = jfCargoRepository.findById(jfCargo.getId()).get();
        // Disconnect from session so that the updates on updatedJfCargo are not directly saved in db
        em.detach(updatedJfCargo);
        updatedJfCargo
            .rq(UPDATED_RQ)
            .zygsdm(UPDATED_ZYGSDM)
            .hth(UPDATED_HTH)
            .zywtrid(UPDATED_ZYWTRID)
            .zywtr(UPDATED_ZYWTR)
            .zhwchm(UPDATED_ZHWCHM)
            .hwmch(UPDATED_HWMCH)
            .gq(UPDATED_GQ)
            .shl(UPDATED_SHL)
            .syl(UPDATED_SYL);

        restJfCargoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJfCargo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJfCargo))
            )
            .andExpect(status().isOk());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeUpdate);
        JfCargo testJfCargo = jfCargoList.get(jfCargoList.size() - 1);
        assertThat(testJfCargo.getRq()).isEqualTo(UPDATED_RQ);
        assertThat(testJfCargo.getZygsdm()).isEqualTo(UPDATED_ZYGSDM);
        assertThat(testJfCargo.getHth()).isEqualTo(UPDATED_HTH);
        assertThat(testJfCargo.getZywtrid()).isEqualTo(UPDATED_ZYWTRID);
        assertThat(testJfCargo.getZywtr()).isEqualTo(UPDATED_ZYWTR);
        assertThat(testJfCargo.getZhwchm()).isEqualTo(UPDATED_ZHWCHM);
        assertThat(testJfCargo.getHwmch()).isEqualTo(UPDATED_HWMCH);
        assertThat(testJfCargo.getGq()).isEqualTo(UPDATED_GQ);
        assertThat(testJfCargo.getShl()).isEqualTo(UPDATED_SHL);
        assertThat(testJfCargo.getSyl()).isEqualTo(UPDATED_SYL);
    }

    @Test
    @Transactional
    void putNonExistingJfCargo() throws Exception {
        int databaseSizeBeforeUpdate = jfCargoRepository.findAll().size();
        jfCargo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfCargoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jfCargo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfCargo))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJfCargo() throws Exception {
        int databaseSizeBeforeUpdate = jfCargoRepository.findAll().size();
        jfCargo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCargoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfCargo))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJfCargo() throws Exception {
        int databaseSizeBeforeUpdate = jfCargoRepository.findAll().size();
        jfCargo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCargoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfCargo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJfCargoWithPatch() throws Exception {
        // Initialize the database
        jfCargoRepository.saveAndFlush(jfCargo);

        int databaseSizeBeforeUpdate = jfCargoRepository.findAll().size();

        // Update the jfCargo using partial update
        JfCargo partialUpdatedJfCargo = new JfCargo();
        partialUpdatedJfCargo.setId(jfCargo.getId());

        partialUpdatedJfCargo.rq(UPDATED_RQ).zygsdm(UPDATED_ZYGSDM).zywtrid(UPDATED_ZYWTRID).hwmch(UPDATED_HWMCH).gq(UPDATED_GQ);

        restJfCargoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfCargo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfCargo))
            )
            .andExpect(status().isOk());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeUpdate);
        JfCargo testJfCargo = jfCargoList.get(jfCargoList.size() - 1);
        assertThat(testJfCargo.getRq()).isEqualTo(UPDATED_RQ);
        assertThat(testJfCargo.getZygsdm()).isEqualTo(UPDATED_ZYGSDM);
        assertThat(testJfCargo.getHth()).isEqualTo(DEFAULT_HTH);
        assertThat(testJfCargo.getZywtrid()).isEqualTo(UPDATED_ZYWTRID);
        assertThat(testJfCargo.getZywtr()).isEqualTo(DEFAULT_ZYWTR);
        assertThat(testJfCargo.getZhwchm()).isEqualTo(DEFAULT_ZHWCHM);
        assertThat(testJfCargo.getHwmch()).isEqualTo(UPDATED_HWMCH);
        assertThat(testJfCargo.getGq()).isEqualTo(UPDATED_GQ);
        assertThat(testJfCargo.getShl()).isEqualTo(DEFAULT_SHL);
        assertThat(testJfCargo.getSyl()).isEqualTo(DEFAULT_SYL);
    }

    @Test
    @Transactional
    void fullUpdateJfCargoWithPatch() throws Exception {
        // Initialize the database
        jfCargoRepository.saveAndFlush(jfCargo);

        int databaseSizeBeforeUpdate = jfCargoRepository.findAll().size();

        // Update the jfCargo using partial update
        JfCargo partialUpdatedJfCargo = new JfCargo();
        partialUpdatedJfCargo.setId(jfCargo.getId());

        partialUpdatedJfCargo
            .rq(UPDATED_RQ)
            .zygsdm(UPDATED_ZYGSDM)
            .hth(UPDATED_HTH)
            .zywtrid(UPDATED_ZYWTRID)
            .zywtr(UPDATED_ZYWTR)
            .zhwchm(UPDATED_ZHWCHM)
            .hwmch(UPDATED_HWMCH)
            .gq(UPDATED_GQ)
            .shl(UPDATED_SHL)
            .syl(UPDATED_SYL);

        restJfCargoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfCargo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfCargo))
            )
            .andExpect(status().isOk());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeUpdate);
        JfCargo testJfCargo = jfCargoList.get(jfCargoList.size() - 1);
        assertThat(testJfCargo.getRq()).isEqualTo(UPDATED_RQ);
        assertThat(testJfCargo.getZygsdm()).isEqualTo(UPDATED_ZYGSDM);
        assertThat(testJfCargo.getHth()).isEqualTo(UPDATED_HTH);
        assertThat(testJfCargo.getZywtrid()).isEqualTo(UPDATED_ZYWTRID);
        assertThat(testJfCargo.getZywtr()).isEqualTo(UPDATED_ZYWTR);
        assertThat(testJfCargo.getZhwchm()).isEqualTo(UPDATED_ZHWCHM);
        assertThat(testJfCargo.getHwmch()).isEqualTo(UPDATED_HWMCH);
        assertThat(testJfCargo.getGq()).isEqualTo(UPDATED_GQ);
        assertThat(testJfCargo.getShl()).isEqualTo(UPDATED_SHL);
        assertThat(testJfCargo.getSyl()).isEqualTo(UPDATED_SYL);
    }

    @Test
    @Transactional
    void patchNonExistingJfCargo() throws Exception {
        int databaseSizeBeforeUpdate = jfCargoRepository.findAll().size();
        jfCargo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfCargoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jfCargo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfCargo))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJfCargo() throws Exception {
        int databaseSizeBeforeUpdate = jfCargoRepository.findAll().size();
        jfCargo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCargoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfCargo))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJfCargo() throws Exception {
        int databaseSizeBeforeUpdate = jfCargoRepository.findAll().size();
        jfCargo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCargoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jfCargo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfCargo in the database
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJfCargo() throws Exception {
        // Initialize the database
        jfCargoRepository.saveAndFlush(jfCargo);

        int databaseSizeBeforeDelete = jfCargoRepository.findAll().size();

        // Delete the jfCargo
        restJfCargoMockMvc
            .perform(delete(ENTITY_API_URL_ID, jfCargo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JfCargo> jfCargoList = jfCargoRepository.findAll();
        assertThat(jfCargoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
