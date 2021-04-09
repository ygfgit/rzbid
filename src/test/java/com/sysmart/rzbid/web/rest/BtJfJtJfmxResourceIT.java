package com.sysmart.rzbid.web.rest;

import static com.sysmart.rzbid.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sysmart.rzbid.IntegrationTest;
import com.sysmart.rzbid.domain.BtJfJtJfmx;
import com.sysmart.rzbid.repository.BtJfJtJfmxRepository;
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
 * Integration tests for the {@link BtJfJtJfmxResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BtJfJtJfmxResourceIT {

    private static final Long DEFAULT_ZYGS = 1L;
    private static final Long UPDATED_ZYGS = 2L;

    private static final Long DEFAULT_ZYETRID = 1L;
    private static final Long UPDATED_ZYETRID = 2L;

    private static final String DEFAULT_FLMCH = "AAAAAAAAAA";
    private static final String UPDATED_FLMCH = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_FL = new BigDecimal(1);
    private static final BigDecimal UPDATED_FL = new BigDecimal(2);

    private static final Double DEFAULT_SHL = 1D;
    private static final Double UPDATED_SHL = 2D;

    private static final BigDecimal DEFAULT_JE = new BigDecimal(1);
    private static final BigDecimal UPDATED_JE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SHLV = new BigDecimal(1);
    private static final BigDecimal UPDATED_SHLV = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SHE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SHE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SHHJE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SHHJE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_JMJE = new BigDecimal(1);
    private static final BigDecimal UPDATED_JMJE = new BigDecimal(2);

    private static final LocalDate DEFAULT_STARTD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTD = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDD = LocalDate.now(ZoneId.systemDefault());

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

    private static final Long DEFAULT_CREATE_GWID = 1L;
    private static final Long UPDATED_CREATE_GWID = 2L;

    private static final String DEFAULT_CREATE_GWM = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_GWM = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFYBY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFYBY = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFYBYID = 1L;
    private static final Long UPDATED_MODIFYBYID = 2L;

    private static final LocalDate DEFAULT_MODIFYON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MODIFYON = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/bt-jf-jt-jfmxes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BtJfJtJfmxRepository btJfJtJfmxRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBtJfJtJfmxMockMvc;

    private BtJfJtJfmx btJfJtJfmx;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BtJfJtJfmx createEntity(EntityManager em) {
        BtJfJtJfmx btJfJtJfmx = new BtJfJtJfmx()
            .zygs(DEFAULT_ZYGS)
            .zyetrid(DEFAULT_ZYETRID)
            .flmch(DEFAULT_FLMCH)
            .fl(DEFAULT_FL)
            .shl(DEFAULT_SHL)
            .je(DEFAULT_JE)
            .shlv(DEFAULT_SHLV)
            .she(DEFAULT_SHE)
            .shhje(DEFAULT_SHHJE)
            .jmje(DEFAULT_JMJE)
            .startd(DEFAULT_STARTD)
            .endd(DEFAULT_ENDD)
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
        return btJfJtJfmx;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BtJfJtJfmx createUpdatedEntity(EntityManager em) {
        BtJfJtJfmx btJfJtJfmx = new BtJfJtJfmx()
            .zygs(UPDATED_ZYGS)
            .zyetrid(UPDATED_ZYETRID)
            .flmch(UPDATED_FLMCH)
            .fl(UPDATED_FL)
            .shl(UPDATED_SHL)
            .je(UPDATED_JE)
            .shlv(UPDATED_SHLV)
            .she(UPDATED_SHE)
            .shhje(UPDATED_SHHJE)
            .jmje(UPDATED_JMJE)
            .startd(UPDATED_STARTD)
            .endd(UPDATED_ENDD)
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
        return btJfJtJfmx;
    }

    @BeforeEach
    public void initTest() {
        btJfJtJfmx = createEntity(em);
    }

    @Test
    @Transactional
    void createBtJfJtJfmx() throws Exception {
        int databaseSizeBeforeCreate = btJfJtJfmxRepository.findAll().size();
        // Create the BtJfJtJfmx
        restBtJfJtJfmxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(btJfJtJfmx)))
            .andExpect(status().isCreated());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeCreate + 1);
        BtJfJtJfmx testBtJfJtJfmx = btJfJtJfmxList.get(btJfJtJfmxList.size() - 1);
        assertThat(testBtJfJtJfmx.getZygs()).isEqualTo(DEFAULT_ZYGS);
        assertThat(testBtJfJtJfmx.getZyetrid()).isEqualTo(DEFAULT_ZYETRID);
        assertThat(testBtJfJtJfmx.getFlmch()).isEqualTo(DEFAULT_FLMCH);
        assertThat(testBtJfJtJfmx.getFl()).isEqualByComparingTo(DEFAULT_FL);
        assertThat(testBtJfJtJfmx.getShl()).isEqualTo(DEFAULT_SHL);
        assertThat(testBtJfJtJfmx.getJe()).isEqualByComparingTo(DEFAULT_JE);
        assertThat(testBtJfJtJfmx.getShlv()).isEqualByComparingTo(DEFAULT_SHLV);
        assertThat(testBtJfJtJfmx.getShe()).isEqualByComparingTo(DEFAULT_SHE);
        assertThat(testBtJfJtJfmx.getShhje()).isEqualByComparingTo(DEFAULT_SHHJE);
        assertThat(testBtJfJtJfmx.getJmje()).isEqualByComparingTo(DEFAULT_JMJE);
        assertThat(testBtJfJtJfmx.getStartd()).isEqualTo(DEFAULT_STARTD);
        assertThat(testBtJfJtJfmx.getEndd()).isEqualTo(DEFAULT_ENDD);
        assertThat(testBtJfJtJfmx.getMark1()).isEqualTo(DEFAULT_MARK_1);
        assertThat(testBtJfJtJfmx.getMark2()).isEqualTo(DEFAULT_MARK_2);
        assertThat(testBtJfJtJfmx.getCreateby()).isEqualTo(DEFAULT_CREATEBY);
        assertThat(testBtJfJtJfmx.getCreatebyid()).isEqualTo(DEFAULT_CREATEBYID);
        assertThat(testBtJfJtJfmx.getCreateon()).isEqualTo(DEFAULT_CREATEON);
        assertThat(testBtJfJtJfmx.getCreateGsid()).isEqualTo(DEFAULT_CREATE_GSID);
        assertThat(testBtJfJtJfmx.getCreateGsmch()).isEqualTo(DEFAULT_CREATE_GSMCH);
        assertThat(testBtJfJtJfmx.getCreateBmid()).isEqualTo(DEFAULT_CREATE_BMID);
        assertThat(testBtJfJtJfmx.getCreateBmm()).isEqualTo(DEFAULT_CREATE_BMM);
        assertThat(testBtJfJtJfmx.getCreateGwid()).isEqualTo(DEFAULT_CREATE_GWID);
        assertThat(testBtJfJtJfmx.getCreateGwm()).isEqualTo(DEFAULT_CREATE_GWM);
        assertThat(testBtJfJtJfmx.getModifyby()).isEqualTo(DEFAULT_MODIFYBY);
        assertThat(testBtJfJtJfmx.getModifybyid()).isEqualTo(DEFAULT_MODIFYBYID);
        assertThat(testBtJfJtJfmx.getModifyon()).isEqualTo(DEFAULT_MODIFYON);
    }

    @Test
    @Transactional
    void createBtJfJtJfmxWithExistingId() throws Exception {
        // Create the BtJfJtJfmx with an existing ID
        btJfJtJfmx.setId(1L);

        int databaseSizeBeforeCreate = btJfJtJfmxRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBtJfJtJfmxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(btJfJtJfmx)))
            .andExpect(status().isBadRequest());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBtJfJtJfmxes() throws Exception {
        // Initialize the database
        btJfJtJfmxRepository.saveAndFlush(btJfJtJfmx);

        // Get all the btJfJtJfmxList
        restBtJfJtJfmxMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(btJfJtJfmx.getId().intValue())))
            .andExpect(jsonPath("$.[*].zygs").value(hasItem(DEFAULT_ZYGS.intValue())))
            .andExpect(jsonPath("$.[*].zyetrid").value(hasItem(DEFAULT_ZYETRID.intValue())))
            .andExpect(jsonPath("$.[*].flmch").value(hasItem(DEFAULT_FLMCH)))
            .andExpect(jsonPath("$.[*].fl").value(hasItem(sameNumber(DEFAULT_FL))))
            .andExpect(jsonPath("$.[*].shl").value(hasItem(DEFAULT_SHL.doubleValue())))
            .andExpect(jsonPath("$.[*].je").value(hasItem(sameNumber(DEFAULT_JE))))
            .andExpect(jsonPath("$.[*].shlv").value(hasItem(sameNumber(DEFAULT_SHLV))))
            .andExpect(jsonPath("$.[*].she").value(hasItem(sameNumber(DEFAULT_SHE))))
            .andExpect(jsonPath("$.[*].shhje").value(hasItem(sameNumber(DEFAULT_SHHJE))))
            .andExpect(jsonPath("$.[*].jmje").value(hasItem(sameNumber(DEFAULT_JMJE))))
            .andExpect(jsonPath("$.[*].startd").value(hasItem(DEFAULT_STARTD.toString())))
            .andExpect(jsonPath("$.[*].endd").value(hasItem(DEFAULT_ENDD.toString())))
            .andExpect(jsonPath("$.[*].mark1").value(hasItem(DEFAULT_MARK_1)))
            .andExpect(jsonPath("$.[*].mark2").value(hasItem(DEFAULT_MARK_2)))
            .andExpect(jsonPath("$.[*].createby").value(hasItem(DEFAULT_CREATEBY)))
            .andExpect(jsonPath("$.[*].createbyid").value(hasItem(DEFAULT_CREATEBYID.intValue())))
            .andExpect(jsonPath("$.[*].createon").value(hasItem(DEFAULT_CREATEON.toString())))
            .andExpect(jsonPath("$.[*].createGsid").value(hasItem(DEFAULT_CREATE_GSID)))
            .andExpect(jsonPath("$.[*].createGsmch").value(hasItem(DEFAULT_CREATE_GSMCH)))
            .andExpect(jsonPath("$.[*].createBmid").value(hasItem(DEFAULT_CREATE_BMID.intValue())))
            .andExpect(jsonPath("$.[*].createBmm").value(hasItem(DEFAULT_CREATE_BMM)))
            .andExpect(jsonPath("$.[*].createGwid").value(hasItem(DEFAULT_CREATE_GWID.intValue())))
            .andExpect(jsonPath("$.[*].createGwm").value(hasItem(DEFAULT_CREATE_GWM)))
            .andExpect(jsonPath("$.[*].modifyby").value(hasItem(DEFAULT_MODIFYBY)))
            .andExpect(jsonPath("$.[*].modifybyid").value(hasItem(DEFAULT_MODIFYBYID.intValue())))
            .andExpect(jsonPath("$.[*].modifyon").value(hasItem(DEFAULT_MODIFYON.toString())));
    }

    @Test
    @Transactional
    void getBtJfJtJfmx() throws Exception {
        // Initialize the database
        btJfJtJfmxRepository.saveAndFlush(btJfJtJfmx);

        // Get the btJfJtJfmx
        restBtJfJtJfmxMockMvc
            .perform(get(ENTITY_API_URL_ID, btJfJtJfmx.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(btJfJtJfmx.getId().intValue()))
            .andExpect(jsonPath("$.zygs").value(DEFAULT_ZYGS.intValue()))
            .andExpect(jsonPath("$.zyetrid").value(DEFAULT_ZYETRID.intValue()))
            .andExpect(jsonPath("$.flmch").value(DEFAULT_FLMCH))
            .andExpect(jsonPath("$.fl").value(sameNumber(DEFAULT_FL)))
            .andExpect(jsonPath("$.shl").value(DEFAULT_SHL.doubleValue()))
            .andExpect(jsonPath("$.je").value(sameNumber(DEFAULT_JE)))
            .andExpect(jsonPath("$.shlv").value(sameNumber(DEFAULT_SHLV)))
            .andExpect(jsonPath("$.she").value(sameNumber(DEFAULT_SHE)))
            .andExpect(jsonPath("$.shhje").value(sameNumber(DEFAULT_SHHJE)))
            .andExpect(jsonPath("$.jmje").value(sameNumber(DEFAULT_JMJE)))
            .andExpect(jsonPath("$.startd").value(DEFAULT_STARTD.toString()))
            .andExpect(jsonPath("$.endd").value(DEFAULT_ENDD.toString()))
            .andExpect(jsonPath("$.mark1").value(DEFAULT_MARK_1))
            .andExpect(jsonPath("$.mark2").value(DEFAULT_MARK_2))
            .andExpect(jsonPath("$.createby").value(DEFAULT_CREATEBY))
            .andExpect(jsonPath("$.createbyid").value(DEFAULT_CREATEBYID.intValue()))
            .andExpect(jsonPath("$.createon").value(DEFAULT_CREATEON.toString()))
            .andExpect(jsonPath("$.createGsid").value(DEFAULT_CREATE_GSID))
            .andExpect(jsonPath("$.createGsmch").value(DEFAULT_CREATE_GSMCH))
            .andExpect(jsonPath("$.createBmid").value(DEFAULT_CREATE_BMID.intValue()))
            .andExpect(jsonPath("$.createBmm").value(DEFAULT_CREATE_BMM))
            .andExpect(jsonPath("$.createGwid").value(DEFAULT_CREATE_GWID.intValue()))
            .andExpect(jsonPath("$.createGwm").value(DEFAULT_CREATE_GWM))
            .andExpect(jsonPath("$.modifyby").value(DEFAULT_MODIFYBY))
            .andExpect(jsonPath("$.modifybyid").value(DEFAULT_MODIFYBYID.intValue()))
            .andExpect(jsonPath("$.modifyon").value(DEFAULT_MODIFYON.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBtJfJtJfmx() throws Exception {
        // Get the btJfJtJfmx
        restBtJfJtJfmxMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBtJfJtJfmx() throws Exception {
        // Initialize the database
        btJfJtJfmxRepository.saveAndFlush(btJfJtJfmx);

        int databaseSizeBeforeUpdate = btJfJtJfmxRepository.findAll().size();

        // Update the btJfJtJfmx
        BtJfJtJfmx updatedBtJfJtJfmx = btJfJtJfmxRepository.findById(btJfJtJfmx.getId()).get();
        // Disconnect from session so that the updates on updatedBtJfJtJfmx are not directly saved in db
        em.detach(updatedBtJfJtJfmx);
        updatedBtJfJtJfmx
            .zygs(UPDATED_ZYGS)
            .zyetrid(UPDATED_ZYETRID)
            .flmch(UPDATED_FLMCH)
            .fl(UPDATED_FL)
            .shl(UPDATED_SHL)
            .je(UPDATED_JE)
            .shlv(UPDATED_SHLV)
            .she(UPDATED_SHE)
            .shhje(UPDATED_SHHJE)
            .jmje(UPDATED_JMJE)
            .startd(UPDATED_STARTD)
            .endd(UPDATED_ENDD)
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

        restBtJfJtJfmxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBtJfJtJfmx.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBtJfJtJfmx))
            )
            .andExpect(status().isOk());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeUpdate);
        BtJfJtJfmx testBtJfJtJfmx = btJfJtJfmxList.get(btJfJtJfmxList.size() - 1);
        assertThat(testBtJfJtJfmx.getZygs()).isEqualTo(UPDATED_ZYGS);
        assertThat(testBtJfJtJfmx.getZyetrid()).isEqualTo(UPDATED_ZYETRID);
        assertThat(testBtJfJtJfmx.getFlmch()).isEqualTo(UPDATED_FLMCH);
        assertThat(testBtJfJtJfmx.getFl()).isEqualTo(UPDATED_FL);
        assertThat(testBtJfJtJfmx.getShl()).isEqualTo(UPDATED_SHL);
        assertThat(testBtJfJtJfmx.getJe()).isEqualTo(UPDATED_JE);
        assertThat(testBtJfJtJfmx.getShlv()).isEqualTo(UPDATED_SHLV);
        assertThat(testBtJfJtJfmx.getShe()).isEqualTo(UPDATED_SHE);
        assertThat(testBtJfJtJfmx.getShhje()).isEqualTo(UPDATED_SHHJE);
        assertThat(testBtJfJtJfmx.getJmje()).isEqualTo(UPDATED_JMJE);
        assertThat(testBtJfJtJfmx.getStartd()).isEqualTo(UPDATED_STARTD);
        assertThat(testBtJfJtJfmx.getEndd()).isEqualTo(UPDATED_ENDD);
        assertThat(testBtJfJtJfmx.getMark1()).isEqualTo(UPDATED_MARK_1);
        assertThat(testBtJfJtJfmx.getMark2()).isEqualTo(UPDATED_MARK_2);
        assertThat(testBtJfJtJfmx.getCreateby()).isEqualTo(UPDATED_CREATEBY);
        assertThat(testBtJfJtJfmx.getCreatebyid()).isEqualTo(UPDATED_CREATEBYID);
        assertThat(testBtJfJtJfmx.getCreateon()).isEqualTo(UPDATED_CREATEON);
        assertThat(testBtJfJtJfmx.getCreateGsid()).isEqualTo(UPDATED_CREATE_GSID);
        assertThat(testBtJfJtJfmx.getCreateGsmch()).isEqualTo(UPDATED_CREATE_GSMCH);
        assertThat(testBtJfJtJfmx.getCreateBmid()).isEqualTo(UPDATED_CREATE_BMID);
        assertThat(testBtJfJtJfmx.getCreateBmm()).isEqualTo(UPDATED_CREATE_BMM);
        assertThat(testBtJfJtJfmx.getCreateGwid()).isEqualTo(UPDATED_CREATE_GWID);
        assertThat(testBtJfJtJfmx.getCreateGwm()).isEqualTo(UPDATED_CREATE_GWM);
        assertThat(testBtJfJtJfmx.getModifyby()).isEqualTo(UPDATED_MODIFYBY);
        assertThat(testBtJfJtJfmx.getModifybyid()).isEqualTo(UPDATED_MODIFYBYID);
        assertThat(testBtJfJtJfmx.getModifyon()).isEqualTo(UPDATED_MODIFYON);
    }

    @Test
    @Transactional
    void putNonExistingBtJfJtJfmx() throws Exception {
        int databaseSizeBeforeUpdate = btJfJtJfmxRepository.findAll().size();
        btJfJtJfmx.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBtJfJtJfmxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, btJfJtJfmx.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(btJfJtJfmx))
            )
            .andExpect(status().isBadRequest());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBtJfJtJfmx() throws Exception {
        int databaseSizeBeforeUpdate = btJfJtJfmxRepository.findAll().size();
        btJfJtJfmx.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBtJfJtJfmxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(btJfJtJfmx))
            )
            .andExpect(status().isBadRequest());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBtJfJtJfmx() throws Exception {
        int databaseSizeBeforeUpdate = btJfJtJfmxRepository.findAll().size();
        btJfJtJfmx.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBtJfJtJfmxMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(btJfJtJfmx)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBtJfJtJfmxWithPatch() throws Exception {
        // Initialize the database
        btJfJtJfmxRepository.saveAndFlush(btJfJtJfmx);

        int databaseSizeBeforeUpdate = btJfJtJfmxRepository.findAll().size();

        // Update the btJfJtJfmx using partial update
        BtJfJtJfmx partialUpdatedBtJfJtJfmx = new BtJfJtJfmx();
        partialUpdatedBtJfJtJfmx.setId(btJfJtJfmx.getId());

        partialUpdatedBtJfJtJfmx
            .zyetrid(UPDATED_ZYETRID)
            .shlv(UPDATED_SHLV)
            .jmje(UPDATED_JMJE)
            .startd(UPDATED_STARTD)
            .mark2(UPDATED_MARK_2)
            .createby(UPDATED_CREATEBY)
            .createbyid(UPDATED_CREATEBYID)
            .createon(UPDATED_CREATEON)
            .createGsmch(UPDATED_CREATE_GSMCH)
            .createBmid(UPDATED_CREATE_BMID)
            .createBmm(UPDATED_CREATE_BMM)
            .modifybyid(UPDATED_MODIFYBYID)
            .modifyon(UPDATED_MODIFYON);

        restBtJfJtJfmxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBtJfJtJfmx.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBtJfJtJfmx))
            )
            .andExpect(status().isOk());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeUpdate);
        BtJfJtJfmx testBtJfJtJfmx = btJfJtJfmxList.get(btJfJtJfmxList.size() - 1);
        assertThat(testBtJfJtJfmx.getZygs()).isEqualTo(DEFAULT_ZYGS);
        assertThat(testBtJfJtJfmx.getZyetrid()).isEqualTo(UPDATED_ZYETRID);
        assertThat(testBtJfJtJfmx.getFlmch()).isEqualTo(DEFAULT_FLMCH);
        assertThat(testBtJfJtJfmx.getFl()).isEqualByComparingTo(DEFAULT_FL);
        assertThat(testBtJfJtJfmx.getShl()).isEqualTo(DEFAULT_SHL);
        assertThat(testBtJfJtJfmx.getJe()).isEqualByComparingTo(DEFAULT_JE);
        assertThat(testBtJfJtJfmx.getShlv()).isEqualByComparingTo(UPDATED_SHLV);
        assertThat(testBtJfJtJfmx.getShe()).isEqualByComparingTo(DEFAULT_SHE);
        assertThat(testBtJfJtJfmx.getShhje()).isEqualByComparingTo(DEFAULT_SHHJE);
        assertThat(testBtJfJtJfmx.getJmje()).isEqualByComparingTo(UPDATED_JMJE);
        assertThat(testBtJfJtJfmx.getStartd()).isEqualTo(UPDATED_STARTD);
        assertThat(testBtJfJtJfmx.getEndd()).isEqualTo(DEFAULT_ENDD);
        assertThat(testBtJfJtJfmx.getMark1()).isEqualTo(DEFAULT_MARK_1);
        assertThat(testBtJfJtJfmx.getMark2()).isEqualTo(UPDATED_MARK_2);
        assertThat(testBtJfJtJfmx.getCreateby()).isEqualTo(UPDATED_CREATEBY);
        assertThat(testBtJfJtJfmx.getCreatebyid()).isEqualTo(UPDATED_CREATEBYID);
        assertThat(testBtJfJtJfmx.getCreateon()).isEqualTo(UPDATED_CREATEON);
        assertThat(testBtJfJtJfmx.getCreateGsid()).isEqualTo(DEFAULT_CREATE_GSID);
        assertThat(testBtJfJtJfmx.getCreateGsmch()).isEqualTo(UPDATED_CREATE_GSMCH);
        assertThat(testBtJfJtJfmx.getCreateBmid()).isEqualTo(UPDATED_CREATE_BMID);
        assertThat(testBtJfJtJfmx.getCreateBmm()).isEqualTo(UPDATED_CREATE_BMM);
        assertThat(testBtJfJtJfmx.getCreateGwid()).isEqualTo(DEFAULT_CREATE_GWID);
        assertThat(testBtJfJtJfmx.getCreateGwm()).isEqualTo(DEFAULT_CREATE_GWM);
        assertThat(testBtJfJtJfmx.getModifyby()).isEqualTo(DEFAULT_MODIFYBY);
        assertThat(testBtJfJtJfmx.getModifybyid()).isEqualTo(UPDATED_MODIFYBYID);
        assertThat(testBtJfJtJfmx.getModifyon()).isEqualTo(UPDATED_MODIFYON);
    }

    @Test
    @Transactional
    void fullUpdateBtJfJtJfmxWithPatch() throws Exception {
        // Initialize the database
        btJfJtJfmxRepository.saveAndFlush(btJfJtJfmx);

        int databaseSizeBeforeUpdate = btJfJtJfmxRepository.findAll().size();

        // Update the btJfJtJfmx using partial update
        BtJfJtJfmx partialUpdatedBtJfJtJfmx = new BtJfJtJfmx();
        partialUpdatedBtJfJtJfmx.setId(btJfJtJfmx.getId());

        partialUpdatedBtJfJtJfmx
            .zygs(UPDATED_ZYGS)
            .zyetrid(UPDATED_ZYETRID)
            .flmch(UPDATED_FLMCH)
            .fl(UPDATED_FL)
            .shl(UPDATED_SHL)
            .je(UPDATED_JE)
            .shlv(UPDATED_SHLV)
            .she(UPDATED_SHE)
            .shhje(UPDATED_SHHJE)
            .jmje(UPDATED_JMJE)
            .startd(UPDATED_STARTD)
            .endd(UPDATED_ENDD)
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

        restBtJfJtJfmxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBtJfJtJfmx.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBtJfJtJfmx))
            )
            .andExpect(status().isOk());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeUpdate);
        BtJfJtJfmx testBtJfJtJfmx = btJfJtJfmxList.get(btJfJtJfmxList.size() - 1);
        assertThat(testBtJfJtJfmx.getZygs()).isEqualTo(UPDATED_ZYGS);
        assertThat(testBtJfJtJfmx.getZyetrid()).isEqualTo(UPDATED_ZYETRID);
        assertThat(testBtJfJtJfmx.getFlmch()).isEqualTo(UPDATED_FLMCH);
        assertThat(testBtJfJtJfmx.getFl()).isEqualByComparingTo(UPDATED_FL);
        assertThat(testBtJfJtJfmx.getShl()).isEqualTo(UPDATED_SHL);
        assertThat(testBtJfJtJfmx.getJe()).isEqualByComparingTo(UPDATED_JE);
        assertThat(testBtJfJtJfmx.getShlv()).isEqualByComparingTo(UPDATED_SHLV);
        assertThat(testBtJfJtJfmx.getShe()).isEqualByComparingTo(UPDATED_SHE);
        assertThat(testBtJfJtJfmx.getShhje()).isEqualByComparingTo(UPDATED_SHHJE);
        assertThat(testBtJfJtJfmx.getJmje()).isEqualByComparingTo(UPDATED_JMJE);
        assertThat(testBtJfJtJfmx.getStartd()).isEqualTo(UPDATED_STARTD);
        assertThat(testBtJfJtJfmx.getEndd()).isEqualTo(UPDATED_ENDD);
        assertThat(testBtJfJtJfmx.getMark1()).isEqualTo(UPDATED_MARK_1);
        assertThat(testBtJfJtJfmx.getMark2()).isEqualTo(UPDATED_MARK_2);
        assertThat(testBtJfJtJfmx.getCreateby()).isEqualTo(UPDATED_CREATEBY);
        assertThat(testBtJfJtJfmx.getCreatebyid()).isEqualTo(UPDATED_CREATEBYID);
        assertThat(testBtJfJtJfmx.getCreateon()).isEqualTo(UPDATED_CREATEON);
        assertThat(testBtJfJtJfmx.getCreateGsid()).isEqualTo(UPDATED_CREATE_GSID);
        assertThat(testBtJfJtJfmx.getCreateGsmch()).isEqualTo(UPDATED_CREATE_GSMCH);
        assertThat(testBtJfJtJfmx.getCreateBmid()).isEqualTo(UPDATED_CREATE_BMID);
        assertThat(testBtJfJtJfmx.getCreateBmm()).isEqualTo(UPDATED_CREATE_BMM);
        assertThat(testBtJfJtJfmx.getCreateGwid()).isEqualTo(UPDATED_CREATE_GWID);
        assertThat(testBtJfJtJfmx.getCreateGwm()).isEqualTo(UPDATED_CREATE_GWM);
        assertThat(testBtJfJtJfmx.getModifyby()).isEqualTo(UPDATED_MODIFYBY);
        assertThat(testBtJfJtJfmx.getModifybyid()).isEqualTo(UPDATED_MODIFYBYID);
        assertThat(testBtJfJtJfmx.getModifyon()).isEqualTo(UPDATED_MODIFYON);
    }

    @Test
    @Transactional
    void patchNonExistingBtJfJtJfmx() throws Exception {
        int databaseSizeBeforeUpdate = btJfJtJfmxRepository.findAll().size();
        btJfJtJfmx.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBtJfJtJfmxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, btJfJtJfmx.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(btJfJtJfmx))
            )
            .andExpect(status().isBadRequest());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBtJfJtJfmx() throws Exception {
        int databaseSizeBeforeUpdate = btJfJtJfmxRepository.findAll().size();
        btJfJtJfmx.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBtJfJtJfmxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(btJfJtJfmx))
            )
            .andExpect(status().isBadRequest());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBtJfJtJfmx() throws Exception {
        int databaseSizeBeforeUpdate = btJfJtJfmxRepository.findAll().size();
        btJfJtJfmx.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBtJfJtJfmxMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(btJfJtJfmx))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BtJfJtJfmx in the database
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBtJfJtJfmx() throws Exception {
        // Initialize the database
        btJfJtJfmxRepository.saveAndFlush(btJfJtJfmx);

        int databaseSizeBeforeDelete = btJfJtJfmxRepository.findAll().size();

        // Delete the btJfJtJfmx
        restBtJfJtJfmxMockMvc
            .perform(delete(ENTITY_API_URL_ID, btJfJtJfmx.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BtJfJtJfmx> btJfJtJfmxList = btJfJtJfmxRepository.findAll();
        assertThat(btJfJtJfmxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
