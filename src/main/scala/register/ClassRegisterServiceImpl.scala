package register
import org.slf4j.LoggerFactory
import register.proto.{BookingResponse, ClassBooking}

import scala.concurrent.Future

class ClassRegisterServiceImpl extends proto.ClassRegisterService {
  private val logger = LoggerFactory.getLogger(getClass)

  override def completeClass(in: ClassBooking): Future[BookingResponse] = {
    logger.info("Class {} with participants {} closed.", in.classId, in.participantNames)
    Future.successful(BookingResponse(ok = true))
  }
}
