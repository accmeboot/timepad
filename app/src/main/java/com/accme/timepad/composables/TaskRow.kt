package com.accme.timepad.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.accme.timepad.data.Task
import com.accme.timepad.utils.*
import java.time.LocalDateTime
import java.time.LocalTime

@Preview(showBackground = true)
@Composable
fun TaskRow(
    modifier: Modifier = Modifier,
    task: Task = Task(
        name = "Try KMM",
        duration = LocalTime.now(),
        date = LocalDateTime.now()
    )
) {
    val date =
        if (task.date.isToday())
            "${stringResource(id = com.accme.timepad.R.string.date_today)} at ${task.date.getNiceTime()}"
        else if (task.date.isTomorrow())
            "${stringResource(id = com.accme.timepad.R.string.date_tomorrow)} at ${task.date.getNiceTime()}"
        else task.date.getNiceDate()

    Surface(
        modifier = modifier.padding(horizontal = 18.dp, vertical = 10.dp),
        color = MaterialTheme.colors.secondary,
        shape = MaterialTheme.shapes.small,
        elevation = 0.5.dp

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(color = Constants.getRandomColor()),
                contentAlignment = Alignment.Center
            ) {
                Text(text = task.name.substring(0, 1), color = MaterialTheme.colors.onBackground)
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.fillMaxWidth(0.84F),
            ) {
                Text(
                    text = task.name,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = date,
                    style = MaterialTheme.typography.caption.copy(fontSize = 11.sp),
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = task.duration.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.height(10.dp))
                Icon(
                    painter = painterResource(id = com.accme.timepad.R.drawable.ic_play),
                    contentDescription = "Start task ${task.name}",
                    tint = Color.Unspecified
                )
            }

        }
    }
}