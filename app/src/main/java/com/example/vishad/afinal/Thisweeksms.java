package com.example.vishad.afinal;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Thisweeksms extends AppCompatActivity {


    String s="";
    String [] num;
    String number;
    SharedPreferences prefs;
    ArrayAdapter arrayAdapter;
    String []  datte;
    String  yesterdayDate;
    String YesterdayDate2;
    String YesterdayDate3;
    String YesterdayDate4;
    String YesterdayDate5;
    String YesterdayDate6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thisweeksms);

        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        s = prefs.getString("number", null);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        Log.v("savedNumber", " " + s);

        number = getIntent().getStringExtra("MyNumber");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("ddMMMyyyy");
        String strDate = "Current Date : " + mdformat.format(calendar.getTime());

        //  Log.v(TAG,"Current: "+strDate);

        // Log.v( "friday" ,"" +number);
        refreshSmsInbox();
    }


    public void refreshSmsInbox() {
        int rows = 0;
        double sum = 0;
        double sumyesterday = 0;
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxcursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int indexBody = smsInboxcursor.getColumnIndex("body");
        int indexAddress = smsInboxcursor.getColumnIndex("address");
        String i = s;
        Log.v("hel", "msg" + i);
        //    String check = "00923362503565";
        Calendar calenda = Calendar.getInstance();
        calenda.add(Calendar.DATE, -1);
        Date dat = calenda.getTime();
        String[] yesterdayDateParts = dat.toString().split(" ");
        yesterdayDate = yesterdayDateParts[2] + yesterdayDateParts[1];

        calenda.add(Calendar.DATE, -2);
        Date dat2 = calenda.getTime();
        String[] yesterdayDate2 = dat2.toString().split(" ");
        YesterdayDate2 = yesterdayDate2[2] + yesterdayDate2[1];

        calenda.add(Calendar.DATE, -3);
        Date dat3 = calenda.getTime();
        String[] yesterdayDate3 = dat3.toString().split(" ");
        YesterdayDate3 = yesterdayDate3[2] + yesterdayDate3[1];


        calenda.add(Calendar.DATE, -4);
        Date dat4 = calenda.getTime();
        String[] yesterdayDate4 = dat4.toString().split(" ");
        YesterdayDate4 = yesterdayDate4[2] + yesterdayDate4[1];

        calenda.add(Calendar.DATE, -5);
        Date dat5 = calenda.getTime();
        String[] yesterdayDate5 = dat5.toString().split(" ");
        YesterdayDate5 = yesterdayDate5[2] + yesterdayDate5[1];

        calenda.add(Calendar.DATE, -6);
        Date dat6 = calenda.getTime();
        String[] yesterdayDate6 = dat6.toString().split(" ");
        YesterdayDate6 = yesterdayDate6[2] + yesterdayDate6[1];

        if (indexBody < 0 || !smsInboxcursor.moveToFirst())
            return;
        do {
            String number = smsInboxcursor.getString(indexAddress);
            if (number.equals(i)) {

                int dateIndex = smsInboxcursor.getColumnIndex("date");
                Log.v("dateIndex", "" + dateIndex);

                String date = smsInboxcursor.getString(smsInboxcursor.getColumnIndex("date"));
                Long timestamp = Long.parseLong(date);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);

                Date finaldate = calendar.getTime();
                String smsDate = finaldate.toString();
                Log.v("act", "" + smsDate);

                datte = smsDate.split(" ");
                Log.v("Day", "" + datte[0]);
                Log.v("Month", "" + datte[1]);
                Log.v("Date", "" + datte[2]);
                Log.v("Time", "" + datte[3]);

                String da = datte[2].concat(datte[1]);
                String datecomplete = da.concat(datte[5]);
                Log.v("da", "" + datecomplete);
                // num = smsInboxcursor.getString(indexBody).split(": ");
               // Log.v(TAG, smsInboxcursor.getString(indexBody));

                //   Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("ddMMMyyyy");
                String strDate = "Current Date : " + mdformat.format(calendar.getTime());

             //   Log.v(TAG, "Current: " + strDate);

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
                String formattedDate = df.format(c);
                Log.v("c", "" + formattedDate);

                calendar.add(Calendar.DATE, -1);
                String z = mdformat.format(calendar.getTime());
                //  int k = Integer.parseInt(formattedDate);
                //int q= k-1;
                //String z= String.valueOf(k);
                Log.v("last", "" + z);

                String[] s = strDate.split(" ");
                Log.v("s1", "" + s[0]);
                Log.v("s2", "" + s[1]);

                if ((datecomplete.equals(formattedDate)) || (da.equals(yesterdayDate)) || (da.equals(YesterdayDate2)) || (da.equals(YesterdayDate3)) || (da.equals(YesterdayDate4)) || (da.equals(YesterdayDate5)) || (da.equals(YesterdayDate6))) {
                    String[] value = smsInboxcursor.getString(indexBody).split(": ");
                    Log.v("value",""+value[1]);
                    sum += Double.parseDouble(value[1]);
                    String str = "SMS From: " + smsInboxcursor.getString(indexAddress) +
                            "\n" + smsInboxcursor.getString(indexBody) + "\n" + "date: " +da +"\n"+"Time: "+datte[3]+"\n" ;

                    arrayAdapter.add(str);
                }

            }

        }
        while (smsInboxcursor.moveToNext());
    }
}