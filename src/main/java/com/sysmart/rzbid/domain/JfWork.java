package com.sysmart.rzbid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 表单提报主表\n@author sysmart
 */
@ApiModel(description = "表单提报主表\n@author sysmart")
@Entity
@Table(name = "jf_work")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JfWork implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 货物名称
     */
    @ApiModelProperty(value = "货物名称")
    @Column(name = "hwmch")
    private String hwmch;

    /**
     * 计划数量
     */
    @ApiModelProperty(value = "计划数量")
    @Column(name = "jhsl")
    private Double jhsl;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "bz")
    private String bz;

    @OneToMany(mappedBy = "master")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "master" }, allowSetters = true)
    private Set<JfCargo> cargos = new HashSet<>();

    @OneToMany(mappedBy = "master")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "master" }, allowSetters = true)
    private Set<JfTarget> tagets = new HashSet<>();

    @OneToMany(mappedBy = "master")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "master" }, allowSetters = true)
    private Set<JfTank> tanks = new HashSet<>();

    @OneToMany(mappedBy = "master")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "master" }, allowSetters = true)
    private Set<JfWorkDetails> details = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JfWork id(Long id) {
        this.id = id;
        return this;
    }

    public String getHwmch() {
        return this.hwmch;
    }

    public JfWork hwmch(String hwmch) {
        this.hwmch = hwmch;
        return this;
    }

    public void setHwmch(String hwmch) {
        this.hwmch = hwmch;
    }

    public Double getJhsl() {
        return this.jhsl;
    }

    public JfWork jhsl(Double jhsl) {
        this.jhsl = jhsl;
        return this;
    }

    public void setJhsl(Double jhsl) {
        this.jhsl = jhsl;
    }

    public String getBz() {
        return this.bz;
    }

    public JfWork bz(String bz) {
        this.bz = bz;
        return this;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Set<JfCargo> getCargos() {
        return this.cargos;
    }

    public JfWork cargos(Set<JfCargo> jfCargos) {
        this.setCargos(jfCargos);
        return this;
    }

    public JfWork addCargo(JfCargo jfCargo) {
        this.cargos.add(jfCargo);
        jfCargo.setMaster(this);
        return this;
    }

    public JfWork removeCargo(JfCargo jfCargo) {
        this.cargos.remove(jfCargo);
        jfCargo.setMaster(null);
        return this;
    }

    public void setCargos(Set<JfCargo> jfCargos) {
        if (this.cargos != null) {
            this.cargos.forEach(i -> i.setMaster(null));
        }
        if (jfCargos != null) {
            jfCargos.forEach(i -> i.setMaster(this));
        }
        this.cargos = jfCargos;
    }

    public Set<JfTarget> getTagets() {
        return this.tagets;
    }

    public JfWork tagets(Set<JfTarget> jfTargets) {
        this.setTagets(jfTargets);
        return this;
    }

    public JfWork addTaget(JfTarget jfTarget) {
        this.tagets.add(jfTarget);
        jfTarget.setMaster(this);
        return this;
    }

    public JfWork removeTaget(JfTarget jfTarget) {
        this.tagets.remove(jfTarget);
        jfTarget.setMaster(null);
        return this;
    }

    public void setTagets(Set<JfTarget> jfTargets) {
        if (this.tagets != null) {
            this.tagets.forEach(i -> i.setMaster(null));
        }
        if (jfTargets != null) {
            jfTargets.forEach(i -> i.setMaster(this));
        }
        this.tagets = jfTargets;
    }

    public Set<JfTank> getTanks() {
        return this.tanks;
    }

    public JfWork tanks(Set<JfTank> jfTanks) {
        this.setTanks(jfTanks);
        return this;
    }

    public JfWork addTank(JfTank jfTank) {
        this.tanks.add(jfTank);
        jfTank.setMaster(this);
        return this;
    }

    public JfWork removeTank(JfTank jfTank) {
        this.tanks.remove(jfTank);
        jfTank.setMaster(null);
        return this;
    }

    public void setTanks(Set<JfTank> jfTanks) {
        if (this.tanks != null) {
            this.tanks.forEach(i -> i.setMaster(null));
        }
        if (jfTanks != null) {
            jfTanks.forEach(i -> i.setMaster(this));
        }
        this.tanks = jfTanks;
    }

    public Set<JfWorkDetails> getDetails() {
        return this.details;
    }

    public JfWork details(Set<JfWorkDetails> jfWorkDetails) {
        this.setDetails(jfWorkDetails);
        return this;
    }

    public JfWork addDetail(JfWorkDetails jfWorkDetails) {
        this.details.add(jfWorkDetails);
        jfWorkDetails.setMaster(this);
        return this;
    }

    public JfWork removeDetail(JfWorkDetails jfWorkDetails) {
        this.details.remove(jfWorkDetails);
        jfWorkDetails.setMaster(null);
        return this;
    }

    public void setDetails(Set<JfWorkDetails> jfWorkDetails) {
        if (this.details != null) {
            this.details.forEach(i -> i.setMaster(null));
        }
        if (jfWorkDetails != null) {
            jfWorkDetails.forEach(i -> i.setMaster(this));
        }
        this.details = jfWorkDetails;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JfWork)) {
            return false;
        }
        return id != null && id.equals(((JfWork) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JfWork{" +
            "id=" + getId() +
            ", hwmch='" + getHwmch() + "'" +
            ", jhsl=" + getJhsl() +
            ", bz='" + getBz() + "'" +
            "}";
    }
}
