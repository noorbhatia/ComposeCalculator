package com.calculator.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.calculator.compose.ui.theme.DarkBackground
import com.calculator.compose.ui.theme.DarkButton
import com.calculator.compose.ui.theme.PurpleButton

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit,
    buttonSpacing: Dp
) {
    val row1 =
        arrayOf(
            ButtonAction(
                buttonSymbol = "C",
                action = CalculatorAction.Clear
            ),
            ButtonAction(
                buttonSymbol = "\u232b",
                action = CalculatorAction.Delete
            ),
            ButtonAction(
                buttonSymbol = "/",
                action = CalculatorAction.Operation(operation = CalculatorOperation.Divide)
            ),
        )
    val row2 =
        arrayOf(
            ButtonAction(
                buttonSymbol = "7",
                action = CalculatorAction.Number(number = 7)
            ),
            ButtonAction(
                buttonSymbol = "8",
                action = CalculatorAction.Number(number = 8)
            ),
            ButtonAction(
                buttonSymbol = "9",
                action = CalculatorAction.Number(number = 9)
            ),
            ButtonAction(
                buttonSymbol = "x",
                action = CalculatorAction.Operation(operation = CalculatorOperation.Multiply)
            ),
        )
    val row3 =
        arrayOf(
            ButtonAction(
                buttonSymbol = "4",
                action = CalculatorAction.Number(number = 4)
            ),
            ButtonAction(
                buttonSymbol = "5",
                action = CalculatorAction.Number(number = 5)
            ),
            ButtonAction(
                buttonSymbol = "6",
                action = CalculatorAction.Number(number = 6)
            ),
            ButtonAction(
                buttonSymbol = "-",
                action = CalculatorAction.Operation(operation = CalculatorOperation.Subtract)
            ),
        )
    val row4 =
        arrayOf(
            ButtonAction(
                buttonSymbol = "1",
                action = CalculatorAction.Number(number = 1)
            ),
            ButtonAction(
                buttonSymbol = "2",
                action = CalculatorAction.Number(number = 2)
            ),
            ButtonAction(
                buttonSymbol = "3",
                action = CalculatorAction.Number(number = 3)
            ),
            ButtonAction(
                buttonSymbol = "+",
                action = CalculatorAction.Operation(operation = CalculatorOperation.Add)
            ),
        )
    val row5 =
        arrayOf(
            ButtonAction(
                buttonSymbol = "0",
                action = CalculatorAction.Number(number = 0)
            ),
            ButtonAction(
                buttonSymbol = ".",
                action = CalculatorAction.Decimal
            ),
            ButtonAction(
                buttonSymbol = "=",
                action = CalculatorAction.Calculate
            ),
        )
    Box(
        modifier = Modifier
            .background(color = DarkBackground)
            .fillMaxHeight()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(vertical = 32.dp),
                fontSize = 80.sp,
                color = Color.White,
                maxLines = 2
            )
            CalculatorRow(
                row = row1,
                modifier = Modifier,
                onButtonTap = { action -> onAction(action) })
            CalculatorRow(
                row = row2,
                modifier = Modifier,
                onButtonTap = { action -> onAction(action) })
            CalculatorRow(
                row = row3,
                modifier = Modifier,
                onButtonTap = { action -> onAction(action) })
            CalculatorRow(
                row = row4,
                modifier = Modifier,
                onButtonTap = { action -> onAction(action) })
            CalculatorRow(
                row = row5,
                modifier = Modifier,
                onButtonTap = { action -> onAction(action) })
        }
    }
}

@Composable
fun CalculatorRow(
    row: Array<ButtonAction>,
    modifier: Modifier,
    onButtonTap: (CalculatorAction) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {

        repeat(row.size) {
            CalculatorButton(
                symbol = row[it].buttonSymbol,
                modifier = modifier
                    .background(color = if (it == row.size - 1) PurpleButton else DarkButton)
                    .aspectRatio(if ((row.size % 2 != 0) && (it == 0)) 2f else 1f)
                    .weight(if ((row.size % 2 != 0) && (it == 0)) 2f else 1f),
                onTap = { onButtonTap(row[it].action) },
            )
        }

    }
}

data class ButtonAction(
    val buttonSymbol: String,
    val action: CalculatorAction
)