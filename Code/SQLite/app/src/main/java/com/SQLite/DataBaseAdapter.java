package com.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseAdapter  {

    private SQLiteHelper sqLiteHelper;
    private Context context;

    public DataBaseAdapter(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
        this.context=context;
    }

    public long  insertData(String username, String pass)
    {

        String name=username.replaceAll("\\s+","");
        String password=pass.replaceAll("\\s+","");
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase db=sqLiteHelper.getWritableDatabase();
        contentValues.put(SQLiteHelper.NAME,name);
        contentValues.put(SQLiteHelper.PASSWORD,password);
        long id=db.insert(sqLiteHelper.TABLE_NAME,null,contentValues);
        Log.d("value",String.valueOf(id));
        return id;

    }

    public String  getAllData()
    {
        SQLiteDatabase db=sqLiteHelper.getWritableDatabase();
        String columns[]={sqLiteHelper.UID,sqLiteHelper.NAME,sqLiteHelper.PASSWORD};
        StringBuffer buffer=new StringBuffer();
        Cursor cursor = db.query(SQLiteHelper.TABLE_NAME, columns, null, null, null, null,null);
        int i=0;

        if(cursor==null)
        {
            return "no data was found";
        }

            while(cursor.moveToNext()) {

                    int id=cursor.getInt(0);
                    String name=cursor.getString(1);
                    String pasword=cursor.getString(2);
                    buffer.append(id+" "+name+" "+pasword+"\n");


            }
            return buffer.toString();

    }


    public String getData(String name){
        SQLiteDatabase db=sqLiteHelper.getWritableDatabase();
        String columns[]={sqLiteHelper.NAME,sqLiteHelper.PASSWORD};
        /**1st way**/
        /*
        String whereCondition=columns[0]+"=\""+name+"\"";
        Cursor cursor=db.query(sqLiteHelper.TABLE_NAME,columns,whereCondition,null,null,null,null);
        */
        /***2nd way**/
         String selectionArgs[]={name};
         String whereCondition=columns[0]+"=?";
         Cursor cursor=db.query(sqLiteHelper.TABLE_NAME,columns,whereCondition,selectionArgs,null,null,null);
         Log.d("cursor",cursor.toString());
        StringBuffer stringBuffer=new StringBuffer();
        int index;
        if(cursor!=null) {

            while (cursor.moveToNext()) {

                /***
                 * we could also specify index row
                 * String name=cursor.getString(1)
                 * String password=cursor.getString(2)
                 * In this particularly  case all values are string so we can use a for to retrieve data.
                 * This is way better, because if database structure change nothing will happen to this piece of code,
                 * Also column returned may change depending of query we perform
                 */
                for (int i = 0; i < columns.length; ++i) {
                    index = cursor.getColumnIndex(columns[i]);
                    stringBuffer.append(cursor.getString(index) + " ");

                }
                stringBuffer.append("\n");

            }
        }
        else
        {
            Toast.makeText(context,"empty Database",Toast.LENGTH_SHORT).show();

        }
        return stringBuffer.toString();

    }






    class SQLiteHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME ="MyDataBase.db";
        private static final int DATABASE_VERSION = 3;
        static final String PASSWORD = "Password";
        static final String NAME ="Name";
        static final String UID="_id";
        static final String TABLE_NAME ="USERS";
        private static final String createTable="CREATE TABLE "+TABLE_NAME+"("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255), "+PASSWORD+" VARCHAR(255), UNIQUE("+NAME+"));";
        private static final String dropTable="DROP TABLE IF EXISTS "+TABLE_NAME+";";
        private Context context;




        public SQLiteHelper(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
            Toast.makeText(context,"constructor called",Toast.LENGTH_SHORT).show();
        }



        @Override
        public void onCreate(SQLiteDatabase db) {

            try{
                //Toast.makeText(context,"onCreate called",Toast.LENGTH_SHORT).show();
                db.execSQL(createTable);
                //db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES (\"john\" )");
                //db.execSQL("CREATE TABLE "+ TABLE_NAME+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255));");

            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE if exists table1;");

            try {
                db.execSQL(dropTable);
                onCreate(db);
                //Toast.makeText(context, "onUpgrade called", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



}

