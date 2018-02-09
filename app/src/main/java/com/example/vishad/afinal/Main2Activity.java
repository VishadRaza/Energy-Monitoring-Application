package com.example.vishad.afinal;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    String number;
    String s="";
    String [] num;
    String []  datte;
    SharedPreferences prefs;
    ArrayAdapter arrayAdapter;
    TextView textView;
    private static final String TAG = Main2Activity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       // textView = (TextView) findViewById(R.id.unit);

        handleUserInteraction();

        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        s=prefs.getString("number",null);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        Log.v("savedNumber"," "+s);

        number = getIntent().getStringExtra("MyNumber");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("ddMMMyyyy");
        String strDate = "Current Date : " + mdformat.format(calendar.getTime());

        Log.v(TAG,"Current: "+strDate);

        Log.v( "friday" ,"" +number);
        refreshSmsInbox();

    }

    public void refreshSmsInbox() {
        int rows = 0;
        double sum = 0;
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxcursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int indexBody = smsInboxcursor.getColumnIndex("body");
        int indexAddress = smsInboxcursor.getColumnIndex("address");
        String i = s;
        Log.v("hel" ,"msg" +i);
        //    String check = "00923362503565";

        if (indexBody < 0 || !smsInboxcursor.moveToFirst())
            return;
        do{
            String number = smsInboxcursor.getString(indexAddress);
            if (number.equals(i)) {

                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();


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
                Log.v("da",""+datecomplete);
                // num = smsInboxcursor.getString(indexBody).split(": ");
                Log.v(TAG, smsInboxcursor.getString(indexBody));

                //   Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("ddMMMyyyy");
                String strDate = "Current Date : " + mdformat.format(calendar.getTime());

                Log.v(TAG,"Current: "+strDate);
                String[] s = strDate.split(" ");
                Log.v("s1",""+s[0]);
                Log.v("s2",""+s[1]);

                Double finel;
                String def = "07Feb2018";

                if(datecomplete.equals(def)) {
                    String[] value = smsInboxcursor.getString(indexBody).split(": ");
                    Log.v("value",""+value[1]);
                    sum += Double.parseDouble(value[1]);


                }
            }
            String sum1 = String.valueOf(sum);

            String ans = String.format("%.2f", sum);
            Log.v(TAG, sum1);
            //textView.setText(ans);
        }
        while (smsInboxcursor.moveToNext() );

    }
    private void handleUserInteraction() {

    }

}
