package com.andromeda.araserver.skills

import com.andromeda.araserver.util.OutputModel
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.URL
import java.util.*


class Weather {
    private var log: String? = null
    private var lat: String? = null
    private var term: String? = null
    fun getWeatherNow(url:String): String {
        //get api params
        val pairs =
            ArrayList(listOf(*url.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
        //Finish the job an get the raw values
        for (pair in pairs) {
            when {
                pair.startsWith("log") -> log = pair.replace("log=", "")
                pair.startsWith("lat") -> lat = pair.replace("lat=", "")
                else -> term = pair.replace("/weath/", "")
            }
        }
        val urlGrid = URL("https://api.darksky.net/forecast/7b7fd158d8733db19ddac66bb71132b2/$lat,$log")
        println(urlGrid.toString())
        val finalData = urlGrid.readText()
        val json = JsonParser().parse(finalData)
        val dataSet = json.asJsonObject.getAsJsonObject("currently")
        val temp = dataSet.asJsonObject.get("temperature").asInt.toString()
        val foreCast = dataSet.asJsonObject.get("summary").asString
        val title = "$temp and $foreCast"

        val toReturn = OutputModel(title,"", "", "", title, "");
        return Gson().toJson(toReturn)


    }
    fun mainPart(url: String){
        val pairs =
            ArrayList(listOf(*url.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
        //Finish the job an get the raw values
        for (pair in pairs) {
            when {
                pair.startsWith("log") -> log = pair.replace("log=", "")
                pair.startsWith("lat") -> lat = pair.replace("lat=", "")
                else -> term = pair.replace("/weath/", "")

            }
        }

    }
    fun getTime(term:String){

    }
    fun getLocation(){

    }

    companion object {
        const val NOW = 0
    }
}