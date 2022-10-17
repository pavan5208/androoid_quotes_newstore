package com.newstore.quotes.utils

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.newstore.quotes.R
import com.newstore.quotes.domain.MockResponseData
import com.newstore.quotes.utils.AppUtils.getQuoteDetailString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class AppUtilsTest {

    @Mock
    private lateinit var mMockContext: Context

    @Before
    fun initialSetUp() {
        mMockContext = mock {
            on { getString(R.string.tags) } doReturn TAGS
            on { getString(R.string.fav_count) } doReturn FAV_COUNT
            on { getString(R.string.upvote_count) } doReturn UP_COUNT
            on { getString(R.string.down_vote_count) } doReturn DOWN_COUNT
            on { getString(R.string.author) } doReturn AUTHOR
            on { getString(R.string.na) } doReturn NA
        }
    }

    @Test
    fun `verify quote details when has all values`() {
        val result = getQuoteDetailString(MockResponseData.getQuoteOneMappedData(),mMockContext)

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(MockResponseData.getQuoteDetails())
    }

    @Test
    fun `verify quote details when tags are missing`() {
        val result = getQuoteDetailString(MockResponseData.getQuoteTwoMappedData(),mMockContext)

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(MockResponseData.getQuoteDetailsTwo())
    }
    companion object {
        const val TAGS = "Tags : "
        const val FAV_COUNT = "Favourite Count : "
        const val UP_COUNT = "Upvote Count : "
        const val DOWN_COUNT = "Down Count : "
        const val AUTHOR = "Author - "
        const val NA = "NA"
    }
}