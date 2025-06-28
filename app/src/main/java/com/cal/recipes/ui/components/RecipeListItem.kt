package com.cal.recipes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cal.recipes.models.responses.RecipeItem

@Composable
fun RecipeListItem(
    modifier: Modifier = Modifier,
    recipe: RecipeItem,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = recipe.thumb,
                contentDescription = recipe.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${recipe.name}",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "Fats: ${recipe.fats}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Calories: ${recipe.calories}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Carbs: ${recipe.carbos}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewRecipeListItem() {
    val sample = RecipeItem(
        name = "Avocado Toast",
        thumb = "https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/54511c31ff604dee7b8b4571.jpg",
        image = "",
        fats = "12g",
        calories = "290 kcal",
        carbos = "20g",
        description = "Simple avocado toast with olive oil and chili flakes."
    )
    RecipeListItem(recipe = sample, onClick = {})
}
