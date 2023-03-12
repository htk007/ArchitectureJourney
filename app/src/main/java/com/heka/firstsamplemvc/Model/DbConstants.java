package com.heka.firstsamplemvc.Model;

public final class DbConstants {
    private DbConstants() {

    }

    public static final String DATABASE_NAME="notebook_db";
    public static final String TABLE_NAME="notes";
    public static final int DATABASE_Version = 2;
    public static final String NID="_id";     // Primary Key
    public static final String NOTE = "Note";
    public static final String CREATE_TIME="CreateTime";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+NID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NOTE+" VARCHAR(255),"+ CREATE_TIME + " VARCHAR(255));";
    public static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;

}
