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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    TextView tday;
    TextView yday;
    TextView thisweekday;
    TextView lastweekday;
    String number;
    String s="";
    String [] num;
    String []  datte;
    String  yesterdayDate;
    String YesterdayDate2;
    String YesterdayDate3;
    String YesterdayDate4;
    String YesterdayDate5;
    String YesterdayDate6;
    String YesterdayDate7;
    String YesterdayDate8;
    String YesterdayDate9;
    String YesterdayDate10;
    String YesterdayDate11;
    String YesterdayDate12;
    String YesterdayDate13;
    String YesterdayDate14;

    Double value2=0.0;
    Double value1=0.0;
    SharedPreferences prefs;
    ArrayAdapter arrayAdapter;
    TextView textView;
    Double value=0.0;
    private static final String TAG = Main2Activity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tday = (TextView) findViewById(R.id.tday);
        yday = (TextView) findViewById(R.id.yday);
        thisweekday = (TextView) findViewById(R.id.thisweekday);
        lastweekday = (TextView) findViewById(R.id.lastweekday);
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
        double sumyesterday = 0;
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxcursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int indexBody = smsInboxcursor.getColumnIndex("body");
        int indexAddress = smsInboxcursor.getColumnIndex("address");
        String i = s;
        Log.v("hel" ,"msg" +i);
        //    String check = "00923362503565";
        Calendar calenda = Calendar.getInstance();
        calenda.add(Calendar.DATE,-1);
        Date dat = calenda.getTime();
        String[] yesterdayDateParts = dat.toString().split(" ");
        yesterdayDate = yesterdayDateParts[2]+yesterdayDateParts[1];

        calenda.add(Calendar.DATE,-2);
        Date dat2 = calenda.getTime();
        String[] yesterdayDate2 = dat2.toString().split(" ");
        YesterdayDate2= yesterdayDate2[2]+yesterdayDate2[1];

        calenda.add(Calendar.DATE,-3);
        Date dat3 = calenda.getTime();
        String[] yesterdayDate3 = dat2.toString().split(" ");
        YesterdayDate3= yesterdayDate3[2]+yesterdayDate3[1];


        calenda.add(Calendar.DATE,-4);
        Date dat4 = calenda.getTime();
        String[] yesterdayDate4 = dat2.toString().split(" ");
        YesterdayDate4= yesterdayDate4[2]+yesterdayDate4[1];

        calenda.add(Calendar.DATE,-5);
        Date dat5 = calenda.getTime();
        String[] yesterdayDate5 = dat2.toString().split(" ");
        YesterdayDate5= yesterdayDate5[2]+yesterdayDate5[1];

        calenda.add(Calendar.DATE,-6);
        Date dat6 = calenda.getTime();
        String[] yesterdayDate6 = dat2.toString().split(" ");
        YesterdayDate6= yesterdayDate6[2]+yesterdayDate6[1];

        calenda.add(Calendar.DATE,-7);
        Date dat7 = calenda.getTime();
        String[] yesterdayDate7 = dat2.toString().split(" ");
        YesterdayDate7= yesterdayDate7[2]+yesterdayDate7[1];


        //lastweek
        calenda.add(Calendar.DATE,-8);
        Date dat8 = calenda.getTime();
        String[] yesterdayDate8 = dat2.toString().split(" ");
        YesterdayDate8= yesterdayDate8[2]+yesterdayDate8[1];

        calenda.add(Calendar.DATE,-9);
        Date dat9 = calenda.getTime();
        String[] yesterdayDate9 = dat2.toString().split(" ");
        YesterdayDate9= yesterdayDate9[2]+yesterdayDate9[1];

        calenda.add(Calendar.DATE,-10);
        Date dat10 = calenda.getTime();
        String[] yesterdayDate10 = dat2.toString().split(" ");
        YesterdayDate10= yesterdayDate10[2]+yesterdayDate10[1];

        calenda.add(Calendar.DATE,-11);
        Date dat11 = calenda.getTime();
        String[] yesterdayDate11 = dat2.toString().split(" ");
        YesterdayDate11= yesterdayDate11[2]+yesterdayDate11[1];

        calenda.add(Calendar.DATE,-12);
        Date dat12 = calenda.getTime();
        String[] yesterdayDate12 = dat2.toString().split(" ");
        YesterdayDate12= yesterdayDate12[2]+yesterdayDate12[1];

        calenda.add(Calendar.DATE,-13);
        Date dat13 = calenda.getTime();
        String[] yesterdayDate13 = dat2.toString().split(" ");
        YesterdayDate13= yesterdayDate13[2]+yesterdayDate13[1];

        calenda.add(Calendar.DATE,-14);
        Date dat14 = calenda.getTime();
        String[] yesterdayDate14 = dat2.toString().split(" ");
        YesterdayDate14= yesterdayDate14[2]+yesterdayDate14[1];

        if (indexBody < 0 || !smsInboxcursor.moveToFirst())
            return;
        do{
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
                Log.v("da",""+datecomplete);
                // num = smsInboxcursor.getString(indexBody).split(": ");
                Log.v(TAG, smsInboxcursor.getString(indexBody));

                //   Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("ddMMMyyyy");
                String strDate = "Current Date : " + mdformat.format(calendar.getTime());

                Log.v(TAG,"Current: "+strDate);

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy");
                String formattedDate = df.format(c);
                Log.v("c",""+formattedDate);

                calendar.add(Calendar.DATE , -1 );
                String z = mdformat.format(calendar.getTime());
                //  int k = Integer.parseInt(formattedDate);
                //int q= k-1;
                //String z= String.valueOf(k);
                Log.v("last",""+z);

                String[] s = strDate.split(" ");
                Log.v("s1",""+s[0]);
                Log.v("s2",""+s[1]);

               // Log.v("ds",""+formattedDate);
              // String def = "07Feb2018";
                if(datecomplete.equals(formattedDate)) {

                    String[] value = smsInboxcursor.getString(indexBody).split(": ");
                    Log.v("value",""+value[1]);
                    sum += Double.parseDouble(value[1]);

                }


                if (da.equals(yesterdayDate))
                {
                    String msg = smsInboxcursor.getString(indexBody);
                    String[] msgParts = msg.split(": ");
                    double energyValue = Double.parseDouble(msgParts[1].toString());
                    value = value+energyValue;
                }

                Log.v(TAG,"Sum of energy is "+Math.round(value));


                if((datecomplete.equals(formattedDate))|| (da.equals(yesterdayDate))|| (da.equals(YesterdayDate2))|| (da.equals(YesterdayDate3)) || (da.equals(YesterdayDate4)) || (da.equals(YesterdayDate5)) || (da.equals(YesterdayDate6)) ){
                    String msg = smsInboxcursor.getString(indexBody);
                    String[] msgParts = msg.split(": ");
                    double energyValue = Double.parseDouble(msgParts[1].toString());
                    value1 = value1 + energyValue;
                }


                if((da.equals(YesterdayDate8))|| (da.equals(YesterdayDate9))|| (da.equals(YesterdayDate10)) || (da.equals(YesterdayDate11)) || (da.equals(YesterdayDate12)) || (da.equals(YesterdayDate13)) || (da.equals(YesterdayDate7))){
                    String msg = smsInboxcursor.getString(indexBody);
                    String[] msgParts = msg.split(": ");
                    double energyValue = Double.parseDouble(msgParts[1].toString());
                    value2 = value2 + energyValue;
                }


            }


                   /* if(Integer.parseInt(formattedDate) - Integer.parseInt(datecomplete)   == 1 ){
                        String[] value = smsInboxcursor.getString(indexBody).split(": ");
                        Log.v("yesterday",""+value[1]);
                        sumyesterday +=Double.parseDouble(value[1]);
                    }*/

                /*  if(datecomplete.equals(z)){

                       String[] value = smsInboxcursor.getString(indexBody).split(": ");
                       Log.v("yesterday",""+value[1]);
                       sumyesterday +=Double.parseDouble(value[1]);
                   }*/

            String sum1 = String.valueOf(sum);
            String ans = String.format("%.2f", sum);
            Log.v(TAG, sum1);
            tday.setText(ans);

            String sumyester = String.valueOf(value);
            Log.v("yes",""+sumyester);
            String an = String.format("%.2f",value);
            yday.setText(an);


            String sumweek = String.valueOf(value1);
            String an1= String.format("%.2f",value1);
            thisweekday.setText(an1);


            String sumlastweek = String.valueOf(value2);
            String an2= String.format("%.2f",value2);
            lastweekday.setText(an2);


        }
        while (smsInboxcursor.moveToNext() );

    }
    private void handleUserInteraction() {

    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
