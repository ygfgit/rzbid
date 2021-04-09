package com.sysmart.rzbid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 流向信息表\n@author sysmart
 */
@ApiModel(description = "流向信息表\n@author sysmart")
@Entity
@Table(name = "jf_target")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JfTarget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 分流重量
     */
    @ApiModelProperty(value = "分流重量")
    @Column(name = "flzl")
    private Double flzl;

    @ManyToOne
    @JsonIgnoreProperties(value = { "cargos", "tagets", "tanks", "details" }, allowSetters = true)
    private JfWork master;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JfTarget id(Long id) {
        this.id = id;
        return this;
    }

    public Double getFlzl() {
        return this.flzl;
    }

    public JfTarget flzl(Double flzl) {
        this.flzl = flzl;
        return this;
    }

    public void setFlzl(Double flzl) {
        this.flzl = flzl;
    }

    public JfWork getMaster() {
        return this.master;
    }

    public JfTarget master(JfWork jfWork) {
        this.setMaster(jfWork);
        return this;
    }

    public void setMaster(JfWork jfWork) {
        this.master = jfWork;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JfTarget)) {
            return false;
        }
        return id != null && id.equals(((JfTarget) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JfTarget{" +
            "id=" + getId() +
            ", flzl=" + getFlzl() +
            "}";
    }
}
