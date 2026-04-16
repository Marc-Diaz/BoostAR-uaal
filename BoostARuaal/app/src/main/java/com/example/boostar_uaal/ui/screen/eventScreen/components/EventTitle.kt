package com.example.boostar_uaal.ui.screen.eventScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.StringAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.snap.camerakit.internal.tr

@Composable
fun EventTitle(logo: String, title: String){
    Row(Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = rememberAsyncImagePainter(logo),
            tint = Color.Unspecified,
            modifier = Modifier.size(40.dp),
            contentDescription = title
        )
        Spacer(Modifier.width(10.dp))
        InterText(
            text = AnnotatedString.fromHtml(title),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventeTitle(){
    val logo = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/logo_bad_bunny.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9sb2dvX2JhZF9idW5ueS5wbmciLCJpYXQiOjE3NzYxNTEwODQsImV4cCI6MTgwNzY4NzA4NH0.cni6XOVznVQSthyNKnB0cNEPuiwZwuKCeJH1Ll7y1JQ"
    val text = "Boostar<span style=\"color:#007AFF\">.</span>"
    EventTitle(logo, text)
}