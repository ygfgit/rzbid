package com.sysmart.rzbid.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sysmart.rzbid.IntegrationTest;
import com.sysmart.rzbid.domain.JfTank;
import com.sysmart.rzbid.repository.JfTankRepository;
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
 * Integration tests for the {@link JfTankResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JfTankResourceIT {

    private static final String DEFAULT_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/jf-tanks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JfTankRepository jfTankRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJfTankMockMvc;

    private JfTank jfTank;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfTank createEntity(EntityManager em) {
        JfTank jfTank = new JfTank().zone(DEFAULT_ZONE).code(DEFAULT_CODE);
        return jfTank;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfTank createUpdatedEntity(EntityManager em) {
        JfTank jfTank = new JfTank().zone(UPDATED_ZONE).code(UPDATED_CODE);
        return jfTank;
    }

    @BeforeEach
    public void initTest() {
        jfTank = createEntity(em);
    }

    @Test
    @Transactional
    void createJfTank() throws Exception {
        int databaseSizeBeforeCreate = jfTankRepository.findAll().size();
        // Create the JfTank
        restJfTankMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfTank)))
            .andExpect(status().isCreated());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeCreate + 1);
        JfTank testJfTank = jfTankList.get(jfTankList.size() - 1);
        assertThat(testJfTank.getZone()).isEqualTo(DEFAULT_ZONE);
        assertThat(testJfTank.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    void createJfTankWithExistingId() throws Exception {
        // Create the JfTank with an existing ID
        jfTank.setId(1L);

        int databaseSizeBeforeCreate = jfTankRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJfTankMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfTank)))
            .andExpect(status().isBadRequest());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJfTanks() throws Exception {
        // Initialize the database
        jfTankRepository.saveAndFlush(jfTank);

        // Get all the jfTankList
        restJfTankMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jfTank.getId().intValue())))
            .andExpect(jsonPath("$.[*].zone").value(hasItem(DEFAULT_ZONE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }

    @Test
    @Transactional
    void getJfTank() throws Exception {
        // Initialize the database
        jfTankRepository.saveAndFlush(jfTank);

        // Get the jfTank
        restJfTankMockMvc
            .perform(get(ENTITY_API_URL_ID, jfTank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jfTank.getId().intValue()))
            .andExpect(jsonPath("$.zone").value(DEFAULT_ZONE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }

    @Test
    @Transactional
    void getNonExistingJfTank() throws Exception {
        // Get the jfTank
        restJfTankMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJfTank() throws Exception {
        // Initialize the database
        jfTankRepository.saveAndFlush(jfTank);

        int databaseSizeBeforeUpdate = jfTankRepository.findAll().size();

        // Update the jfTank
        JfTank updatedJfTank = jfTankRepository.findById(jfTank.getId()).get();
        // Disconnect from session so that the updates on updatedJfTank are not directly saved in db
        em.detach(updatedJfTank);
        updatedJfTank.zone(UPDATED_ZONE).code(UPDATED_CODE);

        restJfTankMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJfTank.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJfTank))
            )
            .andExpect(status().isOk());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeUpdate);
        JfTank testJfTank = jfTankList.get(jfTankList.size() - 1);
        assertThat(testJfTank.getZone()).isEqualTo(UPDATED_ZONE);
        assertThat(testJfTank.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    void putNonExistingJfTank() throws Exception {
        int databaseSizeBeforeUpdate = jfTankRepository.findAll().size();
        jfTank.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfTankMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jfTank.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfTank))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJfTank() throws Exception {
        int databaseSizeBeforeUpdate = jfTankRepository.findAll().size();
        jfTank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfTankMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfTank))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJfTank() throws Exception {
        int databaseSizeBeforeUpdate = jfTankRepository.findAll().size();
        jfTank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfTankMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfTank)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJfTankWithPatch() throws Exception {
        // Initialize the database
        jfTankRepository.saveAndFlush(jfTank);

        int databaseSizeBeforeUpdate = jfTankRepository.findAll().size();

        // Update the jfTank using partial update
        JfTank partialUpdatedJfTank = new JfTank();
        partialUpdatedJfTank.setId(jfTank.getId());

        partialUpdatedJfTank.zone(UPDATED_ZONE).code(UPDATED_CODE);

        restJfTankMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfTank.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfTank))
            )
            .andExpect(status().isOk());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeUpdate);
        JfTank testJfTank = jfTankList.get(jfTankList.size() - 1);
        assertThat(testJfTank.getZone()).isEqualTo(UPDATED_ZONE);
        assertThat(testJfTank.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    void fullUpdateJfTankWithPatch() throws Exception {
        // Initialize the database
        jfTankRepository.saveAndFlush(jfTank);

        int databaseSizeBeforeUpdate = jfTankRepository.findAll().size();

        // Update the jfTank using partial update
        JfTank partialUpdatedJfTank = new JfTank();
        partialUpdatedJfTank.setId(jfTank.getId());

        partialUpdatedJfTank.zone(UPDATED_ZONE).code(UPDATED_CODE);

        restJfTankMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfTank.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfTank))
            )
            .andExpect(status().isOk());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeUpdate);
        JfTank testJfTank = jfTankList.get(jfTankList.size() - 1);
        assertThat(testJfTank.getZone()).isEqualTo(UPDATED_ZONE);
        assertThat(testJfTank.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingJfTank() throws Exception {
        int databaseSizeBeforeUpdate = jfTankRepository.findAll().size();
        jfTank.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfTankMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jfTank.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfTank))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJfTank() throws Exception {
        int databaseSizeBeforeUpdate = jfTankRepository.findAll().size();
        jfTank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfTankMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfTank))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJfTank() throws Exception {
        int databaseSizeBeforeUpdate = jfTankRepository.findAll().size();
        jfTank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfTankMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jfTank)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfTank in the database
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJfTank() throws Exception {
        // Initialize the database
        jfTankRepository.saveAndFlush(jfTank);

        int databaseSizeBeforeDelete = jfTankRepository.findAll().size();

        // Delete the jfTank
        restJfTankMockMvc
            .perform(delete(ENTITY_API_URL_ID, jfTank.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JfTank> jfTankList = jfTankRepository.findAll();
        assertThat(jfTankList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
