package com.mycompany.myapp.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BestUser.
 */
@Entity
@Table(name = "best_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BestUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_1")
    private Integer value1;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_2")
    private Integer value2;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_3")
    private Integer value3;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_4")
    private Integer value4;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_5")
    private Integer value5;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_6")
    private Integer value6;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_7")
    private Integer value7;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_8")
    private Integer value8;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_9")
    private Integer value9;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_10")
    private Integer value10;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_11")
    private Integer value11;

    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "value_12")
    private Integer value12;

    @Column(name = "yuanyin")
    private String yuanyin;

    @Column(name = "des_1")
    private String des1;

    @Column(name = "des_2")
    private String des2;

    @Column(name = "des_3")
    private String des3;

    @Column(name = "des_4")
    private String des4;

    @Column(name = "des_5")
    private String des5;

    @Column(name = "des_6")
    private String des6;

    @Column(name = "des_7")
    private String des7;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public BestUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getValue1() {
        return value1;
    }

    public BestUser value1(Integer value1) {
        this.value1 = value1;
        return this;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    public Integer getValue2() {
        return value2;
    }

    public BestUser value2(Integer value2) {
        this.value2 = value2;
        return this;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }

    public Integer getValue3() {
        return value3;
    }

    public BestUser value3(Integer value3) {
        this.value3 = value3;
        return this;
    }

    public void setValue3(Integer value3) {
        this.value3 = value3;
    }

    public Integer getValue4() {
        return value4;
    }

    public BestUser value4(Integer value4) {
        this.value4 = value4;
        return this;
    }

    public void setValue4(Integer value4) {
        this.value4 = value4;
    }

    public Integer getValue5() {
        return value5;
    }

    public BestUser value5(Integer value5) {
        this.value5 = value5;
        return this;
    }

    public void setValue5(Integer value5) {
        this.value5 = value5;
    }

    public Integer getValue6() {
        return value6;
    }

    public BestUser value6(Integer value6) {
        this.value6 = value6;
        return this;
    }

    public void setValue6(Integer value6) {
        this.value6 = value6;
    }

    public Integer getValue7() {
        return value7;
    }

    public BestUser value7(Integer value7) {
        this.value7 = value7;
        return this;
    }

    public void setValue7(Integer value7) {
        this.value7 = value7;
    }

    public Integer getValue8() {
        return value8;
    }

    public BestUser value8(Integer value8) {
        this.value8 = value8;
        return this;
    }

    public void setValue8(Integer value8) {
        this.value8 = value8;
    }

    public Integer getValue9() {
        return value9;
    }

    public BestUser value9(Integer value9) {
        this.value9 = value9;
        return this;
    }

    public void setValue9(Integer value9) {
        this.value9 = value9;
    }

    public Integer getValue10() {
        return value10;
    }

    public BestUser value10(Integer value10) {
        this.value10 = value10;
        return this;
    }

    public void setValue10(Integer value10) {
        this.value10 = value10;
    }

    public Integer getValue11() {
        return value11;
    }

    public BestUser value11(Integer value11) {
        this.value11 = value11;
        return this;
    }

    public void setValue11(Integer value11) {
        this.value11 = value11;
    }

    public Integer getValue12() {
        return value12;
    }

    public BestUser value12(Integer value12) {
        this.value12 = value12;
        return this;
    }

    public void setValue12(Integer value12) {
        this.value12 = value12;
    }

    public String getYuanyin() {
        return yuanyin;
    }

    public BestUser yuanyin(String yuanyin) {
        this.yuanyin = yuanyin;
        return this;
    }

    public void setYuanyin(String yuanyin) {
        this.yuanyin = yuanyin;
    }

    public String getDes1() {
        return des1;
    }

    public BestUser des1(String des1) {
        this.des1 = des1;
        return this;
    }

    public void setDes1(String des1) {
        this.des1 = des1;
    }

    public String getDes2() {
        return des2;
    }

    public BestUser des2(String des2) {
        this.des2 = des2;
        return this;
    }

    public void setDes2(String des2) {
        this.des2 = des2;
    }

    public String getDes3() {
        return des3;
    }

    public BestUser des3(String des3) {
        this.des3 = des3;
        return this;
    }

    public void setDes3(String des3) {
        this.des3 = des3;
    }

    public String getDes4() {
        return des4;
    }

    public BestUser des4(String des4) {
        this.des4 = des4;
        return this;
    }

    public void setDes4(String des4) {
        this.des4 = des4;
    }

    public String getDes5() {
        return des5;
    }

    public BestUser des5(String des5) {
        this.des5 = des5;
        return this;
    }

    public void setDes5(String des5) {
        this.des5 = des5;
    }

    public String getDes6() {
        return des6;
    }

    public BestUser des6(String des6) {
        this.des6 = des6;
        return this;
    }

    public void setDes6(String des6) {
        this.des6 = des6;
    }

    public String getDes7() {
        return des7;
    }

    public BestUser des7(String des7) {
        this.des7 = des7;
        return this;
    }

    public void setDes7(String des7) {
        this.des7 = des7;
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
        BestUser bestUser = (BestUser) o;
        if (bestUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bestUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BestUser{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", value1=" + getValue1() +
            ", value2=" + getValue2() +
            ", value3=" + getValue3() +
            ", value4=" + getValue4() +
            ", value5=" + getValue5() +
            ", value6=" + getValue6() +
            ", value7=" + getValue7() +
            ", value8=" + getValue8() +
            ", value9=" + getValue9() +
            ", value10=" + getValue10() +
            ", value11=" + getValue11() +
            ", value12=" + getValue12() +
            ", yuanyin='" + getYuanyin() + "'" +
            ", des1='" + getDes1() + "'" +
            ", des2='" + getDes2() + "'" +
            ", des3='" + getDes3() + "'" +
            ", des4='" + getDes4() + "'" +
            ", des5='" + getDes5() + "'" +
            ", des6='" + getDes6() + "'" +
            ", des7='" + getDes7() + "'" +
            "}";
    }
}
