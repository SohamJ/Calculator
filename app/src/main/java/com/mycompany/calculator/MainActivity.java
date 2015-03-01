package com.mycompany.calculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity {
    TextView display;
    char c1, c2;    // current and previous char
    String str;    // store string input
    int d;          //d=0 dot not allowed d=1 dot allowed flag for decimal
    double result;   // store numeric result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.display);
        c1 = c2 = '\0';
        result = 0;
        str = "";
        d = 0;
        Button del = (Button) findViewById(R.id.clear);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.length() != 0) {
                    if (str.length() == 1) {
                        c1 = c2 = '\0';
                        str = "";
                        display.setText("0");
                    } else if (str.length() == 2) {
                        c1 = c2;
                        c2 = '\0';
                        str = str.substring(0, str.length() - 1);
                        display.setText(str);
                    } else {
                        c1 = c2;
                        c2 = str.charAt(str.length() - 3);
                        str = str.substring(0, (str.length() - 1));
                        display.setText(str);
                    }
                }
            }
        });

        del.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                display.setText("0");
                str = "";
                c1 = c2 = '\0';
                return true;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {        //each click runs this function
        Button b = (Button) view;
        c2=b.getText().toString().charAt(0); // current character
        if(str.length()>=1)
            c1=str.charAt(str.length()-1);

        if(str.length()==0)                         //initial input
        {
            if(!(c2=='*'||c2=='/'||c2=='+')) {          // 1st input can't be +,*,/ can be 0-9,-,.
                if(c2=='.'){
                    str=str+'0';                        // make 0.x instead of .x
                    c1='0';                             // previous character will be 0 now
                    d=1;                                // set dot flag to 1 since . is used
                }
                str = str + c2;                         // add input to string
            }

        }
        else
        {
            if(c2=='+'||c2=='*'||c2=='/'||c2=='-')
            {
                if(!(c1=='+'||c1=='*'||c1=='/'||c1=='-'||c1=='.'))      //input validation cant have continuous operators
                {
                    d=0;                                                // dot can now be used
                    str=str+c2;
                }
            }
            else if(c2=='.')
            {
                if((!(c1=='+'||c1=='*'||c1=='/'||c1=='-'||c1=='.'))&&d==0){
                    str=str+c2;
                    d=1;
                }
            }
            else                            // numeric
            {
                str=str+c2;
            }
        }

        display.setText(str);

    }
    public void calculate()
    {
        if(!(str.length()==0))
        {
           str=str.trim();
        }


    }

}
