package com.grandeflorum.contract.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CONTRACT_ENTRUSTMENT")
public class ContractEntrustment implements Cloneable{

    @Id
    @Column(name = "ID")
    public String id;

    /**
     *存量房合同备案id
     */
    @Column(name = "STOCKTRADEID")
    public String stocktradeid;

    /**
     *合同编号
     */
    @Column(name = "HT1")
    public String ht1;

    @Column(name = "JF1")
    public String jf1;

    @Column(name = "JF2")
    public String jf2;

    @Column(name = "JF3")
    public String jf3;

    @Column(name = "JF4")
    public String jf4;

    @Column(name = "JF5")
    public String jf5;

    @Column(name = "JF6")
    public String jf6;

    @Column(name = "JF7")
    public String jf7;

    @Column(name = "JF8")
    public String jf8;

    @Column(name = "JF9")
    public String jf9;

    @Column(name = "YF1")
    public String yf1;

    @Column(name = "YF2")
    public String yf2;

    @Column(name = "YF3")
    public String yf3;

    @Column(name = "YF4")
    public String yf4;

    @Column(name = "YF5")
    public String yf5;

    @Column(name = "YF6")
    public String yf6;

    @Column(name = "YF7")
    public String yf7;

    @Column(name = "YF8")
    public String yf8;

    @Column(name = "YF9")
    public String yf9;

    @Column(name = "D1T1")
    public String d1t1;

    @Column(name = "D2T1")
    public String d2t1;

    @Column(name = "D3T1")
    public String d3t1;

    @Column(name = "D3T2")
    public String d3t2;

    @Column(name = "D4T1")
    public String d4t1;

    @Column(name = "D4T2")
    public String d4t2;

    @Column(name = "D6T1")
    public String d6t1;

    @Column(name = "D6T2")
    public String d6t2;

    @Column(name = "D6T3")
    public String d6t3;

    @Column(name = "D6T4")
    public String d6t4;

    @Column(name = "D6T5")
    public String d6t5;

    @Column(name = "D9T1")
    public String d9t1;

    @Column(name = "D9T2")
    public String d9t2;

    @Column(name = "D12T1")
    public String d12t1;

    @Column(name = "FJ1JW1")
    public String fj1jw1;

    @Column(name = "FJ1JW2")
    public String fj1jw2;

    @Column(name = "FJ1JW3")
    public String fj1jw3;

    @Column(name = "FJ1JW4")
    public String fj1jw4;

    @Column(name = "FJ1JW5")
    public String fj1jw5;

    @Column(name = "FJ1JW6")
    public String fj1jw6;

    @Column(name = "FJ1JW7")
    public String fj1jw7;

    @Column(name = "FJ1JW8")
    public String fj1jw8;

    @Column(name = "FJ1JW9")
    public String fj1jw9;

    @Column(name = "FJ1JW10")
    public String fj1jw10;

    @Column(name = "FJ1JW11")
    public String fj1jw11;

    @Column(name = "FJ1JW12")
    public String fj1jw12;

    @Column(name = "FJ1JW13")
    public String fj1jw13;

    @Column(name = "FJ1JW14")
    public String fj1jw14;

    @Column(name = "FJ1JW15")
    public String fj1jw15;

    @Column(name = "FJ1JW16")
    public String fj1jw16;

    @Column(name = "FJ1JW17")
    public String fj1jw17;

    @Column(name = "FJ1JW18")
    public String fj1jw18;

    @Column(name = "QZ1")
    public String qz1;

    @Column(name = "QZ2")
    public String qz2;

    @Column(name = "QZ3")
    public String qz3;

    @Column(name = "QZ4")
    public String qz4;

    @Column(name = "QZ5")
    public String qz5;

    @Column(name = "QZ6")
    public String qz6;

    @Column(name = "QZ7")
    public String qz7;

    @Column(name = "QZ8")
    public String qz8;

    @Column(name = "QZ9")
    public String qz9;

    @Column(name = "ISJFSFZZH")
    public Integer isjfsfzzh;

    @Column(name = "ISJFHZH")
    public Integer isjfhzh;

    @Column(name = "ISJFYYZZ")
    public Integer isjfyyzz;

    @Column(name = "ISYFSFZZH")
    public Integer isyfsfzzh;

    @Column(name = "ISYFHZH")
    public Integer isyfhzh;

    @Column(name = "ISYFYYZZ")
    public Integer isyfyyzz;

    @Column(name = "ISCS")
    public Integer iscs;

    @Column(name = "ISCZ")
    public Integer iscz;

    @Column(name = "ISCG")
    public Integer iscg;

    @Column(name = "ISCZ1")
    public Integer iscz1;

    @Column(name = "ISZH")
    public Integer iszh;

    public Integer getIscs() {
        return iscs;
    }

    public void setIscs(Integer iscs) {
        this.iscs = iscs;
    }

    public Integer getIscz() {
        return iscz;
    }

    public void setIscz(Integer iscz) {
        this.iscz = iscz;
    }

    public Integer getIscg() {
        return iscg;
    }

    public void setIscg(Integer iscg) {
        this.iscg = iscg;
    }

    public Integer getIscz1() {
        return iscz1;
    }

    public void setIscz1(Integer iscz1) {
        this.iscz1 = iscz1;
    }

    public Integer getIszh() {
        return iszh;
    }

    public void setIszh(Integer iszh) {
        this.iszh = iszh;
    }

    public Integer getIsjfsfzzh() {
        return isjfsfzzh;
    }

    public void setIsjfsfzzh(Integer isjfsfzzh) {
        this.isjfsfzzh = isjfsfzzh;
    }

    public Integer getIsjfhzh() {
        return isjfhzh;
    }

    public void setIsjfhzh(Integer isjfhzh) {
        this.isjfhzh = isjfhzh;
    }

    public Integer getIsjfyyzz() {
        return isjfyyzz;
    }

    public void setIsjfyyzz(Integer isjfyyzz) {
        this.isjfyyzz = isjfyyzz;
    }

    public Integer getIsyfsfzzh() {
        return isyfsfzzh;
    }

    public void setIsyfsfzzh(Integer isyfsfzzh) {
        this.isyfsfzzh = isyfsfzzh;
    }

    public Integer getIsyfhzh() {
        return isyfhzh;
    }

    public void setIsyfhzh(Integer isyfhzh) {
        this.isyfhzh = isyfhzh;
    }

    public Integer getIsyfyyzz() {
        return isyfyyzz;
    }

    public void setIsyfyyzz(Integer isyfyyzz) {
        this.isyfyyzz = isyfyyzz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStocktradeid() {
        return stocktradeid;
    }

    public void setStocktradeid(String stocktradeid) {
        this.stocktradeid = stocktradeid;
    }

    public String getHt1() {
        return ht1;
    }

    public void setHt1(String ht1) {
        this.ht1 = ht1;
    }

    public String getJf1() {
        return jf1;
    }

    public void setJf1(String jf1) {
        this.jf1 = jf1;
    }

    public String getJf2() {
        return jf2;
    }

    public void setJf2(String jf2) {
        this.jf2 = jf2;
    }

    public String getJf3() {
        return jf3;
    }

    public void setJf3(String jf3) {
        this.jf3 = jf3;
    }

    public String getJf4() {
        return jf4;
    }

    public void setJf4(String jf4) {
        this.jf4 = jf4;
    }

    public String getJf5() {
        return jf5;
    }

    public void setJf5(String jf5) {
        this.jf5 = jf5;
    }

    public String getJf6() {
        return jf6;
    }

    public void setJf6(String jf6) {
        this.jf6 = jf6;
    }

    public String getJf7() {
        return jf7;
    }

    public void setJf7(String jf7) {
        this.jf7 = jf7;
    }

    public String getJf8() {
        return jf8;
    }

    public void setJf8(String jf8) {
        this.jf8 = jf8;
    }

    public String getJf9() {
        return jf9;
    }

    public void setJf9(String jf9) {
        this.jf9 = jf9;
    }

    public String getYf1() {
        return yf1;
    }

    public void setYf1(String yf1) {
        this.yf1 = yf1;
    }

    public String getYf2() {
        return yf2;
    }

    public void setYf2(String yf2) {
        this.yf2 = yf2;
    }

    public String getYf3() {
        return yf3;
    }

    public void setYf3(String yf3) {
        this.yf3 = yf3;
    }

    public String getYf4() {
        return yf4;
    }

    public void setYf4(String yf4) {
        this.yf4 = yf4;
    }

    public String getYf5() {
        return yf5;
    }

    public void setYf5(String yf5) {
        this.yf5 = yf5;
    }

    public String getYf6() {
        return yf6;
    }

    public void setYf6(String yf6) {
        this.yf6 = yf6;
    }

    public String getYf7() {
        return yf7;
    }

    public void setYf7(String yf7) {
        this.yf7 = yf7;
    }

    public String getYf8() {
        return yf8;
    }

    public void setYf8(String yf8) {
        this.yf8 = yf8;
    }

    public String getYf9() {
        return yf9;
    }

    public void setYf9(String yf9) {
        this.yf9 = yf9;
    }

    public String getD1t1() {
        return d1t1;
    }

    public void setD1t1(String d1t1) {
        this.d1t1 = d1t1;
    }

    public String getD2t1() {
        return d2t1;
    }

    public void setD2t1(String d2t1) {
        this.d2t1 = d2t1;
    }

    public String getD3t1() {
        return d3t1;
    }

    public void setD3t1(String d3t1) {
        this.d3t1 = d3t1;
    }

    public String getD3t2() {
        return d3t2;
    }

    public void setD3t2(String d3t2) {
        this.d3t2 = d3t2;
    }

    public String getD4t1() {
        return d4t1;
    }

    public void setD4t1(String d4t1) {
        this.d4t1 = d4t1;
    }

    public String getD4t2() {
        return d4t2;
    }

    public void setD4t2(String d4t2) {
        this.d4t2 = d4t2;
    }

    public String getD6t1() {
        return d6t1;
    }

    public void setD6t1(String d6t1) {
        this.d6t1 = d6t1;
    }

    public String getD6t2() {
        return d6t2;
    }

    public void setD6t2(String d6t2) {
        this.d6t2 = d6t2;
    }

    public String getD6t3() {
        return d6t3;
    }

    public void setD6t3(String d6t3) {
        this.d6t3 = d6t3;
    }

    public String getD6t4() {
        return d6t4;
    }

    public void setD6t4(String d6t4) {
        this.d6t4 = d6t4;
    }

    public String getD6t5() {
        return d6t5;
    }

    public void setD6t5(String d6t5) {
        this.d6t5 = d6t5;
    }

    public String getD9t1() {
        return d9t1;
    }

    public void setD9t1(String d9t1) {
        this.d9t1 = d9t1;
    }

    public String getD9t2() {
        return d9t2;
    }

    public void setD9t2(String d9t2) {
        this.d9t2 = d9t2;
    }

    public String getD12t1() {
        return d12t1;
    }

    public void setD12t1(String d12t1) {
        this.d12t1 = d12t1;
    }

    public String getFj1jw1() {
        return fj1jw1;
    }

    public void setFj1jw1(String fj1jw1) {
        this.fj1jw1 = fj1jw1;
    }

    public String getFj1jw2() {
        return fj1jw2;
    }

    public void setFj1jw2(String fj1jw2) {
        this.fj1jw2 = fj1jw2;
    }

    public String getFj1jw3() {
        return fj1jw3;
    }

    public void setFj1jw3(String fj1jw3) {
        this.fj1jw3 = fj1jw3;
    }

    public String getFj1jw4() {
        return fj1jw4;
    }

    public void setFj1jw4(String fj1jw4) {
        this.fj1jw4 = fj1jw4;
    }

    public String getFj1jw5() {
        return fj1jw5;
    }

    public void setFj1jw5(String fj1jw5) {
        this.fj1jw5 = fj1jw5;
    }

    public String getFj1jw6() {
        return fj1jw6;
    }

    public void setFj1jw6(String fj1jw6) {
        this.fj1jw6 = fj1jw6;
    }

    public String getFj1jw7() {
        return fj1jw7;
    }

    public void setFj1jw7(String fj1jw7) {
        this.fj1jw7 = fj1jw7;
    }

    public String getFj1jw8() {
        return fj1jw8;
    }

    public void setFj1jw8(String fj1jw8) {
        this.fj1jw8 = fj1jw8;
    }

    public String getFj1jw9() {
        return fj1jw9;
    }

    public void setFj1jw9(String fj1jw9) {
        this.fj1jw9 = fj1jw9;
    }

    public String getFj1jw10() {
        return fj1jw10;
    }

    public void setFj1jw10(String fj1jw10) {
        this.fj1jw10 = fj1jw10;
    }

    public String getFj1jw11() {
        return fj1jw11;
    }

    public void setFj1jw11(String fj1jw11) {
        this.fj1jw11 = fj1jw11;
    }

    public String getFj1jw12() {
        return fj1jw12;
    }

    public void setFj1jw12(String fj1jw12) {
        this.fj1jw12 = fj1jw12;
    }

    public String getFj1jw13() {
        return fj1jw13;
    }

    public void setFj1jw13(String fj1jw13) {
        this.fj1jw13 = fj1jw13;
    }

    public String getFj1jw14() {
        return fj1jw14;
    }

    public void setFj1jw14(String fj1jw14) {
        this.fj1jw14 = fj1jw14;
    }

    public String getFj1jw15() {
        return fj1jw15;
    }

    public void setFj1jw15(String fj1jw15) {
        this.fj1jw15 = fj1jw15;
    }

    public String getFj1jw16() {
        return fj1jw16;
    }

    public void setFj1jw16(String fj1jw16) {
        this.fj1jw16 = fj1jw16;
    }

    public String getFj1jw17() {
        return fj1jw17;
    }

    public void setFj1jw17(String fj1jw17) {
        this.fj1jw17 = fj1jw17;
    }

    public String getFj1jw18() {
        return fj1jw18;
    }

    public void setFj1jw18(String fj1jw18) {
        this.fj1jw18 = fj1jw18;
    }

    public String getQz1() {
        return qz1;
    }

    public void setQz1(String qz1) {
        this.qz1 = qz1;
    }

    public String getQz2() {
        return qz2;
    }

    public void setQz2(String qz2) {
        this.qz2 = qz2;
    }

    public String getQz3() {
        return qz3;
    }

    public void setQz3(String qz3) {
        this.qz3 = qz3;
    }

    public String getQz4() {
        return qz4;
    }

    public void setQz4(String qz4) {
        this.qz4 = qz4;
    }

    public String getQz5() {
        return qz5;
    }

    public void setQz5(String qz5) {
        this.qz5 = qz5;
    }

    public String getQz6() {
        return qz6;
    }

    public void setQz6(String qz6) {
        this.qz6 = qz6;
    }

    public String getQz7() {
        return qz7;
    }

    public void setQz7(String qz7) {
        this.qz7 = qz7;
    }

    public String getQz8() {
        return qz8;
    }

    public void setQz8(String qz8) {
        this.qz8 = qz8;
    }

    public String getQz9() {
        return qz9;
    }

    public void setQz9(String qz9) {
        this.qz9 = qz9;
    }
}
