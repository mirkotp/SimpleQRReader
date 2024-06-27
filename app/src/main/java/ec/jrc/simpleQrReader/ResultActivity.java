package ec.jrc.simpleQrReader;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            TextView textResult = findViewById(R.id.textResult);
            textResult.setText(bundle.getString("BARCODE_RAW_TEXT"));
        }
    }
}
