package rever.finatra.mustache.controller.http

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.response.Mustache

/**
  * Created by duydb on 10/17/16.
  */
class BaseController extends Controller {
  get("/") {
    request: Request => {
      HomeView("Home", "This is Home", request.path)
    }
  }
  get("/page/:id") {
    request: Request => {
      val id = request.params("id")
      new PageView(s"Page$id", s"This is Page $id", request.path)
    }
  }
  get("/public/:*") {
    request: Request => {
      response.ok.file(request.params("*"))
    }
  }
}

object HomeView {
  val appName = "Finatra Mustache Example"
  val home = Map("name" -> "Home", "href" -> "/")
  val page1 = Map("name" -> "Page 1", "href" -> "/page/1")
  val page2 = Map("name" -> "Page 2", "href" -> "/page/2")
  val page3 = Map("name" -> "Page 3", "href" -> "/page/3")

  def menus(path: String) = {
    Seq(Map("name" -> "Home", "href" -> "/", "selected" -> path.equalsIgnoreCase("/")),
      Map("name" -> "Page 1", "href" -> "/page/1", "selected" -> path.equalsIgnoreCase("/page/1")),
      Map("name" -> "Page 2", "href" -> "/page/2", "selected" -> path.equalsIgnoreCase("/page/2")),
      Map("name" -> "Page 3", "href" -> "/page/3", "selected" -> path.equalsIgnoreCase("/page/3")))
  }
}

@Mustache("home/home")
case class HomeView(title: String, hello: String, path: String) {
  val menus = HomeView.menus(path)
  val appName = HomeView.appName
  //  val header: HeaderView = HeaderView(title)
}

@Mustache("others/header")
case class HeaderView(title: String)

@Mustache("page/page")
class PageView(title: String, hello: String, path: String) extends HomeView(title, hello, path) {
}