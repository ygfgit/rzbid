package com.sysmart.rzbid.web.rest;

import com.sysmart.rzbid.domain.JfCargo;
import com.sysmart.rzbid.repository.JfCargoRepository;
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
 * REST controller for managing {@link com.sysmart.rzbid.domain.JfCargo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JfCargoResource {

    private final Logger log = LoggerFactory.getLogger(JfCargoResource.class);

    private static final String ENTITY_NAME = "jfCargo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JfCargoRepository jfCargoRepository;

    public JfCargoResource(JfCargoRepository jfCargoRepository) {
        this.jfCargoRepository = jfCargoRepository;
    }

    /**
     * {@code POST  /jf-cargos} : Create a new jfCargo.
     *
     * @param jfCargo the jfCargo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jfCargo, or with status {@code 400 (Bad Request)} if the jfCargo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jf-cargos")
    public ResponseEntity<JfCargo> createJfCargo(@RequestBody JfCargo jfCargo) throws URISyntaxException {
        log.debug("REST request to save JfCargo : {}", jfCargo);
        if (jfCargo.getId() != null) {
            throw new BadRequestAlertException("A new jfCargo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JfCargo result = jfCargoRepository.save(jfCargo);
        return ResponseEntity
            .created(new URI("/api/jf-cargos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jf-cargos/:id} : Updates an existing jfCargo.
     *
     * @param id the id of the jfCargo to save.
     * @param jfCargo the jfCargo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfCargo,
     * or with status {@code 400 (Bad Request)} if the jfCargo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jfCargo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jf-cargos/{id}")
    public ResponseEntity<JfCargo> updateJfCargo(@PathVariable(value = "id", required = false) final Long id, @RequestBody JfCargo jfCargo)
        throws URISyntaxException {
        log.debug("REST request to update JfCargo : {}, {}", id, jfCargo);
        if (jfCargo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfCargo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfCargoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JfCargo result = jfCargoRepository.save(jfCargo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfCargo.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jf-cargos/:id} : Partial updates given fields of an existing jfCargo, field will ignore if it is null
     *
     * @param id the id of the jfCargo to save.
     * @param jfCargo the jfCargo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfCargo,
     * or with status {@code 400 (Bad Request)} if the jfCargo is not valid,
     * or with status {@code 404 (Not Found)} if the jfCargo is not found,
     * or with status {@code 500 (Internal Server Error)} if the jfCargo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jf-cargos/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JfCargo> partialUpdateJfCargo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JfCargo jfCargo
    ) throws URISyntaxException {
        log.debug("REST request to partial update JfCargo partially : {}, {}", id, jfCargo);
        if (jfCargo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfCargo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfCargoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JfCargo> result = jfCargoRepository
            .findById(jfCargo.getId())
            .map(
                existingJfCargo -> {
                    if (jfCargo.getRq() != null) {
                        existingJfCargo.setRq(jfCargo.getRq());
                    }
                    if (jfCargo.getZygsdm() != null) {
                        existingJfCargo.setZygsdm(jfCargo.getZygsdm());
                    }
                    if (jfCargo.getHth() != null) {
                        existingJfCargo.setHth(jfCargo.getHth());
                    }
                    if (jfCargo.getZywtrid() != null) {
                        existingJfCargo.setZywtrid(jfCargo.getZywtrid());
                    }
                    if (jfCargo.getZywtr() != null) {
                        existingJfCargo.setZywtr(jfCargo.getZywtr());
                    }
                    if (jfCargo.getZhwchm() != null) {
                        existingJfCargo.setZhwchm(jfCargo.getZhwchm());
                    }
                    if (jfCargo.getHwmch() != null) {
                        existingJfCargo.setHwmch(jfCargo.getHwmch());
                    }
                    if (jfCargo.getGq() != null) {
                        existingJfCargo.setGq(jfCargo.getGq());
                    }
                    if (jfCargo.getShl() != null) {
                        existingJfCargo.setShl(jfCargo.getShl());
                    }
                    if (jfCargo.getSyl() != null) {
                        existingJfCargo.setSyl(jfCargo.getSyl());
                    }

                    return existingJfCargo;
                }
            )
            .map(jfCargoRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfCargo.getId().toString())
        );
    }

    /**
     * {@code GET  /jf-cargos} : get all the jfCargos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jfCargos in body.
     */
    @GetMapping("/jf-cargos")
    public List<JfCargo> getAllJfCargos() {
        log.debug("REST request to get all JfCargos");
        return jfCargoRepository.findAll();
    }

    /**
     * {@code GET  /jf-cargos/:id} : get the "id" jfCargo.
     *
     * @param id the id of the jfCargo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jfCargo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jf-cargos/{id}")
    public ResponseEntity<JfCargo> getJfCargo(@PathVariable Long id) {
        log.debug("REST request to get JfCargo : {}", id);
        Optional<JfCargo> jfCargo = jfCargoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jfCargo);
    }

    /**
     * {@code DELETE  /jf-cargos/:id} : delete the "id" jfCargo.
     *
     * @param id the id of the jfCargo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jf-cargos/{id}")
    public ResponseEntity<Void> deleteJfCargo(@PathVariable Long id) {
        log.debug("REST request to delete JfCargo : {}", id);
        jfCargoRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
