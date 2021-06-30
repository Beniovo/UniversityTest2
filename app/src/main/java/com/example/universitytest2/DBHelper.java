package com.example.universitytest2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "uni2db.db";
    public SQLiteDatabase myDatabase;
    private final Context mycontext;
    private static final String DB_PATH = "/data/data/com.example.universitytest2/databases/";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
        this.mycontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean checkDatabase(){
        try {
            final String mPath = DB_PATH + DB_NAME;
            final File file = new File(mPath);
            if (file.exists())
                return true;
            else
                return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private void copyDatabase() throws IOException{
        try{
           InputStream myInput = this.mycontext.getAssets().open(DB_NAME);
            //InputStream myInput = mycontext.getResources().openRawResource(R.raw.uniesdb);
            String outFname = DB_PATH + DB_NAME;
            OutputStream myOut = new FileOutputStream(outFname);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = myInput.read(mBuffer)) > 0) {
                myOut.write(mBuffer, 0, mLength);
            }
            myOut.flush();
            myOut.close();
            myInput.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("Copied dbase",  "DBASE COPIED");
    }

    public void createDatabase() throws IOException {
        boolean mDatabaseExist = checkDatabase();
        if(!mDatabaseExist){
            this.getReadableDatabase();
            this.close();


            try{
                copyDatabase(); //seems i copy the database everytime
            }catch (IOException mIoexception){
                mIoexception.printStackTrace();
                throw new Error("Error copying");
            }finally {
                {
                    this.close();
                }
            }
        }
    }

    @Override
    public synchronized void close() {
        if(myDatabase != null)
            myDatabase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }

    public ArrayList<String> getStates(){

        try{
            createDatabase();
        }catch (IOException e){
            throw new Error("can do it");
        }
        ArrayList<String> s = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();



        Cursor c = db.rawQuery("select distinct state from Institution", null);

        while(c.moveToNext()){
           s.add(c.getString(0));
        }
        c.close();
        db.close();

        return s;
    }
}
