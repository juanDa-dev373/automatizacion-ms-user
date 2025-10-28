package org.project.sura.automatizacionmsuser.context;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private static final String TOKEN_FILE = "token.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    private static Map<String, Object> readExistingData() {
        try {
            File file = new File(TOKEN_FILE);
            if (file.exists()) {
                return mapper.readValue(file, Map.class);
            }
        } catch (IOException e) {
            System.err.println("⚠️ No se pudo leer el archivo existente: " + e.getMessage());
        }
        return new HashMap<>(); // si no existe, devuelve un mapa vacío
    }

    private static void writeData(Map<String, Object> data) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(TOKEN_FILE), data);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar datos en el archivo " + TOKEN_FILE, e);
        }
    }

    public static void setToken(String token, long durationMinutes) {
        Map<String, Object> data = readExistingData();
        data.put("token", token);
        data.put("expiration", Instant.now().plusSeconds(durationMinutes * 60).toString());
        writeData(data);
        System.out.println("✅ Token guardado en archivo token.json (válido por " + durationMinutes + " min)");
    }

    public static String getToken() {
        return readExistingData().get("token").toString();
    }
    public static Long getId(){
        return Long.parseLong(readExistingData().get("id").toString());
    }
    public static String getUsername(){
        return readExistingData().get("username").toString();
    }
    public static void setUsername(String username) {
        Map<String, Object> data = readExistingData();
        data.put("username", username);
        writeData(data);
        System.out.println("👤 Username agregado: " + username);
    }

    public static void setId(Long id) {
        Map<String, Object> data = readExistingData();
        data.put("id", id);
        writeData(data);
        System.out.println("🆔 ID agregado: " + id);
    }

    public static void setTokenRecovery(String tokenRecovery){
        Map<String, Object> data = readExistingData();
        Map<String, Object> recovery = new HashMap<>();
        recovery.put("token_recovery", tokenRecovery);
        data.put("recovery", recovery);
        writeData(data);
    }

    public static void setPassword(String password){
        Map<String, Object> data = readExistingData();
        data.put("password", password);
        writeData(data);
    }
    public static String getPassword(){
        return readExistingData().get("password").toString();
    }

}
