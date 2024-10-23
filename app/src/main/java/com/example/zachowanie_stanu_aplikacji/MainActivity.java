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

/**
 * Klasa MainActivity odpowiedzialna za zachowanie stanu aplikacji podczas jej restartu (np. zmiana orientacji ekranu).
 */
public class MainActivity extends AppCompatActivity {

    // Klucze do zapisywania stanu aplikacji
    private static final String KEY_COUNT = "ilosc_klikniec";
    private static final String STATE_CHECKBOX = "sprawdz";
    private static final String STATE_SWITCH = "tryb";
    private static final String STATE_INPUT = "tekst";

    // Zmienne do przechowywania referencji do widoków i stanów
    private TextView tekst;
    private EditText name;
    private String name_input;
    private Button btn;
    private Button btn2;
    private TextView liczba;
    private TextView stan_check;
    private Switch tryb;
    private CheckBox stan;
    private int ilosc_klikniec = 0;
    private boolean stan_checkbox;
    private boolean stan_switch;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Aktywacja trybu "edge to edge" (rozciąganie na całą przestrzeń ekranu)
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicjalizacja referencji do widoków w interfejsie użytkownika
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        liczba = findViewById(R.id.textView);
        stan_check = findViewById(R.id.textView2);
        stan = findViewById(R.id.checkBox);
        tryb = findViewById(R.id.switch1);
        layout = findViewById(R.id.main);
        name = findViewById(R.id.editTextText);
        tekst = findViewById(R.id.tekst_input);

        // Przywrócenie stanu dla tekstu wprowadzającego, jeśli został zapisany
        if (savedInstanceState != null) {
            name_input = savedInstanceState.getString(STATE_INPUT);
        }
        updateText();  // Aktualizacja tekstu wyświetlanego na ekranie

        // Obsługa kliknięcia przycisku "Zatwierdź" (btn2) - zapisuje tekst z EditText
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_input = name.getText().toString().trim();
                tekst.setText(name_input);
            }
        });

        // Przywrócenie stanu dla przełącznika (tryb ciemny/jasny), jeśli został zapisany
        if (savedInstanceState != null) {
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

        // Obsługa zmiany stanu przełącznika (trybu jasny/ciemny)
        tryb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                stan_switch = true;
                tryb.setText("tryb ciemny");
                Toast.makeText(MainActivity.this, "zmieniono tryb", Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.BLACK);
            } else {
                stan_switch = false;
                tryb.setText("tryb jasny");
                Toast.makeText(MainActivity.this, "zmieniono tryb", Toast.LENGTH_SHORT).show();
                layout.setBackgroundColor(Color.WHITE);
            }
        });

        // Zaktualizowanie stanu przełącznika po jego zmianie
        tryb.setChecked(stan_switch);

        // Przywrócenie stanu checkboxa, jeśli został zapisany
        if (savedInstanceState != null) {
            boolean isCheckChecked = savedInstanceState.getBoolean(STATE_CHECKBOX);
            stan.setChecked(isCheckChecked);
        }

        // Obsługa zmiany stanu checkboxa
        stan.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                stan_checkbox = true;
                stan_check.setText("check box zaznaczony");
            } else {
                stan_checkbox = false;
                stan_check.setText("check box odzaznaczony");
            }
        });

        // Zaktualizowanie stanu checkboxa po jego zmianie
        stan.setChecked(stan_checkbox);

        // Przywrócenie liczby kliknięć, jeśli stan został zapisany
        if (savedInstanceState != null) {
            ilosc_klikniec = savedInstanceState.getInt(KEY_COUNT);
        }
        updateCountText();  // Aktualizacja liczby kliknięć

        // Obsługa kliknięcia przycisku "Kliknij mnie" (btn) - zwiększa licznik kliknięć
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilosc_klikniec++;
                updateCountText();
            }
        });
    }

    // Zapisanie stanu aplikacji (liczba kliknięć, stan checkboxa, stan przełącznika, tekst wprowadzony przez użytkownika)
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, ilosc_klikniec);
        outState.putBoolean(STATE_CHECKBOX, stan_checkbox);
        outState.putBoolean(STATE_SWITCH, stan_switch);
        outState.putString(STATE_INPUT, name_input);
    }

    // Aktualizacja wyświetlanego tekstu
    private void updateText() {
        tekst.setText(name_input);
    }

    // Aktualizacja licznika kliknięć
    private void updateCountText() {
        liczba.setText("liczba: " + ilosc_klikniec);
    }

    // Aktualizacja stanu checkboxa (metoda pomocnicza, choć nie jest używana w kodzie)
    private void updateCheckState() {
        if (stan_checkbox) {
            stan_check.setText("check box zaznaczony");
        } else {
            stan_check.setText("check box odzaznaczony");
        }
    }

    // Aktualizacja stanu przełącznika (metoda pomocnicza, choć nie jest używana w kodzie)
    private void updateSwitchState() {
        if (stan_switch) {
            tryb.setText("tryb ciemny");
        } else {
            stan.setText("tryb jasny");
        }
    }
}
