package com.base.pokedex

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
    fun stringFormatTest() {
        val data =
            "[(hp, 80), (attack, 82), (defense, 83), (special-attack, 100), (special-defense, 100), (speed, 80)]"

        val regex = Regex("\\((.*?), (\\d+)\\)")
        val list: List<Pair<String, Int>> = regex.findAll(data)
            .map { matchResult ->
                val (key, value) = matchResult.destructured
                Pair(key, value.toInt())
            }
            .toList()


        println("list = $list")

        val result = list.zipWithNext()
        println("result = $result")
    }
}