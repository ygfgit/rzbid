package com.sysmart.rzbid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 作业详细信息表\n@author sysmart
 */
@ApiModel(description = "作业详细信息表\n@author sysmart")
@Entity
@Table(name = "jf_wk_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JfWorkDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    @Column(name = "rq")
    private LocalDate rq;

    /**
     * 集疏港类别
     */
    @ApiModelProperty(value = "集疏港类别")
    @Column(name = "lb")
    private String lb;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @Column(name = "shl")
    private Double shl;

    /**
     * 罐区
     */
    @ApiModelProperty(value = "罐区")
    @Column(name = "gq")
    private String gq;

    /**
     * 港存剩余量
     */
    @ApiModelProperty(value = "港存剩余量")
    @Column(name = "syl")
    private Double syl;

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

    public JfWorkDetails id(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getRq() {
        return this.rq;
    }

    public JfWorkDetails rq(LocalDate rq) {
        this.rq = rq;
        return this;
    }

    public void setRq(LocalDate rq) {
        this.rq = rq;
    }

    public String getLb() {
        return this.lb;
    }

    public JfWorkDetails lb(String lb) {
        this.lb = lb;
        return this;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public Double getShl() {
        return this.shl;
    }

    public JfWorkDetails shl(Double shl) {
        this.shl = shl;
        return this;
    }

    public void setShl(Double shl) {
        this.shl = shl;
    }

    public String getGq() {
        return this.gq;
    }

    public JfWorkDetails gq(String gq) {
        this.gq = gq;
        return this;
    }

    public void setGq(String gq) {
        this.gq = gq;
    }

    public Double getSyl() {
        return this.syl;
    }

    public JfWorkDetails syl(Double syl) {
        this.syl = syl;
        return this;
    }

    public void setSyl(Double syl) {
        this.syl = syl;
    }

    public JfWork getMaster() {
        return this.master;
    }

    public JfWorkDetails master(JfWork jfWork) {
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
        if (!(o instanceof JfWorkDetails)) {
            return false;
        }
        return id != null && id.equals(((JfWorkDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JfWorkDetails{" +
            "id=" + getId() +
            ", rq='" + getRq() + "'" +
            ", lb='" + getLb() + "'" +
            ", shl=" + getShl() +
            ", gq='" + getGq() + "'" +
            ", syl=" + getSyl() +
            "}";
    }
}
