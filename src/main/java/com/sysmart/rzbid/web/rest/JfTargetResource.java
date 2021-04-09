package com.sysmart.rzbid.web.rest;

import com.sysmart.rzbid.domain.JfTarget;
import com.sysmart.rzbid.repository.JfTargetRepository;
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
 * REST controller for managing {@link com.sysmart.rzbid.domain.JfTarget}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JfTargetResource {

    private final Logger log = LoggerFactory.getLogger(JfTargetResource.class);

    private static final String ENTITY_NAME = "jfTarget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JfTargetRepository jfTargetRepository;

    public JfTargetResource(JfTargetRepository jfTargetRepository) {
        this.jfTargetRepository = jfTargetRepository;
    }

    /**
     * {@code POST  /jf-targets} : Create a new jfTarget.
     *
     * @param jfTarget the jfTarget to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jfTarget, or with status {@code 400 (Bad Request)} if the jfTarget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jf-targets")
    public ResponseEntity<JfTarget> createJfTarget(@RequestBody JfTarget jfTarget) throws URISyntaxException {
        log.debug("REST request to save JfTarget : {}", jfTarget);
        if (jfTarget.getId() != null) {
            throw new BadRequestAlertException("A new jfTarget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JfTarget result = jfTargetRepository.save(jfTarget);
        return ResponseEntity
            .created(new URI("/api/jf-targets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jf-targets/:id} : Updates an existing jfTarget.
     *
     * @param id the id of the jfTarget to save.
     * @param jfTarget the jfTarget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfTarget,
     * or with status {@code 400 (Bad Request)} if the jfTarget is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jfTarget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jf-targets/{id}")
    public ResponseEntity<JfTarget> updateJfTarget(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JfTarget jfTarget
    ) throws URISyntaxException {
        log.debug("REST request to update JfTarget : {}, {}", id, jfTarget);
        if (jfTarget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfTarget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfTargetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JfTarget result = jfTargetRepository.save(jfTarget);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfTarget.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jf-targets/:id} : Partial updates given fields of an existing jfTarget, field will ignore if it is null
     *
     * @param id the id of the jfTarget to save.
     * @param jfTarget the jfTarget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfTarget,
     * or with status {@code 400 (Bad Request)} if the jfTarget is not valid,
     * or with status {@code 404 (Not Found)} if the jfTarget is not found,
     * or with status {@code 500 (Internal Server Error)} if the jfTarget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jf-targets/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JfTarget> partialUpdateJfTarget(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JfTarget jfTarget
    ) throws URISyntaxException {
        log.debug("REST request to partial update JfTarget partially : {}, {}", id, jfTarget);
        if (jfTarget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfTarget.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfTargetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JfTarget> result = jfTargetRepository
            .findById(jfTarget.getId())
            .map(
                existingJfTarget -> {
                    if (jfTarget.getFlzl() != null) {
                        existingJfTarget.setFlzl(jfTarget.getFlzl());
                    }

                    return existingJfTarget;
                }
            )
            .map(jfTargetRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfTarget.getId().toString())
        );
    }

    /**
     * {@code GET  /jf-targets} : get all the jfTargets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jfTargets in body.
     */
    @GetMapping("/jf-targets")
    public List<JfTarget> getAllJfTargets() {
        log.debug("REST request to get all JfTargets");
        return jfTargetRepository.findAll();
    }

    /**
     * {@code GET  /jf-targets/:id} : get the "id" jfTarget.
     *
     * @param id the id of the jfTarget to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jfTarget, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jf-targets/{id}")
    public ResponseEntity<JfTarget> getJfTarget(@PathVariable Long id) {
        log.debug("REST request to get JfTarget : {}", id);
        Optional<JfTarget> jfTarget = jfTargetRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jfTarget);
    }

    /**
     * {@code DELETE  /jf-targets/:id} : delete the "id" jfTarget.
     *
     * @param id the id of the jfTarget to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jf-targets/{id}")
    public ResponseEntity<Void> deleteJfTarget(@PathVariable Long id) {
        log.debug("REST request to delete JfTarget : {}", id);
        jfTargetRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
