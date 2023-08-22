package com.base.pokedex

import com.base.pokedex.data.model.remote.dto.Type
import com.base.pokedex.data.model.remote.dto.TypeX
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun CollectionTest() {
        val list = listOf<Type>(
            Type(
                slot = 0,
                type = TypeX(
                    name = "bug",
                    url = ""
                )
            ),
            Type(
                slot = 0,
                type = TypeX(
                    name = "poison",
                    url = ""
                )
            ),
        )

        val result = list.map { it.type.name }.toList()
        println(result)
    }
}