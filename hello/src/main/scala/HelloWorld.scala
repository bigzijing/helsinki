import zio.App
import zio.Console.{printLine, readLine}


object HelloWorld extends App {

  def run(args: List[String]) =
    myAppLogic.exitCode

  val myAppLogic =
    for {
      _    <- printLine("Hello! What is your name?")
      name <- readLine
      _    <- printLine(s"Hello, $name, welcome to ZIO!")
    } yield ()

}
