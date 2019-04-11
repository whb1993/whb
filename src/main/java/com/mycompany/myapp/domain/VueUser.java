package com.mycompany.myapp.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A VueUser.
 */
@Entity
@Table(name = "vue_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VueUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "jhi_password", nullable = false)
    private String password;

    @Column(name = "cname")
    private String cname;

    @Column(name = "user_pic")
    private String userPic;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private Integer age;

    @Column(name = "mobile")
    private Integer mobile;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "status")
    private String status;

    @Column(name = "creat_time")
    private LocalDate creatTime;

    @Column(name = "login_num")
    private Integer loginNum;

    @Column(name = "err_nmu")
    private Integer errNmu;

    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "creator")
    private String creator;

    @Column(name = "lock_time")
    private LocalDate lockTime;

    @Column(name = "lock_reason")
    private String lockReason;

    @Column(name = "description")
    private String description;

    @Column(name = "reserve")
    private String reserve;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public VueUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public VueUser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCname() {
        return cname;
    }

    public VueUser cname(String cname) {
        this.cname = cname;
        return this;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUserPic() {
        return userPic;
    }

    public VueUser userPic(String userPic) {
        this.userPic = userPic;
        return this;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getAddress() {
        return address;
    }

    public VueUser address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public VueUser age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMobile() {
        return mobile;
    }

    public VueUser mobile(Integer mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public VueUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public VueUser status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatTime() {
        return creatTime;
    }

    public VueUser creatTime(LocalDate creatTime) {
        this.creatTime = creatTime;
        return this;
    }

    public void setCreatTime(LocalDate creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public VueUser loginNum(Integer loginNum) {
        this.loginNum = loginNum;
        return this;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Integer getErrNmu() {
        return errNmu;
    }

    public VueUser errNmu(Integer errNmu) {
        this.errNmu = errNmu;
        return this;
    }

    public void setErrNmu(Integer errNmu) {
        this.errNmu = errNmu;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public VueUser deptId(Integer deptId) {
        this.deptId = deptId;
        return this;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getCreator() {
        return creator;
    }

    public VueUser creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDate getLockTime() {
        return lockTime;
    }

    public VueUser lockTime(LocalDate lockTime) {
        this.lockTime = lockTime;
        return this;
    }

    public void setLockTime(LocalDate lockTime) {
        this.lockTime = lockTime;
    }

    public String getLockReason() {
        return lockReason;
    }

    public VueUser lockReason(String lockReason) {
        this.lockReason = lockReason;
        return this;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }

    public String getDescription() {
        return description;
    }

    public VueUser description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReserve() {
        return reserve;
    }

    public VueUser reserve(String reserve) {
        this.reserve = reserve;
        return this;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VueUser vueUser = (VueUser) o;
        if (vueUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vueUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VueUser{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", password='" + getPassword() + "'" +
            ", cname='" + getCname() + "'" +
            ", userPic='" + getUserPic() + "'" +
            ", address='" + getAddress() + "'" +
            ", age=" + getAge() +
            ", mobile=" + getMobile() +
            ", email='" + getEmail() + "'" +
            ", status='" + getStatus() + "'" +
            ", creatTime='" + getCreatTime() + "'" +
            ", loginNum=" + getLoginNum() +
            ", errNmu=" + getErrNmu() +
            ", deptId=" + getDeptId() +
            ", creator='" + getCreator() + "'" +
            ", lockTime='" + getLockTime() + "'" +
            ", lockReason='" + getLockReason() + "'" +
            ", description='" + getDescription() + "'" +
            ", reserve='" + getReserve() + "'" +
            "}";
    }
}
