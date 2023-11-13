package com.http.service;

import com.http.util.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {

    private final static ImageService INSTANCE = new ImageService();

    private final String basePath = PropertiesUtil.get("image.base.url");

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent){
        Path imageFullPath = Path.of(basePath, imagePath);
        try (imageContent) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
    @SneakyThrows
    public Optional<InputStream> get(String imagePath){
        Path path = Path.of(basePath, imagePath);
        return Files.exists(path)
                ? Optional.of(Files.newInputStream(path))
                : Optional.empty();
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }
}
