package com.jkgarage.kopiorder;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View;
import android.view.View.OnClickListener;
//import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    Button orderButton, clearButton;
    EditText orderTextView;

    final int [] imageButtonIDList = new int[] {
            R.id.btn_1, R.id.btn_2,
            R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6,
            R.id.btn_7, R.id.btn_8,
            R.id.btn_9
    };

    static OrderEntity[] orderList;

    private void initOrderList () {
        orderList = new OrderEntity[] {
                new OrderEntity("Teh"),
                new OrderEntity("Teh-Ping"),
                new OrderEntity("Teh-O"),
                new OrderEntity("Kopi"),
                new OrderEntity("Kopi-Ping"),
                new OrderEntity("Kopi-O"),
                new OrderEntity("Coke"),
                new OrderEntity("Ice Lemon Tea"),
                new OrderEntity("Jia Jia")
        };
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initOrderList();

        //btnList = new ImageButton[imageButtonIDList.length];

        orderTextView = (EditText) findViewById( R.id.orderListText );

        //this is a work around to prevent text view as editable
        orderTextView.setKeyListener(null);

        orderButton = (Button) findViewById( R.id.orderbutton );
        addListenerOnOrderButton();

        clearButton = (Button) findViewById( R.id.clearbutton );
        addListenerOnClearButton();

        for (int i = 0; i < imageButtonIDList.length; i++) {
            addListenerOnButton(i);
        }
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

    public void addListenerOnButton(final int index) {
        //final String itemName = MainActivity.orderList[index].getItemName();

        OnClickListener listenerWithReturn = new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Toast.makeText(MainActivity.this,
                //        "1 " + itemName + " added!",
                //        Toast.LENGTH_SHORT).show();

                //Todo : this is not the best way to code
                //need to find a way to pass variable from activity to Listener
                MainActivity.orderList[index].addOrder();

                orderTextView.setText( listOrder() );
            }
        };


        //btnList[index] = (ImageButton) findViewById( imageButtonIDList[index] );
        //btnList[index].setOnClickListener(listenerWithReturn);
        findViewById( imageButtonIDList[index] ).setOnClickListener(listenerWithReturn);
    }

    public void addListenerOnButton(ImageButton btn, final int indexOfOrder) {

        OnClickListener listenerWithReturn = new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Todo : this is not the best way to code
                //need to find a way to pass variable from activity to Listener
                MainActivity.orderList[indexOfOrder].addOrder();

                orderTextView.setText( listOrder() );
            }
        };
        btn.setOnClickListener(listenerWithReturn);
    }


    public void addListenerOnOrderButton() {
        orderButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                orderTextView.setText( listOrder() );
            }
        });

    }

    public void addListenerOnClearButton() {
        clearButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                initOrderList();

                orderTextView.setText( "All orders cleared !" );
            }
        });

    }

    private String listOrder () {
        int DISPLAY_LENGTH = 17;
        int totalQuantity = 0;
        String result = "";

        //use a counter to determine display on 1st column, or 2nd
        int counter = 0;

        for (OrderEntity order : orderList ) {
            if (order.getQuantity() > 0 ) {
                if (counter++ % 2 == 0)
                    result += "\n> " +
                            appendToFixLength(order.getItemName() + ":" + order.getQuantity(), DISPLAY_LENGTH);
                else
                    result += order.getItemName() + ":" + order.getQuantity();

                totalQuantity += order.getQuantity();
            }
        }
        result = "=== Current orders : (" + totalQuantity + ") ===" + result;
        return result;
    }


    // this utility function is append the string with a set of blank spaces
    //to fill up the string to a fix width
    private String appendToFixLength(String input, int length) {
        return String.format("%-" + length + "s", input);
    }

}
