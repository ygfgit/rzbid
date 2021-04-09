package com.sysmart.rzbid.web.rest;

import com.sysmart.rzbid.domain.JfCompany;
import com.sysmart.rzbid.repository.JfCompanyRepository;
import com.sysmart.rzbid.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sysmart.rzbid.domain.JfCompany}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JfCompanyResource {

    private final Logger log = LoggerFactory.getLogger(JfCompanyResource.class);

    private static final String ENTITY_NAME = "jfCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JfCompanyRepository jfCompanyRepository;

    public JfCompanyResource(JfCompanyRepository jfCompanyRepository) {
        this.jfCompanyRepository = jfCompanyRepository;
    }

    /**
     * {@code POST  /jf-companies} : Create a new jfCompany.
     *
     * @param jfCompany the jfCompany to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jfCompany, or with status {@code 400 (Bad Request)} if the jfCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jf-companies")
    public ResponseEntity<JfCompany> createJfCompany(@Valid @RequestBody JfCompany jfCompany) throws URISyntaxException {
        log.debug("REST request to save JfCompany : {}", jfCompany);
        if (jfCompany.getId() != null) {
            throw new BadRequestAlertException("A new jfCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JfCompany result = jfCompanyRepository.save(jfCompany);
        return ResponseEntity
            .created(new URI("/api/jf-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jf-companies/:id} : Updates an existing jfCompany.
     *
     * @param id the id of the jfCompany to save.
     * @param jfCompany the jfCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfCompany,
     * or with status {@code 400 (Bad Request)} if the jfCompany is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jfCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jf-companies/{id}")
    public ResponseEntity<JfCompany> updateJfCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody JfCompany jfCompany
    ) throws URISyntaxException {
        log.debug("REST request to update JfCompany : {}, {}", id, jfCompany);
        if (jfCompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfCompany.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JfCompany result = jfCompanyRepository.save(jfCompany);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfCompany.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jf-companies/:id} : Partial updates given fields of an existing jfCompany, field will ignore if it is null
     *
     * @param id the id of the jfCompany to save.
     * @param jfCompany the jfCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfCompany,
     * or with status {@code 400 (Bad Request)} if the jfCompany is not valid,
     * or with status {@code 404 (Not Found)} if the jfCompany is not found,
     * or with status {@code 500 (Internal Server Error)} if the jfCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jf-companies/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JfCompany> partialUpdateJfCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody JfCompany jfCompany
    ) throws URISyntaxException {
        log.debug("REST request to partial update JfCompany partially : {}, {}", id, jfCompany);
        if (jfCompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfCompany.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JfCompany> result = jfCompanyRepository
            .findById(jfCompany.getId())
            .map(
                existingJfCompany -> {
                    if (jfCompany.getName() != null) {
                        existingJfCompany.setName(jfCompany.getName());
                    }

                    return existingJfCompany;
                }
            )
            .map(jfCompanyRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfCompany.getId().toString())
        );
    }

    /**
     * {@code GET  /jf-companies} : get all the jfCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jfCompanies in body.
     */
    @GetMapping("/jf-companies")
    public List<JfCompany> getAllJfCompanies() {
        log.debug("REST request to get all JfCompanies");
        return jfCompanyRepository.findAll();
    }

    /**
     * {@code GET  /jf-companies/:id} : get the "id" jfCompany.
     *
     * @param id the id of the jfCompany to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jfCompany, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jf-companies/{id}")
    public ResponseEntity<JfCompany> getJfCompany(@PathVariable Long id) {
        log.debug("REST request to get JfCompany : {}", id);
        Optional<JfCompany> jfCompany = jfCompanyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jfCompany);
    }

    /**
     * {@code DELETE  /jf-companies/:id} : delete the "id" jfCompany.
     *
     * @param id the id of the jfCompany to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jf-companies/{id}")
    public ResponseEntity<Void> deleteJfCompany(@PathVariable Long id) {
        log.debug("REST request to delete JfCompany : {}", id);
        jfCompanyRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
