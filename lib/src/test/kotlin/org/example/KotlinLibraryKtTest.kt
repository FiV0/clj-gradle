package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class KotlinLibraryKtTest {

    @Test
    fun testMyFunction() {
        assertEquals(myFunction(),"Hello, World!")
    }
}