package com.example.plantree.Services;

import android.content.Context;

import com.example.plantree.model.User;
import com.google.gson.Gson;

import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ArchiveService {

    public Boolean createUserJsonFile(Context context, User user) {
        // Cria um objeto JSON
        JSONObject userJson = new JSONObject();
        try {
            userJson.put("name", user.getNome());
            userJson.put("key", user.getKey());
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

        // Converte o JSON para uma string
        String jsonString = userJson.toString();

        // Define o nome do arquivo e o diretório
        String fileName = "user.json";
        File directory = new File(context.getExternalFilesDir(null), "content");

        // Verifica se o diretório existe, se não, cria
        if (!directory.exists()) {
            directory.mkdirs(); // Cria o diretório e todos os diretórios pai necessários
        }

        // Cria o arquivo no diretório especificado
        File file = new File(directory, fileName);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            // Escreve o conteúdo JSON no arquivo
            outputStream.write(jsonString.getBytes());

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User readUserJsonFile(Context context) {
        // Define o nome do arquivo e o diretório
        String fileName = "user.json";
        File directory = new File(context.getExternalFilesDir(null), "content");
        File file = new File(directory, fileName);

        // Verifica se o arquivo existe
        if (!file.exists()) {
            return null;
        }

        try (FileInputStream inputStream = new FileInputStream(file)) {
            // Lê o conteúdo do arquivo
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            // Converte o conteúdo para uma string
            String jsonString = new String(buffer, StandardCharsets.UTF_8);

            // Usa o Gson para desserializar o JSON em um objeto User
            Gson gson = new Gson();
            return gson.fromJson(jsonString, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
