package com.jonnyhsia.core.ext

/**
 * Run the block when boolean equals to true
 * and return itself
 */
inline infix fun Boolean.yes(block: () -> Unit): Boolean {
    if (this) {
        block()
    }
    return this
}

/**
 * Run the block when boolean equals to false
 * and return itself
 */
inline infix fun Boolean.otherwise(block: () -> Unit): Boolean {
    if (this.not()) {
        block()
    }
    return this
}

/**
 * exp
 */
inline infix fun <T> T.case(predicate: (T) -> Boolean): T? {
    if (predicate(this)) {
        return this
    }
    return null
}

/**
 * exp
 */
inline infix fun <T> T?.then(block: (T) -> Unit): T? {
    this ?: return null
    block(this)
    return this
}