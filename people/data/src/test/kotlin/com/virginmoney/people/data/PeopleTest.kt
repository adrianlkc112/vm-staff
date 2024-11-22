package com.virginmoney.people.data

import com.squareup.moshi.Types
import com.virginmoney.network.NetworkModule_Companion_ProvideMoshiFactory
import org.intellij.lang.annotations.Language
import org.junit.Assert.assertEquals
import org.junit.Test

class PeopleTest {
    @Test
    fun `People - should deserialise fields correctly`() {
        val result =
            NetworkModule_Companion_ProvideMoshiFactory
                .provideMoshi()
                .adapter<List<People>>(
                    Types.newParameterizedType(
                        List::class.java,
                        People::class.java,
                    ),
                ).fromJson(peopleJson)

        assertEquals(3, result?.size)
        val first = result?.first()
        assertEquals("Maggie", first?.firstName)
        assertEquals("Brekke", first?.lastName)
        assertEquals(1, first?.id)
        assertEquals("Crystel.Nicolas61@hotmail.com", first?.email)
        assertEquals("https://randomuser.me/api/portraits/women/21.jpg", first?.avatar)
        assertEquals("Future Functionality Strategist", first?.jobTitle)
        assertEquals("pink", first?.favouriteColor)

        val createdAt = first?.createdAt
        assertEquals(2022, createdAt?.year)
        assertEquals(1, createdAt?.monthValue)
        assertEquals(24, createdAt?.dayOfMonth)
        assertEquals(17, createdAt?.hour)
        assertEquals(2, createdAt?.minute)
        assertEquals(23, createdAt?.second)
    }

    @Language("JSON")
    private val peopleJson =
        """
        [
          {
            "createdAt":"2022-01-24T17:02:23.729Z",
            "firstName":"Maggie",
            "avatar":"https://randomuser.me/api/portraits/women/21.jpg",
            "lastName":"Brekke",
            "email":"Crystel.Nicolas61@hotmail.com",
            "jobtitle":"Future Functionality Strategist",
            "favouriteColor":"pink",
            "id":"1"
          },
          {
            "createdAt":"2022-01-24T22:47:43.227Z",
            "firstName":"Armando",
            "avatar":"https://randomuser.me/api/portraits/women/23.jpg",
            "lastName":"Weber","email":"Milton.Wisoky@gmail.com",
            "jobtitle":"Principal Accounts Developer",
            "favouriteColor":"sky blue",
            "id":"2"
          },
          { 
            "createdAt":"2022-01-25T02:27:00.553Z",
            "firstName":"Ceasar",
            "avatar":"https://randomuser.me/api/portraits/women/4.jpg",
            "lastName":"Turner",
            "email":"Hettie31@gmail.com",
            "jobtitle":"Future Interactions Supervisor",
            "favouriteColor":"cyan",
            "id":"3"
          }
        ]
        """.trimIndent()
}
