package br.com.fiap.tipcalculator;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private NumberFormat cF = NumberFormat.getCurrencyInstance();
    private NumberFormat pF = NumberFormat.getPercentInstance();
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView percentTextView;
    private SeekBar percentSeekBar;
    private TextView totalTextView;
    private TextView tipTextView;
    private double billAmount = 0;
    private double percent = 0.15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountEditText = findViewById(R.id.amoutEditText);
        amountTextView = findViewById(R.id.amoutTextView);
        percentTextView = findViewById(R.id.percentTextView);
        percentSeekBar = findViewById(R.id.percentSeekBar);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
        percentSeekBar.setOnSeekBarChangeListener(new MeuObservadorDeSeekBar());
    }
    private class MeuObservadorDeSeekBar implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100d;
            percentTextView.setText(pF.format(percent));
            calculation();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
    private void calculation(){
        double tip = billAmount * percent;
        double total = billAmount + tip;
        amountTextView.setText(cF.format(billAmount));
        tipTextView.setText(cF.format(tip));
        totalTextView.setText(cF.format(total));
    }
    private TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!s.toString().equals("")) {
                billAmount = Double.parseDouble(s.toString()) / 100;
                calculation();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
