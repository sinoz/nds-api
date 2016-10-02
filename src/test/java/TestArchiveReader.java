import com.google.common.collect.ImmutableSet;
import nds_api.RomFinder;
import nds_api.RomHeader;
import nds_api.RomHeaderReader;
import nds_api.RomMapping;
import nds_api.fs.FileAllocTableEntry;
import nds_api.fs.FileSystem;
import nds_api.fs.FileSystemReader;
import nds_api.fs.narc.Archive;
import nds_api.fs.narc.ArchiveReader;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TestArchiveReader {
    @Test
    public void testArchiveReader() throws IOException {
        RomFinder finder = new RomFinder(Paths.get("roms/"), "nds");
        ImmutableSet<Path> paths = finder.find();

        for (Path romPath : paths) {
            try (FileChannel channel = FileChannel.open(romPath)) {
                RomMapping mapping = new RomMapping(channel);

                RomHeaderReader headerReader = new RomHeaderReader(mapping);
                RomHeader hdr = headerReader.read();

                FileSystemReader fsReader = new FileSystemReader(mapping, hdr);
                FileSystem fs = fsReader.read();

                FileAllocTableEntry archiveEntry = fs.getFat().get(420);
                RomMapping archiveMapping = mapping.map(archiveEntry.getAddress(), archiveEntry.getSize());

                ArchiveReader archiveReader = new ArchiveReader(archiveMapping, archiveEntry);
                Archive archive = archiveReader.read();

                // TODO assert archive
            }
        }
    }
}
