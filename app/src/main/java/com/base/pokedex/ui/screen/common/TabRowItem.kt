package com.base.pokedex.ui.screen.common

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

data class TabRowItem(
    val title: String,
    val screen: @Composable () -> Unit,
)

@Stable
class NoInteraction : MutableInteractionSource {
    override val interactions: Flow<Interaction> = MutableSharedFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = false
}