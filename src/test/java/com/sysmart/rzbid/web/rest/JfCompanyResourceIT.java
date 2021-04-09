package com.sysmart.rzbid.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sysmart.rzbid.IntegrationTest;
import com.sysmart.rzbid.domain.JfCompany;
import com.sysmart.rzbid.repository.JfCompanyRepository;
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
 * Integration tests for the {@link JfCompanyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JfCompanyResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/jf-companies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JfCompanyRepository jfCompanyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJfCompanyMockMvc;

    private JfCompany jfCompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfCompany createEntity(EntityManager em) {
        JfCompany jfCompany = new JfCompany().name(DEFAULT_NAME);
        return jfCompany;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfCompany createUpdatedEntity(EntityManager em) {
        JfCompany jfCompany = new JfCompany().name(UPDATED_NAME);
        return jfCompany;
    }

    @BeforeEach
    public void initTest() {
        jfCompany = createEntity(em);
    }

    @Test
    @Transactional
    void createJfCompany() throws Exception {
        int databaseSizeBeforeCreate = jfCompanyRepository.findAll().size();
        // Create the JfCompany
        restJfCompanyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfCompany)))
            .andExpect(status().isCreated());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        JfCompany testJfCompany = jfCompanyList.get(jfCompanyList.size() - 1);
        assertThat(testJfCompany.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void createJfCompanyWithExistingId() throws Exception {
        // Create the JfCompany with an existing ID
        jfCompany.setId(1L);

        int databaseSizeBeforeCreate = jfCompanyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJfCompanyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfCompany)))
            .andExpect(status().isBadRequest());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJfCompanies() throws Exception {
        // Initialize the database
        jfCompanyRepository.saveAndFlush(jfCompany);

        // Get all the jfCompanyList
        restJfCompanyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jfCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getJfCompany() throws Exception {
        // Initialize the database
        jfCompanyRepository.saveAndFlush(jfCompany);

        // Get the jfCompany
        restJfCompanyMockMvc
            .perform(get(ENTITY_API_URL_ID, jfCompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jfCompany.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingJfCompany() throws Exception {
        // Get the jfCompany
        restJfCompanyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJfCompany() throws Exception {
        // Initialize the database
        jfCompanyRepository.saveAndFlush(jfCompany);

        int databaseSizeBeforeUpdate = jfCompanyRepository.findAll().size();

        // Update the jfCompany
        JfCompany updatedJfCompany = jfCompanyRepository.findById(jfCompany.getId()).get();
        // Disconnect from session so that the updates on updatedJfCompany are not directly saved in db
        em.detach(updatedJfCompany);
        updatedJfCompany.name(UPDATED_NAME);

        restJfCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJfCompany.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJfCompany))
            )
            .andExpect(status().isOk());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeUpdate);
        JfCompany testJfCompany = jfCompanyList.get(jfCompanyList.size() - 1);
        assertThat(testJfCompany.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void putNonExistingJfCompany() throws Exception {
        int databaseSizeBeforeUpdate = jfCompanyRepository.findAll().size();
        jfCompany.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jfCompany.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfCompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJfCompany() throws Exception {
        int databaseSizeBeforeUpdate = jfCompanyRepository.findAll().size();
        jfCompany.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfCompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJfCompany() throws Exception {
        int databaseSizeBeforeUpdate = jfCompanyRepository.findAll().size();
        jfCompany.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCompanyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfCompany)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJfCompanyWithPatch() throws Exception {
        // Initialize the database
        jfCompanyRepository.saveAndFlush(jfCompany);

        int databaseSizeBeforeUpdate = jfCompanyRepository.findAll().size();

        // Update the jfCompany using partial update
        JfCompany partialUpdatedJfCompany = new JfCompany();
        partialUpdatedJfCompany.setId(jfCompany.getId());

        restJfCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfCompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfCompany))
            )
            .andExpect(status().isOk());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeUpdate);
        JfCompany testJfCompany = jfCompanyList.get(jfCompanyList.size() - 1);
        assertThat(testJfCompany.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void fullUpdateJfCompanyWithPatch() throws Exception {
        // Initialize the database
        jfCompanyRepository.saveAndFlush(jfCompany);

        int databaseSizeBeforeUpdate = jfCompanyRepository.findAll().size();

        // Update the jfCompany using partial update
        JfCompany partialUpdatedJfCompany = new JfCompany();
        partialUpdatedJfCompany.setId(jfCompany.getId());

        partialUpdatedJfCompany.name(UPDATED_NAME);

        restJfCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfCompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfCompany))
            )
            .andExpect(status().isOk());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeUpdate);
        JfCompany testJfCompany = jfCompanyList.get(jfCompanyList.size() - 1);
        assertThat(testJfCompany.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingJfCompany() throws Exception {
        int databaseSizeBeforeUpdate = jfCompanyRepository.findAll().size();
        jfCompany.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jfCompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfCompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJfCompany() throws Exception {
        int databaseSizeBeforeUpdate = jfCompanyRepository.findAll().size();
        jfCompany.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfCompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJfCompany() throws Exception {
        int databaseSizeBeforeUpdate = jfCompanyRepository.findAll().size();
        jfCompany.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jfCompany))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfCompany in the database
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJfCompany() throws Exception {
        // Initialize the database
        jfCompanyRepository.saveAndFlush(jfCompany);

        int databaseSizeBeforeDelete = jfCompanyRepository.findAll().size();

        // Delete the jfCompany
        restJfCompanyMockMvc
            .perform(delete(ENTITY_API_URL_ID, jfCompany.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JfCompany> jfCompanyList = jfCompanyRepository.findAll();
        assertThat(jfCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
