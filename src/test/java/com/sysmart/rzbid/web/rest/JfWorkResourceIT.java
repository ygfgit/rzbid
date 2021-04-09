package com.sysmart.rzbid.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sysmart.rzbid.IntegrationTest;
import com.sysmart.rzbid.domain.JfWork;
import com.sysmart.rzbid.repository.JfWorkRepository;
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
 * Integration tests for the {@link JfWorkResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JfWorkResourceIT {

    private static final String DEFAULT_HWMCH = "AAAAAAAAAA";
    private static final String UPDATED_HWMCH = "BBBBBBBBBB";

    private static final Double DEFAULT_JHSL = 1D;
    private static final Double UPDATED_JHSL = 2D;

    private static final String DEFAULT_BZ = "AAAAAAAAAA";
    private static final String UPDATED_BZ = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/jf-works";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JfWorkRepository jfWorkRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJfWorkMockMvc;

    private JfWork jfWork;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfWork createEntity(EntityManager em) {
        JfWork jfWork = new JfWork().hwmch(DEFAULT_HWMCH).jhsl(DEFAULT_JHSL).bz(DEFAULT_BZ);
        return jfWork;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfWork createUpdatedEntity(EntityManager em) {
        JfWork jfWork = new JfWork().hwmch(UPDATED_HWMCH).jhsl(UPDATED_JHSL).bz(UPDATED_BZ);
        return jfWork;
    }

    @BeforeEach
    public void initTest() {
        jfWork = createEntity(em);
    }

    @Test
    @Transactional
    void createJfWork() throws Exception {
        int databaseSizeBeforeCreate = jfWorkRepository.findAll().size();
        // Create the JfWork
        restJfWorkMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfWork)))
            .andExpect(status().isCreated());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeCreate + 1);
        JfWork testJfWork = jfWorkList.get(jfWorkList.size() - 1);
        assertThat(testJfWork.getHwmch()).isEqualTo(DEFAULT_HWMCH);
        assertThat(testJfWork.getJhsl()).isEqualTo(DEFAULT_JHSL);
        assertThat(testJfWork.getBz()).isEqualTo(DEFAULT_BZ);
    }

    @Test
    @Transactional
    void createJfWorkWithExistingId() throws Exception {
        // Create the JfWork with an existing ID
        jfWork.setId(1L);

        int databaseSizeBeforeCreate = jfWorkRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJfWorkMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfWork)))
            .andExpect(status().isBadRequest());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJfWorks() throws Exception {
        // Initialize the database
        jfWorkRepository.saveAndFlush(jfWork);

        // Get all the jfWorkList
        restJfWorkMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jfWork.getId().intValue())))
            .andExpect(jsonPath("$.[*].hwmch").value(hasItem(DEFAULT_HWMCH)))
            .andExpect(jsonPath("$.[*].jhsl").value(hasItem(DEFAULT_JHSL.doubleValue())))
            .andExpect(jsonPath("$.[*].bz").value(hasItem(DEFAULT_BZ)));
    }

    @Test
    @Transactional
    void getJfWork() throws Exception {
        // Initialize the database
        jfWorkRepository.saveAndFlush(jfWork);

        // Get the jfWork
        restJfWorkMockMvc
            .perform(get(ENTITY_API_URL_ID, jfWork.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jfWork.getId().intValue()))
            .andExpect(jsonPath("$.hwmch").value(DEFAULT_HWMCH))
            .andExpect(jsonPath("$.jhsl").value(DEFAULT_JHSL.doubleValue()))
            .andExpect(jsonPath("$.bz").value(DEFAULT_BZ));
    }

    @Test
    @Transactional
    void getNonExistingJfWork() throws Exception {
        // Get the jfWork
        restJfWorkMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJfWork() throws Exception {
        // Initialize the database
        jfWorkRepository.saveAndFlush(jfWork);

        int databaseSizeBeforeUpdate = jfWorkRepository.findAll().size();

        // Update the jfWork
        JfWork updatedJfWork = jfWorkRepository.findById(jfWork.getId()).get();
        // Disconnect from session so that the updates on updatedJfWork are not directly saved in db
        em.detach(updatedJfWork);
        updatedJfWork.hwmch(UPDATED_HWMCH).jhsl(UPDATED_JHSL).bz(UPDATED_BZ);

        restJfWorkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJfWork.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJfWork))
            )
            .andExpect(status().isOk());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeUpdate);
        JfWork testJfWork = jfWorkList.get(jfWorkList.size() - 1);
        assertThat(testJfWork.getHwmch()).isEqualTo(UPDATED_HWMCH);
        assertThat(testJfWork.getJhsl()).isEqualTo(UPDATED_JHSL);
        assertThat(testJfWork.getBz()).isEqualTo(UPDATED_BZ);
    }

    @Test
    @Transactional
    void putNonExistingJfWork() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkRepository.findAll().size();
        jfWork.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfWorkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jfWork.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfWork))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJfWork() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkRepository.findAll().size();
        jfWork.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfWorkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfWork))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJfWork() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkRepository.findAll().size();
        jfWork.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfWorkMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfWork)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJfWorkWithPatch() throws Exception {
        // Initialize the database
        jfWorkRepository.saveAndFlush(jfWork);

        int databaseSizeBeforeUpdate = jfWorkRepository.findAll().size();

        // Update the jfWork using partial update
        JfWork partialUpdatedJfWork = new JfWork();
        partialUpdatedJfWork.setId(jfWork.getId());

        partialUpdatedJfWork.hwmch(UPDATED_HWMCH).bz(UPDATED_BZ);

        restJfWorkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfWork.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfWork))
            )
            .andExpect(status().isOk());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeUpdate);
        JfWork testJfWork = jfWorkList.get(jfWorkList.size() - 1);
        assertThat(testJfWork.getHwmch()).isEqualTo(UPDATED_HWMCH);
        assertThat(testJfWork.getJhsl()).isEqualTo(DEFAULT_JHSL);
        assertThat(testJfWork.getBz()).isEqualTo(UPDATED_BZ);
    }

    @Test
    @Transactional
    void fullUpdateJfWorkWithPatch() throws Exception {
        // Initialize the database
        jfWorkRepository.saveAndFlush(jfWork);

        int databaseSizeBeforeUpdate = jfWorkRepository.findAll().size();

        // Update the jfWork using partial update
        JfWork partialUpdatedJfWork = new JfWork();
        partialUpdatedJfWork.setId(jfWork.getId());

        partialUpdatedJfWork.hwmch(UPDATED_HWMCH).jhsl(UPDATED_JHSL).bz(UPDATED_BZ);

        restJfWorkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfWork.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfWork))
            )
            .andExpect(status().isOk());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeUpdate);
        JfWork testJfWork = jfWorkList.get(jfWorkList.size() - 1);
        assertThat(testJfWork.getHwmch()).isEqualTo(UPDATED_HWMCH);
        assertThat(testJfWork.getJhsl()).isEqualTo(UPDATED_JHSL);
        assertThat(testJfWork.getBz()).isEqualTo(UPDATED_BZ);
    }

    @Test
    @Transactional
    void patchNonExistingJfWork() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkRepository.findAll().size();
        jfWork.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfWorkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jfWork.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfWork))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJfWork() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkRepository.findAll().size();
        jfWork.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfWorkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfWork))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJfWork() throws Exception {
        int databaseSizeBeforeUpdate = jfWorkRepository.findAll().size();
        jfWork.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfWorkMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jfWork)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfWork in the database
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJfWork() throws Exception {
        // Initialize the database
        jfWorkRepository.saveAndFlush(jfWork);

        int databaseSizeBeforeDelete = jfWorkRepository.findAll().size();

        // Delete the jfWork
        restJfWorkMockMvc
            .perform(delete(ENTITY_API_URL_ID, jfWork.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JfWork> jfWorkList = jfWorkRepository.findAll();
        assertThat(jfWorkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
