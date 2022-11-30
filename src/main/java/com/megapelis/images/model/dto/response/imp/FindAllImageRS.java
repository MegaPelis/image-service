package com.megapelis.images.model.dto.response.imp;


import java.util.List;

public class FindAllImageRS {

    private List<FindByIdImageRS> findAll;

    public FindAllImageRS() {
    }

    public FindAllImageRS(List<FindByIdImageRS> findAll) {
        this.findAll = findAll;
    }

    public List<FindByIdImageRS> getFindAll() {
        return findAll;
    }

    public void setFindAll(List<FindByIdImageRS> findAll) {
        this.findAll = findAll;
    }
}
