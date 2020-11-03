package com.grandeflorum.contract.domain;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ADVANCE_SALES_TEMPLATE")
public class AdvanceSalesTemplate implements Cloneable{

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

    @Column(name = "D4T6")
    public String d4t6;

    @Column(name = "D4T7")
    public String d4t7;

    @Column(name = "D4T8")
    public String d4t8;

    @Column(name = "D4T9")
    public String d4t9;

    @Column(name = "D4T10")
    public String d4t10;

    @Column(name = "D4T11")
    public String d4t11;

    @Column(name = "D4T12")
    public String d4t12;

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

    @Column(name = "D6T4")
    public String d6t4;

    @Column(name = "D6T5")
    public String d6t5;

    @Column(name = "D6T6")
    public String d6t6;

    @Column(name = "D6T7")
    public String d6t7;

    @Column(name = "D6T8")
    public String d6t8;

    @Column(name = "D6T9")
    public String d6t9;

    @Column(name = "D6T10")
    public String d6t10;

    @Column(name = "D6T11")
    public String d6t11;

    @Column(name = "D6T12")
    public String d6t12;

    @Column(name = "D6T13")
    public String d6t13;

    @Column(name = "D6T14")
    public String d6t14;

    @Column(name = "D6T15")
    public String d6t15;

    @Column(name = "D6T16")
    public String d6t16;

    @Column(name = "D6T17")
    public String d6t17;

    @Column(name = "D6T18")
    public String d6t18;

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

    @Column(name = "D7T19")
    public String d7t19;

    @Column(name = "D7T20")
    public String d7t20;

    @Column(name = "D7T21")
    public String d7t21;

    @Column(name = "D7T22")
    public String d7t22;

    @Column(name = "D7T23")
    public String d7t23;

    @Column(name = "D7T24")
    public String d7t24;

    @Column(name = "D7T25")
    public String d7t25;

    @Column(name = "D7T26")
    public String d7t26;

    @Column(name = "D7T27")
    public String d7t27;

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

    @Column(name = "D10T4")
    public String d10t4;

    @Column(name = "D10T5")
    public String d10t5;

    @Column(name = "D10T6")
    public String d10t6;

    @Column(name = "D10T7")
    public String d10t7;

    @Column(name = "D10T8")
    public String d10t8;

    @Column(name = "D10T9")
    public String d10t9;

    @Column(name = "D10T10")
    public String d10t10;

    @Column(name = "D10T11")
    public String d10t11;

    @Column(name = "D10T12")
    public String d10t12;

    @Column(name = "D10T13")
    public String d10t13;

    @Column(name = "D10T14")
    public String d10t14;

    @Column(name = "D10T15")
    public String d10t15;

    @Column(name = "D10T16")
    public String d10t16;

    @Column(name = "D10T17")
    public String d10t17;

    @Column(name = "D10T18")
    public String d10t18;

    @Column(name = "D10T19")
    public String d10t19;

    @Column(name = "D10T20")
    public String d10t20;

    @Column(name = "D10T21")
    public String d10t21;

    @Column(name = "D10T22")
    public String d10t22;

    @Column(name = "D10T23")
    public String d10t23;

    @Column(name = "D10T24")
    public String d10t24;

    @Column(name = "D10T25")
    public String d10t25;

    @Column(name = "D10T26")
    public String d10t26;

    @Column(name = "D10T27")
    public String d10t27;

    @Column(name = "D10T28")
    public String d10t28;

    @Column(name = "D10T29")
    public String d10t29;

    @Column(name = "D10T30")
    public String d10t30;

    @Column(name = "D10T31")
    public String d10t31;

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



    @Column(name = "D14T1")
    public String d14t1;

    @Column(name = "D14T2")
    public String d14t2;

    @Column(name = "D14T3")
    public String d14t3;


    @Column(name = "D15T1")
    public String d15t1;

    @Column(name = "D15T2")
    public String d15t2;

    @Column(name = "D15T3")
    public String d15t3;

    @Column(name = "D15T4")
    public String d15t4;

    @Column(name = "D15T5")
    public String d15t5;

    @Column(name = "D15T6")
    public String d15t6;


    @Column(name = "D16T1")
    public String d16t1;

    @Column(name = "D16T2")
    public String d16t2;

    @Column(name = "D16T3")
    public String d16t3;

    @Column(name = "D16T4")
    public String d16t4;

    @Column(name = "D16T5")
    public String d16t5;

    @Column(name = "D16T6")
    public String d16t6;

    @Column(name = "D16T7")
    public String d16t7;

    @Column(name = "D16T8")
    public String d16t8;

    @Column(name = "D16T9")
    public String d16t9;

    @Column(name = "D16T10")
    public String d16t10;

    @Column(name = "D16T11")
    public String d16t11;

    @Column(name = "D16T12")
    public String d16t12;

    @Column(name = "D16T13")
    public String d16t13;

    @Column(name = "D16T14")
    public String d16t14;

    @Column(name = "D16T15")
    public String d16t15;


    @Column(name = "D17T1")
    public String d17t1;

    @Column(name = "D17T2")
    public String d17t2;



    @Column(name = "D18T1")
    public String d18t1;


    @Column(name = "D19T1")
    public String d19t1;

    @Column(name = "D19T2")
    public String d19t2;

    @Column(name = "D19T3")
    public String d19t3;

    @Column(name = "D20T1")
    public String d20t1;

    @Column(name = "D20T2")
    public String d20t2;

    @Column(name = "D20T3")
    public String d20t3;

    @Column(name = "D20T4")
    public String d20t4;

    @Column(name = "D20T5")
    public String d20t5;


    @Column(name = "D21T1")
    public String d21t1;

    @Column(name = "D21T2")
    public String d21t2;

    @Column(name = "D21T3")
    public String d21t3;

    @Column(name = "D21T4")
    public String d21t4;

    @Column(name = "D21T5")
    public String d21t5;



    @Column(name = "D22T1")
    public String d22t1;

    @Column(name = "D22T2")
    public String d22t2;

    @Column(name = "D22T3")
    public String d22t3;

    @Column(name = "D22T4")
    public String d22t4;


    
    @Column(name = "D23T1")
    public String d23t1;

    
    @Column(name = "D24T1")
    public String d24t1;

    @Column(name = "D24T2")
    public String d24t2;


    @Column(name = "D25T1")
    public String d25t1;

    @Column(name = "D25T2")
    public String d25t2;

    @Column(name = "D27T1")
    public String d27t1;

    @Column(name = "D27T2")
    public String d27t2;



    @Column(name = "D29T1")
    public String d29t1;

    @Column(name = "D29T2")
    public String d29t2;

    @Column(name = "D29T3")
    public String d29t3;

    @Column(name = "D29T4")
    public String d29t4;

    @Column(name = "D29T5")
    public String d29t5;

    @Column(name = "D29T6")
    public String d29t6;

    @Column(name = "D29T7")
    public String d29t7;

    @Column(name = "D29T8")
    public String d29t8;

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

    @Column(name = "FJ6JW1")
    public String fj6jw1;

    @Column(name = "FJ6JW2")
    public String fj6jw2;

    @Column(name = "FJ6JW3")
    public String fj6jw3;

    @Column(name = "FJ6JW4")
    public String fj6jw4;

    @Column(name = "FJ6JW5")
    public String fj6jw5;

    @Column(name = "FJ6JW6")
    public String fj6jw6;

    @Column(name = "FJ6JW7")
    public String fj6jw7;

    @Column(name = "FJ6JW8")
    public String fj6jw8;

    @Column(name = "FJ6JW9")
    public String fj6jw9;

    @Column(name = "FJ6JW10")
    public String fj6jw10;

    @Column(name = "FJ6JW11")
    public String fj6jw11;

    @Column(name = "FJ6JW12")
    public String fj6jw12;

    @Column(name = "FJ6JW13")
    public String fj6jw13;

    @Column(name = "FJ6JW14")
    public String fj6jw14;

    @Column(name = "FJ6JW15")
    public String fj6jw15;

    @Column(name = "FJ6JW16")
    public String fj6jw16;

    @Column(name = "FJ6JW17")
    public String fj6jw17;

    @Column(name = "FJ6JW18")
    public String fj6jw18;

    @Column(name = "FJ6JW19")
    public String fj6jw19;

    @Column(name = "FJ6JW20")
    public String fj6jw20;

    @Column(name = "FJ6JW21")
    public String fj6jw21;

    @Column(name = "FJ6JW22")
    public String fj6jw22;

    @Column(name = "FJ6JW23")
    public String fj6jw23;

    @Column(name = "FJ6JW24")
    public String fj6jw24;

    @Column(name = "FJ6JW25")
    public String fj6jw25;

    @Column(name = "FJ6JW26")
    public String fj6jw26;

    @Column(name = "FJ6JW27")
    public String fj6jw27;

    @Column(name = "FJ6JW28")
    public String fj6jw28;

    @Column(name = "FJ6JW29")
    public String fj6jw29;

    @Column(name = "FJ6JW30")
    public String fj6jw30;

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

    @Column(name = "HTMC")
    public String htmc;

    @Column(name = "FH")
    public String fh;

    @Column(name = "ISDBR")
    public Integer isdbr;

    @Column(name = "ISFZR")
    public Integer isfzr;

    @Column(name = "ISDBRGJ")
    public Integer isdbrgj;

    @Column(name = "ISDBRHJ")
    public Integer isdbrhj;

    @Column(name = "ISDBRSFZ")
    public Integer isdbrsfz;

    @Column(name = "ISDBRHZ")
    public Integer isdbrhz;

    @Column(name = "ISDBRYYZZ")
    public Integer isdbryyzz;

    @Column(name = "ISWTDLR")
    public Integer iswtdlr;

    @Column(name = "ISFDDLR")
    public Integer isfddlr;

    @Column(name = "ISDLRGJ")
    public Integer isdlrgj;

    @Column(name = "ISDLRHJ")
    public Integer isdlrhj;

    @Column(name = "ISDLRSFZ")
    public Integer isdlrsfz;

    @Column(name = "ISDLRHZ")
    public Integer isdlrhz;

    @Column(name = "ISDLRYYZZ")
    public Integer isdlryyzz;

    @Column(name = "ISCRD1")
    public Integer iscrd1;

    @Column(name = "ISHBD1")
    public Integer ishbd1;


    @Column(name = "ISZZD3")
    public Integer iszzd3;

    @Column(name = "ISBGD3")
    public Integer isbgd3;

    @Column(name = "ISSYD3")
    public Integer issyd3;

    @Column(name = "ISZ1D3")
    public Integer isz1d3;

    @Column(name = "ISZ2D3")
    public Integer isz2d3;


    @Column(name = "ISDYD4")
    public Integer isdyd4;

    @Column(name = "ISWDYD4")
    public Integer iswdyd4;


    @Column(name = "ISYFFKD5")
    public Integer isyffkd5;

    @Column(name = "ISQBSSD5")
    public Integer isqbssd5;


    @Column(name = "ISHTQDD7")
    public Integer ishtqdd7;

    @Column(name = "ISJFSFKD7")
    public Integer isjfsfkd7;

    @Column(name = "ISDZD7")
    public Integer isdzd7;

    @Column(name = "ISGJJDKD7")
    public Integer isgjjdkd7;

    @Column(name = "ISSYDKD7")
    public Integer issydkd7;

    @Column(name = "ISYFFKD16")
    public Integer isyffkd16;

    @Column(name = "ISQBSSD16")
    public Integer isqbssd16;

    @Column(name = "ISGJ1D16")
    public Integer isgj1d16;

    @Column(name = "ISDF1D16")
    public Integer isdf1d16;

    @Column(name = "ISGJ2D16")
    public Integer isgj2d16;

    @Column(name = "ISDF2D16")
    public Integer isdf2d16;

    @Column(name = "IS30TND19")
    public Integer is30tnd19;

    @Column(name = "ISBGZD21")
    public Integer isbgzd21;

    @Column(name = "ISCJZD21")
    public Integer iscjzd21;

    @Column(name = "ISKDD25")
    public Integer iskdd25;

    @Column(name = "ISGHXD25")
    public Integer isghxd25;

    public Integer getIsdbr() {
        return isdbr;
    }

    public void setIsdbr(Integer isdbr) {
        this.isdbr = isdbr;
    }

    public Integer getIsfzr() {
        return isfzr;
    }

    public void setIsfzr(Integer isfzr) {
        this.isfzr = isfzr;
    }

    public Integer getIsdbrgj() {
        return isdbrgj;
    }

    public void setIsdbrgj(Integer isdbrgj) {
        this.isdbrgj = isdbrgj;
    }

    public Integer getIsdbrhj() {
        return isdbrhj;
    }

    public void setIsdbrhj(Integer isdbrhj) {
        this.isdbrhj = isdbrhj;
    }

    public Integer getIsdbrsfz() {
        return isdbrsfz;
    }

    public void setIsdbrsfz(Integer isdbrsfz) {
        this.isdbrsfz = isdbrsfz;
    }

    public Integer getIsdbrhz() {
        return isdbrhz;
    }

    public void setIsdbrhz(Integer isdbrhz) {
        this.isdbrhz = isdbrhz;
    }

    public Integer getIsdbryyzz() {
        return isdbryyzz;
    }

    public void setIsdbryyzz(Integer isdbryyzz) {
        this.isdbryyzz = isdbryyzz;
    }

    public Integer getIswtdlr() {
        return iswtdlr;
    }

    public void setIswtdlr(Integer iswtdlr) {
        this.iswtdlr = iswtdlr;
    }

    public Integer getIsfddlr() {
        return isfddlr;
    }

    public void setIsfddlr(Integer isfddlr) {
        this.isfddlr = isfddlr;
    }

    public Integer getIsdlrgj() {
        return isdlrgj;
    }

    public void setIsdlrgj(Integer isdlrgj) {
        this.isdlrgj = isdlrgj;
    }

    public Integer getIsdlrhj() {
        return isdlrhj;
    }

    public void setIsdlrhj(Integer isdlrhj) {
        this.isdlrhj = isdlrhj;
    }

    public Integer getIsdlrsfz() {
        return isdlrsfz;
    }

    public void setIsdlrsfz(Integer isdlrsfz) {
        this.isdlrsfz = isdlrsfz;
    }

    public Integer getIsdlrhz() {
        return isdlrhz;
    }

    public void setIsdlrhz(Integer isdlrhz) {
        this.isdlrhz = isdlrhz;
    }

    public Integer getIsdlryyzz() {
        return isdlryyzz;
    }

    public void setIsdlryyzz(Integer isdlryyzz) {
        this.isdlryyzz = isdlryyzz;
    }

    public Integer getIscrd1() {
        return iscrd1;
    }

    public void setIscrd1(Integer iscrd1) {
        this.iscrd1 = iscrd1;
    }

    public Integer getIshbd1() {
        return ishbd1;
    }

    public void setIshbd1(Integer ishbd1) {
        this.ishbd1 = ishbd1;
    }

    public Integer getIszzd3() {
        return iszzd3;
    }

    public void setIszzd3(Integer iszzd3) {
        this.iszzd3 = iszzd3;
    }

    public Integer getIsbgd3() {
        return isbgd3;
    }

    public void setIsbgd3(Integer isbgd3) {
        this.isbgd3 = isbgd3;
    }

    public Integer getIssyd3() {
        return issyd3;
    }

    public void setIssyd3(Integer issyd3) {
        this.issyd3 = issyd3;
    }

    public Integer getIsz1d3() {
        return isz1d3;
    }

    public void setIsz1d3(Integer isz1d3) {
        this.isz1d3 = isz1d3;
    }

    public Integer getIsz2d3() {
        return isz2d3;
    }

    public void setIsz2d3(Integer isz2d3) {
        this.isz2d3 = isz2d3;
    }

    public Integer getIsdyd4() {
        return isdyd4;
    }

    public void setIsdyd4(Integer isdyd4) {
        this.isdyd4 = isdyd4;
    }

    public Integer getIswdyd4() {
        return iswdyd4;
    }

    public void setIswdyd4(Integer iswdyd4) {
        this.iswdyd4 = iswdyd4;
    }

    public Integer getIsyffkd5() {
        return isyffkd5;
    }

    public void setIsyffkd5(Integer isyffkd5) {
        this.isyffkd5 = isyffkd5;
    }

    public Integer getIsqbssd5() {
        return isqbssd5;
    }

    public void setIsqbssd5(Integer isqbssd5) {
        this.isqbssd5 = isqbssd5;
    }

    public Integer getIshtqdd7() {
        return ishtqdd7;
    }

    public void setIshtqdd7(Integer ishtqdd7) {
        this.ishtqdd7 = ishtqdd7;
    }

    public Integer getIsjfsfkd7() {
        return isjfsfkd7;
    }

    public void setIsjfsfkd7(Integer isjfsfkd7) {
        this.isjfsfkd7 = isjfsfkd7;
    }

    public Integer getIsdzd7() {
        return isdzd7;
    }

    public void setIsdzd7(Integer isdzd7) {
        this.isdzd7 = isdzd7;
    }

    public Integer getIsgjjdkd7() {
        return isgjjdkd7;
    }

    public void setIsgjjdkd7(Integer isgjjdkd7) {
        this.isgjjdkd7 = isgjjdkd7;
    }

    public Integer getIssydkd7() {
        return issydkd7;
    }

    public void setIssydkd7(Integer issydkd7) {
        this.issydkd7 = issydkd7;
    }

    public Integer getIsyffkd16() {
        return isyffkd16;
    }

    public void setIsyffkd16(Integer isyffkd16) {
        this.isyffkd16 = isyffkd16;
    }

    public Integer getIsqbssd16() {
        return isqbssd16;
    }

    public void setIsqbssd16(Integer isqbssd16) {
        this.isqbssd16 = isqbssd16;
    }

    public Integer getIsgj1d16() {
        return isgj1d16;
    }

    public void setIsgj1d16(Integer isgj1d16) {
        this.isgj1d16 = isgj1d16;
    }

    public Integer getIsdf1d16() {
        return isdf1d16;
    }

    public void setIsdf1d16(Integer isdf1d16) {
        this.isdf1d16 = isdf1d16;
    }

    public Integer getIsgj2d16() {
        return isgj2d16;
    }

    public void setIsgj2d16(Integer isgj2d16) {
        this.isgj2d16 = isgj2d16;
    }

    public Integer getIsdf2d16() {
        return isdf2d16;
    }

    public void setIsdf2d16(Integer isdf2d16) {
        this.isdf2d16 = isdf2d16;
    }



    public Integer getIs30tnd19() {
        return is30tnd19;
    }

    public void setIs30tnd19(Integer is30tnd19) {
        this.is30tnd19 = is30tnd19;
    }

    public Integer getIsbgzd21() {
        return isbgzd21;
    }

    public void setIsbgzd21(Integer isbgzd21) {
        this.isbgzd21 = isbgzd21;
    }

    public Integer getIscjzd21() {
        return iscjzd21;
    }

    public void setIscjzd21(Integer iscjzd21) {
        this.iscjzd21 = iscjzd21;
    }

    public Integer getIskdd25() {
        return iskdd25;
    }

    public void setIskdd25(Integer iskdd25) {
        this.iskdd25 = iskdd25;
    }

    public Integer getIsghxd25() {
        return isghxd25;
    }

    public void setIsghxd25(Integer isghxd25) {
        this.isghxd25 = isghxd25;
    }

    public String getHtmc() {
        return htmc;
    }

    public void setHtmc(String htmc) {
        this.htmc = htmc;
    }

    public String getFh() {
        return fh;
    }

    public void setFh(String fh) {
        this.fh = fh;
    }

    public String getD13t5() {
        return d13t5;
    }

    public String getD16t13() {
        return d16t13;
    }

    public void setD16t13(String d16t13) {
        this.d16t13 = d16t13;
    }

    public String getD16t14() {
        return d16t14;
    }

    public void setD16t14(String d16t14) {
        this.d16t14 = d16t14;
    }

    public String getD16t15() {
        return d16t15;
    }

    public void setD16t15(String d16t15) {
        this.d16t15 = d16t15;
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

    public String getD4t5() {
        return d4t5;
    }

    public void setD4t5(String d4t5) {
        this.d4t5 = d4t5;
    }

    public String getD4t6() {
        return d4t6;
    }

    public void setD4t6(String d4t6) {
        this.d4t6 = d4t6;
    }

    public String getD4t7() {
        return d4t7;
    }

    public void setD4t7(String d4t7) {
        this.d4t7 = d4t7;
    }

    public String getD4t8() {
        return d4t8;
    }

    public void setD4t8(String d4t8) {
        this.d4t8 = d4t8;
    }

    public String getD4t9() {
        return d4t9;
    }

    public void setD4t9(String d4t9) {
        this.d4t9 = d4t9;
    }

    public String getD4t10() {
        return d4t10;
    }

    public void setD4t10(String d4t10) {
        this.d4t10 = d4t10;
    }

    public String getD4t11() {
        return d4t11;
    }

    public void setD4t11(String d4t11) {
        this.d4t11 = d4t11;
    }

    public String getD4t12() {
        return d4t12;
    }

    public void setD4t12(String d4t12) {
        this.d4t12 = d4t12;
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

    public String getD6t6() {
        return d6t6;
    }

    public void setD6t6(String d6t6) {
        this.d6t6 = d6t6;
    }

    public String getD6t7() {
        return d6t7;
    }

    public void setD6t7(String d6t7) {
        this.d6t7 = d6t7;
    }

    public String getD6t8() {
        return d6t8;
    }

    public void setD6t8(String d6t8) {
        this.d6t8 = d6t8;
    }

    public String getD6t9() {
        return d6t9;
    }

    public void setD6t9(String d6t9) {
        this.d6t9 = d6t9;
    }

    public String getD6t10() {
        return d6t10;
    }

    public void setD6t10(String d6t10) {
        this.d6t10 = d6t10;
    }

    public String getD6t11() {
        return d6t11;
    }

    public void setD6t11(String d6t11) {
        this.d6t11 = d6t11;
    }

    public String getD6t12() {
        return d6t12;
    }

    public void setD6t12(String d6t12) {
        this.d6t12 = d6t12;
    }

    public String getD6t13() {
        return d6t13;
    }

    public void setD6t13(String d6t13) {
        this.d6t13 = d6t13;
    }

    public String getD6t14() {
        return d6t14;
    }

    public void setD6t14(String d6t14) {
        this.d6t14 = d6t14;
    }

    public String getD6t15() {
        return d6t15;
    }

    public void setD6t15(String d6t15) {
        this.d6t15 = d6t15;
    }

    public String getD6t16() {
        return d6t16;
    }

    public void setD6t16(String d6t16) {
        this.d6t16 = d6t16;
    }

    public String getD6t17() {
        return d6t17;
    }

    public void setD6t17(String d6t17) {
        this.d6t17 = d6t17;
    }

    public String getD6t18() {
        return d6t18;
    }

    public void setD6t18(String d6t18) {
        this.d6t18 = d6t18;
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

    public String getD7t19() {
        return d7t19;
    }

    public void setD7t19(String d7t19) {
        this.d7t19 = d7t19;
    }

    public String getD7t20() {
        return d7t20;
    }

    public void setD7t20(String d7t20) {
        this.d7t20 = d7t20;
    }

    public String getD7t21() {
        return d7t21;
    }

    public void setD7t21(String d7t21) {
        this.d7t21 = d7t21;
    }

    public String getD7t22() {
        return d7t22;
    }

    public void setD7t22(String d7t22) {
        this.d7t22 = d7t22;
    }

    public String getD7t23() {
        return d7t23;
    }

    public void setD7t23(String d7t23) {
        this.d7t23 = d7t23;
    }

    public String getD7t24() {
        return d7t24;
    }

    public void setD7t24(String d7t24) {
        this.d7t24 = d7t24;
    }

    public String getD7t25() {
        return d7t25;
    }

    public void setD7t25(String d7t25) {
        this.d7t25 = d7t25;
    }

    public String getD7t26() {
        return d7t26;
    }

    public void setD7t26(String d7t26) {
        this.d7t26 = d7t26;
    }

    public String getD7t27() {
        return d7t27;
    }

    public void setD7t27(String d7t27) {
        this.d7t27 = d7t27;
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

    public String getD10t4() {
        return d10t4;
    }

    public void setD10t4(String d10t4) {
        this.d10t4 = d10t4;
    }

    public String getD10t5() {
        return d10t5;
    }

    public void setD10t5(String d10t5) {
        this.d10t5 = d10t5;
    }

    public String getD10t6() {
        return d10t6;
    }

    public void setD10t6(String d10t6) {
        this.d10t6 = d10t6;
    }

    public String getD10t7() {
        return d10t7;
    }

    public void setD10t7(String d10t7) {
        this.d10t7 = d10t7;
    }

    public String getD10t8() {
        return d10t8;
    }

    public void setD10t8(String d10t8) {
        this.d10t8 = d10t8;
    }

    public String getD10t9() {
        return d10t9;
    }

    public void setD10t9(String d10t9) {
        this.d10t9 = d10t9;
    }

    public String getD10t10() {
        return d10t10;
    }

    public void setD10t10(String d10t10) {
        this.d10t10 = d10t10;
    }

    public String getD10t11() {
        return d10t11;
    }

    public void setD10t11(String d10t11) {
        this.d10t11 = d10t11;
    }

    public String getD10t12() {
        return d10t12;
    }

    public void setD10t12(String d10t12) {
        this.d10t12 = d10t12;
    }

    public String getD10t13() {
        return d10t13;
    }

    public void setD10t13(String d10t13) {
        this.d10t13 = d10t13;
    }

    public String getD10t14() {
        return d10t14;
    }

    public void setD10t14(String d10t14) {
        this.d10t14 = d10t14;
    }

    public String getD10t15() {
        return d10t15;
    }

    public void setD10t15(String d10t15) {
        this.d10t15 = d10t15;
    }

    public String getD10t16() {
        return d10t16;
    }

    public void setD10t16(String d10t16) {
        this.d10t16 = d10t16;
    }

    public String getD10t17() {
        return d10t17;
    }

    public void setD10t17(String d10t17) {
        this.d10t17 = d10t17;
    }

    public String getD10t18() {
        return d10t18;
    }

    public void setD10t18(String d10t18) {
        this.d10t18 = d10t18;
    }

    public String getD10t19() {
        return d10t19;
    }

    public void setD10t19(String d10t19) {
        this.d10t19 = d10t19;
    }

    public String getD10t20() {
        return d10t20;
    }

    public void setD10t20(String d10t20) {
        this.d10t20 = d10t20;
    }

    public String getD10t21() {
        return d10t21;
    }

    public void setD10t21(String d10t21) {
        this.d10t21 = d10t21;
    }

    public String getD10t22() {
        return d10t22;
    }

    public void setD10t22(String d10t22) {
        this.d10t22 = d10t22;
    }

    public String getD10t23() {
        return d10t23;
    }

    public void setD10t23(String d10t23) {
        this.d10t23 = d10t23;
    }

    public String getD10t24() {
        return d10t24;
    }

    public void setD10t24(String d10t24) {
        this.d10t24 = d10t24;
    }

    public String getD10t25() {
        return d10t25;
    }

    public void setD10t25(String d10t25) {
        this.d10t25 = d10t25;
    }

    public String getD10t26() {
        return d10t26;
    }

    public void setD10t26(String d10t26) {
        this.d10t26 = d10t26;
    }

    public String getD10t27() {
        return d10t27;
    }

    public void setD10t27(String d10t27) {
        this.d10t27 = d10t27;
    }

    public String getD10t28() {
        return d10t28;
    }

    public void setD10t28(String d10t28) {
        this.d10t28 = d10t28;
    }

    public String getD10t29() {
        return d10t29;
    }

    public void setD10t29(String d10t29) {
        this.d10t29 = d10t29;
    }

    public String getD10t30() {
        return d10t30;
    }

    public void setD10t30(String d10t30) {
        this.d10t30 = d10t30;
    }

    public String getD10t31() {
        return d10t31;
    }

    public void setD10t31(String d10t31) {
        this.d10t31 = d10t31;
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

    public String getD15t3() {
        return d15t3;
    }

    public void setD15t3(String d15t3) {
        this.d15t3 = d15t3;
    }

    public String getD15t4() {
        return d15t4;
    }

    public void setD15t4(String d15t4) {
        this.d15t4 = d15t4;
    }

    public String getD15t5() {
        return d15t5;
    }

    public void setD15t5(String d15t5) {
        this.d15t5 = d15t5;
    }

    public String getD15t6() {
        return d15t6;
    }

    public void setD15t6(String d15t6) {
        this.d15t6 = d15t6;
    }

    public String getD16t1() {
        return d16t1;
    }

    public void setD16t1(String d16t1) {
        this.d16t1 = d16t1;
    }

    public String getD16t2() {
        return d16t2;
    }

    public void setD16t2(String d16t2) {
        this.d16t2 = d16t2;
    }

    public String getD16t3() {
        return d16t3;
    }

    public void setD16t3(String d16t3) {
        this.d16t3 = d16t3;
    }

    public String getD16t4() {
        return d16t4;
    }

    public void setD16t4(String d16t4) {
        this.d16t4 = d16t4;
    }

    public String getD16t5() {
        return d16t5;
    }

    public void setD16t5(String d16t5) {
        this.d16t5 = d16t5;
    }

    public String getD16t6() {
        return d16t6;
    }

    public void setD16t6(String d16t6) {
        this.d16t6 = d16t6;
    }

    public String getD16t7() {
        return d16t7;
    }

    public void setD16t7(String d16t7) {
        this.d16t7 = d16t7;
    }

    public String getD16t8() {
        return d16t8;
    }

    public void setD16t8(String d16t8) {
        this.d16t8 = d16t8;
    }

    public String getD16t9() {
        return d16t9;
    }

    public void setD16t9(String d16t9) {
        this.d16t9 = d16t9;
    }

    public String getD16t10() {
        return d16t10;
    }

    public void setD16t10(String d16t10) {
        this.d16t10 = d16t10;
    }

    public String getD16t11() {
        return d16t11;
    }

    public void setD16t11(String d16t11) {
        this.d16t11 = d16t11;
    }

    public String getD16t12() {
        return d16t12;
    }

    public void setD16t12(String d16t12) {
        this.d16t12 = d16t12;
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

    public String getD18t1() {
        return d18t1;
    }

    public void setD18t1(String d18t1) {
        this.d18t1 = d18t1;
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

    public String getD20t1() {
        return d20t1;
    }

    public void setD20t1(String d20t1) {
        this.d20t1 = d20t1;
    }

    public String getD20t2() {
        return d20t2;
    }

    public void setD20t2(String d20t2) {
        this.d20t2 = d20t2;
    }

    public String getD20t3() {
        return d20t3;
    }

    public void setD20t3(String d20t3) {
        this.d20t3 = d20t3;
    }

    public String getD20t4() {
        return d20t4;
    }

    public void setD20t4(String d20t4) {
        this.d20t4 = d20t4;
    }

    public String getD20t5() {
        return d20t5;
    }

    public void setD20t5(String d20t5) {
        this.d20t5 = d20t5;
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

    public String getD21t3() {
        return d21t3;
    }

    public void setD21t3(String d21t3) {
        this.d21t3 = d21t3;
    }

    public String getD21t4() {
        return d21t4;
    }

    public void setD21t4(String d21t4) {
        this.d21t4 = d21t4;
    }

    public String getD21t5() {
        return d21t5;
    }

    public void setD21t5(String d21t5) {
        this.d21t5 = d21t5;
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

    public String getD22t3() {
        return d22t3;
    }

    public void setD22t3(String d22t3) {
        this.d22t3 = d22t3;
    }

    public String getD22t4() {
        return d22t4;
    }

    public void setD22t4(String d22t4) {
        this.d22t4 = d22t4;
    }

    public String getD23t1() {
        return d23t1;
    }

    public void setD23t1(String d23t1) {
        this.d23t1 = d23t1;
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

    public String getD25t1() {
        return d25t1;
    }

    public void setD25t1(String d25t1) {
        this.d25t1 = d25t1;
    }

    public String getD25t2() {
        return d25t2;
    }

    public void setD25t2(String d25t2) {
        this.d25t2 = d25t2;
    }

    public String getD27t1() {
        return d27t1;
    }

    public void setD27t1(String d27t1) {
        this.d27t1 = d27t1;
    }

    public String getD27t2() {
        return d27t2;
    }

    public void setD27t2(String d27t2) {
        this.d27t2 = d27t2;
    }

    public String getD29t1() {
        return d29t1;
    }

    public void setD29t1(String d29t1) {
        this.d29t1 = d29t1;
    }

    public String getD29t2() {
        return d29t2;
    }

    public void setD29t2(String d29t2) {
        this.d29t2 = d29t2;
    }

    public String getD29t3() {
        return d29t3;
    }

    public void setD29t3(String d29t3) {
        this.d29t3 = d29t3;
    }

    public String getD29t4() {
        return d29t4;
    }

    public void setD29t4(String d29t4) {
        this.d29t4 = d29t4;
    }

    public String getD29t5() {
        return d29t5;
    }

    public void setD29t5(String d29t5) {
        this.d29t5 = d29t5;
    }

    public String getD29t6() {
        return d29t6;
    }

    public void setD29t6(String d29t6) {
        this.d29t6 = d29t6;
    }

    public String getD29t7() {
        return d29t7;
    }

    public void setD29t7(String d29t7) {
        this.d29t7 = d29t7;
    }

    public String getD29t8() {
        return d29t8;
    }

    public void setD29t8(String d29t8) {
        this.d29t8 = d29t8;
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

    public String getFj6jw1() {
        return fj6jw1;
    }

    public void setFj6jw1(String fj6jw1) {
        this.fj6jw1 = fj6jw1;
    }

    public String getFj6jw2() {
        return fj6jw2;
    }

    public void setFj6jw2(String fj6jw2) {
        this.fj6jw2 = fj6jw2;
    }

    public String getFj6jw3() {
        return fj6jw3;
    }

    public void setFj6jw3(String fj6jw3) {
        this.fj6jw3 = fj6jw3;
    }

    public String getFj6jw4() {
        return fj6jw4;
    }

    public void setFj6jw4(String fj6jw4) {
        this.fj6jw4 = fj6jw4;
    }

    public String getFj6jw5() {
        return fj6jw5;
    }

    public void setFj6jw5(String fj6jw5) {
        this.fj6jw5 = fj6jw5;
    }

    public String getFj6jw6() {
        return fj6jw6;
    }

    public void setFj6jw6(String fj6jw6) {
        this.fj6jw6 = fj6jw6;
    }

    public String getFj6jw7() {
        return fj6jw7;
    }

    public void setFj6jw7(String fj6jw7) {
        this.fj6jw7 = fj6jw7;
    }

    public String getFj6jw8() {
        return fj6jw8;
    }

    public void setFj6jw8(String fj6jw8) {
        this.fj6jw8 = fj6jw8;
    }

    public String getFj6jw9() {
        return fj6jw9;
    }

    public void setFj6jw9(String fj6jw9) {
        this.fj6jw9 = fj6jw9;
    }

    public String getFj6jw10() {
        return fj6jw10;
    }

    public void setFj6jw10(String fj6jw10) {
        this.fj6jw10 = fj6jw10;
    }

    public String getFj6jw11() {
        return fj6jw11;
    }

    public void setFj6jw11(String fj6jw11) {
        this.fj6jw11 = fj6jw11;
    }

    public String getFj6jw12() {
        return fj6jw12;
    }

    public void setFj6jw12(String fj6jw12) {
        this.fj6jw12 = fj6jw12;
    }

    public String getFj6jw13() {
        return fj6jw13;
    }

    public void setFj6jw13(String fj6jw13) {
        this.fj6jw13 = fj6jw13;
    }

    public String getFj6jw14() {
        return fj6jw14;
    }

    public void setFj6jw14(String fj6jw14) {
        this.fj6jw14 = fj6jw14;
    }

    public String getFj6jw15() {
        return fj6jw15;
    }

    public void setFj6jw15(String fj6jw15) {
        this.fj6jw15 = fj6jw15;
    }

    public String getFj6jw16() {
        return fj6jw16;
    }

    public void setFj6jw16(String fj6jw16) {
        this.fj6jw16 = fj6jw16;
    }

    public String getFj6jw17() {
        return fj6jw17;
    }

    public void setFj6jw17(String fj6jw17) {
        this.fj6jw17 = fj6jw17;
    }

    public String getFj6jw18() {
        return fj6jw18;
    }

    public void setFj6jw18(String fj6jw18) {
        this.fj6jw18 = fj6jw18;
    }

    public String getFj6jw19() {
        return fj6jw19;
    }

    public void setFj6jw19(String fj6jw19) {
        this.fj6jw19 = fj6jw19;
    }

    public String getFj6jw20() {
        return fj6jw20;
    }

    public void setFj6jw20(String fj6jw20) {
        this.fj6jw20 = fj6jw20;
    }

    public String getFj6jw21() {
        return fj6jw21;
    }

    public void setFj6jw21(String fj6jw21) {
        this.fj6jw21 = fj6jw21;
    }

    public String getFj6jw22() {
        return fj6jw22;
    }

    public void setFj6jw22(String fj6jw22) {
        this.fj6jw22 = fj6jw22;
    }

    public String getFj6jw23() {
        return fj6jw23;
    }

    public void setFj6jw23(String fj6jw23) {
        this.fj6jw23 = fj6jw23;
    }

    public String getFj6jw24() {
        return fj6jw24;
    }

    public void setFj6jw24(String fj6jw24) {
        this.fj6jw24 = fj6jw24;
    }

    public String getFj6jw25() {
        return fj6jw25;
    }

    public void setFj6jw25(String fj6jw25) {
        this.fj6jw25 = fj6jw25;
    }

    public String getFj6jw26() {
        return fj6jw26;
    }

    public void setFj6jw26(String fj6jw26) {
        this.fj6jw26 = fj6jw26;
    }

    public String getFj6jw27() {
        return fj6jw27;
    }

    public void setFj6jw27(String fj6jw27) {
        this.fj6jw27 = fj6jw27;
    }

    public String getFj6jw28() {
        return fj6jw28;
    }

    public void setFj6jw28(String fj6jw28) {
        this.fj6jw28 = fj6jw28;
    }

    public String getFj6jw29() {
        return fj6jw29;
    }

    public void setFj6jw29(String fj6jw29) {
        this.fj6jw29 = fj6jw29;
    }

    public String getFj6jw30() {
        return fj6jw30;
    }

    public void setFj6jw30(String fj6jw30) {
        this.fj6jw30 = fj6jw30;
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
}
