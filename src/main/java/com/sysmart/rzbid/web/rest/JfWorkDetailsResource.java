package com.sysmart.rzbid.web.rest;

import com.sysmart.rzbid.domain.JfWorkDetails;
import com.sysmart.rzbid.repository.JfWorkDetailsRepository;
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
 * REST controller for managing {@link com.sysmart.rzbid.domain.JfWorkDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JfWorkDetailsResource {

    private final Logger log = LoggerFactory.getLogger(JfWorkDetailsResource.class);

    private static final String ENTITY_NAME = "jfWorkDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JfWorkDetailsRepository jfWorkDetailsRepository;

    public JfWorkDetailsResource(JfWorkDetailsRepository jfWorkDetailsRepository) {
        this.jfWorkDetailsRepository = jfWorkDetailsRepository;
    }

    /**
     * {@code POST  /jf-work-details} : Create a new jfWorkDetails.
     *
     * @param jfWorkDetails the jfWorkDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jfWorkDetails, or with status {@code 400 (Bad Request)} if the jfWorkDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jf-work-details")
    public ResponseEntity<JfWorkDetails> createJfWorkDetails(@RequestBody JfWorkDetails jfWorkDetails) throws URISyntaxException {
        log.debug("REST request to save JfWorkDetails : {}", jfWorkDetails);
        if (jfWorkDetails.getId() != null) {
            throw new BadRequestAlertException("A new jfWorkDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JfWorkDetails result = jfWorkDetailsRepository.save(jfWorkDetails);
        return ResponseEntity
            .created(new URI("/api/jf-work-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jf-work-details/:id} : Updates an existing jfWorkDetails.
     *
     * @param id the id of the jfWorkDetails to save.
     * @param jfWorkDetails the jfWorkDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfWorkDetails,
     * or with status {@code 400 (Bad Request)} if the jfWorkDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jfWorkDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jf-work-details/{id}")
    public ResponseEntity<JfWorkDetails> updateJfWorkDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JfWorkDetails jfWorkDetails
    ) throws URISyntaxException {
        log.debug("REST request to update JfWorkDetails : {}, {}", id, jfWorkDetails);
        if (jfWorkDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfWorkDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfWorkDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JfWorkDetails result = jfWorkDetailsRepository.save(jfWorkDetails);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfWorkDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jf-work-details/:id} : Partial updates given fields of an existing jfWorkDetails, field will ignore if it is null
     *
     * @param id the id of the jfWorkDetails to save.
     * @param jfWorkDetails the jfWorkDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfWorkDetails,
     * or with status {@code 400 (Bad Request)} if the jfWorkDetails is not valid,
     * or with status {@code 404 (Not Found)} if the jfWorkDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the jfWorkDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jf-work-details/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JfWorkDetails> partialUpdateJfWorkDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JfWorkDetails jfWorkDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update JfWorkDetails partially : {}, {}", id, jfWorkDetails);
        if (jfWorkDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfWorkDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfWorkDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JfWorkDetails> result = jfWorkDetailsRepository
            .findById(jfWorkDetails.getId())
            .map(
                existingJfWorkDetails -> {
                    if (jfWorkDetails.getRq() != null) {
                        existingJfWorkDetails.setRq(jfWorkDetails.getRq());
                    }
                    if (jfWorkDetails.getLb() != null) {
                        existingJfWorkDetails.setLb(jfWorkDetails.getLb());
                    }
                    if (jfWorkDetails.getShl() != null) {
                        existingJfWorkDetails.setShl(jfWorkDetails.getShl());
                    }
                    if (jfWorkDetails.getGq() != null) {
                        existingJfWorkDetails.setGq(jfWorkDetails.getGq());
                    }
                    if (jfWorkDetails.getSyl() != null) {
                        existingJfWorkDetails.setSyl(jfWorkDetails.getSyl());
                    }

                    return existingJfWorkDetails;
                }
            )
            .map(jfWorkDetailsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfWorkDetails.getId().toString())
        );
    }

    /**
     * {@code GET  /jf-work-details} : get all the jfWorkDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jfWorkDetails in body.
     */
    @GetMapping("/jf-work-details")
    public ResponseEntity<List<JfWorkDetails>> getAllJfWorkDetails(Pageable pageable) {
        log.debug("REST request to get a page of JfWorkDetails");
        Page<JfWorkDetails> page = jfWorkDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jf-work-details/:id} : get the "id" jfWorkDetails.
     *
     * @param id the id of the jfWorkDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jfWorkDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jf-work-details/{id}")
    public ResponseEntity<JfWorkDetails> getJfWorkDetails(@PathVariable Long id) {
        log.debug("REST request to get JfWorkDetails : {}", id);
        Optional<JfWorkDetails> jfWorkDetails = jfWorkDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jfWorkDetails);
    }

    /**
     * {@code DELETE  /jf-work-details/:id} : delete the "id" jfWorkDetails.
     *
     * @param id the id of the jfWorkDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jf-work-details/{id}")
    public ResponseEntity<Void> deleteJfWorkDetails(@PathVariable Long id) {
        log.debug("REST request to delete JfWorkDetails : {}", id);
        jfWorkDetailsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
