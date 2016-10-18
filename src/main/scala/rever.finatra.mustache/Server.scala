package rever.finatra.mustache


import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import rever.finatra.mustache.controller.http
import rever.finatra.mustache.util.ZConfig

/**
 * Created by SangDang on 9/8/
 **/
object MainApp extends Server

class Server extends HttpServer {

  override protected def defaultFinatraHttpPort: String = ZConfig.getString("server.http.port", ":8080")


  override protected def disableAdminHttpServer: Boolean = ZConfig.getBoolean("server.admin.disable", true)

  override protected def configureHttp(router: HttpRouter): Unit = {
    router.filter[CommonFilters]
      .add[http.Hello]
      .add[http.BaseController]
  }
}
