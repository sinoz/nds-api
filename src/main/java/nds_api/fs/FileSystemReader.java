package nds_api.fs;

import nds_api.RomHeader;
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
     * The {@link RomHeader}.
     */
    private final RomHeader header;

    /**
     * Creates a new {@link FileSystemReader}.
     */
    public FileSystemReader(RomMapping mapping, RomHeader header) {
        this.mapping = mapping;
        this.header = header;
    }

    public FileSystem read() throws IOException {
        int[] addresses = new int[header.getFatSize()];
        int[] sizes = new int[header.getFatSize()];

        for (int entryId = 0; entryId < header.getFatSize(); entryId++) {
            addresses[entryId] = mapping.readUIntLE(header.getFatAddress() + (entryId << 2));
            sizes[entryId] = mapping.readUIntLE() - addresses[entryId];
        }

        int rootTableOffset = mapping.readUIntLE(header.getFntAddress());
        int somethingElse = mapping.readUShortLE();
        int amtChildDirectories = mapping.readUShortLE();

        // TODO

        return new FileSystem(null);
    }
}
