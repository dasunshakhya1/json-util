package core.utils;


import core.configs.Configs;
import core.exceptions.JsonFileNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class JsonReader {
    private static final Map<String, Path> JSON_FILES = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(JsonReader.class.getName());

    static {

        try (Stream<Path> paths = Files.walk(Paths.get(Configs.TEST_DATA_DIR))) {
            paths.forEach(f -> {
                if (Files.isRegularFile(f)) {
                    String[] filePath = f.toString().split("/");
                    String key = filePath[filePath.length - 1].toUpperCase();
                    JSON_FILES.put(key, f);
                }
            });
            LOGGER.info("Total Data Files :: " + JSON_FILES.size());
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    private static <T> Path getFilePath(Class<T> tClass) {
        String extractedClassName = tClass.getName().replace("[L", "").replace(";", "");
        String[] arr = extractedClassName.split("\\.");
        String className = arr[arr.length - 1].toUpperCase().concat(".JSON");
        Path path = JSON_FILES.get(className);
        if (path == null) {
            throw new JsonFileNotFoundException(className + " is not found");
        }
        return path;
    }


    public static <T> List<T> getObjectsList(Class<T> tClass) {
        StringBuilder stringBuilder = new StringBuilder();
        Charset charset = StandardCharsets.US_ASCII;
        try (BufferedReader reader = Files.newBufferedReader(getFilePath(tClass), charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException x) {
            LOGGER.warning(x.getMessage());
        }
        return ObjectMapperUtil.mapStringArrayToObjectArray(tClass, stringBuilder.toString());
    }


    public static <T> T getObject(Class<T> t, String id) {
        Optional<T> optional;
        try {
            Field field = t.getDeclaredField("id");
            optional = getObjectsList(t).stream().filter(data -> {
                try {
                    field.setAccessible(true);
                    return field.get(data).equals(id);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).findFirst();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return optional.orElseThrow();
    }


}