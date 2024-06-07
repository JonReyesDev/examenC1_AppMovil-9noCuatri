package com.example.examenc1;

public class CuentaBancoActivity extends AppCompatActivity {
    private TextView saldoTextView;
    private EditText cantidadEditText;
    private Spinner movimientosSpinner;
    private Button aplicarButton;

    private CuentaBanco cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuentabanco);

        saldoTextView = findViewById(R.id.saldoTextView);
        cantidadEditText = findViewById(R.id.cantidadEditText);
        movimientosSpinner = findViewById(R.id.movimientosSpinner);
        aplicarButton = findViewById(R.id.aplicarButton);

        // Inicializar la cuenta del usuario (en un caso real, se obtendría de una base de datos)
        cuenta = new CuentaBanco("12345", "Admin", "BancoX", 1000.00);

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
                    case "Consultar":
                        // No se hace nada ya que el saldo siempre está visible
                        break;
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
    }

    private void actualizarSaldo() {
        saldoTextView.setText(String.format("Saldo: %.2f", cuenta.consultarSaldo()));
    }
}

