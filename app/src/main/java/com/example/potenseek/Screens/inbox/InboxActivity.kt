package com.example.potenseek.Screens.inbox

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.potenseek.Model.Chat
import com.example.potenseek.Model.Payment
import com.example.potenseek.Screens.ui.theme.GreyBackground
import com.example.potenseek.components.ChatCard
import com.example.potenseek.components.PaymentCard
import com.example.potenseek.components.TeacherPsychologistCard
import com.example.potenseek.ui.theme.PotenSeekTheme
import okhttp3.internal.wait

class InboxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PotenSeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold() {
                    }
                }
            }
        }
    }
}

@Composable
fun Inbox(inboxViewModel: InboxViewModel) {
    val inboxSectionLoading = remember {
        mutableStateOf(true)
    }

    val parent = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 =  inboxViewModel.inboxData.collectAsState().value.data){
        inboxViewModel.getInboxData("0Z0AiQBhYGqUqukSWia7")
        Log.d(ContentValues.TAG, "inboxHomeData: ${inboxViewModel.inboxData.value.data}")
        if(inboxViewModel.inboxData.value.data != null){
            inboxSectionLoading.value = false
        }
    }

    Column(
        modifier = Modifier
            .background(color = GreyBackground)
            .fillMaxWidth()
    ) {
        Text(
            text = "Inbox",
            modifier = Modifier
                .padding(20.dp, 16.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )
        if (inboxSectionLoading.value) {
            Row(modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                var temp = false
                itemsIndexed(items = inboxViewModel.inboxData.value.data!!) {index, item ->
                    if (item is Chat) {
                            inboxViewModel.getParentData(item.from!!)
                            Log.d(ContentValues.TAG, "inboxGetParentDataChat: ${inboxViewModel.parentData.collectAsState().value.data}")
                            if(inboxViewModel.parentData.collectAsState().value.data != null && inboxViewModel.parentId.collectAsState().value.data == item.from){
                                temp = true
                            }

                        if (temp) {
                            ChatCard(chat = item, inboxViewModel.parentData.collectAsState().value.data!!)
                            Log.d(ContentValues.TAG, "inboxGetParentDataChatX: ${inboxViewModel.parentData.collectAsState().value.data}")
                            temp = false
                        }
                    } else if (item is Payment){
                            inboxViewModel.getParentData(item.madeby!!)
                            Log.d(ContentValues.TAG, "inboxGetParentDataPayment: ${inboxViewModel.parentData.collectAsState().value.data}")
                            if(inboxViewModel.parentData.collectAsState().value.data != null && inboxViewModel.parentId.collectAsState().value.data == item.madeby){
                                temp = true
                            }

                        if (temp) {
                            Log.d(ContentValues.TAG, "inboxGetParentDataPaymentX: ${inboxViewModel.parentData.collectAsState().value.data}")
                            PaymentCard(payment = item, inboxViewModel.parentData.collectAsState().value.data!!)
                            temp = false
                        }
                    }
                }
            }
        }
    }

}