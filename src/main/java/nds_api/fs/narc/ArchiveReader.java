package nds_api.fs.narc;

import nds_api.RomMapping;
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

        // TODO fat block

        return new Archive();
    }
}
