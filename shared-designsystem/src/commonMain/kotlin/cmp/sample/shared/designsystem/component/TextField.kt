package cmp.sample.shared.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextField(
  value: String,
  placeholder: @Composable (() -> Unit)?,
  supportingText: @Composable (() -> Unit)? = null,
  onValueChange: (String) -> Unit,
  singleLine: Boolean = true,
  textStyle: TextStyle = MaterialTheme.typography.titleMedium,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  modifier: Modifier = Modifier,
) {
  androidx.compose.material3.TextField(
    value = value,
    onValueChange = onValueChange,
    singleLine = singleLine,
    textStyle = textStyle,
    placeholder = placeholder,
    supportingText = supportingText,
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    modifier = modifier,
    colors = TextFieldDefaults.colors(
      focusedContainerColor = Color.Transparent,
      unfocusedContainerColor = Color.Transparent,
      focusedPlaceholderColor = MaterialTheme.colorScheme.tertiary,
      unfocusedPlaceholderColor = MaterialTheme.colorScheme.tertiary,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
    ),
  )
}

@Composable
fun TextField(
  value: TextFieldValue,
  placeholder: @Composable (() -> Unit)?,
  onValueChange: (TextFieldValue) -> Unit,
  textStyle: TextStyle = MaterialTheme.typography.titleMedium,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  modifier: Modifier = Modifier,
) {
  androidx.compose.material3.TextField(
    value = value,
    onValueChange = onValueChange,
    singleLine = true,
    textStyle = textStyle,
    placeholder = placeholder,
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    modifier = modifier,
    colors = TextFieldDefaults.colors(
      focusedContainerColor = Color.Transparent,
      unfocusedContainerColor = Color.Transparent,
      focusedPlaceholderColor = MaterialTheme.colorScheme.tertiary,
      unfocusedPlaceholderColor = MaterialTheme.colorScheme.tertiary,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
    ),
  )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DateTextField(
  focusManager: FocusManager,
  year: TextFieldValue?,
  month: TextFieldValue?,
  day: TextFieldValue?,
  onValueChange: (year: TextFieldValue?, month: TextFieldValue?, day: TextFieldValue?) -> Unit,
  horizontalArrangement: Arrangement.Horizontal,
  modifier: Modifier = Modifier,
) {
  val (first, second, third) = remember { FocusRequester.createRefs() }
  var localYear by rememberSaveable(year) { mutableStateOf(year) }
  var localMonth by rememberSaveable(month) { mutableStateOf(month) }
  var localDay by rememberSaveable(day) { mutableStateOf(day) }

  LaunchedEffect(Unit) {
    first.requestFocus()
  }

  val yearFilter: (TextFieldValue) -> Unit = {
    when {
      it.text.toUIntOrNull() == null -> {
        localYear = TextFieldValue()
      }

      it.text.length < YEAR_LENGTH -> {
        localYear = it
      }

      localYear?.text?.length == YEAR_LENGTH -> {
        localYear = localYear
      }

      else -> {
        val text = it.annotatedString
        val end = if (text[YEAR_LENGTH - 1].isHighSurrogate()) {
          YEAR_LENGTH - 1
        } else {
          YEAR_LENGTH
        }
        focusManager.moveFocus(FocusDirection.Next)
        localYear = it.copy(annotatedString = text.subSequence(0, end))

      }
    }
  }

  val monthFilter: (TextFieldValue) -> Unit = {
    when {
      it.text.toUIntOrNull() == null -> {
        localMonth = TextFieldValue()
      }

      it.text.length < MONTH_LENGTH -> {
        localMonth = it
      }

      localMonth?.text?.length == MONTH_LENGTH -> {
        localMonth = localMonth
      }

      else -> {
        val text = it.annotatedString
        val end = if (text[MONTH_LENGTH - 1].isHighSurrogate()) {
          MONTH_LENGTH - 1
        } else {
          MONTH_LENGTH
        }
        focusManager.moveFocus(FocusDirection.Next)
        localMonth = it.copy(annotatedString = text.subSequence(0, end))
      }
    }
  }

  val dayFilter: (TextFieldValue) -> Unit = {
    when {
      it.text.toUIntOrNull() == null -> {
        localDay = TextFieldValue()
      }

      it.text.length < DAY_LENGTH -> {
        localDay = it
      }

      localDay?.text?.length == DAY_LENGTH -> {
        localDay = localDay
      }

      else -> {
        val text = it.annotatedString
        val end = if (text[DAY_LENGTH - 1].isHighSurrogate()) {
          DAY_LENGTH - 1
        } else {
          DAY_LENGTH
        }
        focusManager.clearFocus(true)
        localDay = it.copy(annotatedString = text.subSequence(0, end))
      }
    }
  }

  Row(
    horizontalArrangement = horizontalArrangement,
    modifier = modifier,
  ) {
    val baseModifier = remember {
      Modifier.alignByBaseline()
    }
    Spacer(modifier = baseModifier.weight(1f))
    TextField(
      value = localYear ?: TextFieldValue(),
      placeholder = null,
      onValueChange = {
        onValueChange.invoke(it, localMonth, localDay)
        yearFilter.invoke(it)
      },
      textStyle = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.End),
      visualTransformation = VisualTransformation.None,
      keyboardOptions = KeyboardOptions.Default.copy(
        autoCorrect = false,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next,
      ),
      keyboardActions = KeyboardActions(
        onNext = { second.requestFocus() },
      ),
      modifier = baseModifier
        .weight(1f, false)
        .focusRequester(first)
        .focusProperties {
          previous = third
          next = second
        }
    )
    Text(
      text = "年",
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.titleLarge,
      modifier = baseModifier,
    )

    TextField(
      value = localMonth ?: TextFieldValue(),
      placeholder = null,
      onValueChange = {
        onValueChange.invoke(localYear, it, localDay)
        monthFilter.invoke(it)
      },
      textStyle = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.End),
      visualTransformation = VisualTransformation.None,
      keyboardOptions = KeyboardOptions.Default.copy(
        autoCorrect = false,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next,
      ),
      keyboardActions = KeyboardActions(
        onNext = { third.requestFocus() },
      ),
      modifier = baseModifier.weight(1f, false)
        .focusRequester(second)
        .focusProperties {
          previous = first
          next = third
        },
    )
    Text(
      text = "月",
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.titleLarge,
      modifier = baseModifier,
    )

    TextField(
      value = localDay ?: TextFieldValue(),
      placeholder = null,
      onValueChange = {
        onValueChange.invoke(localYear, localMonth, it)
        dayFilter.invoke(it)
      },
      textStyle = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.End),
      visualTransformation = VisualTransformation.None,
      keyboardOptions = KeyboardOptions.Default.copy(
        autoCorrect = false,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done,
      ),
      keyboardActions = KeyboardActions(
        onDone = {
          focusManager.clearFocus(true)
        },
      ),
      modifier = baseModifier.weight(1f, false)
        .focusRequester(third)
        .focusProperties {
          previous = second
          next = first
        },
    )
    Text(
      text = "日",
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.titleLarge,
      modifier = baseModifier,
    )
    Spacer(modifier = baseModifier.weight(1f))
  }
}

private const val YEAR_LENGTH = 4
private const val MONTH_LENGTH = 2
private const val DAY_LENGTH = 2
