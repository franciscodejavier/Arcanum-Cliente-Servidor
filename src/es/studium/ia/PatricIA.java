package es.studium.ia;

public class PatricIA {
    static String listaNegra = "";
    static String listaBlanca = "";

    public static String IA() {
        String numeroPiloto = "1234";
        return numeroPiloto;
    }

    public static String IA(String numeroIA, String entradaIA) {
        if (entradaIA.equals("xxxx")) {
            int i = 0;
            while (i < 4) {
                String cadenaNumero = String.valueOf(numeroIA.charAt(i));
                if (!listaNegra.contains(cadenaNumero)) {
                    listaNegra = String.valueOf(listaNegra) + cadenaNumero;
                }
                ++i;
            }
        }
        String salidaIA = "";
        while (salidaIA.length() < 4) {
            String digito = String.valueOf(Math.random() * 10.0).substring(0, 1);
            while (salidaIA.contains(digito) || listaNegra.contains(digito)) {
                digito = String.valueOf(Math.random() * 10.0).substring(0, 1);
            }
            salidaIA = String.valueOf(salidaIA) + digito;
        }
        return salidaIA;
    }
}
