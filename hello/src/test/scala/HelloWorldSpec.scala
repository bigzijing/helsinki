import HelloWorld._
import zio.Console._
import zio._
import zio.test.Assertion._
import zio.test._
import zio.test.environment._

object HelloWorld {
  def sayHello: ZIO[Has[Console], Nothing, Unit] =
    printLine("Hello, World!").orDie
}

object HelloWorldSpec extends DefaultRunnableSpec {
  def spec = suite("HelloWorldSpec")(
    test("sayHello correctly displays output") {
      for {
        _      <- sayHello
        output <- TestConsole.output
      } yield assert(output)(equalTo(Vector("Hello, World!\n")))
    }
  )
}
