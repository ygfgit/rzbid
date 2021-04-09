package com.sysmart.rzbid.web.rest;

import static com.sysmart.rzbid.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sysmart.rzbid.IntegrationTest;
import com.sysmart.rzbid.domain.JfMaster;
import com.sysmart.rzbid.repository.JfMasterRepository;
import java.math.BigDecimal;
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
 * Integration tests for the {@link JfMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JfMasterResourceIT {

    private static final String DEFAULT_ZYGS = "AAAAAAAAAA";
    private static final String UPDATED_ZYGS = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_JE = new BigDecimal(1);
    private static final BigDecimal UPDATED_JE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SHE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SHE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SHHJE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SHHJE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_JMJE = new BigDecimal(1);
    private static final BigDecimal UPDATED_JMJE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_JMHJE = new BigDecimal(1);
    private static final BigDecimal UPDATED_JMHJE = new BigDecimal(2);

    private static final Long DEFAULT_ID_HW = 1L;
    private static final Long UPDATED_ID_HW = 2L;

    private static final Long DEFAULT_HTID = 1L;
    private static final Long UPDATED_HTID = 2L;

    private static final String DEFAULT_MB = "AAAAAAAAAA";
    private static final String UPDATED_MB = "BBBBBBBBBB";

    private static final Long DEFAULT_ZYWTRID = 1L;
    private static final Long UPDATED_ZYWTRID = 2L;

    private static final String DEFAULT_MARK_1 = "AAAAAAAAAA";
    private static final String UPDATED_MARK_1 = "BBBBBBBBBB";

    private static final String DEFAULT_MARK_2 = "AAAAAAAAAA";
    private static final String UPDATED_MARK_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEBY = "AAAAAAAAAA";
    private static final String UPDATED_CREATEBY = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATEBYID = 1L;
    private static final Long UPDATED_CREATEBYID = 2L;

    private static final LocalDate DEFAULT_CREATEON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATEON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATE_GSID = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_GSID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_GSMCH = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_GSMCH = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATE_BMID = 1L;
    private static final Long UPDATED_CREATE_BMID = 2L;

    private static final String DEFAULT_CREATE_BMM = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_BMM = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_GWID = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_GWID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_GWM = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_GWM = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFYBY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFYBY = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFYBYID = 1L;
    private static final Long UPDATED_MODIFYBYID = 2L;

    private static final LocalDate DEFAULT_MODIFYON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MODIFYON = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/jf-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JfMasterRepository jfMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJfMasterMockMvc;

    private JfMaster jfMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfMaster createEntity(EntityManager em) {
        JfMaster jfMaster = new JfMaster()
            .zygs(DEFAULT_ZYGS)
            .je(DEFAULT_JE)
            .she(DEFAULT_SHE)
            .shhje(DEFAULT_SHHJE)
            .jmje(DEFAULT_JMJE)
            .jmhje(DEFAULT_JMHJE)
            .idHw(DEFAULT_ID_HW)
            .htid(DEFAULT_HTID)
            .mb(DEFAULT_MB)
            .zywtrid(DEFAULT_ZYWTRID)
            .mark1(DEFAULT_MARK_1)
            .mark2(DEFAULT_MARK_2)
            .createby(DEFAULT_CREATEBY)
            .createbyid(DEFAULT_CREATEBYID)
            .createon(DEFAULT_CREATEON)
            .createGsid(DEFAULT_CREATE_GSID)
            .createGsmch(DEFAULT_CREATE_GSMCH)
            .createBmid(DEFAULT_CREATE_BMID)
            .createBmm(DEFAULT_CREATE_BMM)
            .createGwid(DEFAULT_CREATE_GWID)
            .createGwm(DEFAULT_CREATE_GWM)
            .modifyby(DEFAULT_MODIFYBY)
            .modifybyid(DEFAULT_MODIFYBYID)
            .modifyon(DEFAULT_MODIFYON);
        return jfMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JfMaster createUpdatedEntity(EntityManager em) {
        JfMaster jfMaster = new JfMaster()
            .zygs(UPDATED_ZYGS)
            .je(UPDATED_JE)
            .she(UPDATED_SHE)
            .shhje(UPDATED_SHHJE)
            .jmje(UPDATED_JMJE)
            .jmhje(UPDATED_JMHJE)
            .idHw(UPDATED_ID_HW)
            .htid(UPDATED_HTID)
            .mb(UPDATED_MB)
            .zywtrid(UPDATED_ZYWTRID)
            .mark1(UPDATED_MARK_1)
            .mark2(UPDATED_MARK_2)
            .createby(UPDATED_CREATEBY)
            .createbyid(UPDATED_CREATEBYID)
            .createon(UPDATED_CREATEON)
            .createGsid(UPDATED_CREATE_GSID)
            .createGsmch(UPDATED_CREATE_GSMCH)
            .createBmid(UPDATED_CREATE_BMID)
            .createBmm(UPDATED_CREATE_BMM)
            .createGwid(UPDATED_CREATE_GWID)
            .createGwm(UPDATED_CREATE_GWM)
            .modifyby(UPDATED_MODIFYBY)
            .modifybyid(UPDATED_MODIFYBYID)
            .modifyon(UPDATED_MODIFYON);
        return jfMaster;
    }

    @BeforeEach
    public void initTest() {
        jfMaster = createEntity(em);
    }

    @Test
    @Transactional
    void createJfMaster() throws Exception {
        int databaseSizeBeforeCreate = jfMasterRepository.findAll().size();
        // Create the JfMaster
        restJfMasterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfMaster)))
            .andExpect(status().isCreated());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeCreate + 1);
        JfMaster testJfMaster = jfMasterList.get(jfMasterList.size() - 1);
        assertThat(testJfMaster.getZygs()).isEqualTo(DEFAULT_ZYGS);
        assertThat(testJfMaster.getJe()).isEqualByComparingTo(DEFAULT_JE);
        assertThat(testJfMaster.getShe()).isEqualByComparingTo(DEFAULT_SHE);
        assertThat(testJfMaster.getShhje()).isEqualByComparingTo(DEFAULT_SHHJE);
        assertThat(testJfMaster.getJmje()).isEqualByComparingTo(DEFAULT_JMJE);
        assertThat(testJfMaster.getJmhje()).isEqualByComparingTo(DEFAULT_JMHJE);
        assertThat(testJfMaster.getIdHw()).isEqualTo(DEFAULT_ID_HW);
        assertThat(testJfMaster.getHtid()).isEqualTo(DEFAULT_HTID);
        assertThat(testJfMaster.getMb()).isEqualTo(DEFAULT_MB);
        assertThat(testJfMaster.getZywtrid()).isEqualTo(DEFAULT_ZYWTRID);
        assertThat(testJfMaster.getMark1()).isEqualTo(DEFAULT_MARK_1);
        assertThat(testJfMaster.getMark2()).isEqualTo(DEFAULT_MARK_2);
        assertThat(testJfMaster.getCreateby()).isEqualTo(DEFAULT_CREATEBY);
        assertThat(testJfMaster.getCreatebyid()).isEqualTo(DEFAULT_CREATEBYID);
        assertThat(testJfMaster.getCreateon()).isEqualTo(DEFAULT_CREATEON);
        assertThat(testJfMaster.getCreateGsid()).isEqualTo(DEFAULT_CREATE_GSID);
        assertThat(testJfMaster.getCreateGsmch()).isEqualTo(DEFAULT_CREATE_GSMCH);
        assertThat(testJfMaster.getCreateBmid()).isEqualTo(DEFAULT_CREATE_BMID);
        assertThat(testJfMaster.getCreateBmm()).isEqualTo(DEFAULT_CREATE_BMM);
        assertThat(testJfMaster.getCreateGwid()).isEqualTo(DEFAULT_CREATE_GWID);
        assertThat(testJfMaster.getCreateGwm()).isEqualTo(DEFAULT_CREATE_GWM);
        assertThat(testJfMaster.getModifyby()).isEqualTo(DEFAULT_MODIFYBY);
        assertThat(testJfMaster.getModifybyid()).isEqualTo(DEFAULT_MODIFYBYID);
        assertThat(testJfMaster.getModifyon()).isEqualTo(DEFAULT_MODIFYON);
    }

    @Test
    @Transactional
    void createJfMasterWithExistingId() throws Exception {
        // Create the JfMaster with an existing ID
        jfMaster.setId(1L);

        int databaseSizeBeforeCreate = jfMasterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJfMasterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfMaster)))
            .andExpect(status().isBadRequest());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJfMasters() throws Exception {
        // Initialize the database
        jfMasterRepository.saveAndFlush(jfMaster);

        // Get all the jfMasterList
        restJfMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jfMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].zygs").value(hasItem(DEFAULT_ZYGS)))
            .andExpect(jsonPath("$.[*].je").value(hasItem(sameNumber(DEFAULT_JE))))
            .andExpect(jsonPath("$.[*].she").value(hasItem(sameNumber(DEFAULT_SHE))))
            .andExpect(jsonPath("$.[*].shhje").value(hasItem(sameNumber(DEFAULT_SHHJE))))
            .andExpect(jsonPath("$.[*].jmje").value(hasItem(sameNumber(DEFAULT_JMJE))))
            .andExpect(jsonPath("$.[*].jmhje").value(hasItem(sameNumber(DEFAULT_JMHJE))))
            .andExpect(jsonPath("$.[*].idHw").value(hasItem(DEFAULT_ID_HW.intValue())))
            .andExpect(jsonPath("$.[*].htid").value(hasItem(DEFAULT_HTID.intValue())))
            .andExpect(jsonPath("$.[*].mb").value(hasItem(DEFAULT_MB)))
            .andExpect(jsonPath("$.[*].zywtrid").value(hasItem(DEFAULT_ZYWTRID.intValue())))
            .andExpect(jsonPath("$.[*].mark1").value(hasItem(DEFAULT_MARK_1)))
            .andExpect(jsonPath("$.[*].mark2").value(hasItem(DEFAULT_MARK_2)))
            .andExpect(jsonPath("$.[*].createby").value(hasItem(DEFAULT_CREATEBY)))
            .andExpect(jsonPath("$.[*].createbyid").value(hasItem(DEFAULT_CREATEBYID.intValue())))
            .andExpect(jsonPath("$.[*].createon").value(hasItem(DEFAULT_CREATEON.toString())))
            .andExpect(jsonPath("$.[*].createGsid").value(hasItem(DEFAULT_CREATE_GSID)))
            .andExpect(jsonPath("$.[*].createGsmch").value(hasItem(DEFAULT_CREATE_GSMCH)))
            .andExpect(jsonPath("$.[*].createBmid").value(hasItem(DEFAULT_CREATE_BMID.intValue())))
            .andExpect(jsonPath("$.[*].createBmm").value(hasItem(DEFAULT_CREATE_BMM)))
            .andExpect(jsonPath("$.[*].createGwid").value(hasItem(DEFAULT_CREATE_GWID)))
            .andExpect(jsonPath("$.[*].createGwm").value(hasItem(DEFAULT_CREATE_GWM)))
            .andExpect(jsonPath("$.[*].modifyby").value(hasItem(DEFAULT_MODIFYBY)))
            .andExpect(jsonPath("$.[*].modifybyid").value(hasItem(DEFAULT_MODIFYBYID.intValue())))
            .andExpect(jsonPath("$.[*].modifyon").value(hasItem(DEFAULT_MODIFYON.toString())));
    }

    @Test
    @Transactional
    void getJfMaster() throws Exception {
        // Initialize the database
        jfMasterRepository.saveAndFlush(jfMaster);

        // Get the jfMaster
        restJfMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, jfMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jfMaster.getId().intValue()))
            .andExpect(jsonPath("$.zygs").value(DEFAULT_ZYGS))
            .andExpect(jsonPath("$.je").value(sameNumber(DEFAULT_JE)))
            .andExpect(jsonPath("$.she").value(sameNumber(DEFAULT_SHE)))
            .andExpect(jsonPath("$.shhje").value(sameNumber(DEFAULT_SHHJE)))
            .andExpect(jsonPath("$.jmje").value(sameNumber(DEFAULT_JMJE)))
            .andExpect(jsonPath("$.jmhje").value(sameNumber(DEFAULT_JMHJE)))
            .andExpect(jsonPath("$.idHw").value(DEFAULT_ID_HW.intValue()))
            .andExpect(jsonPath("$.htid").value(DEFAULT_HTID.intValue()))
            .andExpect(jsonPath("$.mb").value(DEFAULT_MB))
            .andExpect(jsonPath("$.zywtrid").value(DEFAULT_ZYWTRID.intValue()))
            .andExpect(jsonPath("$.mark1").value(DEFAULT_MARK_1))
            .andExpect(jsonPath("$.mark2").value(DEFAULT_MARK_2))
            .andExpect(jsonPath("$.createby").value(DEFAULT_CREATEBY))
            .andExpect(jsonPath("$.createbyid").value(DEFAULT_CREATEBYID.intValue()))
            .andExpect(jsonPath("$.createon").value(DEFAULT_CREATEON.toString()))
            .andExpect(jsonPath("$.createGsid").value(DEFAULT_CREATE_GSID))
            .andExpect(jsonPath("$.createGsmch").value(DEFAULT_CREATE_GSMCH))
            .andExpect(jsonPath("$.createBmid").value(DEFAULT_CREATE_BMID.intValue()))
            .andExpect(jsonPath("$.createBmm").value(DEFAULT_CREATE_BMM))
            .andExpect(jsonPath("$.createGwid").value(DEFAULT_CREATE_GWID))
            .andExpect(jsonPath("$.createGwm").value(DEFAULT_CREATE_GWM))
            .andExpect(jsonPath("$.modifyby").value(DEFAULT_MODIFYBY))
            .andExpect(jsonPath("$.modifybyid").value(DEFAULT_MODIFYBYID.intValue()))
            .andExpect(jsonPath("$.modifyon").value(DEFAULT_MODIFYON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingJfMaster() throws Exception {
        // Get the jfMaster
        restJfMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJfMaster() throws Exception {
        // Initialize the database
        jfMasterRepository.saveAndFlush(jfMaster);

        int databaseSizeBeforeUpdate = jfMasterRepository.findAll().size();

        // Update the jfMaster
        JfMaster updatedJfMaster = jfMasterRepository.findById(jfMaster.getId()).get();
        // Disconnect from session so that the updates on updatedJfMaster are not directly saved in db
        em.detach(updatedJfMaster);
        updatedJfMaster
            .zygs(UPDATED_ZYGS)
            .je(UPDATED_JE)
            .she(UPDATED_SHE)
            .shhje(UPDATED_SHHJE)
            .jmje(UPDATED_JMJE)
            .jmhje(UPDATED_JMHJE)
            .idHw(UPDATED_ID_HW)
            .htid(UPDATED_HTID)
            .mb(UPDATED_MB)
            .zywtrid(UPDATED_ZYWTRID)
            .mark1(UPDATED_MARK_1)
            .mark2(UPDATED_MARK_2)
            .createby(UPDATED_CREATEBY)
            .createbyid(UPDATED_CREATEBYID)
            .createon(UPDATED_CREATEON)
            .createGsid(UPDATED_CREATE_GSID)
            .createGsmch(UPDATED_CREATE_GSMCH)
            .createBmid(UPDATED_CREATE_BMID)
            .createBmm(UPDATED_CREATE_BMM)
            .createGwid(UPDATED_CREATE_GWID)
            .createGwm(UPDATED_CREATE_GWM)
            .modifyby(UPDATED_MODIFYBY)
            .modifybyid(UPDATED_MODIFYBYID)
            .modifyon(UPDATED_MODIFYON);

        restJfMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJfMaster.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJfMaster))
            )
            .andExpect(status().isOk());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeUpdate);
        JfMaster testJfMaster = jfMasterList.get(jfMasterList.size() - 1);
        assertThat(testJfMaster.getZygs()).isEqualTo(UPDATED_ZYGS);
        assertThat(testJfMaster.getJe()).isEqualTo(UPDATED_JE);
        assertThat(testJfMaster.getShe()).isEqualTo(UPDATED_SHE);
        assertThat(testJfMaster.getShhje()).isEqualTo(UPDATED_SHHJE);
        assertThat(testJfMaster.getJmje()).isEqualTo(UPDATED_JMJE);
        assertThat(testJfMaster.getJmhje()).isEqualTo(UPDATED_JMHJE);
        assertThat(testJfMaster.getIdHw()).isEqualTo(UPDATED_ID_HW);
        assertThat(testJfMaster.getHtid()).isEqualTo(UPDATED_HTID);
        assertThat(testJfMaster.getMb()).isEqualTo(UPDATED_MB);
        assertThat(testJfMaster.getZywtrid()).isEqualTo(UPDATED_ZYWTRID);
        assertThat(testJfMaster.getMark1()).isEqualTo(UPDATED_MARK_1);
        assertThat(testJfMaster.getMark2()).isEqualTo(UPDATED_MARK_2);
        assertThat(testJfMaster.getCreateby()).isEqualTo(UPDATED_CREATEBY);
        assertThat(testJfMaster.getCreatebyid()).isEqualTo(UPDATED_CREATEBYID);
        assertThat(testJfMaster.getCreateon()).isEqualTo(UPDATED_CREATEON);
        assertThat(testJfMaster.getCreateGsid()).isEqualTo(UPDATED_CREATE_GSID);
        assertThat(testJfMaster.getCreateGsmch()).isEqualTo(UPDATED_CREATE_GSMCH);
        assertThat(testJfMaster.getCreateBmid()).isEqualTo(UPDATED_CREATE_BMID);
        assertThat(testJfMaster.getCreateBmm()).isEqualTo(UPDATED_CREATE_BMM);
        assertThat(testJfMaster.getCreateGwid()).isEqualTo(UPDATED_CREATE_GWID);
        assertThat(testJfMaster.getCreateGwm()).isEqualTo(UPDATED_CREATE_GWM);
        assertThat(testJfMaster.getModifyby()).isEqualTo(UPDATED_MODIFYBY);
        assertThat(testJfMaster.getModifybyid()).isEqualTo(UPDATED_MODIFYBYID);
        assertThat(testJfMaster.getModifyon()).isEqualTo(UPDATED_MODIFYON);
    }

    @Test
    @Transactional
    void putNonExistingJfMaster() throws Exception {
        int databaseSizeBeforeUpdate = jfMasterRepository.findAll().size();
        jfMaster.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jfMaster.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfMaster))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJfMaster() throws Exception {
        int databaseSizeBeforeUpdate = jfMasterRepository.findAll().size();
        jfMaster.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jfMaster))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJfMaster() throws Exception {
        int databaseSizeBeforeUpdate = jfMasterRepository.findAll().size();
        jfMaster.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfMasterMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jfMaster)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJfMasterWithPatch() throws Exception {
        // Initialize the database
        jfMasterRepository.saveAndFlush(jfMaster);

        int databaseSizeBeforeUpdate = jfMasterRepository.findAll().size();

        // Update the jfMaster using partial update
        JfMaster partialUpdatedJfMaster = new JfMaster();
        partialUpdatedJfMaster.setId(jfMaster.getId());

        partialUpdatedJfMaster
            .zygs(UPDATED_ZYGS)
            .she(UPDATED_SHE)
            .shhje(UPDATED_SHHJE)
            .jmje(UPDATED_JMJE)
            .mb(UPDATED_MB)
            .zywtrid(UPDATED_ZYWTRID)
            .createby(UPDATED_CREATEBY)
            .createbyid(UPDATED_CREATEBYID)
            .createon(UPDATED_CREATEON)
            .createGsid(UPDATED_CREATE_GSID)
            .createGsmch(UPDATED_CREATE_GSMCH)
            .createGwid(UPDATED_CREATE_GWID)
            .modifyon(UPDATED_MODIFYON);

        restJfMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfMaster))
            )
            .andExpect(status().isOk());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeUpdate);
        JfMaster testJfMaster = jfMasterList.get(jfMasterList.size() - 1);
        assertThat(testJfMaster.getZygs()).isEqualTo(UPDATED_ZYGS);
        assertThat(testJfMaster.getJe()).isEqualByComparingTo(DEFAULT_JE);
        assertThat(testJfMaster.getShe()).isEqualByComparingTo(UPDATED_SHE);
        assertThat(testJfMaster.getShhje()).isEqualByComparingTo(UPDATED_SHHJE);
        assertThat(testJfMaster.getJmje()).isEqualByComparingTo(UPDATED_JMJE);
        assertThat(testJfMaster.getJmhje()).isEqualByComparingTo(DEFAULT_JMHJE);
        assertThat(testJfMaster.getIdHw()).isEqualTo(DEFAULT_ID_HW);
        assertThat(testJfMaster.getHtid()).isEqualTo(DEFAULT_HTID);
        assertThat(testJfMaster.getMb()).isEqualTo(UPDATED_MB);
        assertThat(testJfMaster.getZywtrid()).isEqualTo(UPDATED_ZYWTRID);
        assertThat(testJfMaster.getMark1()).isEqualTo(DEFAULT_MARK_1);
        assertThat(testJfMaster.getMark2()).isEqualTo(DEFAULT_MARK_2);
        assertThat(testJfMaster.getCreateby()).isEqualTo(UPDATED_CREATEBY);
        assertThat(testJfMaster.getCreatebyid()).isEqualTo(UPDATED_CREATEBYID);
        assertThat(testJfMaster.getCreateon()).isEqualTo(UPDATED_CREATEON);
        assertThat(testJfMaster.getCreateGsid()).isEqualTo(UPDATED_CREATE_GSID);
        assertThat(testJfMaster.getCreateGsmch()).isEqualTo(UPDATED_CREATE_GSMCH);
        assertThat(testJfMaster.getCreateBmid()).isEqualTo(DEFAULT_CREATE_BMID);
        assertThat(testJfMaster.getCreateBmm()).isEqualTo(DEFAULT_CREATE_BMM);
        assertThat(testJfMaster.getCreateGwid()).isEqualTo(UPDATED_CREATE_GWID);
        assertThat(testJfMaster.getCreateGwm()).isEqualTo(DEFAULT_CREATE_GWM);
        assertThat(testJfMaster.getModifyby()).isEqualTo(DEFAULT_MODIFYBY);
        assertThat(testJfMaster.getModifybyid()).isEqualTo(DEFAULT_MODIFYBYID);
        assertThat(testJfMaster.getModifyon()).isEqualTo(UPDATED_MODIFYON);
    }

    @Test
    @Transactional
    void fullUpdateJfMasterWithPatch() throws Exception {
        // Initialize the database
        jfMasterRepository.saveAndFlush(jfMaster);

        int databaseSizeBeforeUpdate = jfMasterRepository.findAll().size();

        // Update the jfMaster using partial update
        JfMaster partialUpdatedJfMaster = new JfMaster();
        partialUpdatedJfMaster.setId(jfMaster.getId());

        partialUpdatedJfMaster
            .zygs(UPDATED_ZYGS)
            .je(UPDATED_JE)
            .she(UPDATED_SHE)
            .shhje(UPDATED_SHHJE)
            .jmje(UPDATED_JMJE)
            .jmhje(UPDATED_JMHJE)
            .idHw(UPDATED_ID_HW)
            .htid(UPDATED_HTID)
            .mb(UPDATED_MB)
            .zywtrid(UPDATED_ZYWTRID)
            .mark1(UPDATED_MARK_1)
            .mark2(UPDATED_MARK_2)
            .createby(UPDATED_CREATEBY)
            .createbyid(UPDATED_CREATEBYID)
            .createon(UPDATED_CREATEON)
            .createGsid(UPDATED_CREATE_GSID)
            .createGsmch(UPDATED_CREATE_GSMCH)
            .createBmid(UPDATED_CREATE_BMID)
            .createBmm(UPDATED_CREATE_BMM)
            .createGwid(UPDATED_CREATE_GWID)
            .createGwm(UPDATED_CREATE_GWM)
            .modifyby(UPDATED_MODIFYBY)
            .modifybyid(UPDATED_MODIFYBYID)
            .modifyon(UPDATED_MODIFYON);

        restJfMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJfMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJfMaster))
            )
            .andExpect(status().isOk());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeUpdate);
        JfMaster testJfMaster = jfMasterList.get(jfMasterList.size() - 1);
        assertThat(testJfMaster.getZygs()).isEqualTo(UPDATED_ZYGS);
        assertThat(testJfMaster.getJe()).isEqualByComparingTo(UPDATED_JE);
        assertThat(testJfMaster.getShe()).isEqualByComparingTo(UPDATED_SHE);
        assertThat(testJfMaster.getShhje()).isEqualByComparingTo(UPDATED_SHHJE);
        assertThat(testJfMaster.getJmje()).isEqualByComparingTo(UPDATED_JMJE);
        assertThat(testJfMaster.getJmhje()).isEqualByComparingTo(UPDATED_JMHJE);
        assertThat(testJfMaster.getIdHw()).isEqualTo(UPDATED_ID_HW);
        assertThat(testJfMaster.getHtid()).isEqualTo(UPDATED_HTID);
        assertThat(testJfMaster.getMb()).isEqualTo(UPDATED_MB);
        assertThat(testJfMaster.getZywtrid()).isEqualTo(UPDATED_ZYWTRID);
        assertThat(testJfMaster.getMark1()).isEqualTo(UPDATED_MARK_1);
        assertThat(testJfMaster.getMark2()).isEqualTo(UPDATED_MARK_2);
        assertThat(testJfMaster.getCreateby()).isEqualTo(UPDATED_CREATEBY);
        assertThat(testJfMaster.getCreatebyid()).isEqualTo(UPDATED_CREATEBYID);
        assertThat(testJfMaster.getCreateon()).isEqualTo(UPDATED_CREATEON);
        assertThat(testJfMaster.getCreateGsid()).isEqualTo(UPDATED_CREATE_GSID);
        assertThat(testJfMaster.getCreateGsmch()).isEqualTo(UPDATED_CREATE_GSMCH);
        assertThat(testJfMaster.getCreateBmid()).isEqualTo(UPDATED_CREATE_BMID);
        assertThat(testJfMaster.getCreateBmm()).isEqualTo(UPDATED_CREATE_BMM);
        assertThat(testJfMaster.getCreateGwid()).isEqualTo(UPDATED_CREATE_GWID);
        assertThat(testJfMaster.getCreateGwm()).isEqualTo(UPDATED_CREATE_GWM);
        assertThat(testJfMaster.getModifyby()).isEqualTo(UPDATED_MODIFYBY);
        assertThat(testJfMaster.getModifybyid()).isEqualTo(UPDATED_MODIFYBYID);
        assertThat(testJfMaster.getModifyon()).isEqualTo(UPDATED_MODIFYON);
    }

    @Test
    @Transactional
    void patchNonExistingJfMaster() throws Exception {
        int databaseSizeBeforeUpdate = jfMasterRepository.findAll().size();
        jfMaster.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJfMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jfMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfMaster))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJfMaster() throws Exception {
        int databaseSizeBeforeUpdate = jfMasterRepository.findAll().size();
        jfMaster.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jfMaster))
            )
            .andExpect(status().isBadRequest());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJfMaster() throws Exception {
        int databaseSizeBeforeUpdate = jfMasterRepository.findAll().size();
        jfMaster.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJfMasterMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jfMaster)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JfMaster in the database
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJfMaster() throws Exception {
        // Initialize the database
        jfMasterRepository.saveAndFlush(jfMaster);

        int databaseSizeBeforeDelete = jfMasterRepository.findAll().size();

        // Delete the jfMaster
        restJfMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, jfMaster.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JfMaster> jfMasterList = jfMasterRepository.findAll();
        assertThat(jfMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
