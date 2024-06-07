package com.example.examenc1;

public class cuentaBanco {
    private String numeroCuenta;
    private String nombre;
    private String banco;
    private double saldo;

    public cuentaBanco(String numeroCuenta, String nombre, String banco, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.nombre = nombre;
        this.banco = banco;
        this.saldo = saldo;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        }
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }
}

