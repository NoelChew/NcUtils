package com.noelchew.ncutils.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by noelchew on 03/11/2016.
 */

public class DatabaseModule extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseModule";

    private SQLiteDatabase sqliteDatabase = null;
    private static Context context;
    private static int dbVersion;
    private static ArrayList<DbObject> dbObjectArrayList;

    private static DatabaseModule mInstance = null;

    public ArrayList<DbObject> getDbObjectArrayList() {
        return dbObjectArrayList;
    }

    public DatabaseModule(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, ArrayList<DbObject> dbObjectArrayList) {
        super(context, name, factory, version);
        this.dbObjectArrayList = dbObjectArrayList;
    }

    public static String getDatabaseName(Context context) {
        return context.getPackageName().replace("\\.", "_") + ".db";
    }

    public static void init(Context _context, int _dbVersion, DbObject... dbObjectArray) {
        context = _context;

        dbObjectArrayList = new ArrayList<>(Arrays.asList(dbObjectArray));
        dbVersion = _dbVersion;
    }

    public synchronized static DatabaseModule getInstance() {
        if (context == null) {
            Log.e(TAG, "DatabaseModule has not been initialised.");
            return null;
        }

        if (mInstance == null) {
            mInstance = new DatabaseModule(context, getDatabaseName(context), null, dbVersion, dbObjectArrayList);
        }

        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        if (sqliteDatabase == null) {
            sqliteDatabase = database;
        }
        for (DbObject dbObject : dbObjectArrayList) {
            database.execSQL(dbObject.getCreateTableQuery());
        }
    }

    @Override
    public synchronized void close() {
        if (sqliteDatabase != null) {
            sqliteDatabase.close();
        }
        super.close();
    }

    public void dropAllTables(SQLiteDatabase database) {
        for (DbObject dbObject : dbObjectArrayList) {
            database.execSQL(dbObject.getDeleteTableQuery());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DatabaseModule.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        dropAllTables(db);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DatabaseModule.class.getName(),
                "Downgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        dropAllTables(db);
        onCreate(db);
    }
}