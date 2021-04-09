package com.sysmart.rzbid.web.rest;

import com.sysmart.rzbid.domain.JfTank;
import com.sysmart.rzbid.repository.JfTankRepository;
import com.sysmart.rzbid.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sysmart.rzbid.domain.JfTank}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JfTankResource {

    private final Logger log = LoggerFactory.getLogger(JfTankResource.class);

    private static final String ENTITY_NAME = "jfTank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JfTankRepository jfTankRepository;

    public JfTankResource(JfTankRepository jfTankRepository) {
        this.jfTankRepository = jfTankRepository;
    }

    /**
     * {@code POST  /jf-tanks} : Create a new jfTank.
     *
     * @param jfTank the jfTank to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jfTank, or with status {@code 400 (Bad Request)} if the jfTank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jf-tanks")
    public ResponseEntity<JfTank> createJfTank(@RequestBody JfTank jfTank) throws URISyntaxException {
        log.debug("REST request to save JfTank : {}", jfTank);
        if (jfTank.getId() != null) {
            throw new BadRequestAlertException("A new jfTank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JfTank result = jfTankRepository.save(jfTank);
        return ResponseEntity
            .created(new URI("/api/jf-tanks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jf-tanks/:id} : Updates an existing jfTank.
     *
     * @param id the id of the jfTank to save.
     * @param jfTank the jfTank to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfTank,
     * or with status {@code 400 (Bad Request)} if the jfTank is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jfTank couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jf-tanks/{id}")
    public ResponseEntity<JfTank> updateJfTank(@PathVariable(value = "id", required = false) final Long id, @RequestBody JfTank jfTank)
        throws URISyntaxException {
        log.debug("REST request to update JfTank : {}, {}", id, jfTank);
        if (jfTank.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfTank.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfTankRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JfTank result = jfTankRepository.save(jfTank);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfTank.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jf-tanks/:id} : Partial updates given fields of an existing jfTank, field will ignore if it is null
     *
     * @param id the id of the jfTank to save.
     * @param jfTank the jfTank to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfTank,
     * or with status {@code 400 (Bad Request)} if the jfTank is not valid,
     * or with status {@code 404 (Not Found)} if the jfTank is not found,
     * or with status {@code 500 (Internal Server Error)} if the jfTank couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jf-tanks/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JfTank> partialUpdateJfTank(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JfTank jfTank
    ) throws URISyntaxException {
        log.debug("REST request to partial update JfTank partially : {}, {}", id, jfTank);
        if (jfTank.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfTank.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfTankRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JfTank> result = jfTankRepository
            .findById(jfTank.getId())
            .map(
                existingJfTank -> {
                    if (jfTank.getZone() != null) {
                        existingJfTank.setZone(jfTank.getZone());
                    }
                    if (jfTank.getCode() != null) {
                        existingJfTank.setCode(jfTank.getCode());
                    }

                    return existingJfTank;
                }
            )
            .map(jfTankRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfTank.getId().toString())
        );
    }

    /**
     * {@code GET  /jf-tanks} : get all the jfTanks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jfTanks in body.
     */
    @GetMapping("/jf-tanks")
    public List<JfTank> getAllJfTanks() {
        log.debug("REST request to get all JfTanks");
        return jfTankRepository.findAll();
    }

    /**
     * {@code GET  /jf-tanks/:id} : get the "id" jfTank.
     *
     * @param id the id of the jfTank to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jfTank, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jf-tanks/{id}")
    public ResponseEntity<JfTank> getJfTank(@PathVariable Long id) {
        log.debug("REST request to get JfTank : {}", id);
        Optional<JfTank> jfTank = jfTankRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jfTank);
    }

    /**
     * {@code DELETE  /jf-tanks/:id} : delete the "id" jfTank.
     *
     * @param id the id of the jfTank to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jf-tanks/{id}")
    public ResponseEntity<Void> deleteJfTank(@PathVariable Long id) {
        log.debug("REST request to delete JfTank : {}", id);
        jfTankRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
