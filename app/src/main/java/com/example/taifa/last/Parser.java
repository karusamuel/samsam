package com.example.taifa.last;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Parser {
    InputStream inputStream;
    TimetableContract one;
    ArrayList<TimetableContract> list;
    Context context;
    public Parser(InputStream stream,Context context){
        inputStream = stream;
        list = new ArrayList<>();
        this.context = context;
        parse();

    }

    public void parse(){

        try {
            XmlPullParser pullParser = Xml.newPullParser();
            pullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            pullParser.setInput(inputStream,null);

            readTag(pullParser);
        }catch (XmlPullParserException  e){

        }



    }

    public  void readTag(XmlPullParser pullParser){
        one = new TimetableContract();
        try {
            while (pullParser.next() != XmlPullParser.END_DOCUMENT) {
                 if(pullParser.getEventType()==XmlPullParser.START_TAG ){
                    startTag(pullParser);

                } else if(pullParser.getEventType()==XmlPullParser.END_TAG ) {
                    endTag(pullParser);
                }



            }
        }catch (IOException e){


        }catch (XmlPullParserException  e){

    }

    }

    public void startTag (XmlPullParser parser)throws IOException,XmlPullParserException{

        switch (parser.getName()){

            case "unit":
                one.unitName=parser.getAttributeValue(0);

                break;

            case "course":
                parser.next();
                one.unitCourse=parser.getText();

                break;

            case "time":
                parser.next();
                one.unitTime=parser.getText();

                break;
            case "venue":
                parser.next();
                one.unitVenue=parser.getText();

                break;
            case "code":
                parser.next();
                one.unitCode=parser.getText();

                break;
            case "lecture":
                parser.next();
                one.lectureName=parser.getText();

                break;
            case "day":
                parser.next();
                one.unitDay=parser.getText();

                break;
        }

    }
    public void endTag(XmlPullParser parser){
        if(parser.getName().equals("unit")){
            list.add(one);
            one = new TimetableContract();

        }else if(parser.getName().equals("Timetable")) {
            Log.e("Parsed","passsed muther fucker");

            TimetableDatabaseHelper helper = new TimetableDatabaseHelper(context);
            do {
                helper.insertData(list.remove(0));
                Log.e("Parsed","inserted");

            }while (list.isEmpty()==false);

        }




    }



}
