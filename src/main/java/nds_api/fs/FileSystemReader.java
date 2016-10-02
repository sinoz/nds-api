package nds_api.fs;

import nds_api.RomHeader;
import nds_api.RomMapping;

import java.io.IOException;

/**
 *
 * Reads the file allocation tables of the Nitro file system to translate to a {@link FileSystem}.
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

        // the file name tables aren't really necessary as we requesting files by id's is more efficient
        // than by their corresponding name (there are roms that dont store names of files and therefore go by id instead)

        return new FileSystem(new FileAllocTable(entries));
    }
}
