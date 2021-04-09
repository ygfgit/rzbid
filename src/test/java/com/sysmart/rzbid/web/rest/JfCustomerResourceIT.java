package com.sysmart.rzbid.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sysmart.rzbid.IntegrationTest;
import com.sysmart.rzbid.domain.JfCustomer;
import com.sysmart.rzbid.repository.JfCustomerRepository;
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
 * Integration tests for the {@link JfCustomerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JfCustomerResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/jf-customers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JfCustomerRepository jfCustomerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJfCustomerMockMvc;

    private JfCustomer jfCustomer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfCustomer createEntity(EntityManager em) {
        JfCustomer jfCustomer = new JfCustomer().name(DEFAULT_NAME);
        return jfCustomer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfCustomer createUpdatedEntity(EntityManager em) {
        JfCustomer jfCustomer = new JfCustomer().name(UPDATED_NAME);
        return jfCustomer;
    }

    @BeforeEach
    public void initTest() {
        jfCustomer = createEntity(em);
    }

    @Test
    @Transactional
    void createJfCustomer() throws Exception {
        int databaseSizeBeforeCreate = jfCustomerRepository.findAll().size();
        // Create the JfCustomer
        restJfCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfCustomer)))
            .andExpect(status().isCreated());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeCreate + 1);
        JfCustomer testJfCustomer = jfCustomerList.get(jfCustomerList.size() - 1);
        assertThat(testJfCustomer.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void createJfCustomerWithExistingId() throws Exception {
        // Create the JfCustomer with an existing ID
        jfCustomer.setId(1L);

        int databaseSizeBeforeCreate = jfCustomerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJfCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfCustomer)))
            .andExpect(status().isBadRequest());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJfCustomers() throws Exception {
        // Initialize the database
        jfCustomerRepository.saveAndFlush(jfCustomer);

        // Get all the jfCustomerList
        restJfCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jfCustomer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getJfCustomer() throws Exception {
        // Initialize the database
        jfCustomerRepository.saveAndFlush(jfCustomer);

        // Get the jfCustomer
        restJfCustomerMockMvc
            .perform(get(ENTITY_API_URL_ID, jfCustomer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jfCustomer.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingJfCustomer() throws Exception {
        // Get the jfCustomer
        restJfCustomerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJfCustomer() throws Exception {
        // Initialize the database
        jfCustomerRepository.saveAndFlush(jfCustomer);

        int databaseSizeBeforeUpdate = jfCustomerRepository.findAll().size();

        // Update the jfCustomer
        JfCustomer updatedJfCustomer = jfCustomerRepository.findById(jfCustomer.getId()).get();
        // Disconnect from session so that the updates on updatedJfCustomer are not directly saved in db
        em.detach(updatedJfCustomer);
        updatedJfCustomer.name(UPDATED_NAME);

        restJfCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJfCustomer.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJfCustomer))
            )
            .andExpect(status().isOk());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeUpdate);
        JfCustomer testJfCustomer = jfCustomerList.get(jfCustomerList.size() - 1);
        assertThat(testJfCustomer.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void putNonExistingJfCustomer() throws Exception {
        int databaseSizeBeforeUpdate = jfCustomerRepository.findAll().size();
        jfCustomer.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jfCustomer.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfCustomer))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJfCustomer() throws Exception {
        int databaseSizeBeforeUpdate = jfCustomerRepository.findAll().size();
        jfCustomer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfCustomer))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJfCustomer() throws Exception {
        int databaseSizeBeforeUpdate = jfCustomerRepository.findAll().size();
        jfCustomer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCustomerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfCustomer)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJfCustomerWithPatch() throws Exception {
        // Initialize the database
        jfCustomerRepository.saveAndFlush(jfCustomer);

        int databaseSizeBeforeUpdate = jfCustomerRepository.findAll().size();

        // Update the jfCustomer using partial update
        JfCustomer partialUpdatedJfCustomer = new JfCustomer();
        partialUpdatedJfCustomer.setId(jfCustomer.getId());

        restJfCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfCustomer))
            )
            .andExpect(status().isOk());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeUpdate);
        JfCustomer testJfCustomer = jfCustomerList.get(jfCustomerList.size() - 1);
        assertThat(testJfCustomer.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void fullUpdateJfCustomerWithPatch() throws Exception {
        // Initialize the database
        jfCustomerRepository.saveAndFlush(jfCustomer);

        int databaseSizeBeforeUpdate = jfCustomerRepository.findAll().size();

        // Update the jfCustomer using partial update
        JfCustomer partialUpdatedJfCustomer = new JfCustomer();
        partialUpdatedJfCustomer.setId(jfCustomer.getId());

        partialUpdatedJfCustomer.name(UPDATED_NAME);

        restJfCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfCustomer))
            )
            .andExpect(status().isOk());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeUpdate);
        JfCustomer testJfCustomer = jfCustomerList.get(jfCustomerList.size() - 1);
        assertThat(testJfCustomer.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingJfCustomer() throws Exception {
        int databaseSizeBeforeUpdate = jfCustomerRepository.findAll().size();
        jfCustomer.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jfCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfCustomer))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJfCustomer() throws Exception {
        int databaseSizeBeforeUpdate = jfCustomerRepository.findAll().size();
        jfCustomer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfCustomer))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJfCustomer() throws Exception {
        int databaseSizeBeforeUpdate = jfCustomerRepository.findAll().size();
        jfCustomer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jfCustomer))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfCustomer in the database
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJfCustomer() throws Exception {
        // Initialize the database
        jfCustomerRepository.saveAndFlush(jfCustomer);

        int databaseSizeBeforeDelete = jfCustomerRepository.findAll().size();

        // Delete the jfCustomer
        restJfCustomerMockMvc
            .perform(delete(ENTITY_API_URL_ID, jfCustomer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JfCustomer> jfCustomerList = jfCustomerRepository.findAll();
        assertThat(jfCustomerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
