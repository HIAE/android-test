package com.lcardoso.android_test

import org.hamcrest.CoreMatchers
import org.junit.Assert

infix fun <T> T.assertInstanceOf(clazz: Class<*>) {
    Assert.assertThat(this, CoreMatchers.instanceOf(clazz))
}