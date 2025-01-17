package com.bnyro.translate.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bnyro.translate.R
import com.bnyro.translate.ui.components.LanguageSelector
import com.bnyro.translate.ui.models.MainModel

@Composable
fun MainContent() {
    val viewModel: MainModel = viewModel()
    val focusRequester = remember {
        FocusRequester()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            modifier = Modifier
                .weight(1.0f)
        ) {
            Box {
                TranslationComponent(
                    focusRequester
                )

                if (viewModel.insertedText != "") {
                    FloatingActionButton(
                        onClick = {
                            viewModel.clearTranslation()
                            focusRequester.requestFocus()
                        },
                        modifier = Modifier.align(
                            alignment = Alignment.BottomEnd
                        )
                            .padding(15.dp, 40.dp)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            null
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LanguageSelector(
                viewModel.availableLanguages,
                viewModel.sourceLanguage,
                autoLanguageEnabled = true
            ) {
                viewModel.sourceLanguage = it
            }

            IconButton(
                onClick = {
                    if (viewModel.sourceLanguage.code == "auto") return@IconButton
                    val temp = viewModel.sourceLanguage
                    viewModel.sourceLanguage = viewModel.targetLanguage
                    viewModel.targetLanguage = temp
                }
            ) {
                Icon(
                    painterResource(R.drawable.ic_switch),
                    null,
                    modifier = Modifier
                        .size(18.dp),
                    tint = if (viewModel.sourceLanguage.code == "auto") {
                        Color.Gray
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
            }

            LanguageSelector(
                viewModel.availableLanguages,
                viewModel.targetLanguage
            ) {
                viewModel.targetLanguage = it
            }
        }
    }
}

@Preview
@Composable
fun MainContentPreview() {
    MainContent()
}
