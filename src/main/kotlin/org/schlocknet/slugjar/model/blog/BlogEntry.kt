package org.schlocknet.slugjar.model.blog

import java.util.*

/**
 * Data class for representing a blog entry
 */
data class BlogEntry(
        val entryId: UUID = UUID.randomUUID(),
        val entryDate: Double,
        val contents: String
)
