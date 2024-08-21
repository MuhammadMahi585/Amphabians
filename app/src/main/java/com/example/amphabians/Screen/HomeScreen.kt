package com.example.amphabians.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphabians.R
import com.example.amphabians.model.AmphibiansPhotos


@Composable
fun HomeScreen(
    amphibiansUIState: AmphibiansUIState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
){
    when(amphibiansUIState){
        is AmphibiansUIState.Loading->LoadingScreen(modifier.size(200.dp))
        is AmphibiansUIState.Success-> SuccessScreen(
            amphibians = amphibiansUIState.photos,
            modifier = modifier
                .padding(
                    start = dimensionResource(com.example.amphabians.R.dimen.padding_medium),
                    top = dimensionResource(com.example.amphabians.R.dimen.padding_medium),
                    end = dimensionResource(com.example.amphabians.R.dimen.padding_medium)
                ),
            contentPadding = contentPadding

            )
        else-> ErrorScreen(modifier.fillMaxSize())
    }
}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = com.example.amphabians.R.drawable.loading_img),
            contentDescription = "Loading Image",
        modifier =modifier)
}
@Composable
fun SuccessScreen(
    amphibians: List<AmphibiansPhotos>,
    modifier: Modifier,
    contentPadding: PaddingValues
){
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(amphibians){item->
            CardDisplay(
                amphibiansPhotos = item,
                modifier = modifier.fillMaxSize()
                )
        }
    }

}
@Composable
fun ErrorScreen(modifier: Modifier){
     Image(
         painter = painterResource(id = R.drawable.ic_broken_image), contentDescription = null,
         modifier = modifier)
}
@Composable
fun CardDisplay(
    amphibiansPhotos: AmphibiansPhotos,
    modifier: Modifier
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    )  {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
           Text(
               text = amphibiansPhotos.name,
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(dimensionResource(R.dimen.padding_medium)),
               style = MaterialTheme.typography.titleLarge,
               fontWeight = FontWeight.Bold,
               textAlign = TextAlign.Start)
            Text(
                text = amphibiansPhotos.type,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start)
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibiansPhotos.imgSrc)
                    .crossfade(true)
                    .build(), contentDescription = null,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image)
            )
            Text(
                text = amphibiansPhotos.description,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
}
}
