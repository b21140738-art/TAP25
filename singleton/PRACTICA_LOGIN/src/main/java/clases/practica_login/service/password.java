package clases.practica_login.service;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class password {

    public String hashPassword(String password) {
        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            String saltB64 = Base64.getEncoder().encodeToString(salt);
            String hashB64 = Base64.getEncoder().encodeToString(hash);
            return "sha256$" + saltB64 + "$" + hashB64;
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password with SHA-256", e);
        }
    }

    public boolean verifyPassword(String password, String hash) {
        try {
            if (hash == null) return false;
            String[] parts = hash.split("\\$");
            if (parts.length != 3 || !"sha256".equalsIgnoreCase(parts[0])) {
                return false;
            }
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] expectedHash = Base64.getDecoder().decode(parts[2]);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] actualHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            return MessageDigest.isEqual(expectedHash, actualHash);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidPassword(String password) {
        // Mínimo 8 caracteres, al menos una mayúscula, una minúscula, un número y un carácter especial
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }
}