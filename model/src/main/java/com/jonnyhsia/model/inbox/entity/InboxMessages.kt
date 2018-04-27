package com.jonnyhsia.model.inbox.entity

import java.util.Date

data class InboxMessages(
        val date: Date,
        val messages: InboxMessage
)