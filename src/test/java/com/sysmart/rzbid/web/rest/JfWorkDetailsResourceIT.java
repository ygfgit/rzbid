package com.sysmart.rzbid.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sysmart.rzbid.IntegrationTest;
import com.sysmart.rzbid.domain.JfWorkDetails;
import com.sysmart.rzbid.repository.JfWorkDetailsRepository;
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
 * Integration tests for the {@link JfWorkDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JfWorkDetailsResourceIT {

    private static final LocalDate DEFAULT_RQ = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RQ = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LB = "AAAAAAAAAA";
    private static final String UPDATED_LB = "BBBBBBBBBB";

    private static final Double DEFAULT_SHL = 1D;
    private static final Double UPDATED_SHL = 2D;

    private static final String DEFAULT_GQ = "AAAAAAAAAA";
    private static final String UPDATED_GQ = "BBBBBBBBBB";

    private static final Double DEFAULT_SYL = 1D;
    private static final Double UPDATED_SYL = 2D;

    private static final String ENTITY_API_URL = "/api/jf-work-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JfWorkDetailsRepository jfWorkDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJfWorkDetailsMockMvc;

    private JfWorkDetails jfWorkDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfWorkDetails createEntity(EntityManager em) {
        JfWorkDetails jfWorkDetails = new JfWorkDetails().rq(DEFAULT_RQ).lb(DEFAULT_LB).shl(DEFAULT_SHL).gq(DEFAULT_GQ).syl(DEFAULT_SYL);
        return jfWorkDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfWorkDetails createUpdatedEntity(EntityManager em) {
        JfWorkDetails jfWorkDetails = new JfWorkDetails().rq(UPDATED_RQ).lb(UPDATED_LB).shl(UPDATED_SHL).gq(UPDATED_GQ).syl(UPDATED_SYL);
        return jfWorkDetails;
    }

    @BeforeEach
    public void initTest() {
        jfWorkDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createJfWorkDetails() throws Exception {
        int databaseSizeBeforeCreate = jfWorkDetailsRepository.findAll().size();
        // Create the JfWorkDetails
        restJfWorkDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfWorkDetails)))
            .andExpect(status().isCreated());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        JfWorkDetails testJfWorkDetails = jfWorkDetailsList.get(jfWorkDetailsList.size() - 1);
        assertThat(testJfWorkDetails.getRq()).isEqualTo(DEFAULT_RQ);
        assertThat(testJfWorkDetails.getLb()).isEqualTo(DEFAULT_LB);
        assertThat(testJfWorkDetails.getShl()).isEqualTo(DEFAULT_SHL);
        assertThat(testJfWorkDetails.getGq()).isEqualTo(DEFAULT_GQ);
        assertThat(testJfWorkDetails.getSyl()).isEqualTo(DEFAULT_SYL);
    }

    @Test
    @Transactional
    void createJfWorkDetailsWithExistingId() throws Exception {
        // Create the JfWorkDetails with an existing ID
        jfWorkDetails.setId(1L);

        int databaseSizeBeforeCreate = jfWorkDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJfWorkDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfWorkDetails)))
            .andExpect(status().isBadRequest());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJfWorkDetails() throws Exception {
        // Initialize the database
        jfWorkDetailsRepository.saveAndFlush(jfWorkDetails);

        // Get all the jfWorkDetailsList
        restJfWorkDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jfWorkDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].rq").value(hasItem(DEFAULT_RQ.toString())))
            .andExpect(jsonPath("$.[*].lb").value(hasItem(DEFAULT_LB)))
            .andExpect(jsonPath("$.[*].shl").value(hasItem(DEFAULT_SHL.doubleValue())))
            .andExpect(jsonPath("$.[*].gq").value(hasItem(DEFAULT_GQ)))
            .andExpect(jsonPath("$.[*].syl").value(hasItem(DEFAULT_SYL.doubleValue())));
    }

    @Test
    @Transactional
    void getJfWorkDetails() throws Exception {
        // Initialize the database
        jfWorkDetailsRepository.saveAndFlush(jfWorkDetails);

        // Get the jfWorkDetails
        restJfWorkDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, jfWorkDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jfWorkDetails.getId().intValue()))
            .andExpect(jsonPath("$.rq").value(DEFAULT_RQ.toString()))
            .andExpect(jsonPath("$.lb").value(DEFAULT_LB))
            .andExpect(jsonPath("$.shl").value(DEFAULT_SHL.doubleValue()))
            .andExpect(jsonPath("$.gq").value(DEFAULT_GQ))
            .andExpect(jsonPath("$.syl").value(DEFAULT_SYL.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingJfWorkDetails() throws Exception {
        // Get the jfWorkDetails
        restJfWorkDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJfWorkDetails() throws Exception {
        // Initialize the database
        jfWorkDetailsRepository.saveAndFlush(jfWorkDetails);

        int databaseSizeBeforeUpdate = jfWorkDetailsRepository.findAll().size();

        // Update the jfWorkDetails
        JfWorkDetails updatedJfWorkDetails = jfWorkDetailsRepository.findById(jfWorkDetails.getId()).get();
        // Disconnect from session so that the updates on updatedJfWorkDetails are not directly saved in db
        em.detach(updatedJfWorkDetails);
        updatedJfWorkDetails.rq(UPDATED_RQ).lb(UPDATED_LB).shl(UPDATED_SHL).gq(UPDATED_GQ).syl(UPDATED_SYL);

        restJfWorkDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJfWorkDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJfWorkDetails))
            )
            .andExpect(status().isOk());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeUpdate);
        JfWorkDetails testJfWorkDetails = jfWorkDetailsList.get(jfWorkDetailsList.size() - 1);
        assertThat(testJfWorkDetails.getRq()).isEqualTo(UPDATED_RQ);
        assertThat(testJfWorkDetails.getLb()).isEqualTo(UPDATED_LB);
        assertThat(testJfWorkDetails.getShl()).isEqualTo(UPDATED_SHL);
        assertThat(testJfWorkDetails.getGq()).isEqualTo(UPDATED_GQ);
        assertThat(testJfWorkDetails.getSyl()).isEqualTo(UPDATED_SYL);
    }

    @Test
    @Transactional
    void putNonExistingJfWorkDetails() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkDetailsRepository.findAll().size();
        jfWorkDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfWorkDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jfWorkDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfWorkDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJfWorkDetails() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkDetailsRepository.findAll().size();
        jfWorkDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfWorkDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfWorkDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJfWorkDetails() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkDetailsRepository.findAll().size();
        jfWorkDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfWorkDetailsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfWorkDetails)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJfWorkDetailsWithPatch() throws Exception {
        // Initialize the database
        jfWorkDetailsRepository.saveAndFlush(jfWorkDetails);

        int databaseSizeBeforeUpdate = jfWorkDetailsRepository.findAll().size();

        // Update the jfWorkDetails using partial update
        JfWorkDetails partialUpdatedJfWorkDetails = new JfWorkDetails();
        partialUpdatedJfWorkDetails.setId(jfWorkDetails.getId());

        partialUpdatedJfWorkDetails.rq(UPDATED_RQ).shl(UPDATED_SHL).gq(UPDATED_GQ).syl(UPDATED_SYL);

        restJfWorkDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfWorkDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfWorkDetails))
            )
            .andExpect(status().isOk());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeUpdate);
        JfWorkDetails testJfWorkDetails = jfWorkDetailsList.get(jfWorkDetailsList.size() - 1);
        assertThat(testJfWorkDetails.getRq()).isEqualTo(UPDATED_RQ);
        assertThat(testJfWorkDetails.getLb()).isEqualTo(DEFAULT_LB);
        assertThat(testJfWorkDetails.getShl()).isEqualTo(UPDATED_SHL);
        assertThat(testJfWorkDetails.getGq()).isEqualTo(UPDATED_GQ);
        assertThat(testJfWorkDetails.getSyl()).isEqualTo(UPDATED_SYL);
    }

    @Test
    @Transactional
    void fullUpdateJfWorkDetailsWithPatch() throws Exception {
        // Initialize the database
        jfWorkDetailsRepository.saveAndFlush(jfWorkDetails);

        int databaseSizeBeforeUpdate = jfWorkDetailsRepository.findAll().size();

        // Update the jfWorkDetails using partial update
        JfWorkDetails partialUpdatedJfWorkDetails = new JfWorkDetails();
        partialUpdatedJfWorkDetails.setId(jfWorkDetails.getId());

        partialUpdatedJfWorkDetails.rq(UPDATED_RQ).lb(UPDATED_LB).shl(UPDATED_SHL).gq(UPDATED_GQ).syl(UPDATED_SYL);

        restJfWorkDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfWorkDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfWorkDetails))
            )
            .andExpect(status().isOk());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeUpdate);
        JfWorkDetails testJfWorkDetails = jfWorkDetailsList.get(jfWorkDetailsList.size() - 1);
        assertThat(testJfWorkDetails.getRq()).isEqualTo(UPDATED_RQ);
        assertThat(testJfWorkDetails.getLb()).isEqualTo(UPDATED_LB);
        assertThat(testJfWorkDetails.getShl()).isEqualTo(UPDATED_SHL);
        assertThat(testJfWorkDetails.getGq()).isEqualTo(UPDATED_GQ);
        assertThat(testJfWorkDetails.getSyl()).isEqualTo(UPDATED_SYL);
    }

    @Test
    @Transactional
    void patchNonExistingJfWorkDetails() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkDetailsRepository.findAll().size();
        jfWorkDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfWorkDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jfWorkDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfWorkDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJfWorkDetails() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkDetailsRepository.findAll().size();
        jfWorkDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfWorkDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfWorkDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJfWorkDetails() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkDetailsRepository.findAll().size();
        jfWorkDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfWorkDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jfWorkDetails))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfWorkDetails in the database
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJfWorkDetails() throws Exception {
        // Initialize the database
        jfWorkDetailsRepository.saveAndFlush(jfWorkDetails);

        int databaseSizeBeforeDelete = jfWorkDetailsRepository.findAll().size();

        // Delete the jfWorkDetails
        restJfWorkDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, jfWorkDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JfWorkDetails> jfWorkDetailsList = jfWorkDetailsRepository.findAll();
        assertThat(jfWorkDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
