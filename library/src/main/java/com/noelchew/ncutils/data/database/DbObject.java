package com.noelchew.ncutils.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by noelchew on 03/11/2016.
 */

public abstract class DbObject<T> {

    private String uuid;
    private long timestamp;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    protected abstract String getTableName();

    public DbObject() {
    }

    public T get(String uuid) {
        Cursor cursor = null;
        try {
            cursor = DatabaseModule.getInstance().getReadableDatabase().rawQuery("SELECT * FROM " + getTableName() + " WHERE " + DbObjectConstants.COLUMN_UUID + "='" +
                    uuid + "'", null);
            if (cursor != null) {
                if (!cursor.moveToFirst()) {
                    return null;
                }

                int indexUuid = cursor.getColumnIndex(DbObjectConstants.COLUMN_UUID);
                int indexJson = cursor.getColumnIndex(DbObjectConstants.COLUMN_JSON);
                int indexTimestamp = cursor.getColumnIndex(DbObjectConstants.COLUMN_TIMESTAMP);

                String json = cursor.getString(indexJson);
                long timestamp = cursor.getLong(indexTimestamp);

//                DbObject<T> object = new Gson().fromJson(json, new TypeToken<DbObject<T>>() {
//                }.getType());

                T object = (T) new Gson().fromJson(json, this.getClass());
                ((DbObject<T>) object).setUuid(uuid);
                ((DbObject<T>) object).setTimestamp(timestamp);

                return object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    public ArrayList<T> getAll() {
        return getAll(true);
    }

    public ArrayList<T> getAll(boolean descendingTimestamp) {
        ArrayList<T> objects = new ArrayList<>();
        Cursor cursor = null;

        String orderByCondition = descendingTimestamp ? " DESC" : " ASC";
        try {
            cursor = DatabaseModule.getInstance().getReadableDatabase().rawQuery("SELECT * FROM " + getTableName()
                    + " ORDER BY " + DbObjectConstants.COLUMN_TIMESTAMP + orderByCondition, null);
            if (cursor != null) {

                if (!cursor.moveToFirst()) {
                    return null;
                }

                int indexUuid = cursor.getColumnIndex(DbObjectConstants.COLUMN_UUID);
                int indexJson = cursor.getColumnIndex(DbObjectConstants.COLUMN_JSON);
                int indexTimestamp = cursor.getColumnIndex(DbObjectConstants.COLUMN_TIMESTAMP);

                do {

                    String uuid = cursor.getString(indexUuid);
                    String json = cursor.getString(indexJson);
                    long timestamp = cursor.getLong(indexTimestamp);

//                    T object = new Gson().fromJson(json, new TypeToken<T>() {}.getType());
                    T object = (T) new Gson().fromJson(json, this.getClass());

//                    DbObject object = new Gson().fromJson(json, DbObject.class);

//                    ((DbObject) object).setUuid(uuid);
//                    ((DbObject) object).setTimestamp(timestamp);

                    objects.add(object);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return objects;
    }

    public ArrayList<T> getPaginatedList(int offset, int takeAmount) {
        return getPaginatedList(offset, takeAmount, true);
    }

    public ArrayList<T> getPaginatedList(int offset, int takeAmount, boolean descendingTimestamp) {
        ArrayList<T> objects = new ArrayList<>();
        Cursor cursor = null;

        String orderByCondition = descendingTimestamp ? " DESC" : " ASC";
        try {
            cursor = DatabaseModule.getInstance().getReadableDatabase().rawQuery("SELECT * FROM " + getTableName()
                    + " ORDER BY " + DbObjectConstants.COLUMN_TIMESTAMP + orderByCondition + " LIMIT " + offset + "," + takeAmount, null);
            if (cursor != null) {

                if (!cursor.moveToFirst()) {
                    return null;
                }

                int indexUuid = cursor.getColumnIndex(DbObjectConstants.COLUMN_UUID);
                int indexJson = cursor.getColumnIndex(DbObjectConstants.COLUMN_JSON);
                int indexTimestamp = cursor.getColumnIndex(DbObjectConstants.COLUMN_TIMESTAMP);

                do {

                    String uuid = cursor.getString(indexUuid);
                    String json = cursor.getString(indexJson);
                    long timestamp = cursor.getLong(indexTimestamp);

                    T object = (T) new Gson().fromJson(json, this.getClass());

//                    ((DbObject) object).setUuid(uuid);
//                    ((DbObject) object).setTimestamp(timestamp);

                    objects.add(object);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return objects;
    }

    public boolean save() {
        if (TextUtils.isEmpty(uuid)) {
            setUuid(UUID.randomUUID().toString());
        }
        String json = toJson();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbObjectConstants.COLUMN_UUID, getUuid());
        contentValues.put(DbObjectConstants.COLUMN_JSON, json);
        contentValues.put(DbObjectConstants.COLUMN_TIMESTAMP, System.currentTimeMillis());
        try {
            DatabaseModule.getInstance().getWritableDatabase().insertOrThrow(getTableName(), null, contentValues);
            return true;
        } catch (SQLiteConstraintException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update() {
        return update(false);
    }

    public boolean update(boolean updateTimestamp) {
        String json = toJson();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbObjectConstants.COLUMN_JSON, json);
        if (updateTimestamp) {
            contentValues.put(DbObjectConstants.COLUMN_TIMESTAMP, System.currentTimeMillis());
        }
        try {
            DatabaseModule.getInstance().getWritableDatabase().update(getTableName(), contentValues, DbObjectConstants.COLUMN_UUID + " = '" + getUuid() + "'", null);
            return true;
        } catch (SQLiteConstraintException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete() {
        try {
            DatabaseModule.getInstance().getWritableDatabase().delete(getTableName(), DbObjectConstants.COLUMN_UUID + " = '" + getUuid() + "'", null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getCreateTableQuery() {
        return DbObjectConstants.getCreateTableQuery(getTableName());
    }

    public String getDeleteTableQuery() {
        return DbObjectConstants.getDeleteTableQuery(getTableName());
    }


    public String toJson() {
        return new Gson().toJson(this);
    }

}
