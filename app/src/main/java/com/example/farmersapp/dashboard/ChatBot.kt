package com.example.farmersapp.dashboard

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import com.example.farmersapp.databinding.ActivityChatBotBinding
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.lexruntimev2.LexRuntimeV2Client
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextRequest
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextResponse
import java.io.IOError
import java.net.URLConnection
import java.util.*


class ChatBot : AppCompatActivity() {
    lateinit var binding: ActivityChatBotBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBotBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configureAwsBot()
    }

    private fun getRecognizedTextRequest(
        botId: String,
        botAliasId: String,
        localeId: String,
        sessionId: String,
        userInput: String
    ): RecognizeTextRequest {

        return RecognizeTextRequest.builder().botAliasId(botAliasId).botId(botId).localeId(localeId)
            .sessionId(sessionId).text(userInput).build()
    }

    private fun configureAwsBot() {
      //  try {

//           
            //val awsCredProvider: AwsCredentialsProvider = StaticCredentialsProvider.create(awsCred)

//            val lexClient: LexRuntimeV2Client =
//                LexRuntimeV2Client.builder().httpClientBuilder(null).credentialsProvider(awsCredProvider)
//                    .region(Region.US_EAST_1).build()
//
//            val recognisedReq = getRecognizedTextRequest(
//                "YWKNS26XME",
//                "TSTALIASID",
//                "en_US",
//                UUID.randomUUID().toString(),
//                "What is weather in noida"
//            )
//            val recognisedRes: RecognizeTextResponse = lexClient.recognizeText(recognisedReq)
//
//            recognisedRes.messages().forEach {
//                Log.d("Bot Reply", it.toString())
//            }

//        }catch (error:IOError){
//            Log.d("Bot Reply", error.toString())
//        }
    }
}
