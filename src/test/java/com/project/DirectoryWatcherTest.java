package com.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DirectoryWatcherTest {

    // The directory for testing
    private static final String TEST_DIR = "testDir";
    private static final String ARCHIVE_DIR = "archive";

    @BeforeEach
    public void setup() throws IOException {
        // Ensure the test directory exists and is empty
        Path testDirPath = Paths.get(TEST_DIR);
        if (!Files.exists(testDirPath)) {
            Files.createDirectory(testDirPath);
        } else {
            Files.walk(testDirPath)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(java.io.File::delete);
        }

        // Ensure the archive directory exists and is empty
        Path archiveDirPath = Paths.get(ARCHIVE_DIR);
        if (!Files.exists(archiveDirPath)) {
            Files.createDirectory(archiveDirPath);
        } else {
            Files.walk(archiveDirPath)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(java.io.File::delete);
        }
    }

    @Test
    public void testWatchWithNoEvents() throws IOException, InterruptedException {
        DirectoryWatcher directoryWatcher = new DirectoryWatcher(TEST_DIR);

        new Thread(() -> {
            try {
                directoryWatcher.watch();
            } catch (IOException | InterruptedException e) {
                System.out.println("Exception in watch method: " + e.getMessage());
            }
        }).start();

        // Allow time for the watcher to setup
        Thread.sleep(2000);
    }

    @Test
    public void testWatchWithNewFile() throws IOException, InterruptedException {
        DirectoryWatcher directoryWatcher = new DirectoryWatcher(TEST_DIR);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String currentTime = LocalDateTime.now().format(formatter);

        new Thread(() -> {
            try {
                directoryWatcher.watch();
            } catch (IOException | InterruptedException e) {
                System.out.println("Exception in watch method: " + e.getMessage());
            }
        }).start();

        // Allow time for the watcher to setup
        Thread.sleep(2000);

        // Create a new file and check whether the DirectoryWatcher reacts as expected
        String fileName = TEST_DIR + "/users_" + currentTime + ".csv";
        Path file = Paths.get(fileName);
        Files.createFile(file);

        // Allow time for the watcher to process the file
        Thread.sleep(2000);

        // Retry loop to check if the file has been moved
        boolean fileMoved = false;
        Path archivedFilePath = Paths.get(ARCHIVE_DIR + "/" + file.getFileName().toString());
        for (int i = 0; i < 10; i++) {
            if (!Files.exists(file) && Files.exists(Paths.get(ARCHIVE_DIR + "/" + file.getFileName().toString()))) {
                fileMoved = true;
                break;
            }
            Thread.sleep(500);
        }

        Assertions.assertTrue(fileMoved, "File should be moved from the test directory to the archive directory");
        Files.delete(archivedFilePath);
    }
}
