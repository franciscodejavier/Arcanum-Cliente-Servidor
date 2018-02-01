/*
 * Decompiled with CFR 0_123.
 */
package es.studium.math;

public class Consule {
    public static String Consulta(String numeroSecreto, String numeroJugador) {
        String respuesta = "";
        int i = 0;
        while (i < numeroSecreto.length()) {
            String caracter = String.valueOf(numeroJugador.charAt(i));
            respuesta = caracter.equals(String.valueOf(numeroSecreto.charAt(i))) ? String.valueOf(respuesta) + "m" : (numeroSecreto.contains(caracter) ? String.valueOf(respuesta) + "h" : String.valueOf(respuesta) + "x");
            ++i;
        }
        return respuesta;
    }
}

