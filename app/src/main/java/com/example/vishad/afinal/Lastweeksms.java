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

public class Lastweeksms extends AppCompatActivity {

    String s = "";
    String[] num;
    String number;
    SharedPreferences prefs;
    ArrayAdapter arrayAdapter;
    String[] datte;
    String YesterdayDate7;
    String YesterdayDate8;
    String YesterdayDate9;
    String YesterdayDate10;
    String YesterdayDate11;
    String YesterdayDate12;
    String YesterdayDate13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lastweeksms);

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
        calenda.add(Calendar.DATE, -7);
        Date dat7 = calenda.getTime();
        String[] yesterdayDate7 = dat7.toString().split(" ");
        YesterdayDate7 = yesterdayDate7[2] + yesterdayDate7[1];


        //lastweek
        calenda.add(Calendar.DATE, -8);
        Date dat8 = calenda.getTime();
        String[] yesterdayDate8 = dat8.toString().split(" ");
        YesterdayDate8 = yesterdayDate8[2] + yesterdayDate8[1];

        calenda.add(Calendar.DATE, -9);
        Date dat9 = calenda.getTime();
        String[] yesterdayDate9 = dat9.toString().split(" ");
        YesterdayDate9 = yesterdayDate9[2] + yesterdayDate9[1];

        calenda.add(Calendar.DATE, -10);
        Date dat10 = calenda.getTime();
        String[] yesterdayDate10 = dat10.toString().split(" ");
        YesterdayDate10 = yesterdayDate10[2] + yesterdayDate10[1];

        calenda.add(Calendar.DATE, -11);
        Date dat11 = calenda.getTime();
        String[] yesterdayDate11 = dat11.toString().split(" ");
        YesterdayDate11 = yesterdayDate11[2] + yesterdayDate11[1];

        calenda.add(Calendar.DATE, -12);
        Date dat12 = calenda.getTime();
        String[] yesterdayDate12 = dat12.toString().split(" ");
        YesterdayDate12 = yesterdayDate12[2] + yesterdayDate12[1];

        calenda.add(Calendar.DATE, -13);
        Date dat13 = calenda.getTime();
        String[] yesterdayDate13 = dat13.toString().split(" ");
        YesterdayDate13 = yesterdayDate13[2] + yesterdayDate13[1];


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

                //  Log.v(TAG,"Current: "+strDate);

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
                String formattedDate = df.format(c);
                Log.v("c", "" + formattedDate);
                String[] s = strDate.split(" ");
                Log.v("s1", "" + s[0]);
                Log.v("s2", "" + s[1]);

                if ((da.equals(YesterdayDate8)) || (da.equals(YesterdayDate9)) || (da.equals(YesterdayDate10)) || (da.equals(YesterdayDate11)) || (da.equals(YesterdayDate12)) || (da.equals(YesterdayDate13)) || (da.equals(YesterdayDate7))) {

                    String[] value = smsInboxcursor.getString(indexBody).split(": ");
                    Log.v("value", "" + value[1]);
                    sum += Double.parseDouble(value[1]);
                    String str = "SMS From: " + smsInboxcursor.getString(indexAddress) +
                            "\n" + smsInboxcursor.getString(indexBody) + "\n" + "date: " + da + "\n" + "Time: " + datte[3] + "\n";

                    arrayAdapter.add(str);
                }

            }


        }
        while (smsInboxcursor.moveToNext());

    }
}