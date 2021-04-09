package com.sysmart.rzbid.web.rest;

import com.sysmart.rzbid.domain.JfWork;
import com.sysmart.rzbid.repository.JfWorkRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sysmart.rzbid.domain.JfWork}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JfWorkResource {

    private final Logger log = LoggerFactory.getLogger(JfWorkResource.class);

    private static final String ENTITY_NAME = "jfWork";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JfWorkRepository jfWorkRepository;

    public JfWorkResource(JfWorkRepository jfWorkRepository) {
        this.jfWorkRepository = jfWorkRepository;
    }

    /**
     * {@code POST  /jf-works} : Create a new jfWork.
     *
     * @param jfWork the jfWork to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jfWork, or with status {@code 400 (Bad Request)} if the jfWork has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jf-works")
    public ResponseEntity<JfWork> createJfWork(@RequestBody JfWork jfWork) throws URISyntaxException {
        log.debug("REST request to save JfWork : {}", jfWork);
        if (jfWork.getId() != null) {
            throw new BadRequestAlertException("A new jfWork cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JfWork result = jfWorkRepository.save(jfWork);
        return ResponseEntity
            .created(new URI("/api/jf-works/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jf-works/:id} : Updates an existing jfWork.
     *
     * @param id the id of the jfWork to save.
     * @param jfWork the jfWork to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfWork,
     * or with status {@code 400 (Bad Request)} if the jfWork is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jfWork couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jf-works/{id}")
    public ResponseEntity<JfWork> updateJfWork(@PathVariable(value = "id", required = false) final Long id, @RequestBody JfWork jfWork)
        throws URISyntaxException {
        log.debug("REST request to update JfWork : {}, {}", id, jfWork);
        if (jfWork.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfWork.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfWorkRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JfWork result = jfWorkRepository.save(jfWork);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfWork.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jf-works/:id} : Partial updates given fields of an existing jfWork, field will ignore if it is null
     *
     * @param id the id of the jfWork to save.
     * @param jfWork the jfWork to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfWork,
     * or with status {@code 400 (Bad Request)} if the jfWork is not valid,
     * or with status {@code 404 (Not Found)} if the jfWork is not found,
     * or with status {@code 500 (Internal Server Error)} if the jfWork couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jf-works/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JfWork> partialUpdateJfWork(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JfWork jfWork
    ) throws URISyntaxException {
        log.debug("REST request to partial update JfWork partially : {}, {}", id, jfWork);
        if (jfWork.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfWork.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfWorkRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JfWork> result = jfWorkRepository
            .findById(jfWork.getId())
            .map(
                existingJfWork -> {
                    if (jfWork.getHwmch() != null) {
                        existingJfWork.setHwmch(jfWork.getHwmch());
                    }
                    if (jfWork.getJhsl() != null) {
                        existingJfWork.setJhsl(jfWork.getJhsl());
                    }
                    if (jfWork.getBz() != null) {
                        existingJfWork.setBz(jfWork.getBz());
                    }

                    return existingJfWork;
                }
            )
            .map(jfWorkRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfWork.getId().toString())
        );
    }

    /**
     * {@code GET  /jf-works} : get all the jfWorks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jfWorks in body.
     */
    @GetMapping("/jf-works")
    public ResponseEntity<List<JfWork>> getAllJfWorks(Pageable pageable) {
        log.debug("REST request to get a page of JfWorks");
        Page<JfWork> page = jfWorkRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jf-works/:id} : get the "id" jfWork.
     *
     * @param id the id of the jfWork to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jfWork, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jf-works/{id}")
    public ResponseEntity<JfWork> getJfWork(@PathVariable Long id) {
        log.debug("REST request to get JfWork : {}", id);
        Optional<JfWork> jfWork = jfWorkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jfWork);
    }

    /**
     * {@code DELETE  /jf-works/:id} : delete the "id" jfWork.
     *
     * @param id the id of the jfWork to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jf-works/{id}")
    public ResponseEntity<Void> deleteJfWork(@PathVariable Long id) {
        log.debug("REST request to delete JfWork : {}", id);
        jfWorkRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
