package com.noelchew.ncutils.database;

import java.util.HashMap;

/**
 * Created by noelchew on 03/11/2016.
 */

public class DbObjectConstants {

    public static final String COLUMN_UUID = "uuid";
    public static final String COLUMN_JSON = "json";
    public static final String COLUMN_TIMESTAMP = "timestamp";


    public static final String COLUMN_INTEGER_TYPE = "integer";
    public static final String COLUMN_LONG_TYPE = "long";
    public static final String COLUMN_TEXT_TYPE = "text";
    public static final String PRIMARY_KEY_TYPE = "primary key(";

    public static String getCreateTableQuery(String tableName) {
        HashMap<String, String> columnNameAndType = new HashMap<String, String>();
        columnNameAndType.put(DbObjectConstants.COLUMN_UUID, DbObjectConstants.COLUMN_TEXT_TYPE);
        columnNameAndType.put(DbObjectConstants.COLUMN_JSON, DbObjectConstants.COLUMN_TEXT_TYPE);
        columnNameAndType.put(DbObjectConstants.COLUMN_TIMESTAMP, DbObjectConstants.COLUMN_LONG_TYPE);
        String primary_key = DbObjectConstants.PRIMARY_KEY_TYPE + DbObjectConstants.COLUMN_UUID + ")";

        String query = QueryHelper.formCreateTableSqlString(tableName, columnNameAndType, primary_key);

        return query;
    }

    public static String getDeleteTableQuery(String tableName) {
        return QueryHelper.formDeleteTableSqlString(tableName);
    }

}
