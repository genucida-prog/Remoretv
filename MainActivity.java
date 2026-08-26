package com.toshiba.remote;

import android.app.Activity;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    ConsumerIrManager irManager;
    int frequency = 38000;

    // PATRONES IR TOSHIBA - Protocolo NEC
    // POWER
    int[] PATTERN_POWER = {9000,4500,560,560,560,560,560,1690,560,560,560,560,560,560,560,560,560,560,560,1690,560,1690,560,1690,560,1690,560,1690,560,1690,560,1690,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,1690,560,560,560,560,560,560,560,560,560,560,560,560,560,1690,560,1690,560,1690,560,1690,560,39600};
    // VOL+
    int[] PATTERN_VOL_UP = {9000,4500,560,560,560,560,560,1690,560,560,560,560,560,560,560,560,560,560,560,1690,560,1690,560,1690,560,1690,560,1690,560,560,560,1690,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,1690,560,560,560,1690,560,1690,560,1690,560,39600};
    // VOL-
    int[] PATTERN_VOL_DOWN = {9000,4500,560,560,560,560,560,1690,560,560,560,560,560,560,560,560,560,560,560,1690,560,1690,560,1690,560,1690,560,1690,560,560,560,560,560,1690,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,560,1690,560,560,560,1690,560,1690,560,1690,560,39600};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        irManager = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);

        Button btnPower = findViewById(R.id.btnPower);
        Button btnVolUp = findViewById(R.id.btnVolUp);
        Button btnVolDown = findViewById(R.id.btnVolDown);
        Button btnChUp = findViewById(R.id.btnChUp);
        Button btnChDown = findViewById(R.id.btnChDown);

        // Comprobar si tiene infrarrojos
        if (irManager != null && !irManager.hasIrEmitter()) {
            Toast.makeText(this, "AVISO: Tu movil NO tiene IR. Necesitas movil con infrarrojos (Xiaomi, Huawei)", Toast.LENGTH_LONG).show();
        }

        btnPower.setOnClickListener(v -> transmit(PATTERN_POWER));
        btnVolUp.setOnClickListener(v -> transmit(PATTERN_VOL_UP));
        btnVolDown.setOnClickListener(v -> transmit(PATTERN_VOL_DOWN));
        btnChUp.setOnClickListener(v -> transmit(PATTERN_POWER)); // temporal
        btnChDown.setOnClickListener(v -> transmit(PATTERN_POWER)); // temporal
    }

    void transmit(int[] pattern) {
        if (irManager != null && irManager.hasIrEmitter()) {
            irManager.transmit(frequency, pattern);
            Toast.makeText(this, "Enviado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No hay emisor IR", Toast.LENGTH_SHORT).show();
        }
    }
}
