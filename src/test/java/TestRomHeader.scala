import java.nio.channels.FileChannel
import java.nio.file.Paths

import nds_api.{RomFinder, RomHeaderReader, RomMapping}
import org.scalatest.FlatSpec

import scala.collection.JavaConversions._

final class TestRomHeader extends FlatSpec {
  "The game title" should "start with IP" in {
    val finder = new RomFinder(Paths.get("roms/"), "nds")
    finder.find() foreach { x =>
      val channel = FileChannel.open(x)
      val mapping = new RomMapping(channel)

      val headerReader = new RomHeaderReader(mapping)
      val header = headerReader.read()

      val title = header.getTitle()

      assert(title.toString().startsWith("IP"))

      channel.close()
    }
  }
}
