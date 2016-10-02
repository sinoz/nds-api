import java.nio.channels.FileChannel
import java.nio.file.Paths

import nds_api.fs.FileSystemReader
import nds_api.{RomFinder, RomHeaderReader, RomMapping}
import org.scalatest.FlatSpec

class TestFileSystem extends FlatSpec {
  // to convert java collections to make use of scala iterators
  import scala.collection.JavaConversions._

  "File extension of archive 420" should "equal NARC" in {
    val finder = new RomFinder(Paths.get("roms/"), "nds")
    finder.find() foreach { romPath =>
      val channel = FileChannel.open(romPath)
      val mapping = new RomMapping(channel)

      val headerReader = new RomHeaderReader(mapping)
      val header = headerReader.read()

      val fsReader = new FileSystemReader(mapping, header)
      val fs = fsReader.read()

      val archive = fs.getFat.get(420)
      val archiveFileExt = mapping.readString(archive.getAddress, 4)

      assert(archiveFileExt.equals("NARC"))
    }
  }
}
