package nds_api.fs.narc;

import nds_api.RomMapping;
import nds_api.fs.FileAllocTable;
import nds_api.fs.FileAllocTableEntry;

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
     * The expected file extension of the FAT block in this archive.
     */
    private static final String FAT_EXT = "BTAF";

    /**
     * The expected file extension of the FNT block in this archive.
     */
    private static final String FNT_EXT = "BTNF";

    /**
     * The amount of chunks that we could expect in an archive.
     */
    private static final int AMT_CHUNKS = 3;

    /**
     * The {@link RomMapping}.
     */
    private final RomMapping mapping;

    /**
     * The {@link FileAllocTableEntry} of the archive.
     */
    private final FileAllocTableEntry archiveEntry;

    /**
     * Creates a new {@link ArchiveReader}.
     */
    public ArchiveReader(RomMapping mapping, FileAllocTableEntry archiveEntry) {
        this.mapping = mapping;
        this.archiveEntry = archiveEntry;
    }

    public Archive read() throws IOException {
        int beforeReading = mapping.readerIndex();

        String stamp = mapping.readString(FILE_EXT.length());
        if (!stamp.equals(FILE_EXT)) {
            throw new IOException();
        }

        int byteOrder = mapping.readUShortLE();
        int version = mapping.readUShortLE();

        int fileSize = mapping.readUIntLE();
        if (fileSize != archiveEntry.getSize()) {
            throw new IOException();
        }

        int headerSize = mapping.readUShortLE();
        int amtChunks = mapping.readUShortLE();
        if (amtChunks > AMT_CHUNKS) {
            throw new IOException();
        }

        int endHeader = mapping.readerIndex();
        if ((endHeader - beforeReading) != headerSize) {
            throw new IOException();
        }

        String fatStamp = mapping.readString(FAT_EXT.length());
        if (!fatStamp.equals(FAT_EXT)) {
            throw new IOException();
        }

        int fatBlockSize = mapping.readUIntLE();
        int amtFatEntries = mapping.readUIntLE();
        if (amtFatEntries == 0) {
            throw new IOException();
        }

        int minimumBlockSize = (amtFatEntries << 3); // address + size = 8 bytes
        if (minimumBlockSize > fatBlockSize) {
            throw new IOException();
        }

        int[] offsets = new int[amtFatEntries];
        int[] sizes = new int[amtFatEntries];

        for (int entryId = 0; entryId < amtFatEntries; entryId++) {
            offsets[entryId] = mapping.readUIntLE();
            sizes[entryId] = mapping.readUIntLE() - offsets[entryId];
        }

        String fntStamp = mapping.readString(FNT_EXT.length());
        if (!fntStamp.equals(FNT_EXT)) {
            throw new IOException();
        }

        int fntBlockSize = mapping.readUIntLE();
        int entriesOffset = archiveEntry.getAddress() + mapping.readerIndex() + fntBlockSize;

        FileAllocTableEntry[] entries = new FileAllocTableEntry[amtFatEntries];
        for (int entryId = 0; entryId < amtFatEntries; entryId++) {
            int address = offsets[entryId] + entriesOffset;
            int size = sizes[entryId];

            entries[entryId] = new FileAllocTableEntry(address, size);
        }

        return new Archive(new FileAllocTable(entries));
    }
}
