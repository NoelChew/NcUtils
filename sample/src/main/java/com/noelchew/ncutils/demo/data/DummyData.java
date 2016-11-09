package com.noelchew.ncutils.demo.data;

import com.noelchew.ncutils.demo.model.DummyObject;

import java.util.ArrayList;

/**
 * Created by noelchew on 09/11/2016.
 */

public class DummyData {
    public static ArrayList<DummyObject> getGeneratedDummyData() {
        ArrayList<DummyObject> dummyObjectArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dummyObjectArrayList.add(new DummyObject("Item " + (i + 1), "Description for Item " + (i + 1)));
        }
        return dummyObjectArrayList;
    }
}
