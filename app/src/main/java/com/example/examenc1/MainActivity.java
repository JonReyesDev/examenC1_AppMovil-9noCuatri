package com.example.examenc1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usuarioEditText;
    private EditText contraseñaEditText;
    private Button ingresarButton;
    private Button salirButton;
    private String usuarioValido;
    private String contraseñaValida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioEditText = findViewById(R.id.usuarioEditText);
        contraseñaEditText = findViewById(R.id.contraseñaEditText);
        ingresarButton = findViewById(R.id.ingresarButton);
        salirButton = findViewById(R.id.salirButton);

        // Obtener las cadenas definidas en strings.xml
        usuarioValido = getString(R.string.user);
        contraseñaValida = getString(R.string.pass);

        ingresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioIngresado = usuarioEditText.getText().toString();
                String contraseñaIngresada = contraseñaEditText.getText().toString();

                if (usuarioIngresado.equals(usuarioValido) && contraseñaIngresada.equals(contraseñaValida)) {
                    Intent intent = new Intent(MainActivity.this, CuentBancoActivity.class);
                    startActivity(intent);
                } else {
                    // Mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la aplicación
            }
        });
    }
}

