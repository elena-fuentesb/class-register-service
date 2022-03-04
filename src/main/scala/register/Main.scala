package register

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.management.cluster.bootstrap.ClusterBootstrap
import akka.management.scaladsl.AkkaManagement
import org.slf4j.LoggerFactory
import shopping.order.{ShoppingOrderServer, ShoppingOrderServiceImpl}

import scala.util.control.NonFatal

object Main {

  val logger = LoggerFactory.getLogger("register.Main")

  def main(args: Array[String]): Unit = {
    val system = ActorSystem[Nothing](Behaviors.empty, "ClassRegisterService")
    try {
      init(system)
    } catch {
      case NonFatal(e) =>
        logger.error("Terminating due to initialization failure.", e)
        system.terminate()
    }
  }

  def init(system: ActorSystem[_]): Unit = {
    AkkaManagement(system).start()
    ClusterBootstrap(system).start()

    val grpcInterface =
      system.settings.config.getString("class-register-service.grpc.interface")
    val grpcPort =
      system.settings.config.getInt("class-register-service.grpc.port")
    val grpcService = new ClassRegisterServiceImpl
    ClassRegisterServer.start(grpcInterface, grpcPort, system, grpcService)
  }

}
