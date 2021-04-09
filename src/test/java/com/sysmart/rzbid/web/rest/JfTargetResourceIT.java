package com.sysmart.rzbid.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sysmart.rzbid.IntegrationTest;
import com.sysmart.rzbid.domain.JfTarget;
import com.sysmart.rzbid.repository.JfTargetRepository;
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
 * Integration tests for the {@link JfTargetResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JfTargetResourceIT {

    private static final Double DEFAULT_FLZL = 1D;
    private static final Double UPDATED_FLZL = 2D;

    private static final String ENTITY_API_URL = "/api/jf-targets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JfTargetRepository jfTargetRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJfTargetMockMvc;

    private JfTarget jfTarget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfTarget createEntity(EntityManager em) {
        JfTarget jfTarget = new JfTarget().flzl(DEFAULT_FLZL);
        return jfTarget;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfTarget createUpdatedEntity(EntityManager em) {
        JfTarget jfTarget = new JfTarget().flzl(UPDATED_FLZL);
        return jfTarget;
    }

    @BeforeEach
    public void initTest() {
        jfTarget = createEntity(em);
    }

    @Test
    @Transactional
    void createJfTarget() throws Exception {
        int databaseSizeBeforeCreate = jfTargetRepository.findAll().size();
        // Create the JfTarget
        restJfTargetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfTarget)))
            .andExpect(status().isCreated());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeCreate + 1);
        JfTarget testJfTarget = jfTargetList.get(jfTargetList.size() - 1);
        assertThat(testJfTarget.getFlzl()).isEqualTo(DEFAULT_FLZL);
    }

    @Test
    @Transactional
    void createJfTargetWithExistingId() throws Exception {
        // Create the JfTarget with an existing ID
        jfTarget.setId(1L);

        int databaseSizeBeforeCreate = jfTargetRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJfTargetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfTarget)))
            .andExpect(status().isBadRequest());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJfTargets() throws Exception {
        // Initialize the database
        jfTargetRepository.saveAndFlush(jfTarget);

        // Get all the jfTargetList
        restJfTargetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jfTarget.getId().intValue())))
            .andExpect(jsonPath("$.[*].flzl").value(hasItem(DEFAULT_FLZL.doubleValue())));
    }

    @Test
    @Transactional
    void getJfTarget() throws Exception {
        // Initialize the database
        jfTargetRepository.saveAndFlush(jfTarget);

        // Get the jfTarget
        restJfTargetMockMvc
            .perform(get(ENTITY_API_URL_ID, jfTarget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jfTarget.getId().intValue()))
            .andExpect(jsonPath("$.flzl").value(DEFAULT_FLZL.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingJfTarget() throws Exception {
        // Get the jfTarget
        restJfTargetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJfTarget() throws Exception {
        // Initialize the database
        jfTargetRepository.saveAndFlush(jfTarget);

        int databaseSizeBeforeUpdate = jfTargetRepository.findAll().size();

        // Update the jfTarget
        JfTarget updatedJfTarget = jfTargetRepository.findById(jfTarget.getId()).get();
        // Disconnect from session so that the updates on updatedJfTarget are not directly saved in db
        em.detach(updatedJfTarget);
        updatedJfTarget.flzl(UPDATED_FLZL);

        restJfTargetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJfTarget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJfTarget))
            )
            .andExpect(status().isOk());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeUpdate);
        JfTarget testJfTarget = jfTargetList.get(jfTargetList.size() - 1);
        assertThat(testJfTarget.getFlzl()).isEqualTo(UPDATED_FLZL);
    }

    @Test
    @Transactional
    void putNonExistingJfTarget() throws Exception {
        int databaseSizeBeforeUpdate = jfTargetRepository.findAll().size();
        jfTarget.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfTargetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jfTarget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfTarget))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJfTarget() throws Exception {
        int databaseSizeBeforeUpdate = jfTargetRepository.findAll().size();
        jfTarget.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfTargetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfTarget))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJfTarget() throws Exception {
        int databaseSizeBeforeUpdate = jfTargetRepository.findAll().size();
        jfTarget.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfTargetMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfTarget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJfTargetWithPatch() throws Exception {
        // Initialize the database
        jfTargetRepository.saveAndFlush(jfTarget);

        int databaseSizeBeforeUpdate = jfTargetRepository.findAll().size();

        // Update the jfTarget using partial update
        JfTarget partialUpdatedJfTarget = new JfTarget();
        partialUpdatedJfTarget.setId(jfTarget.getId());

        partialUpdatedJfTarget.flzl(UPDATED_FLZL);

        restJfTargetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfTarget.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfTarget))
            )
            .andExpect(status().isOk());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeUpdate);
        JfTarget testJfTarget = jfTargetList.get(jfTargetList.size() - 1);
        assertThat(testJfTarget.getFlzl()).isEqualTo(UPDATED_FLZL);
    }

    @Test
    @Transactional
    void fullUpdateJfTargetWithPatch() throws Exception {
        // Initialize the database
        jfTargetRepository.saveAndFlush(jfTarget);

        int databaseSizeBeforeUpdate = jfTargetRepository.findAll().size();

        // Update the jfTarget using partial update
        JfTarget partialUpdatedJfTarget = new JfTarget();
        partialUpdatedJfTarget.setId(jfTarget.getId());

        partialUpdatedJfTarget.flzl(UPDATED_FLZL);

        restJfTargetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfTarget.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfTarget))
            )
            .andExpect(status().isOk());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeUpdate);
        JfTarget testJfTarget = jfTargetList.get(jfTargetList.size() - 1);
        assertThat(testJfTarget.getFlzl()).isEqualTo(UPDATED_FLZL);
    }

    @Test
    @Transactional
    void patchNonExistingJfTarget() throws Exception {
        int databaseSizeBeforeUpdate = jfTargetRepository.findAll().size();
        jfTarget.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfTargetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jfTarget.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfTarget))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJfTarget() throws Exception {
        int databaseSizeBeforeUpdate = jfTargetRepository.findAll().size();
        jfTarget.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfTargetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfTarget))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJfTarget() throws Exception {
        int databaseSizeBeforeUpdate = jfTargetRepository.findAll().size();
        jfTarget.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfTargetMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jfTarget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfTarget in the database
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJfTarget() throws Exception {
        // Initialize the database
        jfTargetRepository.saveAndFlush(jfTarget);

        int databaseSizeBeforeDelete = jfTargetRepository.findAll().size();

        // Delete the jfTarget
        restJfTargetMockMvc
            .perform(delete(ENTITY_API_URL_ID, jfTarget.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JfTarget> jfTargetList = jfTargetRepository.findAll();
        assertThat(jfTargetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
