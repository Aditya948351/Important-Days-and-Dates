package com.importantdays.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

object BhashiniTranslator {

    private const val INFERENCE_KEY = "-Xlcxnoq3oAqE00MK5bqmGplFj6ZDt8HZJoQ0iEgjKc08GRezUObH774Vf_X0mFP"
    private const val API_URL = "https://dhruva-api.bhashini.gov.in/services/inference/pipeline"

    suspend fun translate(text: String, sourceLang: String = "en", targetLang: String = "hi"): String {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(API_URL)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.setRequestProperty("Authorization", INFERENCE_KEY)
                connection.doOutput = true

                val requestJson = JSONObject().apply {
                    put("pipelineTasks", JSONArray().apply {
                        put(JSONObject().apply {
                            put("taskType", "translation")
                            put("config", JSONObject().apply {
                                put("language", JSONObject().apply {
                                    put("sourceLanguage", sourceLang)
                                    put("targetLanguage", targetLang)
                                })
                            })
                        })
                    })
                    put("inputData", JSONObject().apply {
                        put("input", JSONArray().apply {
                            put(JSONObject().apply {
                                put("source", text)
                            })
                        })
                    })
                }

                val writer = OutputStreamWriter(connection.outputStream)
                writer.write(requestJson.toString())
                writer.flush()
                writer.close()

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    val responseStr = reader.readText()
                    reader.close()

                    val responseObj = JSONObject(responseStr)
                    val pipelineResponse = responseObj.getJSONArray("pipelineResponse")
                    val outputData = pipelineResponse.getJSONObject(0).getJSONObject("output")
                    val outputArray = outputData.getJSONArray("target")
                    outputArray.getJSONObject(0).getString("target")
                } else {
                    val errorReader = BufferedReader(InputStreamReader(connection.errorStream))
                    val errorStr = errorReader.readText()
                    errorReader.close()
                    "Translation Failed (Code $responseCode): $errorStr"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                "Translation Error: ${e.message}"
            }
        }
    }
}
