package nds_api.fs;

import com.google.common.base.Preconditions;

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

    public int size() {
        return entries.length;
    }

    public FileAllocTableEntry get(int id) {
        Preconditions.checkElementIndex(id, entries.length);

        return entries[id];
    }
}
