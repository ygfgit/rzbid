package com.sysmart.rzbid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A JfMaster.
 */
@Entity
@Table(name = "jf_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JfMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 作业公司
     */
    @ApiModelProperty(value = "作业公司")
    @Column(name = "zygs")
    private String zygs;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    @Column(name = "je", precision = 21, scale = 2)
    private BigDecimal je;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    @Column(name = "she", precision = 21, scale = 2)
    private BigDecimal she;

    /**
     * 税后金额
     */
    @ApiModelProperty(value = "税后金额")
    @Column(name = "shhje", precision = 21, scale = 2)
    private BigDecimal shhje;

    /**
     * 减免金额
     */
    @ApiModelProperty(value = "减免金额")
    @Column(name = "jmje", precision = 21, scale = 2)
    private BigDecimal jmje;

    /**
     * 减免后总金额
     */
    @ApiModelProperty(value = "减免后总金额")
    @Column(name = "jmhje", precision = 21, scale = 2)
    private BigDecimal jmhje;

    /**
     * 货物表中ID
     */
    @ApiModelProperty(value = "货物表中ID")
    @Column(name = "id_hw")
    private Long idHw;

    /**
     * 合同对应ID
     */
    @ApiModelProperty(value = "合同对应ID")
    @Column(name = "htid")
    private Long htid;

    /**
     * 贸别
     */
    @ApiModelProperty(value = "贸别")
    @Column(name = "mb")
    private String mb;

    /**
     * 作业委托人ID
     */
    @ApiModelProperty(value = "作业委托人ID")
    @Column(name = "zywtrid")
    private Long zywtrid;

    /**
     * 标记1
     */
    @ApiModelProperty(value = "标记1")
    @Column(name = "mark_1")
    private String mark1;

    /**
     * 标记2
     */
    @ApiModelProperty(value = "标记2")
    @Column(name = "mark_2")
    private String mark2;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @Column(name = "createby")
    private String createby;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    @Column(name = "createbyid")
    private Long createbyid;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    @Column(name = "createon")
    private LocalDate createon;

    /**
     * 创建公司
     */
    @ApiModelProperty(value = "创建公司")
    @Column(name = "create_gsid")
    private String createGsid;

    /**
     * 创建公司
     */
    @ApiModelProperty(value = "创建公司")
    @Column(name = "create_gsmch")
    private String createGsmch;

    /**
     * 创建部门ID
     */
    @ApiModelProperty(value = "创建部门ID")
    @Column(name = "create_bmid")
    private Long createBmid;

    /**
     * 创建部门名称
     */
    @ApiModelProperty(value = "创建部门名称")
    @Column(name = "create_bmm")
    private String createBmm;

    /**
     * 创建岗位
     */
    @ApiModelProperty(value = "创建岗位")
    @Column(name = "create_gwid")
    private String createGwid;

    /**
     * 创建岗位
     */
    @ApiModelProperty(value = "创建岗位")
    @Column(name = "create_gwm")
    private String createGwm;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    @Column(name = "modifyby")
    private String modifyby;

    /**
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    @Column(name = "modifybyid")
    private Long modifybyid;

    /**
     * 修改日期
     */
    @ApiModelProperty(value = "修改日期")
    @Column(name = "modifyon")
    private LocalDate modifyon;

    @OneToMany(mappedBy = "master")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "master" }, allowSetters = true)
    private Set<BtJfJtJfmx> btjfjtjfmxes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JfMaster id(Long id) {
        this.id = id;
        return this;
    }

    public String getZygs() {
        return this.zygs;
    }

    public JfMaster zygs(String zygs) {
        this.zygs = zygs;
        return this;
    }

    public void setZygs(String zygs) {
        this.zygs = zygs;
    }

    public BigDecimal getJe() {
        return this.je;
    }

    public JfMaster je(BigDecimal je) {
        this.je = je;
        return this;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public BigDecimal getShe() {
        return this.she;
    }

    public JfMaster she(BigDecimal she) {
        this.she = she;
        return this;
    }

    public void setShe(BigDecimal she) {
        this.she = she;
    }

    public BigDecimal getShhje() {
        return this.shhje;
    }

    public JfMaster shhje(BigDecimal shhje) {
        this.shhje = shhje;
        return this;
    }

    public void setShhje(BigDecimal shhje) {
        this.shhje = shhje;
    }

    public BigDecimal getJmje() {
        return this.jmje;
    }

    public JfMaster jmje(BigDecimal jmje) {
        this.jmje = jmje;
        return this;
    }

    public void setJmje(BigDecimal jmje) {
        this.jmje = jmje;
    }

    public BigDecimal getJmhje() {
        return this.jmhje;
    }

    public JfMaster jmhje(BigDecimal jmhje) {
        this.jmhje = jmhje;
        return this;
    }

    public void setJmhje(BigDecimal jmhje) {
        this.jmhje = jmhje;
    }

    public Long getIdHw() {
        return this.idHw;
    }

    public JfMaster idHw(Long idHw) {
        this.idHw = idHw;
        return this;
    }

    public void setIdHw(Long idHw) {
        this.idHw = idHw;
    }

    public Long getHtid() {
        return this.htid;
    }

    public JfMaster htid(Long htid) {
        this.htid = htid;
        return this;
    }

    public void setHtid(Long htid) {
        this.htid = htid;
    }

    public String getMb() {
        return this.mb;
    }

    public JfMaster mb(String mb) {
        this.mb = mb;
        return this;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public Long getZywtrid() {
        return this.zywtrid;
    }

    public JfMaster zywtrid(Long zywtrid) {
        this.zywtrid = zywtrid;
        return this;
    }

    public void setZywtrid(Long zywtrid) {
        this.zywtrid = zywtrid;
    }

    public String getMark1() {
        return this.mark1;
    }

    public JfMaster mark1(String mark1) {
        this.mark1 = mark1;
        return this;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return this.mark2;
    }

    public JfMaster mark2(String mark2) {
        this.mark2 = mark2;
        return this;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

    public String getCreateby() {
        return this.createby;
    }

    public JfMaster createby(String createby) {
        this.createby = createby;
        return this;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Long getCreatebyid() {
        return this.createbyid;
    }

    public JfMaster createbyid(Long createbyid) {
        this.createbyid = createbyid;
        return this;
    }

    public void setCreatebyid(Long createbyid) {
        this.createbyid = createbyid;
    }

    public LocalDate getCreateon() {
        return this.createon;
    }

    public JfMaster createon(LocalDate createon) {
        this.createon = createon;
        return this;
    }

    public void setCreateon(LocalDate createon) {
        this.createon = createon;
    }

    public String getCreateGsid() {
        return this.createGsid;
    }

    public JfMaster createGsid(String createGsid) {
        this.createGsid = createGsid;
        return this;
    }

    public void setCreateGsid(String createGsid) {
        this.createGsid = createGsid;
    }

    public String getCreateGsmch() {
        return this.createGsmch;
    }

    public JfMaster createGsmch(String createGsmch) {
        this.createGsmch = createGsmch;
        return this;
    }

    public void setCreateGsmch(String createGsmch) {
        this.createGsmch = createGsmch;
    }

    public Long getCreateBmid() {
        return this.createBmid;
    }

    public JfMaster createBmid(Long createBmid) {
        this.createBmid = createBmid;
        return this;
    }

    public void setCreateBmid(Long createBmid) {
        this.createBmid = createBmid;
    }

    public String getCreateBmm() {
        return this.createBmm;
    }

    public JfMaster createBmm(String createBmm) {
        this.createBmm = createBmm;
        return this;
    }

    public void setCreateBmm(String createBmm) {
        this.createBmm = createBmm;
    }

    public String getCreateGwid() {
        return this.createGwid;
    }

    public JfMaster createGwid(String createGwid) {
        this.createGwid = createGwid;
        return this;
    }

    public void setCreateGwid(String createGwid) {
        this.createGwid = createGwid;
    }

    public String getCreateGwm() {
        return this.createGwm;
    }

    public JfMaster createGwm(String createGwm) {
        this.createGwm = createGwm;
        return this;
    }

    public void setCreateGwm(String createGwm) {
        this.createGwm = createGwm;
    }

    public String getModifyby() {
        return this.modifyby;
    }

    public JfMaster modifyby(String modifyby) {
        this.modifyby = modifyby;
        return this;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby;
    }

    public Long getModifybyid() {
        return this.modifybyid;
    }

    public JfMaster modifybyid(Long modifybyid) {
        this.modifybyid = modifybyid;
        return this;
    }

    public void setModifybyid(Long modifybyid) {
        this.modifybyid = modifybyid;
    }

    public LocalDate getModifyon() {
        return this.modifyon;
    }

    public JfMaster modifyon(LocalDate modifyon) {
        this.modifyon = modifyon;
        return this;
    }

    public void setModifyon(LocalDate modifyon) {
        this.modifyon = modifyon;
    }

    public Set<BtJfJtJfmx> getBtjfjtjfmxes() {
        return this.btjfjtjfmxes;
    }

    public JfMaster btjfjtjfmxes(Set<BtJfJtJfmx> btJfJtJfmxes) {
        this.setBtjfjtjfmxes(btJfJtJfmxes);
        return this;
    }

    public JfMaster addBtjfjtjfmx(BtJfJtJfmx btJfJtJfmx) {
        this.btjfjtjfmxes.add(btJfJtJfmx);
        btJfJtJfmx.setMaster(this);
        return this;
    }

    public JfMaster removeBtjfjtjfmx(BtJfJtJfmx btJfJtJfmx) {
        this.btjfjtjfmxes.remove(btJfJtJfmx);
        btJfJtJfmx.setMaster(null);
        return this;
    }

    public void setBtjfjtjfmxes(Set<BtJfJtJfmx> btJfJtJfmxes) {
        if (this.btjfjtjfmxes != null) {
            this.btjfjtjfmxes.forEach(i -> i.setMaster(null));
        }
        if (btJfJtJfmxes != null) {
            btJfJtJfmxes.forEach(i -> i.setMaster(this));
        }
        this.btjfjtjfmxes = btJfJtJfmxes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JfMaster)) {
            return false;
        }
        return id != null && id.equals(((JfMaster) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JfMaster{" +
            "id=" + getId() +
            ", zygs='" + getZygs() + "'" +
            ", je=" + getJe() +
            ", she=" + getShe() +
            ", shhje=" + getShhje() +
            ", jmje=" + getJmje() +
            ", jmhje=" + getJmhje() +
            ", idHw=" + getIdHw() +
            ", htid=" + getHtid() +
            ", mb='" + getMb() + "'" +
            ", zywtrid=" + getZywtrid() +
            ", mark1='" + getMark1() + "'" +
            ", mark2='" + getMark2() + "'" +
            ", createby='" + getCreateby() + "'" +
            ", createbyid=" + getCreatebyid() +
            ", createon='" + getCreateon() + "'" +
            ", createGsid='" + getCreateGsid() + "'" +
            ", createGsmch='" + getCreateGsmch() + "'" +
            ", createBmid=" + getCreateBmid() +
            ", createBmm='" + getCreateBmm() + "'" +
            ", createGwid='" + getCreateGwid() + "'" +
            ", createGwm='" + getCreateGwm() + "'" +
            ", modifyby='" + getModifyby() + "'" +
            ", modifybyid=" + getModifybyid() +
            ", modifyon='" + getModifyon() + "'" +
            "}";
    }
}
