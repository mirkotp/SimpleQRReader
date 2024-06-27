package ec.jrc.simpleQrReader;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // QR scanner
        ActivityResultLauncher<Intent> qrScanner =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), resultData -> {
                    if (resultData.getResultCode() == RESULT_OK) {
                        ScanIntentResult result =
                                ScanIntentResult.parseActivityResult(resultData.getResultCode(), resultData.getData());

                        if (result.getContents() != null) {
                            Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                            Bundle mBundle = new Bundle();
                            mBundle.putString("BARCODE_RAW_TEXT", result.getContents());
                            intent.putExtras(mBundle);
                            startActivity(intent);
                        }
                    }
                });

        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setOrientationLocked(true);
        scanOptions.setPrompt(getString(R.string.please_place_barcode));
        scanOptions.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        findViewById(R.id.imageStartScan).setOnClickListener(
                v -> qrScanner.launch(new ScanContract().createIntent(getBaseContext(), scanOptions)));
    }
}
