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
 * 货物信息表\n@author sysmart
 */
@ApiModel(description = "货物信息表\n@author sysmart")
@Entity
@Table(name = "jf_cargo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JfCargo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 集港时间
     */
    @ApiModelProperty(value = "集港时间")
    @Column(name = "rq")
    private LocalDate rq;

    /**
     * 作业公司代码
     */
    @ApiModelProperty(value = "作业公司代码")
    @Column(name = "zygsdm")
    private String zygsdm;

    /**
     * 合同号
     */
    @ApiModelProperty(value = "合同号")
    @Column(name = "hth")
    private String hth;

    /**
     * 作业委托人ID
     */
    @ApiModelProperty(value = "作业委托人ID")
    @Column(name = "zywtrid")
    private Long zywtrid;

    /**
     * 作业委托人
     */
    @ApiModelProperty(value = "作业委托人")
    @Column(name = "zywtr")
    private String zywtr;

    /**
     * 中文船名
     */
    @ApiModelProperty(value = "中文船名")
    @Column(name = "zhwchm")
    private String zhwchm;

    /**
     * 货物名称
     */
    @ApiModelProperty(value = "货物名称")
    @Column(name = "hwmch")
    private String hwmch;

    /**
     * 罐区
     */
    @ApiModelProperty(value = "罐区")
    @Column(name = "gq")
    private String gq;

    /**
     * 总重量
     */
    @ApiModelProperty(value = "总重量")
    @Column(name = "shl")
    private Double shl;

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

    public JfCargo id(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getRq() {
        return this.rq;
    }

    public JfCargo rq(LocalDate rq) {
        this.rq = rq;
        return this;
    }

    public void setRq(LocalDate rq) {
        this.rq = rq;
    }

    public String getZygsdm() {
        return this.zygsdm;
    }

    public JfCargo zygsdm(String zygsdm) {
        this.zygsdm = zygsdm;
        return this;
    }

    public void setZygsdm(String zygsdm) {
        this.zygsdm = zygsdm;
    }

    public String getHth() {
        return this.hth;
    }

    public JfCargo hth(String hth) {
        this.hth = hth;
        return this;
    }

    public void setHth(String hth) {
        this.hth = hth;
    }

    public Long getZywtrid() {
        return this.zywtrid;
    }

    public JfCargo zywtrid(Long zywtrid) {
        this.zywtrid = zywtrid;
        return this;
    }

    public void setZywtrid(Long zywtrid) {
        this.zywtrid = zywtrid;
    }

    public String getZywtr() {
        return this.zywtr;
    }

    public JfCargo zywtr(String zywtr) {
        this.zywtr = zywtr;
        return this;
    }

    public void setZywtr(String zywtr) {
        this.zywtr = zywtr;
    }

    public String getZhwchm() {
        return this.zhwchm;
    }

    public JfCargo zhwchm(String zhwchm) {
        this.zhwchm = zhwchm;
        return this;
    }

    public void setZhwchm(String zhwchm) {
        this.zhwchm = zhwchm;
    }

    public String getHwmch() {
        return this.hwmch;
    }

    public JfCargo hwmch(String hwmch) {
        this.hwmch = hwmch;
        return this;
    }

    public void setHwmch(String hwmch) {
        this.hwmch = hwmch;
    }

    public String getGq() {
        return this.gq;
    }

    public JfCargo gq(String gq) {
        this.gq = gq;
        return this;
    }

    public void setGq(String gq) {
        this.gq = gq;
    }

    public Double getShl() {
        return this.shl;
    }

    public JfCargo shl(Double shl) {
        this.shl = shl;
        return this;
    }

    public void setShl(Double shl) {
        this.shl = shl;
    }

    public Double getSyl() {
        return this.syl;
    }

    public JfCargo syl(Double syl) {
        this.syl = syl;
        return this;
    }

    public void setSyl(Double syl) {
        this.syl = syl;
    }

    public JfWork getMaster() {
        return this.master;
    }

    public JfCargo master(JfWork jfWork) {
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
        if (!(o instanceof JfCargo)) {
            return false;
        }
        return id != null && id.equals(((JfCargo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JfCargo{" +
            "id=" + getId() +
            ", rq='" + getRq() + "'" +
            ", zygsdm='" + getZygsdm() + "'" +
            ", hth='" + getHth() + "'" +
            ", zywtrid=" + getZywtrid() +
            ", zywtr='" + getZywtr() + "'" +
            ", zhwchm='" + getZhwchm() + "'" +
            ", hwmch='" + getHwmch() + "'" +
            ", gq='" + getGq() + "'" +
            ", shl=" + getShl() +
            ", syl=" + getSyl() +
            "}";
    }
}
