package com.example.yang_.yangzhang_project.database;


public class ItemsTable {
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ID = "itemId";
    public static final String COLUMN_NAME = "itemName";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_INSTRUCTION = "instruction";
    public static final String COLUMN_POSITION = "sortPosition";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_IMAGE = "image";

    public static final String[] ALL_COLUMNS =
            {COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION,
                    COLUMN_CATEGORY, COLUMN_INSTRUCTION, COLUMN_POSITION, COLUMN_COST, COLUMN_IMAGE};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_CATEGORY + " TEXT," +
                    COLUMN_INSTRUCTION + " TEXT," +
                    COLUMN_POSITION + " INTEGER," +
                    COLUMN_COST + " REAL," +
                    COLUMN_IMAGE + " TEXT" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;
}

