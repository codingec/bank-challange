package com.bank.core.data;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PayloadDataUtil {

    public static String buildClientDTODataRequest(){

        return "{ " +
                "  \"person\": {" +
                "    \"name\": \"Test\"," +
                "    \"gender\": \"Masculino\"," +
                "     \"age\": 9," +
                "     \"nationalId\": \"1111122221\"," +
                "     \"address\": \"Juan Jose Leanodas Proaño y La que cruza\"," +
                "     \"telephone\": \"0969372466\" " +
                "  }," +
                "   \"password\": \"123\"," +
                "   \"status\": true" +
                "}";
    }

    public static String buildClientDTOUpdatedDataRequest(){

        return "{ " +
                "  \"person\": {" +
                "    \"name\": \"Anatoly\"," +
                "    \"gender\": \"Masculino\"," +
                "     \"age\": 9," +
                "     \"nationalId\": \"1111122221\"," +
                "     \"address\": \"Juan Jose Leanodas Proaño y La que cruza\"," +
                "     \"telephone\": \"0969372466\" " +
                "  }," +
                "   \"password\": \"123\"," +
                "   \"status\": true" +
                "}";
    }

    public static String getDataStringFromJsonFile(String path) {
        try {
            Path filePath = Paths.get(ClassLoader.getSystemResource(path).toURI());
            return Files.readString(filePath);
        } catch (IOException | URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
