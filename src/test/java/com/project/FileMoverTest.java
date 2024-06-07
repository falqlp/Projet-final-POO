package com.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileMoverTest {
    private final FileMover fileMover = new FileMover();
    private final Path source = Files.createTempFile("source", null);
    private final Path target = Files.createTempDirectory("target");

    public FileMoverTest() throws IOException {}

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(source);
        Files.deleteIfExists(target.resolve(source.getFileName()));
    }

    @Test
    public void whenMoveFile_thenSuccess() throws IOException {
        assertTrue(Files.exists(source));
        assertFalse(Files.exists(target.resolve(source.getFileName())));

        fileMover.moveFile(source, target);

        assertFalse(Files.exists(source));
        assertTrue(Files.exists(target.resolve(source.getFileName())));
    }

    @Test
    public void whenMoveNonExistentFile_thenFail() {
        Path nonExistentPath = Path.of("nonExistentPath");
         
        assertThrows(IOException.class, () -> fileMover.moveFile(nonExistentPath, target));
    }
}