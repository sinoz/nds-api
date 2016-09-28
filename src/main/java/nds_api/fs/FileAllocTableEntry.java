package nds_api.fs;

/**
 *
 * A single entry in the {@link FileAllocTable} containing the memory address and the size of
 * the file.
 *
 * @author Whis
 *
 */
public final class FileAllocTableEntry {
    /**
     * The start address of the entry and the file size.
     */
    private final int address, size;

    /**
     * Creates a new {@link FileAllocTableEntry}.
     */
    public FileAllocTableEntry(int address, int size) {
        this.address = address;
        this.size = size;
    }

    public int getAddress() {
        return address;
    }

    public int getSize() {
        return size;
    }
}
