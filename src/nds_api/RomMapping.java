package nds_api;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import nds_api.util.StringReaders;

import java.io.IOException;
import java.io.StringReader;
import java.nio.channels.FileChannel;

/**
 *
 * A memory mapping wrapper of a ROM's {@link FileChannel} to a {@link ByteBuf}.
 *
 * @author Whis
 *
 */
public final class RomMapping {
    /**
     * The internal {@link ByteBuf}.
     */
    private final ByteBuf buffer;

    /**
     * Creates a new {@link RomMapping}.
     */
    public RomMapping(ByteBuf buffer) {
        this.buffer = buffer;
    }

    /**
     * Creates a new {@link RomMapping}.
     */
    public RomMapping(FileChannel channel) throws IOException {
        if (!channel.isOpen() || channel.size() == 0) {
            throw new IllegalArgumentException();
        }

        this.buffer = Unpooled.wrappedBuffer(channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size()));
    }

    /**
     * Reads an unsigned 8 bit value at the buffer's current reader index.
     */
    public int readUByte() {
        return readUByte(readerIndex());
    }

    /**
     * Reads an unsigned 8 bit value at the given address.
     */
    public int readUByte(int address) {
        readerIndex(address);

        return buffer.readUnsignedByte();
    }

    /**
     * Reads an unsigned 16 bit value at the buffer's current reader index in little endian mode.
     */
    public int readUShortLE() {
        return readUShortLE(readerIndex());
    }

    /**
     * Reads an unsigned 16 bit value at the given address in little endian mode.
     */
    public int readUShortLE(int address) {
        readerIndex(address);

        return buffer.readUnsignedShortLE();
    }

    /**
     * Reads an unsigned 32 bit value at the buffer's current reader index in little endian mode.
     */
    public int readUIntLE() {
        return readUIntLE(readerIndex());
    }

    /**
     * Reads an unsigned 32 bit value at the given address in little endian mode.
     */
    public int readUIntLE(int address) {
        readerIndex(address);

        return buffer.readIntLE();
    }

    /**
     * Reads a {@link String} type at the buffer's current reader index of the given length.
     */
    public String readString(int length) {
        return readString(readerIndex(), length);
    }

    /**
     * Reads a {@link String} type at the given address of the given length.
     */
    public String readString(int address, int length) {
        readerIndex(address);

        return StringReaders.readString(buffer, length);
    }

    /**
     * Repositions the buffer's reader index to the given memory address.
     */
    private void readerIndex(int address) {
        buffer.readerIndex(address);
    }

    /**
     * Returns the buffer's current reader index / position.
     */
    public int readerIndex() {
        return buffer.readerIndex();
    }
}
