package com.plcoding.cleanarchitecturenoteapp.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.cleanarchitecturenoteapp.domain.util.SortType

@Composable
fun RadioButtonCustom(text:String,selected:Boolean,onClickEvent:()->Unit) {
  Row(modifier = Modifier.padding(10.dp)) {
      RadioButton(
          selected = selected,
          onClick = { onClickEvent()},
      )
      Text(text = text)
  }
}