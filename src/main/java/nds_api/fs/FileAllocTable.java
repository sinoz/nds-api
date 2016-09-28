package nds_api.fs;

/**
 *
 * A table of {@link FileAllocTableEntry}s.
 *
 * @author Whis
 *
 */
public final class FileAllocTable {
    /**
     * The collection of {@link FileAllocTableEntry}s.
     */
    private final FileAllocTableEntry[] entries;

    /**
     * Creates a new {@link FileAllocTable}.
     */
    public FileAllocTable(FileAllocTableEntry[] entries) {
        this.entries = entries;
    }

    public FileAllocTableEntry get(int id) {
        return entries[id];
    }
}
