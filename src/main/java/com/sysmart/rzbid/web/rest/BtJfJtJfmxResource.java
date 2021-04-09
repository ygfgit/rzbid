package com.sysmart.rzbid.web.rest;

import com.sysmart.rzbid.domain.BtJfJtJfmx;
import com.sysmart.rzbid.repository.BtJfJtJfmxRepository;
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
 * REST controller for managing {@link com.sysmart.rzbid.domain.BtJfJtJfmx}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BtJfJtJfmxResource {

    private final Logger log = LoggerFactory.getLogger(BtJfJtJfmxResource.class);

    private static final String ENTITY_NAME = "btJfJtJfmx";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BtJfJtJfmxRepository btJfJtJfmxRepository;

    public BtJfJtJfmxResource(BtJfJtJfmxRepository btJfJtJfmxRepository) {
        this.btJfJtJfmxRepository = btJfJtJfmxRepository;
    }

    /**
     * {@code POST  /bt-jf-jt-jfmxes} : Create a new btJfJtJfmx.
     *
     * @param btJfJtJfmx the btJfJtJfmx to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new btJfJtJfmx, or with status {@code 400 (Bad Request)} if the btJfJtJfmx has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bt-jf-jt-jfmxes")
    public ResponseEntity<BtJfJtJfmx> createBtJfJtJfmx(@RequestBody BtJfJtJfmx btJfJtJfmx) throws URISyntaxException {
        log.debug("REST request to save BtJfJtJfmx : {}", btJfJtJfmx);
        if (btJfJtJfmx.getId() != null) {
            throw new BadRequestAlertException("A new btJfJtJfmx cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BtJfJtJfmx result = btJfJtJfmxRepository.save(btJfJtJfmx);
        return ResponseEntity
            .created(new URI("/api/bt-jf-jt-jfmxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bt-jf-jt-jfmxes/:id} : Updates an existing btJfJtJfmx.
     *
     * @param id the id of the btJfJtJfmx to save.
     * @param btJfJtJfmx the btJfJtJfmx to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated btJfJtJfmx,
     * or with status {@code 400 (Bad Request)} if the btJfJtJfmx is not valid,
     * or with status {@code 500 (Internal Server Error)} if the btJfJtJfmx couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bt-jf-jt-jfmxes/{id}")
    public ResponseEntity<BtJfJtJfmx> updateBtJfJtJfmx(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BtJfJtJfmx btJfJtJfmx
    ) throws URISyntaxException {
        log.debug("REST request to update BtJfJtJfmx : {}, {}", id, btJfJtJfmx);
        if (btJfJtJfmx.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, btJfJtJfmx.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!btJfJtJfmxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BtJfJtJfmx result = btJfJtJfmxRepository.save(btJfJtJfmx);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, btJfJtJfmx.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bt-jf-jt-jfmxes/:id} : Partial updates given fields of an existing btJfJtJfmx, field will ignore if it is null
     *
     * @param id the id of the btJfJtJfmx to save.
     * @param btJfJtJfmx the btJfJtJfmx to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated btJfJtJfmx,
     * or with status {@code 400 (Bad Request)} if the btJfJtJfmx is not valid,
     * or with status {@code 404 (Not Found)} if the btJfJtJfmx is not found,
     * or with status {@code 500 (Internal Server Error)} if the btJfJtJfmx couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bt-jf-jt-jfmxes/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BtJfJtJfmx> partialUpdateBtJfJtJfmx(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BtJfJtJfmx btJfJtJfmx
    ) throws URISyntaxException {
        log.debug("REST request to partial update BtJfJtJfmx partially : {}, {}", id, btJfJtJfmx);
        if (btJfJtJfmx.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, btJfJtJfmx.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!btJfJtJfmxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BtJfJtJfmx> result = btJfJtJfmxRepository
            .findById(btJfJtJfmx.getId())
            .map(
                existingBtJfJtJfmx -> {
                    if (btJfJtJfmx.getZygs() != null) {
                        existingBtJfJtJfmx.setZygs(btJfJtJfmx.getZygs());
                    }
                    if (btJfJtJfmx.getZyetrid() != null) {
                        existingBtJfJtJfmx.setZyetrid(btJfJtJfmx.getZyetrid());
                    }
                    if (btJfJtJfmx.getFlmch() != null) {
                        existingBtJfJtJfmx.setFlmch(btJfJtJfmx.getFlmch());
                    }
                    if (btJfJtJfmx.getFl() != null) {
                        existingBtJfJtJfmx.setFl(btJfJtJfmx.getFl());
                    }
                    if (btJfJtJfmx.getShl() != null) {
                        existingBtJfJtJfmx.setShl(btJfJtJfmx.getShl());
                    }
                    if (btJfJtJfmx.getJe() != null) {
                        existingBtJfJtJfmx.setJe(btJfJtJfmx.getJe());
                    }
                    if (btJfJtJfmx.getShlv() != null) {
                        existingBtJfJtJfmx.setShlv(btJfJtJfmx.getShlv());
                    }
                    if (btJfJtJfmx.getShe() != null) {
                        existingBtJfJtJfmx.setShe(btJfJtJfmx.getShe());
                    }
                    if (btJfJtJfmx.getShhje() != null) {
                        existingBtJfJtJfmx.setShhje(btJfJtJfmx.getShhje());
                    }
                    if (btJfJtJfmx.getJmje() != null) {
                        existingBtJfJtJfmx.setJmje(btJfJtJfmx.getJmje());
                    }
                    if (btJfJtJfmx.getStartd() != null) {
                        existingBtJfJtJfmx.setStartd(btJfJtJfmx.getStartd());
                    }
                    if (btJfJtJfmx.getEndd() != null) {
                        existingBtJfJtJfmx.setEndd(btJfJtJfmx.getEndd());
                    }
                    if (btJfJtJfmx.getMark1() != null) {
                        existingBtJfJtJfmx.setMark1(btJfJtJfmx.getMark1());
                    }
                    if (btJfJtJfmx.getMark2() != null) {
                        existingBtJfJtJfmx.setMark2(btJfJtJfmx.getMark2());
                    }
                    if (btJfJtJfmx.getCreateby() != null) {
                        existingBtJfJtJfmx.setCreateby(btJfJtJfmx.getCreateby());
                    }
                    if (btJfJtJfmx.getCreatebyid() != null) {
                        existingBtJfJtJfmx.setCreatebyid(btJfJtJfmx.getCreatebyid());
                    }
                    if (btJfJtJfmx.getCreateon() != null) {
                        existingBtJfJtJfmx.setCreateon(btJfJtJfmx.getCreateon());
                    }
                    if (btJfJtJfmx.getCreateGsid() != null) {
                        existingBtJfJtJfmx.setCreateGsid(btJfJtJfmx.getCreateGsid());
                    }
                    if (btJfJtJfmx.getCreateGsmch() != null) {
                        existingBtJfJtJfmx.setCreateGsmch(btJfJtJfmx.getCreateGsmch());
                    }
                    if (btJfJtJfmx.getCreateBmid() != null) {
                        existingBtJfJtJfmx.setCreateBmid(btJfJtJfmx.getCreateBmid());
                    }
                    if (btJfJtJfmx.getCreateBmm() != null) {
                        existingBtJfJtJfmx.setCreateBmm(btJfJtJfmx.getCreateBmm());
                    }
                    if (btJfJtJfmx.getCreateGwid() != null) {
                        existingBtJfJtJfmx.setCreateGwid(btJfJtJfmx.getCreateGwid());
                    }
                    if (btJfJtJfmx.getCreateGwm() != null) {
                        existingBtJfJtJfmx.setCreateGwm(btJfJtJfmx.getCreateGwm());
                    }
                    if (btJfJtJfmx.getModifyby() != null) {
                        existingBtJfJtJfmx.setModifyby(btJfJtJfmx.getModifyby());
                    }
                    if (btJfJtJfmx.getModifybyid() != null) {
                        existingBtJfJtJfmx.setModifybyid(btJfJtJfmx.getModifybyid());
                    }
                    if (btJfJtJfmx.getModifyon() != null) {
                        existingBtJfJtJfmx.setModifyon(btJfJtJfmx.getModifyon());
                    }

                    return existingBtJfJtJfmx;
                }
            )
            .map(btJfJtJfmxRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, btJfJtJfmx.getId().toString())
        );
    }

    /**
     * {@code GET  /bt-jf-jt-jfmxes} : get all the btJfJtJfmxes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of btJfJtJfmxes in body.
     */
    @GetMapping("/bt-jf-jt-jfmxes")
    public ResponseEntity<List<BtJfJtJfmx>> getAllBtJfJtJfmxes(Pageable pageable) {
        log.debug("REST request to get a page of BtJfJtJfmxes");
        Page<BtJfJtJfmx> page = btJfJtJfmxRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bt-jf-jt-jfmxes/:id} : get the "id" btJfJtJfmx.
     *
     * @param id the id of the btJfJtJfmx to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the btJfJtJfmx, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bt-jf-jt-jfmxes/{id}")
    public ResponseEntity<BtJfJtJfmx> getBtJfJtJfmx(@PathVariable Long id) {
        log.debug("REST request to get BtJfJtJfmx : {}", id);
        Optional<BtJfJtJfmx> btJfJtJfmx = btJfJtJfmxRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(btJfJtJfmx);
    }

    /**
     * {@code DELETE  /bt-jf-jt-jfmxes/:id} : delete the "id" btJfJtJfmx.
     *
     * @param id the id of the btJfJtJfmx to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bt-jf-jt-jfmxes/{id}")
    public ResponseEntity<Void> deleteBtJfJtJfmx(@PathVariable Long id) {
        log.debug("REST request to delete BtJfJtJfmx : {}", id);
        btJfJtJfmxRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
