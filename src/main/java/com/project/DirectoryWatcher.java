package com.project;

import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.List;

public class DirectoryWatcher {
    private final Path folderPath;

    public DirectoryWatcher(String folder) {
        this.folderPath = Paths.get(folder);
    }

    public void watch() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                Path filePath = folderPath.resolve((Path) event.context());
                if (filePath.toString().matches(".*users_\\d{14}\\.csv")) {
                    System.out.println("File detected: " + filePath);
                    processFile(filePath);
                }
            }
            key.reset();
        }
    }

    private void processFile(Path filePath) {
        CSVParser parser = new CSVParser();
        DatabaseManager dbManager = new DatabaseManager();
        FileMover fileMover = new FileMover();

        try {
            List<User> users = parser.parse(filePath);
            String timestamp = extractTimestampFromFileName(filePath.getFileName().toString());

            for (User user : users) {
                dbManager.saveUser(user, timestamp);
            }

            fileMover.moveFile(filePath, Paths.get("archive"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private String extractTimestampFromFileName(String fileName) {
        return fileName.substring(6, 20);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DirectoryWatcher watcher = new DirectoryWatcher("input");
        watcher.watch();
    }
}
