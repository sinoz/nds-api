import java.nio.channels.FileChannel
import java.nio.file.Paths

import nds_api.fs.FileSystemReader
import nds_api.fs.narc.ArchiveReader
import nds_api.{RomFinder, RomHeaderReader, RomMapping}
import org.scalatest.FlatSpec

final class TestArchive extends FlatSpec {
  // to convert java collections to make use of scala iterators
  import scala.collection.JavaConversions._

  "Archive 420" should "not be empty" in {
    val finder = new RomFinder(Paths.get("roms/"), "nds")
    finder.find() foreach { romPath =>
      val channel = FileChannel.open(romPath)
      val mapping = new RomMapping(channel)

      val headerReader = new RomHeaderReader(mapping)
      val header = headerReader.read()

      val fsReader = new FileSystemReader(mapping, header)
      val fs = fsReader.read()

      val archiveEntry = fs.getFat.get(420)
      val archiveMapping = mapping.map(archiveEntry.getAddress, archiveEntry.getSize)

      val archiveReader = new ArchiveReader(archiveMapping, archiveEntry)
      val archive = archiveReader.read()

      assert(archive.size() != 0)
    }
  }
}
