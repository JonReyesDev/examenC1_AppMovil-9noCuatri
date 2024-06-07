package com.example.examenc1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText usuarioEditText;
    private EditText contraseñaEditText;
    private Button ingresarButton;
    private String usuarioValido;
    private String contraseñaValida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioEditText = findViewById(R.id.usuarioEditText);
        contraseñaEditText = findViewById(R.id.contraseñaEditText);
        ingresarButton = findViewById(R.id.ingresarButton);

        // Obtener las cadenas definidas en strings.xml
        usuarioValido = getString(R.string.user);
        contraseñaValida = getString(R.string.pass);

        ingresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioIngresado = usuarioEditText.getText().toString();
                String contraseñaIngresada = contraseñaEditText.getText().toString();

                if (usuarioIngresado.equals(usuarioValido) && contraseñaIngresada.equals(contraseñaValida)) {
                    Intent intent = new Intent(MainActivity.this, CuentaBancoActivity.class);
                    startActivity(intent);
                } else {
                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
