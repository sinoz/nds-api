package nds_api;

import com.google.common.collect.ImmutableSet;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * Locates ROM types of the given extension within the given directory {@link Path}.
 *
 * @author Whis
 *
 */
public final class RomFinder {
    /**
     * The {@link Path} to the directory to look for ROMs.
     */
    private final Path directory;

    /**
     * The file extension to look for that might indicate a rom.
     */
    private final String extension;

    /**
     * Creates a new {@link RomFinder} that turns the given {@link File} to a
     * proper {@link Path}.
     */
    public RomFinder(File directory, String extension) {
        this(directory.toPath(), extension);
    }

    /**
     * Creates a new {@link RomFinder}.
     */
    public RomFinder(Path directory, String extension) {
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException();
        }

        this.directory = directory;
        this.extension = extension;
    }

    public ImmutableSet<Path> find() throws IOException {
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory, "*." + extension)) {
            return ImmutableSet.copyOf(paths);
        }
    }
}
