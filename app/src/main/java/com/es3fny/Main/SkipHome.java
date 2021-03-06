package com.es3fny.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.es3fny.First_Aid.MainActivity;
import com.es3fny.Maps.MainMap;
import com.es3fny.R;
import java.util.Locale;



public class SkipHome extends AppCompatActivity {
    public Button Places_BTN;
    public Button First_Aid_BTN, Blood__BTN, Help_BTN,SOS_Btn;
    boolean doubleBackToExitPressedOnce = false;
    private boolean Language_Changed;

    @Override
    public void onBackPressed() {
        finish();
    }
    int Type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetLocal();
        setContentView(R.layout.activity_skip_home);
        Places_BTN = findViewById(R.id.Places_BTN);
        First_Aid_BTN = findViewById(R.id.First_Aid_BTN);
        Blood__BTN = findViewById(R.id.Blood_BTN);
        Help_BTN = findViewById(R.id.help_request_Btn);
        SOS_Btn = findViewById(R.id.SOS_BTN);
        Bundle extra =  getIntent().getExtras();
        if(extra != null ){
            Type = extra.getInt("type");
        }
        Toolbar toolbar = findViewById(R.id.app_bar);
        TextView mTitle =  toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(R.string.home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Places_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MapsIntnet = new Intent(SkipHome.this, MainMap.class);
                MapsIntnet.putExtra("type",1);
                startActivity(MapsIntnet);
            }
        });
        Blood__BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SkipHome.this, R.string.Must_Login, Toast.LENGTH_SHORT).show();
            }
        });
        Help_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SkipHome.this, R.string.Must_Login, Toast.LENGTH_SHORT).show();
            }
        });
        SOS_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SkipHome.this, R.string.Must_Login, Toast.LENGTH_SHORT).show();
            }
        });
        First_Aid_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent FirstAidIntnet = new Intent(SkipHome.this, MainActivity.class);
                FirstAidIntnet.putExtra("type",1);
                startActivity(FirstAidIntnet);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.skip_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String load = "";
        switch (item.getItemId())
        {
            case R.id.mbtnLogin:
                Log_In();
                break;
            case R.id.Language:
                Language_Changed = true;
                if (item.getTitle().equals("English")){
                    load = "en";
                }else if (item.getTitle().equals("عربي")){
                    load = "ar";
                }
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = defaultSharedPreferences.edit();
                editor.putString("Language",load);
                editor.apply();
                Locale locale = new Locale(load);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getResources().updateConfiguration(config,getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
            default:
        }
        return super.onOptionsItemSelected(item);
    }
    public void Log_In(){
        finish();
    }
    public void SetLocal(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String load = settings.getString("Language", "en");
        Locale locale = new Locale(load);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config,getResources().getDisplayMetrics());
    }
}
