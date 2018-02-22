/*
 * Decompiled with CFR 0_123.
 */
package es.studium.math;

public class Numerum {
    public static String NumeroAleatorio() {
        String numeroSecreto = "";
        while (numeroSecreto.length() < 4) {
            String digito = String.valueOf(Math.random() * 10.0).substring(0, 1);
            while (numeroSecreto.contains(digito)) {
                digito = String.valueOf(Math.random() * 10.0).substring(0, 1);
            }
            numeroSecreto = String.valueOf(numeroSecreto) + digito;
        }
        return numeroSecreto;
    }
}