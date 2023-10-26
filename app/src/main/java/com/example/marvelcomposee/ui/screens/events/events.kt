package com.example.marvelcomposee.ui.screens.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.marvelcomposee.data.entities.Comic
import com.example.marvelcomposee.data.entities.Event
import com.example.marvelcomposee.data.repositories.EventRepository
import com.example.marvelcomposee.ui.screens.charactersDetail.MarvelItemDetailScreen
import com.example.marvelcomposee.ui.screens.charactersDetail.MarvelItemsListScreen
import com.example.marvelcomposee.ui.screens.comics.toStringRes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun EventScreen(onClick : (Event) -> Unit, viewModel: EventsViewModel = viewModel()) {
    MarvelItemsListScreen(
        loading = viewModel.state.loading,
        items = viewModel.state.items,
        onClick = onClick
    )
}

@Composable
@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)

private fun EventFormatsTabRow(
    pagerState: PagerState,
    formats: List<Comic.Format>,
) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        formats.forEach {
            Tab(
                selected = it.ordinal == pagerState.currentPage,
                onClick = { scope.launch { pagerState.animateScrollToPage(page = it.ordinal) } },
                text = { Text(text = stringResource(it.toStringRes()).uppercase()) }
            )
        }
    }
}
@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel = viewModel()) {
        MarvelItemDetailScreen(
            loading = viewModel.state.loading,
            marvelItem = viewModel.state.event)
}