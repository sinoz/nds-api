package nds_api.fs;

/**
 *
 * The context of a loaded in nitro file system.
 *
 * @author Whis
 *
 */
public final class FileSystem {
    /**
     * The {@link FileAllocTable}.
     */
    private final FileAllocTable fat;

    /**
     * Creates a new {@link FileSystem}.
     */
    public FileSystem(FileAllocTable fat) {
        this.fat = fat;
    }

    public FileAllocTable getFat() {
        return fat;
    }
}
