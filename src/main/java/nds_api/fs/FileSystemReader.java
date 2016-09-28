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
        FileAllocTableEntry[] entries = new FileAllocTableEntry[header.getFatSize()];
        for (int entryId = 0; entryId < entries.length; entryId++) {
            int address = mapping.readUIntLE(header.getFatAddress() + (entryId << 2));
            int size = mapping.readUIntLE() - address;

            entries[entryId] = new FileAllocTableEntry(address, size);
        }
        FileAllocTable fat = new FileAllocTable(entries);

        int rootTableOffset = mapping.readUIntLE(header.getFntAddress());
        int somethingElse = mapping.readUShortLE();
        int amtChildDirectories = mapping.readUShortLE();

        // TODO

        return new FileSystem(fat);
    }
}
