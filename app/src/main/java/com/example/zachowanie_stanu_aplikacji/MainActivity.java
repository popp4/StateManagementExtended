package com.example.zachowanie_stanu_aplikacji;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private  static final  String KEY_COUNT="ilosc_klikniec";
    private  static final  String STATE_CHECKBOX="sprawdz";
    private  static final  String STATE_SWITCH="tryb";
    private  static final  String STATE_INPUT="tekst";

    private  TextView tekst;
    private EditText name;
    private  String name_input;
    private Button btn;
    private Button btn2;
    private TextView liczba;
    private  TextView stan_check;
    private Switch tryb;
    private CheckBox stan;
    private int ilosc_klikniec=0;
    private boolean stan_checkbox;
    private boolean stan_switch;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        liczba=findViewById(R.id.textView);
        stan_check=findViewById(R.id.textView2);
        stan=findViewById(R.id.checkBox);
        tryb=findViewById(R.id.switch1);
        layout=findViewById(R.id.main);
        name=findViewById(R.id.editTextText);
        tekst=findViewById(R.id.tekst_input);

        if(savedInstanceState!=null){
            name_input=savedInstanceState.getString(STATE_INPUT);
        }
        updateText();
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_input = name.getText().toString().trim();
                tekst.setText(name_input);
            }
        });


        if(savedInstanceState!=null){
            boolean isSwitchChecked = savedInstanceState.getBoolean(STATE_SWITCH);
            tryb.setChecked(isSwitchChecked);
            if (isSwitchChecked) {
                layout.setBackgroundColor(Color.BLACK);
                tryb.setText("tryb ciemny");
            } else {
                layout.setBackgroundColor(Color.WHITE);
                tryb.setText("tryb jasny");
            }
        }
        tryb.setOnCheckedChangeListener((buttonView,isChecked)->{
            if(isChecked){
                stan_switch=true;
                tryb.setText("tryb ciemny");
                Toast.makeText(MainActivity.this,"zmieniono tryb",Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.BLACK);
            }else{
                stan_switch=false;
                tryb.setText("tryb jasny");
                Toast.makeText(MainActivity.this,"zmieniono tryb",Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.WHITE);
            }
        });
        tryb.setChecked(stan_switch);



        if(savedInstanceState!=null){
            boolean isCheckChecked = savedInstanceState.getBoolean(STATE_CHECKBOX);
            stan.setChecked(isCheckChecked);
        }
        stan.setOnCheckedChangeListener((buttonView,isChecked)->{
        if (isChecked) {
            stan_checkbox=true;
            stan_check.setText("check box zaznaczony");

        }else{
            stan_checkbox=false;
            stan_check.setText("check box odzaznaczony");

        }
        });
        stan.setChecked(stan_checkbox);



        if(savedInstanceState!=null){
            ilosc_klikniec=savedInstanceState.getInt(KEY_COUNT);
        }
        updateCountText();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilosc_klikniec++;
                updateCountText();
            }
        });
        }
        @Override
        protected  void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putInt(KEY_COUNT,ilosc_klikniec);
            outState.putBoolean(STATE_CHECKBOX,stan_checkbox);
            outState.putBoolean(STATE_SWITCH,stan_switch);
            outState.putString(STATE_INPUT,name_input);
        }
        private  void updateText(){
            tekst.setText(name_input);
        }
        private  void  updateCountText(){

        liczba.setText("liczba:"+ilosc_klikniec);

        }
        private void updateCheckState(){
            if(stan_checkbox==true) {
                stan_check.setText("check box zaznaczony");
            }else{
                stan_check.setText("check box odzaznaczony");
            }
        }
        private void updateSwitchState(){
            if(stan_switch==true) {
                tryb.setText("tryb ciemny");
            }else{
                stan.setText("tryb jasny");
            }
        }

}
