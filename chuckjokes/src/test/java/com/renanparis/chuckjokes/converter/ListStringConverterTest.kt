package com.renanparis.chuckjokes.converter

import com.renanparis.chuckjokes.TestHelper
import com.renanparis.chuckjokes.data.database.converter.ListStringConverter
import junit.framework.Assert.assertEquals
import org.junit.Test

class ListStringConverterTest {

    private val categories = TestHelper().getCategoriesList()


    @Test
    fun should_returnString_WhenListStringSaved() {

        val value = ListStringConverter().listToString(categories)
        assertEquals(value, VALUE_STRING)
    }

    @Test
    fun should_returnListString_WhenStringSaved() {
        val list = ListStringConverter().stringTiList(VALUE_STRING)
        assertEquals(3, list.size)
        assertEquals("test1", list[0])
        assertEquals("test2", list[1])
        assertEquals("test3", list[2])

    }

    companion object {

        private const val VALUE_STRING = "test1,test2,test3"
    }
}