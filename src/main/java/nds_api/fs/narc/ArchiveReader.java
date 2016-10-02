package nds_api.fs.narc;

import nds_api.RomMapping;

import java.io.IOException;

/**
 *
 * Reads data to translate to an {@link Archive}.
 *
 * @author Whis
 *
 */
public final class ArchiveReader {
    /**
     * The expected file extension of an archive.
     */
    private static final String FILE_EXT = "NARC";

    /**
     * The {@link RomMapping}.
     */
    private final RomMapping mapping;

    /**
     * Creates a new {@link ArchiveReader}.
     */
    public ArchiveReader(RomMapping mapping) {
        this.mapping = mapping;
    }

    public Archive read() throws IOException {
        String stamp = mapping.readString(FILE_EXT.length());
        if (stamp.equals(FILE_EXT)) {
            throw new IOException();
        }

        return new Archive();
    }
}
