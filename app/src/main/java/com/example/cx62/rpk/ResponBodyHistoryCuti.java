package com.example.cx62.rpk;

import java.util.List;

public class ResponBodyHistoryCuti {

    private List<CutiModel> data;

    public ResponBodyHistoryCuti(List<CutiModel> listCuti) {
        this.data = listCuti;
    }

    public List<CutiModel> getListCuti() {
        return data;
    }

    public void setListCuti(List<CutiModel> listCuti) {
        this.data = listCuti;
    }
}
