package com.example.marvelcomposee.ui.screens.comics

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marvelcomposee.R
import com.example.marvelcomposee.data.entities.Comic
import com.example.marvelcomposee.ui.screens.charactersDetail.MarvelItemDetailScreen
import com.example.marvelcomposee.ui.screens.charactersDetail.MarvelItemsList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun ComicsScreen(onClick : (Comic) -> Unit, viewModel : ComicViewModel = viewModel()) {


    val formats = Comic.Format.values().toList()
    val pagerState = rememberPagerState( )

    Column {
        ComicFormatsTabRow(
            pagerState = pagerState,
            formats = formats
        )
        HorizontalPager(
            count = formats.size,
            state = pagerState
        ) {page ->
            val format = formats[page]
            viewModel.formatRequested(format)
            val pagerState by viewModel.state.getValue(format)
            MarvelItemsList(
                loading = pagerState.loading,
                items = pagerState.items,
                onClick = onClick,
            )
        }
    }
}



@Composable
@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)

private fun ComicFormatsTabRow(
    pagerState: PagerState,
    formats: List<Comic.Format>,
) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(
                    pagerState,
                    tabPositions)
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
fun ComicDetailScreen(viewModel: ComicDetailViewModel = viewModel()) {
    viewModel.state.comic?.let {
        MarvelItemDetailScreen(
            loading = viewModel.state.loading,
            marvelItem = it
        )
    }
}

@StringRes
fun Comic.Format.toStringRes(): Int = when (this) {
    Comic.Format.COMIC -> R.string.comic
    Comic.Format.MAGAZINE -> R.string.magazine
    Comic.Format.TRADE_PAPERBACK -> R.string.trade_paperback
    Comic.Format.HARDCOVER ->  R.string.hardcover
    Comic.Format.DIGEST ->  R.string.digest
    Comic.Format.GRAPHIC_NOVEL ->  R.string.graphic_novel
    Comic.Format.DIGITAL_COMIC ->  R.string.digital_comic
    Comic.Format.INFINITE_COMIC -> R.string.infinite_comic
}



