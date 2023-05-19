package com.example.belajarcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.belajarcompose.ui.theme.BelajarComposeTheme
import com.example.compose.tutorial.SampleData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BelajarComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Conversation(message = SampleData.conversationSample)
                }
            }
        }
    }
}

data class SampleMessage(val name : String, val body : String)

@Composable
fun Greeting(smg : SampleMessage) {

    /** Add padding around our message text **/

    Row(modifier = Modifier.padding(all = 8.dp)) {
       Image(
           painter = painterResource(id = R.drawable.ic_launcher_background),
           contentDescription = "Walpaper",
           modifier = Modifier
               .size(40.dp)
               .clip(CircleShape)
       )
        Spacer(modifier = Modifier.width(5.dp))

        var isExpanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = "Hello ${smg.name}!")
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Welcome ${smg.body}!",
                modifier = Modifier.padding(all = 4.dp),
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun Conversation(message : List<SampleMessage>){
    LazyColumn{
        items(message) { message ->
            Greeting(smg = message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BelajarComposeTheme {
       Conversation(message = SampleData.conversationSample)
    }
}