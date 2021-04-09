package com.sysmart.rzbid.service;

import com.sysmart.rzbid.domain.JfMaster;
import com.sysmart.rzbid.repository.JfMasterRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link JfMaster}.
 */
@Service
@Transactional
public class JfMasterService {

    private final Logger log = LoggerFactory.getLogger(JfMasterService.class);

    private final JfMasterRepository jfMasterRepository;

    public JfMasterService(JfMasterRepository jfMasterRepository) {
        this.jfMasterRepository = jfMasterRepository;
    }

    /**
     * Save a jfMaster.
     *
     * @param jfMaster the entity to save.
     * @return the persisted entity.
     */
    public JfMaster save(JfMaster jfMaster) {
        log.debug("Request to save JfMaster : {}", jfMaster);
        return jfMasterRepository.save(jfMaster);
    }

    /**
     * Partially update a jfMaster.
     *
     * @param jfMaster the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<JfMaster> partialUpdate(JfMaster jfMaster) {
        log.debug("Request to partially update JfMaster : {}", jfMaster);

        return jfMasterRepository
            .findById(jfMaster.getId())
            .map(
                existingJfMaster -> {
                    if (jfMaster.getZygs() != null) {
                        existingJfMaster.setZygs(jfMaster.getZygs());
                    }
                    if (jfMaster.getJe() != null) {
                        existingJfMaster.setJe(jfMaster.getJe());
                    }
                    if (jfMaster.getShe() != null) {
                        existingJfMaster.setShe(jfMaster.getShe());
                    }
                    if (jfMaster.getShhje() != null) {
                        existingJfMaster.setShhje(jfMaster.getShhje());
                    }
                    if (jfMaster.getJmje() != null) {
                        existingJfMaster.setJmje(jfMaster.getJmje());
                    }
                    if (jfMaster.getJmhje() != null) {
                        existingJfMaster.setJmhje(jfMaster.getJmhje());
                    }
                    if (jfMaster.getIdHw() != null) {
                        existingJfMaster.setIdHw(jfMaster.getIdHw());
                    }
                    if (jfMaster.getHtid() != null) {
                        existingJfMaster.setHtid(jfMaster.getHtid());
                    }
                    if (jfMaster.getMb() != null) {
                        existingJfMaster.setMb(jfMaster.getMb());
                    }
                    if (jfMaster.getZywtrid() != null) {
                        existingJfMaster.setZywtrid(jfMaster.getZywtrid());
                    }
                    if (jfMaster.getMark1() != null) {
                        existingJfMaster.setMark1(jfMaster.getMark1());
                    }
                    if (jfMaster.getMark2() != null) {
                        existingJfMaster.setMark2(jfMaster.getMark2());
                    }
                    if (jfMaster.getCreateby() != null) {
                        existingJfMaster.setCreateby(jfMaster.getCreateby());
                    }
                    if (jfMaster.getCreatebyid() != null) {
                        existingJfMaster.setCreatebyid(jfMaster.getCreatebyid());
                    }
                    if (jfMaster.getCreateon() != null) {
                        existingJfMaster.setCreateon(jfMaster.getCreateon());
                    }
                    if (jfMaster.getCreateGsid() != null) {
                        existingJfMaster.setCreateGsid(jfMaster.getCreateGsid());
                    }
                    if (jfMaster.getCreateGsmch() != null) {
                        existingJfMaster.setCreateGsmch(jfMaster.getCreateGsmch());
                    }
                    if (jfMaster.getCreateBmid() != null) {
                        existingJfMaster.setCreateBmid(jfMaster.getCreateBmid());
                    }
                    if (jfMaster.getCreateBmm() != null) {
                        existingJfMaster.setCreateBmm(jfMaster.getCreateBmm());
                    }
                    if (jfMaster.getCreateGwid() != null) {
                        existingJfMaster.setCreateGwid(jfMaster.getCreateGwid());
                    }
                    if (jfMaster.getCreateGwm() != null) {
                        existingJfMaster.setCreateGwm(jfMaster.getCreateGwm());
                    }
                    if (jfMaster.getModifyby() != null) {
                        existingJfMaster.setModifyby(jfMaster.getModifyby());
                    }
                    if (jfMaster.getModifybyid() != null) {
                        existingJfMaster.setModifybyid(jfMaster.getModifybyid());
                    }
                    if (jfMaster.getModifyon() != null) {
                        existingJfMaster.setModifyon(jfMaster.getModifyon());
                    }

                    return existingJfMaster;
                }
            )
            .map(jfMasterRepository::save);
    }

    /**
     * Get all the jfMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<JfMaster> findAll(Pageable pageable) {
        log.debug("Request to get all JfMasters");
        return jfMasterRepository.findAll(pageable);
    }

    /**
     * Get one jfMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JfMaster> findOne(Long id) {
        log.debug("Request to get JfMaster : {}", id);
        return jfMasterRepository.findById(id);
    }

    /**
     * Delete the jfMaster by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete JfMaster : {}", id);
        jfMasterRepository.deleteById(id);
    }
}
