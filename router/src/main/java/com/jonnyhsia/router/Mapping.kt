package com.jonnyhsia.router

import java.util.Collections

data class Mapping(val schema: String = "native",
                   val target: Class<*>,
                   val paramKeys: List<String> = Collections.emptyList(),
                   val requestCode: Int? = null,
                   val mustLogin: Boolean = false)