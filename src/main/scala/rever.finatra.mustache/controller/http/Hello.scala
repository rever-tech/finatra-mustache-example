package rever.finatra.mustache.controller.http

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.response.Mustache

import scala.util.parsing.json.JSONObject

/**
 * Created by zkidkid on 10/14/16.
 */
@Mustache("hello")
case class HelloView(obj:JSONObject)



class Hello extends Controller{

  get("/hello"){
    req : Request =>{
      val params = JSONObject(Map("name" -> "duy", "age" -> 100))

      HelloView(params)
    }
  }
}
