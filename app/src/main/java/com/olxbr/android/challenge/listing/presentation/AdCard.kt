package com.olxbr.android.challenge.listing.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olxbr.android.challenge.R
import com.olxbr.android.challenge.listing.domain.model.Ad
import com.olxbr.android.challenge.ui.theme.cardGray

@Composable
fun AdCard(ad: Ad) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            .background(color = Color.White)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .shadow(
                elevation = 4.dp,
                ambientColor = Color.Black
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

    )
    {
        Row(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .height(135.dp)
                    .width(135.dp),
                Alignment.CenterStart
            ) {

                //LoadImageFromRemote(imageUrl = ad.thumbnail,
                //    imageDescription = "imagem do anúncio") }

                Box(
                    modifier = Modifier
                        .padding(start = 17.dp, top = 12.dp, end = 17.dp, bottom = 11.dp)
                        .fillMaxHeight()
                ) {

                    Column {

                        Text(
                            text = ad.subject,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontSize = 14.5.sp,
                            fontWeight = FontWeight.W300,
                            lineHeight = 22.sp,
                            letterSpacing = 0.5.sp,
                            maxLines = 2,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = ad.price.toString(),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.W500,
                        )

                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .align(Alignment.BottomStart)
                    ) {

                        Row() {
                            Text(
                                text = "${ad.time}, ${ad.location.toString()}",
                                color = cardGray,
                                fontSize = 13.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AdCardPreview() {
    AdCard(
        ad = Ad(
            thumbnail = "https://img.olx.com.br/thumbs256x256/24/245394415450729.jpg",
            subject = "2 Notebooks Samsung NP300E leia com atenção",
            price = "R$590",
            time = "22 Maio 16:54",
            location = "Engenho Novo"
        )
    )
}

