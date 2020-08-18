package com.andromeda.araserver.test
import com.andromeda.araserver.skills.*
import com.andromeda.araserver.skills.Timer
import com.andromeda.araserver.util.*
import java.util.*
import java.net.*
import org.junit.Test
import org.junit.Assert.assertEquals
class SkillsTest{
   @Test
   fun text(){
      val name = "bob"
      if(!checkNlpServerOn()){
         return
      }
      val params = ParseUrl.ApiParams("text bob", "0.0", "0.0", Locale.US, "bvbjrblvrvrkvblfkvn")
      val dataToTest = Text().main(params)
      assertEquals(dataToTest, Feed("list", arrayListOf(SkillsModel("TEXT", name, "")), "texting $name", arrayListOf(FeedModel("Opening text app...", name)))
)
   }
   @Test
   fun call(){
      val name = "bob"
      if(!checkNlpServerOn()){
         return
      }
      val params = ParseUrl.ApiParams("text bob", "0.0", "0.0", Locale.US, "bvbjrblvrvrkvblfkvn")
      val dataToTest = Text().main(params)
      assertEquals(dataToTest, Feed("list", arrayListOf(SkillsModel("CALL", name, "")), "calling $name", arrayListOf(FeedModel("Calling....", name))))

   }
   @Test
   fun timer(){
      if(!checkNlpServerOn()){
         return
      }
      val shouldBe = Feed("list" ,arrayListOf(SkillsModel("TIMER", "300000", "")), "timer starting", arrayListOf(FeedModel("Starting a timer", "for 5 minutes")))

      val params = ParseUrl.ApiParams("set a timer for 5 minutes", "0.0", "0.0", Locale.US, "bvbjrblvrvrkvblfkvn")
      val dataToTest = Timer().main(params)
      assertEquals(shouldBe, dataToTest)

   }

   fun checkNlpServerOn():Boolean{
      URL("https://google.com").readText()
      val url = "http://${System.getenv("NLP")}/v0"
      return try{
         URL(url).readText()
         return true
      }
      catch(e:Exception){
         throw e
         return false
      }
   }
}
