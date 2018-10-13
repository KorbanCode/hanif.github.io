package com.chillguy.footballmatchschedule.matchactivity

import com.chillguy.footballmatchschedule.CoroutineContextProviderAllTest
import com.chillguy.footballmatchschedule.api.ApiRepository
import com.chillguy.footballmatchschedule.api.TheSportDBApi
import com.chillguy.footballmatchschedule.model.MatchModel
import com.chillguy.footballmatchschedule.model.MatchModelResponse
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private lateinit var viewMock: MatchView

    @Mock
    private lateinit var gsonMock: Gson

    @Mock
    private lateinit var apiRepositoryMock: ApiRepository

    private lateinit var presenterMock: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenterMock = MatchPresenter(viewMock,apiRepositoryMock,gsonMock,CoroutineContextProviderAllTest())
    }

    @Test
    fun testGetMatchList() {

        /** Membuat list yg bisa menampung data dari model MatchModel*/
        val events: MutableList<MatchModel> = mutableListOf()
        val  response= MatchModelResponse(events)
        val next = "next"

        /**Gson request data ke server, lalu url tsb di baca sebagai string oleh doRequest(), nah disini endpoint API nya diambil
         * dari fungsi getMatch(), lalu datanya di response oleh MatchModelResponse dengan cara akses dan ambil seluruh data
         * dari kepalanya API tsb*/
        `when`(gsonMock.fromJson(apiRepositoryMock.doRequest(TheSportDBApi.getMatch(next)),MatchModelResponse::class.java))
                .thenReturn(response)

        /**`when`(...).thenReturn(...) pada kode di atas akan memaksa gson untuk mengembalikan respon ketika fungsi fromJson dijalankan.
         * Fungsi when() di atas merupakan sebuah kata kunci cadangan pada Kotlin, karena itu, kita perlu membungkusnya di dalam tanda petik terbalik (` `).
         * Maksdunya khusus when buat Test Unit pakai (``)*/

/**Menjalan dan ngetest getMatchList() supaya nanti bisa ditampilkan ke UI karena ada 3 fungsi dri interface MatchView
 * di presenter ini*/
        presenterMock.getMatchList(next)

        /**Mengetes dan memastikan bahwa data bisa ditampilkan ke UI caranya mengetes fungsi*
        showLoading(), showTeamList(events), dan hideLoading()
         */

        verify(viewMock).showLoading()
        verify(viewMock).showTeamList(events)
        verify(viewMock).hideLoading()
    }
}