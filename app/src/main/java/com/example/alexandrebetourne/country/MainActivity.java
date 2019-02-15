package com.example.alexandrebetourne.country;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton buttonCalendar;
    TextView calendarTextView;
    static Dialog d;
    int year = Calendar.getInstance().get(Calendar.YEAR);

    ImageButton buttonPlaces;
    static Dialog dialog;

    Button changeActivity;

    public static final String EXTRA_MESSAGE =
            "com.example.android.alexandrebetourne.country.MESSAGE";

    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalendar = (ImageButton) findViewById(R.id.calendarbutton);
        buttonCalendar.setOnClickListener(this);

        calendarTextView = (TextView) findViewById(R.id.calendarTextView);
        calendarTextView.setText("" + year);

        buttonPlaces = (ImageButton) findViewById(R.id.placesbutton);
        buttonPlaces.setOnClickListener(this);

        changeActivity = (Button) findViewById(R.id.changeActivityButton);
        changeActivity.setOnClickListener(this);

        editName = (EditText) findViewById(R.id.editText_main);
    }

    public void showYearDialog()
    {
        d = new Dialog(MainActivity.this);
        d.setTitle("Year Picker");
        d.setContentView(R.layout.yeardialog);
        Button set = d.findViewById(R.id.button1);
        Button cancel = d.findViewById(R.id.button2);
        TextView year_text= d.findViewById(R.id.year_text);
        year_text.setText("" + year);
        final NumberPicker nopicker = d.findViewById(R.id.numberPicker1);

        nopicker.setMaxValue(year+50);
        nopicker.setMinValue(year-50);
        nopicker.setWrapSelectorWheel(false);
        nopicker.setValue(year);
        nopicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        set.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                calendarTextView.setText(String.valueOf(nopicker.getValue()));
                calendarTextView.setText(String.valueOf(nopicker.getValue()));
                d.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

    public void showMap()
    {
        dialog= new Dialog(MainActivity.this);
        dialog.setTitle("Place Picker");
        dialog.setContentView(R.layout.map);

        MapView mMapView = dialog.findViewById(R.id.mapView);
        MapsInitializer.initialize(MainActivity.this);

        mMapView.onCreate(dialog.onSaveInstanceState());
        mMapView.onResume();

        dialog.show();
    }

    private void sendMessage() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);

        String message = editName.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calendarbutton:
                showYearDialog();
                break;
            case R.id.placesbutton:
                showMap();
                break;
            case R.id.changeActivityButton:
                sendMessage();
                break;
        }
    }


}
