package nds_api.fs;

import com.google.common.collect.ImmutableList;

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

    public ImmutableList<FileAllocTableEntry> entries() {
        return ImmutableList.copyOf(entries);
    }

    public FileAllocTableEntry get(int id) {
        return entries[id];
    }
}
