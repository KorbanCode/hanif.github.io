package com.chillguy.footballmatchschedule.api

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun doRequest() {
        val apiRepositoryMock = mock(ApiRepository::class.java)
        val url = "https://www.thesportdb.com/api/v1/json/1/search_all_teams.php?I=English%20Premier%20League"
        apiRepositoryMock.doRequest(url)
        verify(apiRepositoryMock).doRequest(url)
    }
}