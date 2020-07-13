package com.grandeflorum.contract.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CASH_SALES_TEMPLATE")
public class CashSalesTemplate implements Cloneable{

    @Id
    @Column(name = "ID")
    public String id;

    /**
     *合同编号
     */
    @Column(name = "HOUSETRADEID")
    public String housetradeid;

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

    @Column(name = "YF19")
    public String yf19;

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

    @Column(name = "D4T1")
    public String d4t1;

    @Column(name = "D4T2")
    public String d4t2;

    @Column(name = "D4T3")
    public String d4t3;

    @Column(name = "D4T4")
    public String d4t4;

    @Column(name = "D4T5")
    public String d4t5;

    @Column(name = "D5T1")
    public String d5t1;

    @Column(name = "D5T2")
    public String d5t2;

    @Column(name = "D5T3")
    public String d5t3;

    @Column(name = "D6T1")
    public String d6t1;

    @Column(name = "D6T2")
    public String d6t2;

    @Column(name = "D6T3")
    public String d6t3;

    @Column(name = "D7T1")
    public String d7t1;

    @Column(name = "D7T2")
    public String d7t2;

    @Column(name = "D7T3")
    public String d7t3;

    @Column(name = "D7T4")
    public String d7t4;

    @Column(name = "D7T5")
    public String d7t5;

    @Column(name = "D7T6")
    public String d7t6;

    @Column(name = "D7T7")
    public String d7t7;

    @Column(name = "D7T8")
    public String d7t8;

    @Column(name = "D7T9")
    public String d7t9;

    @Column(name = "D7T10")
    public String d7t10;

    @Column(name = "D7T11")
    public String d7t11;

    @Column(name = "D7T12")
    public String d7t12;

    @Column(name = "D7T13")
    public String d7t13;

    @Column(name = "D7T14")
    public String d7t14;

    @Column(name = "D7T15")
    public String d7t15;

    @Column(name = "D7T16")
    public String d7t16;

    @Column(name = "D7T17")
    public String d7t17;

    @Column(name = "D7T18")
    public String d7t18;

    @Column(name = "D8T1")
    public String d8t1;

    @Column(name = "D8T2")
    public String d8t2;

    @Column(name = "D8T3")
    public String d8t3;

    @Column(name = "D8T4")
    public String d8t4;

    @Column(name = "D8T5")
    public String d8t5;

    @Column(name = "D8T6")
    public String d8t6;

    @Column(name = "D8T7")
    public String d8t7;

    @Column(name = "D8T8")
    public String d8t8;

    @Column(name = "D8T9")
    public String d8t9;

    @Column(name = "D8T10")
    public String d8t10;

    @Column(name = "D8T11")
    public String d8t11;

    @Column(name = "D8T12")
    public String d8t12;

    @Column(name = "D8T13")
    public String d8t13;

    @Column(name = "D8T14")
    public String d8t14;

    @Column(name = "D8T15")
    public String d8t15;

    @Column(name = "D8T16")
    public String d8t16;

    @Column(name = "D8T17")
    public String d8t17;

    @Column(name = "D8T18")
    public String d8t18;

    @Column(name = "D8T19")
    public String d8t19;

    @Column(name = "D8T20")
    public String d8t20;

    @Column(name = "D8T21")
    public String d8t21;

    @Column(name = "D8T22")
    public String d8t22;

    @Column(name = "D8T23")
    public String d8t23;

    @Column(name = "D8T24")
    public String d8t24;

    @Column(name = "D8T25")
    public String d8t25;

    @Column(name = "D8T26")
    public String d8t26;

    @Column(name = "D8T27")
    public String d8t27;

    @Column(name = "D9T1")
    public String d9t1;

    @Column(name = "D9T2")
    public String d9t2;

    @Column(name = "D9T3")
    public String d9t3;

    @Column(name = "D9T4")
    public String d9t4;

    @Column(name = "D9T5")
    public String d9t5;

    @Column(name = "D9T6")
    public String d9t6;

    @Column(name = "D9T7")
    public String d9t7;

    @Column(name = "D9T8")
    public String d9t8;

    @Column(name = "D10T1")
    public String d10t1;

    @Column(name = "D10T2")
    public String d10t2;

    @Column(name = "D10T3")
    public String d10t3;

    @Column(name = "D10T4")
    public String d10t4;

    @Column(name = "D11T1")
    public String d11t1;

    @Column(name = "D11T2")
    public String d11t2;

    @Column(name = "D11T3")
    public String d11t3;

    @Column(name = "D11T4")
    public String d11t4;

    @Column(name = "D11T5")
    public String d11t5;

    @Column(name = "D11T6")
    public String d11t6;

    @Column(name = "D11T7")
    public String d11t7;

    @Column(name = "D11T8")
    public String d11t8;

    @Column(name = "D11T9")
    public String d11t9;

    @Column(name = "D11T10")
    public String d11t10;

    @Column(name = "D11T11")
    public String d11t11;

    @Column(name = "D11T12")
    public String d11t12;

    @Column(name = "D11T13")
    public String d11t13;

    @Column(name = "D11T14")
    public String d11t14;

    @Column(name = "D11T15")
    public String d11t15;

    @Column(name = "D11T16")
    public String d11t16;

    @Column(name = "D11T17")
    public String d11t17;

    @Column(name = "D11T18")
    public String d11t18;

    @Column(name = "D11T19")
    public String d11t19;

    @Column(name = "D11T20")
    public String d11t20;

    @Column(name = "D11T21")
    public String d11t21;

    @Column(name = "D11T22")
    public String d11t22;

    @Column(name = "D11T23")
    public String d11t23;

    @Column(name = "D11T24")
    public String d11t24;

    @Column(name = "D11T25")
    public String d11t25;

    @Column(name = "D11T26")
    public String d11t26;

    @Column(name = "D11T27")
    public String d11t27;

    @Column(name = "D11T28")
    public String d11t28;

    @Column(name = "D11T29")
    public String d11t29;

    @Column(name = "D11T30")
    public String d11t30;

    @Column(name = "D11T31")
    public String d11t31;

    @Column(name = "D12T1")
    public String d12t1;

    @Column(name = "D12T2")
    public String d12t2;

    @Column(name = "D12T3")
    public String d12t3;

    @Column(name = "D12T4")
    public String d12t4;

    @Column(name = "D12T5")
    public String d12t5;

    @Column(name = "D12T6")
    public String d12t6;

    @Column(name = "D12T7")
    public String d12t7;

    @Column(name = "D12T8")
    public String d12t8;

    @Column(name = "D13T1")
    public String d13t1;

    @Column(name = "D13T2")
    public String d13t2;

    @Column(name = "D13T3")
    public String d13t3;

    @Column(name = "D13T4")
    public String d13t4;

    @Column(name = "D13T5")
    public String d13t5;

    @Column(name = "D13T6")
    public String d13t6;

    @Column(name = "D13T7")
    public String d13t7;

    @Column(name = "D13T8")
    public String d13t8;

    @Column(name = "D14T1")
    public String d14t1;

    @Column(name = "D14T2")
    public String d14t2;

    @Column(name = "D14T3")
    public String d14t3;

    @Column(name = "D14T4")
    public String d14t4;

    @Column(name = "D14T5")
    public String d14t5;

    @Column(name = "D14T6")
    public String d14t6;

    @Column(name = "D14T7")
    public String d14t7;

    @Column(name = "D14T8")
    public String d14t8;

    @Column(name = "D14T9")
    public String d14t9;

    @Column(name = "D14T10")
    public String d14t10;

    @Column(name = "D14T11")
    public String d14t11;

    @Column(name = "D14T12")
    public String d14t12;

    @Column(name = "D14T13")
    public String d14t13;

    @Column(name = "D14T14")
    public String d14t14;

    @Column(name = "D14T15")
    public String d14t15;

    @Column(name = "D15T1")
    public String d15t1;

    @Column(name = "D15T2")
    public String d15t2;

    @Column(name = "D16T1")
    public String d16t1;

    @Column(name = "D17T1")
    public String d17t1;

    @Column(name = "D17T2")
    public String d17t2;

    @Column(name = "D17T3")
    public String d17t3;

    @Column(name = "D17T4")
    public String d17t4;

    @Column(name = "D17T5")
    public String d17t5;

    @Column(name = "D18T1")
    public String d18t1;

    @Column(name = "D18T2")
    public String d18t2;

    @Column(name = "D18T3")
    public String d18t3;

    @Column(name = "D18T4")
    public String d18t4;

    @Column(name = "D18T5")
    public String d18t5;

    @Column(name = "D19T1")
    public String d19t1;

    @Column(name = "D19T2")
    public String d19t2;

    @Column(name = "D19T3")
    public String d19t3;

    @Column(name = "D19T4")
    public String d19t4;

    @Column(name = "D21T1")
    public String d21t1;

    @Column(name = "D21T2")
    public String d21t2;

    @Column(name = "D22T1")
    public String d22t1;

    @Column(name = "D22T2")
    public String d22t2;

    @Column(name = "D24T1")
    public String d24t1;

    @Column(name = "D24T2")
    public String d24t2;

    @Column(name = "D26T1")
    public String d26t1;

    @Column(name = "D26T2")
    public String d26t2;

    @Column(name = "D26T3")
    public String d26t3;

    @Column(name = "D26T4")
    public String d26t4;

    @Column(name = "D26T5")
    public String d26t5;

    @Column(name = "D26T6")
    public String d26t6;

    @Column(name = "D26T7")
    public String d26t7;

    @Column(name = "D26T8")
    public String d26t8;

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

    @Column(name = "QZ10")
    public String qz10;

    @Column(name = "QZ11")
    public String qz11;

    @Column(name = "FJ7JW1")
    public String fj7jw1;

    @Column(name = "FJ7JW2")
    public String fj7jw2;

    @Column(name = "FJ7JW3")
    public String fj7jw3;

    @Column(name = "FJ7JW4")
    public String fj7jw4;

    @Column(name = "FJ7JW5")
    public String fj7jw5;

    @Column(name = "FJ7JW6")
    public String fj7jw6;

    @Column(name = "FJ7JW7")
    public String fj7jw7;

    @Column(name = "FJ7JW8")
    public String fj7jw8;

    @Column(name = "FJ7JW9")
    public String fj7jw9;

    @Column(name = "FJ7JW10")
    public String fj7jw10;

    @Column(name = "FJ7JW11")
    public String fj7jw11;

    @Column(name = "FJ7JW12")
    public String fj7jw12;

    @Column(name = "FJ7JW13")
    public String fj7jw13;

    @Column(name = "FJ7JW14")
    public String fj7jw14;

    @Column(name = "FJ7JW15")
    public String fj7jw15;

    @Column(name = "FJ7JW16")
    public String fj7jw16;

    @Column(name = "FJ7JW17")
    public String fj7jw17;

    @Column(name = "FJ7JW18")
    public String fj7jw18;

    @Column(name = "FJ7JW19")
    public String fj7jw19;

    @Column(name = "FJ7JW20")
    public String fj7jw20;

    @Column(name = "FJ7JW21")
    public String fj7jw21;

    @Column(name = "FJ7JW22")
    public String fj7jw22;

    @Column(name = "FJ7JW23")
    public String fj7jw23;

    @Column(name = "FJ7JW24")
    public String fj7jw24;

    @Column(name = "FJ7JW25")
    public String fj7jw25;

    @Column(name = "FJ7JW26")
    public String fj7jw26;

    @Column(name = "FJ7JW27")
    public String fj7jw27;

    @Column(name = "FJ7JW28")
    public String fj7jw28;

    @Column(name = "FJ7JW29")
    public String fj7jw29;

    @Column(name = "FJ7JW30")
    public String fj7jw30;

    @Column(name = "FJ8JW1")
    public String fj8jw1;

    @Column(name = "FJ8JW2")
    public String fj8jw2;

    @Column(name = "FJ8JW3")
    public String fj8jw3;

    @Column(name = "FJ8JW4")
    public String fj8jw4;

    @Column(name = "FJ8JW5")
    public String fj8jw5;

    @Column(name = "FJ8JW6")
    public String fj8jw6;

    @Column(name = "FJ8JW7")
    public String fj8jw7;

    @Column(name = "FJ8JW8")
    public String fj8jw8;

    @Column(name = "FJ8JW9")
    public String fj8jw9;

    @Column(name = "FJ8JW10")
    public String fj8jw10;

    @Column(name = "FJ8JW11")
    public String fj8jw11;

    @Column(name = "FJ8JW12")
    public String fj8jw12;

    @Column(name = "FJ8JW13")
    public String fj8jw13;

    @Column(name = "FJ8JW14")
    public String fj8jw14;

    public String getD4t5() {
        return d4t5;
    }

    public void setD4t5(String d4t5) {
        this.d4t5 = d4t5;
    }

    public String getD12t7() {
        return d12t7;
    }

    public void setD12t7(String d12t7) {
        this.d12t7 = d12t7;
    }

    public String getD12t8() {
        return d12t8;
    }

    public void setD12t8(String d12t8) {
        this.d12t8 = d12t8;
    }

    public String getFj7jw1() {
        return fj7jw1;
    }

    public void setFj7jw1(String fj7jw1) {
        this.fj7jw1 = fj7jw1;
    }

    public String getFj7jw2() {
        return fj7jw2;
    }

    public void setFj7jw2(String fj7jw2) {
        this.fj7jw2 = fj7jw2;
    }

    public String getFj7jw3() {
        return fj7jw3;
    }

    public void setFj7jw3(String fj7jw3) {
        this.fj7jw3 = fj7jw3;
    }

    public String getFj7jw4() {
        return fj7jw4;
    }

    public void setFj7jw4(String fj7jw4) {
        this.fj7jw4 = fj7jw4;
    }

    public String getFj7jw5() {
        return fj7jw5;
    }

    public void setFj7jw5(String fj7jw5) {
        this.fj7jw5 = fj7jw5;
    }

    public String getFj7jw6() {
        return fj7jw6;
    }

    public void setFj7jw6(String fj7jw6) {
        this.fj7jw6 = fj7jw6;
    }

    public String getFj7jw7() {
        return fj7jw7;
    }

    public void setFj7jw7(String fj7jw7) {
        this.fj7jw7 = fj7jw7;
    }

    public String getFj7jw8() {
        return fj7jw8;
    }

    public void setFj7jw8(String fj7jw8) {
        this.fj7jw8 = fj7jw8;
    }

    public String getFj7jw9() {
        return fj7jw9;
    }

    public void setFj7jw9(String fj7jw9) {
        this.fj7jw9 = fj7jw9;
    }

    public String getFj7jw10() {
        return fj7jw10;
    }

    public void setFj7jw10(String fj7jw10) {
        this.fj7jw10 = fj7jw10;
    }

    public String getFj7jw11() {
        return fj7jw11;
    }

    public void setFj7jw11(String fj7jw11) {
        this.fj7jw11 = fj7jw11;
    }

    public String getFj7jw12() {
        return fj7jw12;
    }

    public void setFj7jw12(String fj7jw12) {
        this.fj7jw12 = fj7jw12;
    }

    public String getFj7jw13() {
        return fj7jw13;
    }

    public void setFj7jw13(String fj7jw13) {
        this.fj7jw13 = fj7jw13;
    }

    public String getFj7jw14() {
        return fj7jw14;
    }

    public void setFj7jw14(String fj7jw14) {
        this.fj7jw14 = fj7jw14;
    }

    public String getFj7jw15() {
        return fj7jw15;
    }

    public void setFj7jw15(String fj7jw15) {
        this.fj7jw15 = fj7jw15;
    }

    public String getFj7jw16() {
        return fj7jw16;
    }

    public void setFj7jw16(String fj7jw16) {
        this.fj7jw16 = fj7jw16;
    }

    public String getFj7jw17() {
        return fj7jw17;
    }

    public void setFj7jw17(String fj7jw17) {
        this.fj7jw17 = fj7jw17;
    }

    public String getFj7jw18() {
        return fj7jw18;
    }

    public void setFj7jw18(String fj7jw18) {
        this.fj7jw18 = fj7jw18;
    }

    public String getFj7jw19() {
        return fj7jw19;
    }

    public void setFj7jw19(String fj7jw19) {
        this.fj7jw19 = fj7jw19;
    }

    public String getFj7jw20() {
        return fj7jw20;
    }

    public void setFj7jw20(String fj7jw20) {
        this.fj7jw20 = fj7jw20;
    }

    public String getFj7jw21() {
        return fj7jw21;
    }

    public void setFj7jw21(String fj7jw21) {
        this.fj7jw21 = fj7jw21;
    }

    public String getFj7jw22() {
        return fj7jw22;
    }

    public void setFj7jw22(String fj7jw22) {
        this.fj7jw22 = fj7jw22;
    }

    public String getFj7jw23() {
        return fj7jw23;
    }

    public void setFj7jw23(String fj7jw23) {
        this.fj7jw23 = fj7jw23;
    }

    public String getFj7jw24() {
        return fj7jw24;
    }

    public void setFj7jw24(String fj7jw24) {
        this.fj7jw24 = fj7jw24;
    }

    public String getFj7jw25() {
        return fj7jw25;
    }

    public void setFj7jw25(String fj7jw25) {
        this.fj7jw25 = fj7jw25;
    }

    public String getFj7jw26() {
        return fj7jw26;
    }

    public void setFj7jw26(String fj7jw26) {
        this.fj7jw26 = fj7jw26;
    }

    public String getFj7jw27() {
        return fj7jw27;
    }

    public void setFj7jw27(String fj7jw27) {
        this.fj7jw27 = fj7jw27;
    }

    public String getFj7jw28() {
        return fj7jw28;
    }

    public void setFj7jw28(String fj7jw28) {
        this.fj7jw28 = fj7jw28;
    }

    public String getFj7jw29() {
        return fj7jw29;
    }

    public void setFj7jw29(String fj7jw29) {
        this.fj7jw29 = fj7jw29;
    }

    public String getFj7jw30() {
        return fj7jw30;
    }

    public void setFj7jw30(String fj7jw30) {
        this.fj7jw30 = fj7jw30;
    }

    public String getFj8jw1() {
        return fj8jw1;
    }

    public void setFj8jw1(String fj8jw1) {
        this.fj8jw1 = fj8jw1;
    }

    public String getFj8jw2() {
        return fj8jw2;
    }

    public void setFj8jw2(String fj8jw2) {
        this.fj8jw2 = fj8jw2;
    }

    public String getFj8jw3() {
        return fj8jw3;
    }

    public void setFj8jw3(String fj8jw3) {
        this.fj8jw3 = fj8jw3;
    }

    public String getFj8jw4() {
        return fj8jw4;
    }

    public void setFj8jw4(String fj8jw4) {
        this.fj8jw4 = fj8jw4;
    }

    public String getFj8jw5() {
        return fj8jw5;
    }

    public void setFj8jw5(String fj8jw5) {
        this.fj8jw5 = fj8jw5;
    }

    public String getFj8jw6() {
        return fj8jw6;
    }

    public void setFj8jw6(String fj8jw6) {
        this.fj8jw6 = fj8jw6;
    }

    public String getFj8jw7() {
        return fj8jw7;
    }

    public void setFj8jw7(String fj8jw7) {
        this.fj8jw7 = fj8jw7;
    }

    public String getFj8jw8() {
        return fj8jw8;
    }

    public void setFj8jw8(String fj8jw8) {
        this.fj8jw8 = fj8jw8;
    }

    public String getFj8jw9() {
        return fj8jw9;
    }

    public void setFj8jw9(String fj8jw9) {
        this.fj8jw9 = fj8jw9;
    }

    public String getFj8jw10() {
        return fj8jw10;
    }

    public void setFj8jw10(String fj8jw10) {
        this.fj8jw10 = fj8jw10;
    }

    public String getFj8jw11() {
        return fj8jw11;
    }

    public void setFj8jw11(String fj8jw11) {
        this.fj8jw11 = fj8jw11;
    }

    public String getFj8jw12() {
        return fj8jw12;
    }

    public void setFj8jw12(String fj8jw12) {
        this.fj8jw12 = fj8jw12;
    }

    public String getFj8jw13() {
        return fj8jw13;
    }

    public void setFj8jw13(String fj8jw13) {
        this.fj8jw13 = fj8jw13;
    }

    public String getFj8jw14() {
        return fj8jw14;
    }

    public void setFj8jw14(String fj8jw14) {
        this.fj8jw14 = fj8jw14;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHousetradeid() {
        return housetradeid;
    }

    public void setHousetradeid(String housetradeid) {
        this.housetradeid = housetradeid;
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

    public String getYf19() {
        return yf19;
    }

    public void setYf19(String yf19) {
        this.yf19 = yf19;
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

    public String getD4t3() {
        return d4t3;
    }

    public void setD4t3(String d4t3) {
        this.d4t3 = d4t3;
    }

    public String getD4t4() {
        return d4t4;
    }

    public void setD4t4(String d4t4) {
        this.d4t4 = d4t4;
    }

    public String getD5t1() {
        return d5t1;
    }

    public void setD5t1(String d5t1) {
        this.d5t1 = d5t1;
    }

    public String getD5t2() {
        return d5t2;
    }

    public void setD5t2(String d5t2) {
        this.d5t2 = d5t2;
    }

    public String getD5t3() {
        return d5t3;
    }

    public void setD5t3(String d5t3) {
        this.d5t3 = d5t3;
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

    public String getD7t5() {
        return d7t5;
    }

    public void setD7t5(String d7t5) {
        this.d7t5 = d7t5;
    }

    public String getD7t6() {
        return d7t6;
    }

    public void setD7t6(String d7t6) {
        this.d7t6 = d7t6;
    }

    public String getD7t7() {
        return d7t7;
    }

    public void setD7t7(String d7t7) {
        this.d7t7 = d7t7;
    }

    public String getD7t8() {
        return d7t8;
    }

    public void setD7t8(String d7t8) {
        this.d7t8 = d7t8;
    }

    public String getD7t9() {
        return d7t9;
    }

    public void setD7t9(String d7t9) {
        this.d7t9 = d7t9;
    }

    public String getD7t10() {
        return d7t10;
    }

    public void setD7t10(String d7t10) {
        this.d7t10 = d7t10;
    }

    public String getD7t11() {
        return d7t11;
    }

    public void setD7t11(String d7t11) {
        this.d7t11 = d7t11;
    }

    public String getD7t12() {
        return d7t12;
    }

    public void setD7t12(String d7t12) {
        this.d7t12 = d7t12;
    }

    public String getD7t13() {
        return d7t13;
    }

    public void setD7t13(String d7t13) {
        this.d7t13 = d7t13;
    }

    public String getD7t14() {
        return d7t14;
    }

    public void setD7t14(String d7t14) {
        this.d7t14 = d7t14;
    }

    public String getD7t15() {
        return d7t15;
    }

    public void setD7t15(String d7t15) {
        this.d7t15 = d7t15;
    }

    public String getD7t16() {
        return d7t16;
    }

    public void setD7t16(String d7t16) {
        this.d7t16 = d7t16;
    }

    public String getD7t17() {
        return d7t17;
    }

    public void setD7t17(String d7t17) {
        this.d7t17 = d7t17;
    }

    public String getD7t18() {
        return d7t18;
    }

    public void setD7t18(String d7t18) {
        this.d7t18 = d7t18;
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

    public String getD8t5() {
        return d8t5;
    }

    public void setD8t5(String d8t5) {
        this.d8t5 = d8t5;
    }

    public String getD8t6() {
        return d8t6;
    }

    public void setD8t6(String d8t6) {
        this.d8t6 = d8t6;
    }

    public String getD8t7() {
        return d8t7;
    }

    public void setD8t7(String d8t7) {
        this.d8t7 = d8t7;
    }

    public String getD8t8() {
        return d8t8;
    }

    public void setD8t8(String d8t8) {
        this.d8t8 = d8t8;
    }

    public String getD8t9() {
        return d8t9;
    }

    public void setD8t9(String d8t9) {
        this.d8t9 = d8t9;
    }

    public String getD8t10() {
        return d8t10;
    }

    public void setD8t10(String d8t10) {
        this.d8t10 = d8t10;
    }

    public String getD8t11() {
        return d8t11;
    }

    public void setD8t11(String d8t11) {
        this.d8t11 = d8t11;
    }

    public String getD8t12() {
        return d8t12;
    }

    public void setD8t12(String d8t12) {
        this.d8t12 = d8t12;
    }

    public String getD8t13() {
        return d8t13;
    }

    public void setD8t13(String d8t13) {
        this.d8t13 = d8t13;
    }

    public String getD8t14() {
        return d8t14;
    }

    public void setD8t14(String d8t14) {
        this.d8t14 = d8t14;
    }

    public String getD8t15() {
        return d8t15;
    }

    public void setD8t15(String d8t15) {
        this.d8t15 = d8t15;
    }

    public String getD8t16() {
        return d8t16;
    }

    public void setD8t16(String d8t16) {
        this.d8t16 = d8t16;
    }

    public String getD8t17() {
        return d8t17;
    }

    public void setD8t17(String d8t17) {
        this.d8t17 = d8t17;
    }

    public String getD8t18() {
        return d8t18;
    }

    public void setD8t18(String d8t18) {
        this.d8t18 = d8t18;
    }

    public String getD8t19() {
        return d8t19;
    }

    public void setD8t19(String d8t19) {
        this.d8t19 = d8t19;
    }

    public String getD8t20() {
        return d8t20;
    }

    public void setD8t20(String d8t20) {
        this.d8t20 = d8t20;
    }

    public String getD8t21() {
        return d8t21;
    }

    public void setD8t21(String d8t21) {
        this.d8t21 = d8t21;
    }

    public String getD8t22() {
        return d8t22;
    }

    public void setD8t22(String d8t22) {
        this.d8t22 = d8t22;
    }

    public String getD8t23() {
        return d8t23;
    }

    public void setD8t23(String d8t23) {
        this.d8t23 = d8t23;
    }

    public String getD8t24() {
        return d8t24;
    }

    public void setD8t24(String d8t24) {
        this.d8t24 = d8t24;
    }

    public String getD8t25() {
        return d8t25;
    }

    public void setD8t25(String d8t25) {
        this.d8t25 = d8t25;
    }

    public String getD8t26() {
        return d8t26;
    }

    public void setD8t26(String d8t26) {
        this.d8t26 = d8t26;
    }

    public String getD8t27() {
        return d8t27;
    }

    public void setD8t27(String d8t27) {
        this.d8t27 = d8t27;
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

    public String getD9t5() {
        return d9t5;
    }

    public void setD9t5(String d9t5) {
        this.d9t5 = d9t5;
    }

    public String getD9t6() {
        return d9t6;
    }

    public void setD9t6(String d9t6) {
        this.d9t6 = d9t6;
    }

    public String getD9t7() {
        return d9t7;
    }

    public void setD9t7(String d9t7) {
        this.d9t7 = d9t7;
    }

    public String getD9t8() {
        return d9t8;
    }

    public void setD9t8(String d9t8) {
        this.d9t8 = d9t8;
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

    public String getD10t4() {
        return d10t4;
    }

    public void setD10t4(String d10t4) {
        this.d10t4 = d10t4;
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

    public String getD11t3() {
        return d11t3;
    }

    public void setD11t3(String d11t3) {
        this.d11t3 = d11t3;
    }

    public String getD11t4() {
        return d11t4;
    }

    public void setD11t4(String d11t4) {
        this.d11t4 = d11t4;
    }

    public String getD11t5() {
        return d11t5;
    }

    public void setD11t5(String d11t5) {
        this.d11t5 = d11t5;
    }

    public String getD11t6() {
        return d11t6;
    }

    public void setD11t6(String d11t6) {
        this.d11t6 = d11t6;
    }

    public String getD11t7() {
        return d11t7;
    }

    public void setD11t7(String d11t7) {
        this.d11t7 = d11t7;
    }

    public String getD11t8() {
        return d11t8;
    }

    public void setD11t8(String d11t8) {
        this.d11t8 = d11t8;
    }

    public String getD11t9() {
        return d11t9;
    }

    public void setD11t9(String d11t9) {
        this.d11t9 = d11t9;
    }

    public String getD11t10() {
        return d11t10;
    }

    public void setD11t10(String d11t10) {
        this.d11t10 = d11t10;
    }

    public String getD11t11() {
        return d11t11;
    }

    public void setD11t11(String d11t11) {
        this.d11t11 = d11t11;
    }

    public String getD11t12() {
        return d11t12;
    }

    public void setD11t12(String d11t12) {
        this.d11t12 = d11t12;
    }

    public String getD11t13() {
        return d11t13;
    }

    public void setD11t13(String d11t13) {
        this.d11t13 = d11t13;
    }

    public String getD11t14() {
        return d11t14;
    }

    public void setD11t14(String d11t14) {
        this.d11t14 = d11t14;
    }

    public String getD11t15() {
        return d11t15;
    }

    public void setD11t15(String d11t15) {
        this.d11t15 = d11t15;
    }

    public String getD11t16() {
        return d11t16;
    }

    public void setD11t16(String d11t16) {
        this.d11t16 = d11t16;
    }

    public String getD11t17() {
        return d11t17;
    }

    public void setD11t17(String d11t17) {
        this.d11t17 = d11t17;
    }

    public String getD11t18() {
        return d11t18;
    }

    public void setD11t18(String d11t18) {
        this.d11t18 = d11t18;
    }

    public String getD11t19() {
        return d11t19;
    }

    public void setD11t19(String d11t19) {
        this.d11t19 = d11t19;
    }

    public String getD11t20() {
        return d11t20;
    }

    public void setD11t20(String d11t20) {
        this.d11t20 = d11t20;
    }

    public String getD11t21() {
        return d11t21;
    }

    public void setD11t21(String d11t21) {
        this.d11t21 = d11t21;
    }

    public String getD11t22() {
        return d11t22;
    }

    public void setD11t22(String d11t22) {
        this.d11t22 = d11t22;
    }

    public String getD11t23() {
        return d11t23;
    }

    public void setD11t23(String d11t23) {
        this.d11t23 = d11t23;
    }

    public String getD11t24() {
        return d11t24;
    }

    public void setD11t24(String d11t24) {
        this.d11t24 = d11t24;
    }

    public String getD11t25() {
        return d11t25;
    }

    public void setD11t25(String d11t25) {
        this.d11t25 = d11t25;
    }

    public String getD11t26() {
        return d11t26;
    }

    public void setD11t26(String d11t26) {
        this.d11t26 = d11t26;
    }

    public String getD11t27() {
        return d11t27;
    }

    public void setD11t27(String d11t27) {
        this.d11t27 = d11t27;
    }

    public String getD11t28() {
        return d11t28;
    }

    public void setD11t28(String d11t28) {
        this.d11t28 = d11t28;
    }

    public String getD11t29() {
        return d11t29;
    }

    public void setD11t29(String d11t29) {
        this.d11t29 = d11t29;
    }

    public String getD11t30() {
        return d11t30;
    }

    public void setD11t30(String d11t30) {
        this.d11t30 = d11t30;
    }

    public String getD11t31() {
        return d11t31;
    }

    public void setD11t31(String d11t31) {
        this.d11t31 = d11t31;
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

    public String getD12t3() {
        return d12t3;
    }

    public void setD12t3(String d12t3) {
        this.d12t3 = d12t3;
    }

    public String getD12t4() {
        return d12t4;
    }

    public void setD12t4(String d12t4) {
        this.d12t4 = d12t4;
    }

    public String getD12t5() {
        return d12t5;
    }

    public void setD12t5(String d12t5) {
        this.d12t5 = d12t5;
    }

    public String getD12t6() {
        return d12t6;
    }

    public void setD12t6(String d12t6) {
        this.d12t6 = d12t6;
    }

    public String getD13t1() {
        return d13t1;
    }

    public void setD13t1(String d13t1) {
        this.d13t1 = d13t1;
    }

    public String getD13t2() {
        return d13t2;
    }

    public void setD13t2(String d13t2) {
        this.d13t2 = d13t2;
    }

    public String getD13t3() {
        return d13t3;
    }

    public void setD13t3(String d13t3) {
        this.d13t3 = d13t3;
    }

    public String getD13t4() {
        return d13t4;
    }

    public void setD13t4(String d13t4) {
        this.d13t4 = d13t4;
    }

    public String getD13t5() {
        return d13t5;
    }

    public void setD13t5(String d13t5) {
        this.d13t5 = d13t5;
    }

    public String getD13t6() {
        return d13t6;
    }

    public void setD13t6(String d13t6) {
        this.d13t6 = d13t6;
    }

    public String getD13t7() {
        return d13t7;
    }

    public void setD13t7(String d13t7) {
        this.d13t7 = d13t7;
    }

    public String getD13t8() {
        return d13t8;
    }

    public void setD13t8(String d13t8) {
        this.d13t8 = d13t8;
    }

    public String getD14t1() {
        return d14t1;
    }

    public void setD14t1(String d14t1) {
        this.d14t1 = d14t1;
    }

    public String getD14t2() {
        return d14t2;
    }

    public void setD14t2(String d14t2) {
        this.d14t2 = d14t2;
    }

    public String getD14t3() {
        return d14t3;
    }

    public void setD14t3(String d14t3) {
        this.d14t3 = d14t3;
    }

    public String getD14t4() {
        return d14t4;
    }

    public void setD14t4(String d14t4) {
        this.d14t4 = d14t4;
    }

    public String getD14t5() {
        return d14t5;
    }

    public void setD14t5(String d14t5) {
        this.d14t5 = d14t5;
    }

    public String getD14t6() {
        return d14t6;
    }

    public void setD14t6(String d14t6) {
        this.d14t6 = d14t6;
    }

    public String getD14t7() {
        return d14t7;
    }

    public void setD14t7(String d14t7) {
        this.d14t7 = d14t7;
    }

    public String getD14t8() {
        return d14t8;
    }

    public void setD14t8(String d14t8) {
        this.d14t8 = d14t8;
    }

    public String getD14t9() {
        return d14t9;
    }

    public void setD14t9(String d14t9) {
        this.d14t9 = d14t9;
    }

    public String getD14t10() {
        return d14t10;
    }

    public void setD14t10(String d14t10) {
        this.d14t10 = d14t10;
    }

    public String getD14t11() {
        return d14t11;
    }

    public void setD14t11(String d14t11) {
        this.d14t11 = d14t11;
    }

    public String getD14t12() {
        return d14t12;
    }

    public void setD14t12(String d14t12) {
        this.d14t12 = d14t12;
    }

    public String getD14t13() {
        return d14t13;
    }

    public void setD14t13(String d14t13) {
        this.d14t13 = d14t13;
    }

    public String getD14t14() {
        return d14t14;
    }

    public void setD14t14(String d14t14) {
        this.d14t14 = d14t14;
    }

    public String getD14t15() {
        return d14t15;
    }

    public void setD14t15(String d14t15) {
        this.d14t15 = d14t15;
    }

    public String getD15t1() {
        return d15t1;
    }

    public void setD15t1(String d15t1) {
        this.d15t1 = d15t1;
    }

    public String getD15t2() {
        return d15t2;
    }

    public void setD15t2(String d15t2) {
        this.d15t2 = d15t2;
    }

    public String getD16t1() {
        return d16t1;
    }

    public void setD16t1(String d16t1) {
        this.d16t1 = d16t1;
    }

    public String getD17t1() {
        return d17t1;
    }

    public void setD17t1(String d17t1) {
        this.d17t1 = d17t1;
    }

    public String getD17t2() {
        return d17t2;
    }

    public void setD17t2(String d17t2) {
        this.d17t2 = d17t2;
    }

    public String getD17t3() {
        return d17t3;
    }

    public void setD17t3(String d17t3) {
        this.d17t3 = d17t3;
    }

    public String getD17t4() {
        return d17t4;
    }

    public void setD17t4(String d17t4) {
        this.d17t4 = d17t4;
    }

    public String getD17t5() {
        return d17t5;
    }

    public void setD17t5(String d17t5) {
        this.d17t5 = d17t5;
    }

    public String getD18t1() {
        return d18t1;
    }

    public void setD18t1(String d18t1) {
        this.d18t1 = d18t1;
    }

    public String getD18t2() {
        return d18t2;
    }

    public void setD18t2(String d18t2) {
        this.d18t2 = d18t2;
    }

    public String getD18t3() {
        return d18t3;
    }

    public void setD18t3(String d18t3) {
        this.d18t3 = d18t3;
    }

    public String getD18t4() {
        return d18t4;
    }

    public void setD18t4(String d18t4) {
        this.d18t4 = d18t4;
    }

    public String getD18t5() {
        return d18t5;
    }

    public void setD18t5(String d18t5) {
        this.d18t5 = d18t5;
    }

    public String getD19t1() {
        return d19t1;
    }

    public void setD19t1(String d19t1) {
        this.d19t1 = d19t1;
    }

    public String getD19t2() {
        return d19t2;
    }

    public void setD19t2(String d19t2) {
        this.d19t2 = d19t2;
    }

    public String getD19t3() {
        return d19t3;
    }

    public void setD19t3(String d19t3) {
        this.d19t3 = d19t3;
    }

    public String getD19t4() {
        return d19t4;
    }

    public void setD19t4(String d19t4) {
        this.d19t4 = d19t4;
    }

    public String getD21t1() {
        return d21t1;
    }

    public void setD21t1(String d21t1) {
        this.d21t1 = d21t1;
    }

    public String getD21t2() {
        return d21t2;
    }

    public void setD21t2(String d21t2) {
        this.d21t2 = d21t2;
    }

    public String getD22t1() {
        return d22t1;
    }

    public void setD22t1(String d22t1) {
        this.d22t1 = d22t1;
    }

    public String getD22t2() {
        return d22t2;
    }

    public void setD22t2(String d22t2) {
        this.d22t2 = d22t2;
    }

    public String getD24t1() {
        return d24t1;
    }

    public void setD24t1(String d24t1) {
        this.d24t1 = d24t1;
    }

    public String getD24t2() {
        return d24t2;
    }

    public void setD24t2(String d24t2) {
        this.d24t2 = d24t2;
    }

    public String getD26t1() {
        return d26t1;
    }

    public void setD26t1(String d26t1) {
        this.d26t1 = d26t1;
    }

    public String getD26t2() {
        return d26t2;
    }

    public void setD26t2(String d26t2) {
        this.d26t2 = d26t2;
    }

    public String getD26t3() {
        return d26t3;
    }

    public void setD26t3(String d26t3) {
        this.d26t3 = d26t3;
    }

    public String getD26t4() {
        return d26t4;
    }

    public void setD26t4(String d26t4) {
        this.d26t4 = d26t4;
    }

    public String getD26t5() {
        return d26t5;
    }

    public void setD26t5(String d26t5) {
        this.d26t5 = d26t5;
    }

    public String getD26t6() {
        return d26t6;
    }

    public void setD26t6(String d26t6) {
        this.d26t6 = d26t6;
    }

    public String getD26t7() {
        return d26t7;
    }

    public void setD26t7(String d26t7) {
        this.d26t7 = d26t7;
    }

    public String getD26t8() {
        return d26t8;
    }

    public void setD26t8(String d26t8) {
        this.d26t8 = d26t8;
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

    public String getQz10() {
        return qz10;
    }

    public void setQz10(String qz10) {
        this.qz10 = qz10;
    }

    public String getQz11() {
        return qz11;
    }

    public void setQz11(String qz11) {
        this.qz11 = qz11;
    }
}
