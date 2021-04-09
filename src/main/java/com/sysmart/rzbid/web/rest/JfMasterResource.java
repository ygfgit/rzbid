package com.sysmart.rzbid.web.rest;

import com.sysmart.rzbid.domain.JfMaster;
import com.sysmart.rzbid.repository.JfMasterRepository;
import com.sysmart.rzbid.service.JfMasterService;
import com.sysmart.rzbid.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sysmart.rzbid.domain.JfMaster}.
 */
@RestController
@RequestMapping("/api")
public class JfMasterResource {

    private final Logger log = LoggerFactory.getLogger(JfMasterResource.class);

    private static final String ENTITY_NAME = "jfMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JfMasterService jfMasterService;

    private final JfMasterRepository jfMasterRepository;

    public JfMasterResource(JfMasterService jfMasterService, JfMasterRepository jfMasterRepository) {
        this.jfMasterService = jfMasterService;
        this.jfMasterRepository = jfMasterRepository;
    }

    /**
     * {@code POST  /jf-masters} : Create a new jfMaster.
     *
     * @param jfMaster the jfMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jfMaster, or with status {@code 400 (Bad Request)} if the jfMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jf-masters")
    public ResponseEntity<JfMaster> createJfMaster(@RequestBody JfMaster jfMaster) throws URISyntaxException {
        log.debug("REST request to save JfMaster : {}", jfMaster);
        if (jfMaster.getId() != null) {
            throw new BadRequestAlertException("A new jfMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JfMaster result = jfMasterService.save(jfMaster);
        return ResponseEntity
            .created(new URI("/api/jf-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jf-masters/:id} : Updates an existing jfMaster.
     *
     * @param id the id of the jfMaster to save.
     * @param jfMaster the jfMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfMaster,
     * or with status {@code 400 (Bad Request)} if the jfMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jfMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jf-masters/{id}")
    public ResponseEntity<JfMaster> updateJfMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JfMaster jfMaster
    ) throws URISyntaxException {
        log.debug("REST request to update JfMaster : {}, {}", id, jfMaster);
        if (jfMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JfMaster result = jfMasterService.save(jfMaster);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jf-masters/:id} : Partial updates given fields of an existing jfMaster, field will ignore if it is null
     *
     * @param id the id of the jfMaster to save.
     * @param jfMaster the jfMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfMaster,
     * or with status {@code 400 (Bad Request)} if the jfMaster is not valid,
     * or with status {@code 404 (Not Found)} if the jfMaster is not found,
     * or with status {@code 500 (Internal Server Error)} if the jfMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jf-masters/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JfMaster> partialUpdateJfMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JfMaster jfMaster
    ) throws URISyntaxException {
        log.debug("REST request to partial update JfMaster partially : {}, {}", id, jfMaster);
        if (jfMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JfMaster> result = jfMasterService.partialUpdate(jfMaster);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfMaster.getId().toString())
        );
    }

    /**
     * {@code GET  /jf-masters} : get all the jfMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jfMasters in body.
     */
    @GetMapping("/jf-masters")
    public ResponseEntity<List<JfMaster>> getAllJfMasters(Pageable pageable) {
        log.debug("REST request to get a page of JfMasters");
        Page<JfMaster> page = jfMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jf-masters/:id} : get the "id" jfMaster.
     *
     * @param id the id of the jfMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jfMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jf-masters/{id}")
    public ResponseEntity<JfMaster> getJfMaster(@PathVariable Long id) {
        log.debug("REST request to get JfMaster : {}", id);
        Optional<JfMaster> jfMaster = jfMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jfMaster);
    }

    /**
     * {@code DELETE  /jf-masters/:id} : delete the "id" jfMaster.
     *
     * @param id the id of the jfMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jf-masters/{id}")
    public ResponseEntity<Void> deleteJfMaster(@PathVariable Long id) {
        log.debug("REST request to delete JfMaster : {}", id);
        jfMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
