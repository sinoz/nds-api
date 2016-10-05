package nds_api.fs.narc;

import nds_api.fs.FileAllocTable;
import nds_api.fs.FileAllocTableEntry;

/**
 *
 * The context of an archive of files within the Nitro file system.
 *
 * @author Whis
 *
 */
public final class Archive {
    /**
     * The {@link FileAllocTable}.
     */
    private final FileAllocTable fat;

    /**
     * Creates a new {@link Archive}.
     */
    public Archive(FileAllocTable fat) {
        this.fat = fat;
    }

    public int size() {
        return fat.size();
    }

    public FileAllocTableEntry get(int id) {
        return fat.get(id);
    }
}
