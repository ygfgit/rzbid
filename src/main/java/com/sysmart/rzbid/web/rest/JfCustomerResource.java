package com.sysmart.rzbid.web.rest;

import com.sysmart.rzbid.domain.JfCustomer;
import com.sysmart.rzbid.repository.JfCustomerRepository;
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
 * REST controller for managing {@link com.sysmart.rzbid.domain.JfCustomer}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JfCustomerResource {

    private final Logger log = LoggerFactory.getLogger(JfCustomerResource.class);

    private static final String ENTITY_NAME = "jfCustomer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JfCustomerRepository jfCustomerRepository;

    public JfCustomerResource(JfCustomerRepository jfCustomerRepository) {
        this.jfCustomerRepository = jfCustomerRepository;
    }

    /**
     * {@code POST  /jf-customers} : Create a new jfCustomer.
     *
     * @param jfCustomer the jfCustomer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jfCustomer, or with status {@code 400 (Bad Request)} if the jfCustomer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jf-customers")
    public ResponseEntity<JfCustomer> createJfCustomer(@Valid @RequestBody JfCustomer jfCustomer) throws URISyntaxException {
        log.debug("REST request to save JfCustomer : {}", jfCustomer);
        if (jfCustomer.getId() != null) {
            throw new BadRequestAlertException("A new jfCustomer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JfCustomer result = jfCustomerRepository.save(jfCustomer);
        return ResponseEntity
            .created(new URI("/api/jf-customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jf-customers/:id} : Updates an existing jfCustomer.
     *
     * @param id the id of the jfCustomer to save.
     * @param jfCustomer the jfCustomer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfCustomer,
     * or with status {@code 400 (Bad Request)} if the jfCustomer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jfCustomer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jf-customers/{id}")
    public ResponseEntity<JfCustomer> updateJfCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody JfCustomer jfCustomer
    ) throws URISyntaxException {
        log.debug("REST request to update JfCustomer : {}, {}", id, jfCustomer);
        if (jfCustomer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfCustomer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfCustomerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JfCustomer result = jfCustomerRepository.save(jfCustomer);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfCustomer.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jf-customers/:id} : Partial updates given fields of an existing jfCustomer, field will ignore if it is null
     *
     * @param id the id of the jfCustomer to save.
     * @param jfCustomer the jfCustomer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jfCustomer,
     * or with status {@code 400 (Bad Request)} if the jfCustomer is not valid,
     * or with status {@code 404 (Not Found)} if the jfCustomer is not found,
     * or with status {@code 500 (Internal Server Error)} if the jfCustomer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jf-customers/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JfCustomer> partialUpdateJfCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody JfCustomer jfCustomer
    ) throws URISyntaxException {
        log.debug("REST request to partial update JfCustomer partially : {}, {}", id, jfCustomer);
        if (jfCustomer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jfCustomer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jfCustomerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JfCustomer> result = jfCustomerRepository
            .findById(jfCustomer.getId())
            .map(
                existingJfCustomer -> {
                    if (jfCustomer.getName() != null) {
                        existingJfCustomer.setName(jfCustomer.getName());
                    }

                    return existingJfCustomer;
                }
            )
            .map(jfCustomerRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jfCustomer.getId().toString())
        );
    }

    /**
     * {@code GET  /jf-customers} : get all the jfCustomers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jfCustomers in body.
     */
    @GetMapping("/jf-customers")
    public List<JfCustomer> getAllJfCustomers() {
        log.debug("REST request to get all JfCustomers");
        return jfCustomerRepository.findAll();
    }

    /**
     * {@code GET  /jf-customers/:id} : get the "id" jfCustomer.
     *
     * @param id the id of the jfCustomer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jfCustomer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jf-customers/{id}")
    public ResponseEntity<JfCustomer> getJfCustomer(@PathVariable Long id) {
        log.debug("REST request to get JfCustomer : {}", id);
        Optional<JfCustomer> jfCustomer = jfCustomerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jfCustomer);
    }

    /**
     * {@code DELETE  /jf-customers/:id} : delete the "id" jfCustomer.
     *
     * @param id the id of the jfCustomer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jf-customers/{id}")
    public ResponseEntity<Void> deleteJfCustomer(@PathVariable Long id) {
        log.debug("REST request to delete JfCustomer : {}", id);
        jfCustomerRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
