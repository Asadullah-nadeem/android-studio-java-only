package razi.constructor.recyclerviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SpotRate extends AppCompatActivity {

    ImageView btnBack;
    Intent intent;
    TextView dateTxt, ounceTxt, tolaTxt, kgTxt, gram24Txt, gram22Txt, gram21Txt, gram18Txt, gram14Txt, gram12Txt, gram10Txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_rate);
        getSupportActionBar().hide();

        intent = getIntent();

        btnBack = findViewById(R.id.btnBack);
        dateTxt = findViewById(R.id.dateTxt);
        ounceTxt = findViewById(R.id.ounceTxt);
        tolaTxt = findViewById(R.id.tolaTxt);
        kgTxt = findViewById(R.id.kgTxt);
        gram24Txt = findViewById(R.id.gram24Txt);
        gram22Txt = findViewById(R.id.gram22Txt);
        gram21Txt = findViewById(R.id.gram21Txt);
        gram18Txt = findViewById(R.id.gram18Txt);
        gram14Txt = findViewById(R.id.gram14Txt);
        gram12Txt = findViewById(R.id.gram12Txt);
        gram10Txt = findViewById(R.id.gram10Txt);

        dateTxt.setText(intent.getStringExtra("datevalue"));
        ounceTxt.setText(intent.getStringExtra("ouncevalue"));
        tolaTxt.setText(intent.getStringExtra("tolavalue"));
        kgTxt.setText(intent.getStringExtra("kgvalue"));
        gram24Txt.setText(intent.getStringExtra("gram24value"));
        gram22Txt.setText(intent.getStringExtra("gram22value"));
        gram21Txt.setText(intent.getStringExtra("gram21value"));
        gram18Txt.setText(intent.getStringExtra("gram18value"));
        gram14Txt.setText(intent.getStringExtra("gram14value"));
        gram12Txt.setText(intent.getStringExtra("gram12value"));
        gram10Txt.setText(intent.getStringExtra("gram10value"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}