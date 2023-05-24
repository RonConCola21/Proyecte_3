package com.example.jukeapp.hash;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Utils {
    public static String convertirSHA1(String input) {
        try {
            // Obtener una instancia de MessageDigest con el algoritmo SHA-1
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

            // Convertir el input en bytes
            byte[] inputBytes = input.getBytes();

            // Calcular el resumen (hash) de los bytes
            byte[] hashBytes = messageDigest.digest(inputBytes);

            // Convertir los bytes a una representación hexadecimal
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexStringBuilder.append("0");
                }
                hexStringBuilder.append(hex);
            }

            // Devolver el hash como un String en formato hexadecimal
            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
