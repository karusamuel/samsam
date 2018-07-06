package com.example.taifa.last;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TimetableDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASENAME="timeTableDatabaseS";
    public static String TABLENAME="timetableList";
    public static String UNITCODE="unitCode";
    public static String UNITNAME="unitName";
    public static String LECTURENAME="unitLecturesName";
    public static String UNITVENUE="unitVenue";
    public static String UNITTIME="unitTime";
    public static String UNITDAY = "unitday";
    public static String UNITCOURSE = "unitcourse";


    public TimetableDatabaseHelper(Context context){
        super(context,DATABASENAME,null,1);
    }

    public void onCreate(SQLiteDatabase myDb){

        myDb.execSQL("CREATE TABLE IF NOT EXISTS  "+TABLENAME+" ("
                + UNITCODE +" TEXT PRIMARY KEY,  "
                + UNITNAME +" TEXT, "
                + UNITVENUE +" TEXT, "
                + LECTURENAME +" TEXT, "
                + UNITTIME +" TEXT, "
                + UNITDAY +" TEXT, "
                + UNITCOURSE + " TEXT)"
                );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int previousVersion, int newVersion) {
        if(newVersion > previousVersion){
            sqLiteDatabase.execSQL("DROP TABLE " + TABLENAME);
            onCreate(sqLiteDatabase);

        }

    }

    public void insertData(TimetableContract contract){
        SQLiteDatabase myDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UNITCODE,contract.unitCode);
        contentValues.put(UNITNAME,contract.getUnitName());
        contentValues.put(UNITTIME,contract.unitTime);
        contentValues.put(LECTURENAME,contract.lectureName);
        contentValues.put(UNITVENUE,contract.getUnitVenue());
        contentValues.put(UNITDAY,contract.unitDay);
        contentValues.put(UNITCOURSE,contract.unitCourse);

        myDb.insert(TABLENAME,null,contentValues);



    }
    public ArrayList<TimetableContract> fetchData(String course){
        TimetableContract contract ;
        ArrayList<TimetableContract> list = new ArrayList<>();
        SQLiteDatabase myDb = getReadableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT* FROM " + TABLENAME +" WHERE "+UNITCOURSE+" = " + course ,null);
        if(cursor.moveToFirst()){
            do{
                contract =  new TimetableContract();
                contract.unitCode=cursor.getString(cursor.getColumnIndex(UNITCODE));
                contract.unitName=cursor.getString(cursor.getColumnIndex(UNITNAME));
                contract.unitTime=cursor.getString(cursor.getColumnIndex(UNITTIME));
                contract.unitVenue=cursor.getString(cursor.getColumnIndex(UNITVENUE));
                contract.lectureName=cursor.getString(cursor.getColumnIndex(LECTURENAME));
                contract.unitDay = cursor.getString(cursor.getColumnIndex(UNITDAY));
                contract.unitCourse = cursor.getString(cursor.getColumnIndex(UNITCOURSE));

                list.add(contract);


            }while (cursor.moveToNext());
        }


        return list;
    }
    public ArrayList<TimetableContract> fetchCourses(){
        TimetableContract contract ;
        ArrayList<TimetableContract> list = new ArrayList<>();
        SQLiteDatabase myDb = getReadableDatabase();
        Cursor cursor = myDb.rawQuery("SELECT "+UNITCOURSE+" FROM " + TABLENAME,null);
        if(cursor.moveToFirst()){
            do{
                contract =  new TimetableContract();
                contract.unitCourse=cursor.getString(cursor.getColumnIndex(UNITCOURSE));


                list.add(contract);


            }while (cursor.moveToNext());
        }


        return list;
    }



}
