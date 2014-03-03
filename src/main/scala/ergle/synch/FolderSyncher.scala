package ergle.synch

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.ExecutionContext
import com.typesafe.scalalogging.slf4j.Logging


@Named
@Singleton
class FolderSyncher extends Logging {

  @Inject
  var fileSender: FileSender = null
  @Inject
  var fileLister: FileLister = null

  def synch(path: String)(implicit ec: ExecutionContext) {
    logger.debug(s"synching path: $path")
    val fileList = fileLister.list(path)

    fileList.foreach {
      fileSender.send
    }
  }
}