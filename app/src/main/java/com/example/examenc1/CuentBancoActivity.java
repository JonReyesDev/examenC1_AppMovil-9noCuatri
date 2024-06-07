package com.example.examenc1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CuentBancoActivity extends AppCompatActivity {
    private TextView usuarioTextView;
    private TextView saldoTextView;
    private EditText numeroCuentaEditText;
    private EditText nombreEditText;
    private EditText bancoEditText;
    private EditText cantidadEditText;
    private Spinner movimientosSpinner;
    private Button aplicarButton;
    private Button limpiarButton;
    private Button regresarButton;

    private cuentaBanco cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuentabanco);

        usuarioTextView = findViewById(R.id.usuarioTextView);
        saldoTextView = findViewById(R.id.saldoTextView);
        numeroCuentaEditText = findViewById(R.id.numeroCuentaEditText);
        nombreEditText = findViewById(R.id.nombreEditText);
        bancoEditText = findViewById(R.id.bancoEditText);
        cantidadEditText = findViewById(R.id.cantidadEditText);
        movimientosSpinner = findViewById(R.id.movimientosSpinner);
        aplicarButton = findViewById(R.id.aplicarButton);
        limpiarButton = findViewById(R.id.limpiarButton);
        regresarButton = findViewById(R.id.regresarButton);

        // Inicializar la cuenta del usuario (en un caso real, se obtendría de una base de datos)
        cuenta = new cuentaBanco("12345", "Admin", "BancoX", 0.00);

        // Mostrar el saldo inicial
        saldoTextView.setText(String.format("Saldo: %.2f", cuenta.consultarSaldo()));

        // Configurar el Spinner con las opciones de movimientos
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.movimientos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movimientosSpinner.setAdapter(adapter);

        aplicarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movimientoSeleccionado = movimientosSpinner.getSelectedItem().toString();
                double cantidad = Double.parseDouble(cantidadEditText.getText().toString());

                switch (movimientoSeleccionado) {
                    case "Depositar":
                        cuenta.depositar(cantidad);
                        break;
                    case "Retirar":
                        if (!cuenta.retirar(cantidad)) {
                            // Mostrar mensaje de error
                            Toast.makeText(CuentBancoActivity.this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                actualizarSaldo();
            }
        });

        limpiarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidadEditText.setText("");
                numeroCuentaEditText.setText("");
                nombreEditText.setText("");
                bancoEditText.setText("");
            }
        });

        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Regresa a la actividad anterior (MainActivity)
            }
        });
    }

    private void actualizarSaldo() {
        saldoTextView.setText(String.format("Saldo: %.2f", cuenta.consultarSaldo()));
    }
}
