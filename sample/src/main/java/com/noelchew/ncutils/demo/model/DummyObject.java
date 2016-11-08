package com.noelchew.ncutils.demo.model;

import com.noelchew.ncutils.database.DbObject;

/**
 * Created by noelchew on 03/11/2016.
 */

public class DummyObject extends DbObject<DummyObject> {

    private String name;
    private String description;

    public DummyObject() {
        super();
    }

    public DummyObject(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected String getTableName() {
        return "DUMMY_TABLE";
    }
}
