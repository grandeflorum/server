package com.grandeflorum.contract.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "HOUSE_STOCK_TRADE_TEMPLATE")
public class StockTradeTemplate implements Cloneable{
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

    /**
     *出卖人
     */
    @Column(name = "HT2")
    public String ht2;

    /**
     *买受人
     */
    @Column(name = "HT3")
    public String ht3;

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

    @Column(name = "JF10")
    public String jf10;

    @Column(name = "JF11")
    public String jf11;

    @Column(name = "JF12")
    public String jf12;

    @Column(name = "JF13")
    public String jf13;

    @Column(name = "JF14")
    public String jf14;

    @Column(name = "JF15")
    public String jf15;

    @Column(name = "JF16")
    public String jf16;

    @Column(name = "JF17")
    public String jf17;

    @Column(name = "JF18")
    public String jf18;

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

    @Column(name = "YF10")
    public String yf10;

    @Column(name = "YF11")
    public String yf11;

    @Column(name = "YF12")
    public String yf12;

    @Column(name = "YF13")
    public String yf13;

    @Column(name = "YF14")
    public String yf14;

    @Column(name = "YF15")
    public String yf15;

    @Column(name = "YF16")
    public String yf16;

    @Column(name = "YF17")
    public String yf17;

    @Column(name = "YF18")
    public String yf18;

    @Column(name = "D1T1")
    public String d1t1;

    @Column(name = "D1T2")
    public String d1t2;

    @Column(name = "D1T3")
    public String d1t3;

    @Column(name = "D1T4")
    public String d1t4;

    @Column(name = "D1T5")
    public String d1t5;

    @Column(name = "D1T6")
    public String d1t6;

    @Column(name = "D1T7")
    public String d1t7;

    @Column(name = "D1T8")
    public String d1t8;

    @Column(name = "D1T9")
    public String d1t9;

    @Column(name = "D1T10")
    public String d1t10;

    @Column(name = "D2T1")
    public String d2t1;

    @Column(name = "D2T2")
    public String d2t2;

    @Column(name = "D3T1")
    public String d3t1;

    @Column(name = "D3T2")
    public String d3t2;

    @Column(name = "D3T3")
    public String d3t3;

    @Column(name = "D3T4")
    public String d3t4;

    @Column(name = "D3T5")
    public String d3t5;

    @Column(name = "D3T6")
    public String d3t6;

    @Column(name = "D3T7")
    public String d3t7;

    @Column(name = "D3T8")
    public String d3t8;

    @Column(name = "D3T9")
    public String d3t9;

    @Column(name = "D3T10")
    public String d3t10;

    @Column(name = "D3T11")
    public String d3t11;

    @Column(name = "D3T12")
    public String d3t12;

    @Column(name = "D3T13")
    public String d3t13;

    @Column(name = "D3T14")
    public String d3t14;

    @Column(name = "D3T15")
    public String d3t15;

    @Column(name = "D3T16")
    public String d3t16;

    @Column(name = "D3T17")
    public String d3t17;

    @Column(name = "D3T18")
    public String d3t18;

    @Column(name = "D3T19")
    public String d3t19;

    @Column(name = "D3T20")
    public String d3t20;

    @Column(name = "D3T21")
    public String d3t21;

    @Column(name = "D3T22")
    public String d3t22;

    @Column(name = "D3T23")
    public String d3t23;

    @Column(name = "D3T24")
    public String d3t24;

    @Column(name = "D3T25")
    public String d3t25;

    @Column(name = "D3T26")
    public String d3t26;

    @Column(name = "D3T27")
    public String d3t27;

    @Column(name = "D4T1")
    public String d4t1;

    @Column(name = "D4T2")
    public String d4t2;

    @Column(name = "D5T1")
    public String d5t1;

    @Column(name = "D6T1")
    public String d6t1;

    @Column(name = "D6T2")
    public String d6t2;

    @Column(name = "D7T1")
    public String d7t1;

    @Column(name = "D7T2")
    public String d7t2;

    @Column(name = "D7T3")
    public String d7t3;

    @Column(name = "D7T4")
    public String d7t4;

    @Column(name = "D8T1")
    public String d8t1;

    @Column(name = "D8T2")
    public String d8t2;

    @Column(name = "D8T3")
    public String d8t3;

    @Column(name = "D8T4")
    public String d8t4;

    @Column(name = "D9T1")
    public String d9t1;

    @Column(name = "D9T2")
    public String d9t2;

    @Column(name = "D9T3")
    public String d9t3;

    @Column(name = "D9T4")
    public String d9t4;

    @Column(name = "D10T1")
    public String d10t1;

    @Column(name = "D10T2")
    public String d10t2;

    @Column(name = "D10T3")
    public String d10t3;

    @Column(name = "D11T1")
    public String d11t1;

    @Column(name = "D11T2")
    public String d11t2;

    @Column(name = "D12T1")
    public String d12t1;

    @Column(name = "D12T2")
    public String d12t2;

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

    public String getHt2() {
        return ht2;
    }

    public void setHt2(String ht2) {
        this.ht2 = ht2;
    }

    public String getHt3() {
        return ht3;
    }

    public void setHt3(String ht3) {
        this.ht3 = ht3;
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

    public String getJf10() {
        return jf10;
    }

    public void setJf10(String jf10) {
        this.jf10 = jf10;
    }

    public String getJf11() {
        return jf11;
    }

    public void setJf11(String jf11) {
        this.jf11 = jf11;
    }

    public String getJf12() {
        return jf12;
    }

    public void setJf12(String jf12) {
        this.jf12 = jf12;
    }

    public String getJf13() {
        return jf13;
    }

    public void setJf13(String jf13) {
        this.jf13 = jf13;
    }

    public String getJf14() {
        return jf14;
    }

    public void setJf14(String jf14) {
        this.jf14 = jf14;
    }

    public String getJf15() {
        return jf15;
    }

    public void setJf15(String jf15) {
        this.jf15 = jf15;
    }

    public String getJf16() {
        return jf16;
    }

    public void setJf16(String jf16) {
        this.jf16 = jf16;
    }

    public String getJf17() {
        return jf17;
    }

    public void setJf17(String jf17) {
        this.jf17 = jf17;
    }

    public String getJf18() {
        return jf18;
    }

    public void setJf18(String jf18) {
        this.jf18 = jf18;
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

    public String getYf10() {
        return yf10;
    }

    public void setYf10(String yf10) {
        this.yf10 = yf10;
    }

    public String getYf11() {
        return yf11;
    }

    public void setYf11(String yf11) {
        this.yf11 = yf11;
    }

    public String getYf12() {
        return yf12;
    }

    public void setYf12(String yf12) {
        this.yf12 = yf12;
    }

    public String getYf13() {
        return yf13;
    }

    public void setYf13(String yf13) {
        this.yf13 = yf13;
    }

    public String getYf14() {
        return yf14;
    }

    public void setYf14(String yf14) {
        this.yf14 = yf14;
    }

    public String getYf15() {
        return yf15;
    }

    public void setYf15(String yf15) {
        this.yf15 = yf15;
    }

    public String getYf16() {
        return yf16;
    }

    public void setYf16(String yf16) {
        this.yf16 = yf16;
    }

    public String getYf17() {
        return yf17;
    }

    public void setYf17(String yf17) {
        this.yf17 = yf17;
    }

    public String getYf18() {
        return yf18;
    }

    public void setYf18(String yf18) {
        this.yf18 = yf18;
    }

    public String getD1t1() {
        return d1t1;
    }

    public void setD1t1(String d1t1) {
        this.d1t1 = d1t1;
    }

    public String getD1t2() {
        return d1t2;
    }

    public void setD1t2(String d1t2) {
        this.d1t2 = d1t2;
    }

    public String getD1t3() {
        return d1t3;
    }

    public void setD1t3(String d1t3) {
        this.d1t3 = d1t3;
    }

    public String getD1t4() {
        return d1t4;
    }

    public void setD1t4(String d1t4) {
        this.d1t4 = d1t4;
    }

    public String getD1t5() {
        return d1t5;
    }

    public void setD1t5(String d1t5) {
        this.d1t5 = d1t5;
    }

    public String getD1t6() {
        return d1t6;
    }

    public void setD1t6(String d1t6) {
        this.d1t6 = d1t6;
    }

    public String getD1t7() {
        return d1t7;
    }

    public void setD1t7(String d1t7) {
        this.d1t7 = d1t7;
    }

    public String getD1t8() {
        return d1t8;
    }

    public void setD1t8(String d1t8) {
        this.d1t8 = d1t8;
    }

    public String getD1t9() {
        return d1t9;
    }

    public void setD1t9(String d1t9) {
        this.d1t9 = d1t9;
    }

    public String getD1t10() {
        return d1t10;
    }

    public void setD1t10(String d1t10) {
        this.d1t10 = d1t10;
    }

    public String getD2t1() {
        return d2t1;
    }

    public void setD2t1(String d2t1) {
        this.d2t1 = d2t1;
    }

    public String getD2t2() {
        return d2t2;
    }

    public void setD2t2(String d2t2) {
        this.d2t2 = d2t2;
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

    public String getD3t3() {
        return d3t3;
    }

    public void setD3t3(String d3t3) {
        this.d3t3 = d3t3;
    }

    public String getD3t4() {
        return d3t4;
    }

    public void setD3t4(String d3t4) {
        this.d3t4 = d3t4;
    }

    public String getD3t5() {
        return d3t5;
    }

    public void setD3t5(String d3t5) {
        this.d3t5 = d3t5;
    }

    public String getD3t6() {
        return d3t6;
    }

    public void setD3t6(String d3t6) {
        this.d3t6 = d3t6;
    }

    public String getD3t7() {
        return d3t7;
    }

    public void setD3t7(String d3t7) {
        this.d3t7 = d3t7;
    }

    public String getD3t8() {
        return d3t8;
    }

    public void setD3t8(String d3t8) {
        this.d3t8 = d3t8;
    }

    public String getD3t9() {
        return d3t9;
    }

    public void setD3t9(String d3t9) {
        this.d3t9 = d3t9;
    }

    public String getD3t10() {
        return d3t10;
    }

    public void setD3t10(String d3t10) {
        this.d3t10 = d3t10;
    }

    public String getD3t11() {
        return d3t11;
    }

    public void setD3t11(String d3t11) {
        this.d3t11 = d3t11;
    }

    public String getD3t12() {
        return d3t12;
    }

    public void setD3t12(String d3t12) {
        this.d3t12 = d3t12;
    }

    public String getD3t13() {
        return d3t13;
    }

    public void setD3t13(String d3t13) {
        this.d3t13 = d3t13;
    }

    public String getD3t14() {
        return d3t14;
    }

    public void setD3t14(String d3t14) {
        this.d3t14 = d3t14;
    }

    public String getD3t15() {
        return d3t15;
    }

    public void setD3t15(String d3t15) {
        this.d3t15 = d3t15;
    }

    public String getD3t16() {
        return d3t16;
    }

    public void setD3t16(String d3t16) {
        this.d3t16 = d3t16;
    }

    public String getD3t17() {
        return d3t17;
    }

    public void setD3t17(String d3t17) {
        this.d3t17 = d3t17;
    }

    public String getD3t18() {
        return d3t18;
    }

    public void setD3t18(String d3t18) {
        this.d3t18 = d3t18;
    }

    public String getD3t19() {
        return d3t19;
    }

    public void setD3t19(String d3t19) {
        this.d3t19 = d3t19;
    }

    public String getD3t20() {
        return d3t20;
    }

    public void setD3t20(String d3t20) {
        this.d3t20 = d3t20;
    }

    public String getD3t21() {
        return d3t21;
    }

    public void setD3t21(String d3t21) {
        this.d3t21 = d3t21;
    }

    public String getD3t22() {
        return d3t22;
    }

    public void setD3t22(String d3t22) {
        this.d3t22 = d3t22;
    }

    public String getD3t23() {
        return d3t23;
    }

    public void setD3t23(String d3t23) {
        this.d3t23 = d3t23;
    }

    public String getD3t24() {
        return d3t24;
    }

    public void setD3t24(String d3t24) {
        this.d3t24 = d3t24;
    }

    public String getD3t25() {
        return d3t25;
    }

    public void setD3t25(String d3t25) {
        this.d3t25 = d3t25;
    }

    public String getD3t26() {
        return d3t26;
    }

    public void setD3t26(String d3t26) {
        this.d3t26 = d3t26;
    }

    public String getD3t27() {
        return d3t27;
    }

    public void setD3t27(String d3t27) {
        this.d3t27 = d3t27;
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

    public String getD5t1() {
        return d5t1;
    }

    public void setD5t1(String d5t1) {
        this.d5t1 = d5t1;
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

    public String getD7t1() {
        return d7t1;
    }

    public void setD7t1(String d7t1) {
        this.d7t1 = d7t1;
    }

    public String getD7t2() {
        return d7t2;
    }

    public void setD7t2(String d7t2) {
        this.d7t2 = d7t2;
    }

    public String getD7t3() {
        return d7t3;
    }

    public void setD7t3(String d7t3) {
        this.d7t3 = d7t3;
    }

    public String getD7t4() {
        return d7t4;
    }

    public void setD7t4(String d7t4) {
        this.d7t4 = d7t4;
    }

    public String getD8t1() {
        return d8t1;
    }

    public void setD8t1(String d8t1) {
        this.d8t1 = d8t1;
    }

    public String getD8t2() {
        return d8t2;
    }

    public void setD8t2(String d8t2) {
        this.d8t2 = d8t2;
    }

    public String getD8t3() {
        return d8t3;
    }

    public void setD8t3(String d8t3) {
        this.d8t3 = d8t3;
    }

    public String getD8t4() {
        return d8t4;
    }

    public void setD8t4(String d8t4) {
        this.d8t4 = d8t4;
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

    public String getD9t3() {
        return d9t3;
    }

    public void setD9t3(String d9t3) {
        this.d9t3 = d9t3;
    }

    public String getD9t4() {
        return d9t4;
    }

    public void setD9t4(String d9t4) {
        this.d9t4 = d9t4;
    }

    public String getD10t1() {
        return d10t1;
    }

    public void setD10t1(String d10t1) {
        this.d10t1 = d10t1;
    }

    public String getD10t2() {
        return d10t2;
    }

    public void setD10t2(String d10t2) {
        this.d10t2 = d10t2;
    }

    public String getD10t3() {
        return d10t3;
    }

    public void setD10t3(String d10t3) {
        this.d10t3 = d10t3;
    }

    public String getD11t1() {
        return d11t1;
    }

    public void setD11t1(String d11t1) {
        this.d11t1 = d11t1;
    }

    public String getD11t2() {
        return d11t2;
    }

    public void setD11t2(String d11t2) {
        this.d11t2 = d11t2;
    }

    public String getD12t1() {
        return d12t1;
    }

    public void setD12t1(String d12t1) {
        this.d12t1 = d12t1;
    }

    public String getD12t2() {
        return d12t2;
    }

    public void setD12t2(String d12t2) {
        this.d12t2 = d12t2;
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



}
