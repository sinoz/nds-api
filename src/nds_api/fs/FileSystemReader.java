package nds_api.fs;

import nds_api.RomMapping;

import java.io.IOException;

/**
 *
 * Reads the file allocation tables and file name tables of the Nitro file system.
 *
 * @author Whis
 *
 */
public final class FileSystemReader {
    /**
     * The {@link RomMapping} to read from.
     */
    private final RomMapping mapping;

    /**
     * Creates a new {@link FileSystemReader}.
     */
    public FileSystemReader(RomMapping mapping) {
        this.mapping = mapping;
    }

    public FileSystem read() throws IOException {
        // TODO

        return new FileSystem();
    }
}
