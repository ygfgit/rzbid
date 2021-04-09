package com.sysmart.rzbid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A JfTank.
 */
@Entity
@Table(name = "jf_tank")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JfTank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 库区
     */
    @ApiModelProperty(value = "库区")
    @Column(name = "jhi_zone")
    private String zone;

    /**
     * 罐号
     */
    @ApiModelProperty(value = "罐号")
    @Column(name = "code")
    private String code;

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

    public JfTank id(Long id) {
        this.id = id;
        return this;
    }

    public String getZone() {
        return this.zone;
    }

    public JfTank zone(String zone) {
        this.zone = zone;
        return this;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCode() {
        return this.code;
    }

    public JfTank code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JfWork getMaster() {
        return this.master;
    }

    public JfTank master(JfWork jfWork) {
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
        if (!(o instanceof JfTank)) {
            return false;
        }
        return id != null && id.equals(((JfTank) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JfTank{" +
            "id=" + getId() +
            ", zone='" + getZone() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
