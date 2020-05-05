package com.kong.ds02.dbConfig;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 13:48
 */
public enum DsKey {
    DS1("ds1DataSource"),
    DS2("ds2DataSource"),
    DS3("ds3DataSource");

    private String dsName;

    DsKey(String dsName) {
        this.dsName = dsName;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }
}
