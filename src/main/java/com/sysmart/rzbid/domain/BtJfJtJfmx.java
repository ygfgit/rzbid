package com.sysmart.rzbid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BtJfJtJfmx.
 */
@Entity
@Table(name = "bt_jf_jt_jfmx")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BtJfJtJfmx implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 作业公司（保存公司代码，参照附件1中公司代码表）
     */
    @ApiModelProperty(value = "作业公司（保存公司代码，参照附件1中公司代码表）")
    @Column(name = "zygs")
    private Long zygs;

    /**
     * 作业委托人ID
     */
    @ApiModelProperty(value = "作业委托人ID")
    @Column(name = "zyetrid")
    private Long zyetrid;

    /**
     * 费率名称（A类、B类、C类）
     */
    @ApiModelProperty(value = "费率名称（A类、B类、C类）")
    @Column(name = "flmch")
    private String flmch;

    /**
     * 费率（从费率表中匹配的费率，阶梯费率时可以为空）
     */
    @ApiModelProperty(value = "费率（从费率表中匹配的费率，阶梯费率时可以为空）")
    @Column(name = "fl", precision = 21, scale = 2)
    private BigDecimal fl;

    /**
     * 货物重量
     */
    @ApiModelProperty(value = "货物重量")
    @Column(name = "shl")
    private Double shl;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    @Column(name = "je", precision = 21, scale = 2)
    private BigDecimal je;

    /**
     * 税率（费率表中税率）
     */
    @ApiModelProperty(value = "税率（费率表中税率）")
    @Column(name = "shlv", precision = 21, scale = 2)
    private BigDecimal shlv;

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
     * 开始日期(用于计算B类计费)
     */
    @ApiModelProperty(value = "开始日期(用于计算B类计费)")
    @Column(name = "startd")
    private LocalDate startd;

    /**
     * 结束始日期(用于计算B类计费)
     */
    @ApiModelProperty(value = "结束始日期(用于计算B类计费)")
    @Column(name = "endd")
    private LocalDate endd;

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
    private Long createGwid;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "btjfjtjfmxes" }, allowSetters = true)
    private JfMaster master;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BtJfJtJfmx id(Long id) {
        this.id = id;
        return this;
    }

    public Long getZygs() {
        return this.zygs;
    }

    public BtJfJtJfmx zygs(Long zygs) {
        this.zygs = zygs;
        return this;
    }

    public void setZygs(Long zygs) {
        this.zygs = zygs;
    }

    public Long getZyetrid() {
        return this.zyetrid;
    }

    public BtJfJtJfmx zyetrid(Long zyetrid) {
        this.zyetrid = zyetrid;
        return this;
    }

    public void setZyetrid(Long zyetrid) {
        this.zyetrid = zyetrid;
    }

    public String getFlmch() {
        return this.flmch;
    }

    public BtJfJtJfmx flmch(String flmch) {
        this.flmch = flmch;
        return this;
    }

    public void setFlmch(String flmch) {
        this.flmch = flmch;
    }

    public BigDecimal getFl() {
        return this.fl;
    }

    public BtJfJtJfmx fl(BigDecimal fl) {
        this.fl = fl;
        return this;
    }

    public void setFl(BigDecimal fl) {
        this.fl = fl;
    }

    public Double getShl() {
        return this.shl;
    }

    public BtJfJtJfmx shl(Double shl) {
        this.shl = shl;
        return this;
    }

    public void setShl(Double shl) {
        this.shl = shl;
    }

    public BigDecimal getJe() {
        return this.je;
    }

    public BtJfJtJfmx je(BigDecimal je) {
        this.je = je;
        return this;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public BigDecimal getShlv() {
        return this.shlv;
    }

    public BtJfJtJfmx shlv(BigDecimal shlv) {
        this.shlv = shlv;
        return this;
    }

    public void setShlv(BigDecimal shlv) {
        this.shlv = shlv;
    }

    public BigDecimal getShe() {
        return this.she;
    }

    public BtJfJtJfmx she(BigDecimal she) {
        this.she = she;
        return this;
    }

    public void setShe(BigDecimal she) {
        this.she = she;
    }

    public BigDecimal getShhje() {
        return this.shhje;
    }

    public BtJfJtJfmx shhje(BigDecimal shhje) {
        this.shhje = shhje;
        return this;
    }

    public void setShhje(BigDecimal shhje) {
        this.shhje = shhje;
    }

    public BigDecimal getJmje() {
        return this.jmje;
    }

    public BtJfJtJfmx jmje(BigDecimal jmje) {
        this.jmje = jmje;
        return this;
    }

    public void setJmje(BigDecimal jmje) {
        this.jmje = jmje;
    }

    public LocalDate getStartd() {
        return this.startd;
    }

    public BtJfJtJfmx startd(LocalDate startd) {
        this.startd = startd;
        return this;
    }

    public void setStartd(LocalDate startd) {
        this.startd = startd;
    }

    public LocalDate getEndd() {
        return this.endd;
    }

    public BtJfJtJfmx endd(LocalDate endd) {
        this.endd = endd;
        return this;
    }

    public void setEndd(LocalDate endd) {
        this.endd = endd;
    }

    public String getMark1() {
        return this.mark1;
    }

    public BtJfJtJfmx mark1(String mark1) {
        this.mark1 = mark1;
        return this;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return this.mark2;
    }

    public BtJfJtJfmx mark2(String mark2) {
        this.mark2 = mark2;
        return this;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

    public String getCreateby() {
        return this.createby;
    }

    public BtJfJtJfmx createby(String createby) {
        this.createby = createby;
        return this;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Long getCreatebyid() {
        return this.createbyid;
    }

    public BtJfJtJfmx createbyid(Long createbyid) {
        this.createbyid = createbyid;
        return this;
    }

    public void setCreatebyid(Long createbyid) {
        this.createbyid = createbyid;
    }

    public LocalDate getCreateon() {
        return this.createon;
    }

    public BtJfJtJfmx createon(LocalDate createon) {
        this.createon = createon;
        return this;
    }

    public void setCreateon(LocalDate createon) {
        this.createon = createon;
    }

    public String getCreateGsid() {
        return this.createGsid;
    }

    public BtJfJtJfmx createGsid(String createGsid) {
        this.createGsid = createGsid;
        return this;
    }

    public void setCreateGsid(String createGsid) {
        this.createGsid = createGsid;
    }

    public String getCreateGsmch() {
        return this.createGsmch;
    }

    public BtJfJtJfmx createGsmch(String createGsmch) {
        this.createGsmch = createGsmch;
        return this;
    }

    public void setCreateGsmch(String createGsmch) {
        this.createGsmch = createGsmch;
    }

    public Long getCreateBmid() {
        return this.createBmid;
    }

    public BtJfJtJfmx createBmid(Long createBmid) {
        this.createBmid = createBmid;
        return this;
    }

    public void setCreateBmid(Long createBmid) {
        this.createBmid = createBmid;
    }

    public String getCreateBmm() {
        return this.createBmm;
    }

    public BtJfJtJfmx createBmm(String createBmm) {
        this.createBmm = createBmm;
        return this;
    }

    public void setCreateBmm(String createBmm) {
        this.createBmm = createBmm;
    }

    public Long getCreateGwid() {
        return this.createGwid;
    }

    public BtJfJtJfmx createGwid(Long createGwid) {
        this.createGwid = createGwid;
        return this;
    }

    public void setCreateGwid(Long createGwid) {
        this.createGwid = createGwid;
    }

    public String getCreateGwm() {
        return this.createGwm;
    }

    public BtJfJtJfmx createGwm(String createGwm) {
        this.createGwm = createGwm;
        return this;
    }

    public void setCreateGwm(String createGwm) {
        this.createGwm = createGwm;
    }

    public String getModifyby() {
        return this.modifyby;
    }

    public BtJfJtJfmx modifyby(String modifyby) {
        this.modifyby = modifyby;
        return this;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby;
    }

    public Long getModifybyid() {
        return this.modifybyid;
    }

    public BtJfJtJfmx modifybyid(Long modifybyid) {
        this.modifybyid = modifybyid;
        return this;
    }

    public void setModifybyid(Long modifybyid) {
        this.modifybyid = modifybyid;
    }

    public LocalDate getModifyon() {
        return this.modifyon;
    }

    public BtJfJtJfmx modifyon(LocalDate modifyon) {
        this.modifyon = modifyon;
        return this;
    }

    public void setModifyon(LocalDate modifyon) {
        this.modifyon = modifyon;
    }

    public JfMaster getMaster() {
        return this.master;
    }

    public BtJfJtJfmx master(JfMaster jfMaster) {
        this.setMaster(jfMaster);
        return this;
    }

    public void setMaster(JfMaster jfMaster) {
        this.master = jfMaster;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BtJfJtJfmx)) {
            return false;
        }
        return id != null && id.equals(((BtJfJtJfmx) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BtJfJtJfmx{" +
            "id=" + getId() +
            ", zygs=" + getZygs() +
            ", zyetrid=" + getZyetrid() +
            ", flmch='" + getFlmch() + "'" +
            ", fl=" + getFl() +
            ", shl=" + getShl() +
            ", je=" + getJe() +
            ", shlv=" + getShlv() +
            ", she=" + getShe() +
            ", shhje=" + getShhje() +
            ", jmje=" + getJmje() +
            ", startd='" + getStartd() + "'" +
            ", endd='" + getEndd() + "'" +
            ", mark1='" + getMark1() + "'" +
            ", mark2='" + getMark2() + "'" +
            ", createby='" + getCreateby() + "'" +
            ", createbyid=" + getCreatebyid() +
            ", createon='" + getCreateon() + "'" +
            ", createGsid='" + getCreateGsid() + "'" +
            ", createGsmch='" + getCreateGsmch() + "'" +
            ", createBmid=" + getCreateBmid() +
            ", createBmm='" + getCreateBmm() + "'" +
            ", createGwid=" + getCreateGwid() +
            ", createGwm='" + getCreateGwm() + "'" +
            ", modifyby='" + getModifyby() + "'" +
            ", modifybyid=" + getModifybyid() +
            ", modifyon='" + getModifyon() + "'" +
            "}";
    }
}
