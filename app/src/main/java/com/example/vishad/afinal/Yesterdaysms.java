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
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Yesterdaysms extends AppCompatActivity {

    String number;
    SharedPreferences prefs;
    ArrayAdapter arrayAdapter;
    String s;
    String[] datte;
    String yesterdayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesterdaysms);
        ListView listView = (ListView) findViewById(R.id.list);

        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        s = prefs.getString("number", null);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
        Log.v("savedNumber", " " + s);

        number = getIntent().getStringExtra("MyNumber");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("ddMMMyyyy");
        String strDate = "Current Date : " + mdformat.format(calendar.getTime());

        // Log.v(TAG,"Current: "+strDate);

        Log.v("friday", "" + number);
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
        yesterdayDate = yesterdayDateParts[2]+yesterdayDateParts[1];


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
                //   Log.v(TAG, smsInboxcursor.getString(indexBody));

                //   Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("ddMMMyyyy");
                String strDate = "Current Date : " + mdformat.format(calendar.getTime());

                // Log.v(TAG,"Current: "+strDate);

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
                String formattedDate = df.format(c);
                Log.v("c", "" + formattedDate);

                calendar.add(Calendar.DATE, -1);
                String z = mdformat.format(calendar.getTime());
                Log.v("last", "" + z);

                String[] s = strDate.split(" ");
                Log.v("s1", "" + s[0]);
                Log.v("s2", "" + s[1]);


                if (da.equals(yesterdayDate))
                {

                    String str = "SMS From: " + smsInboxcursor.getString(indexAddress) +
                            "\n" + smsInboxcursor.getString(indexBody) + "\n" + "date: " +da +"\n"+"Time: "+datte[3]+"\n" ;

                    arrayAdapter.add(str);

                }

            }

        }
        while (smsInboxcursor.moveToNext()) ;
    }
}