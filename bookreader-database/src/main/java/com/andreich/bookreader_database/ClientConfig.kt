package com.andreich.bookreader_database

import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import aws.smithy.kotlin.runtime.net.url.Url

data class ClientConfig(
    val bucketName: String,
    val region: String,
    val url: Url,
    val credentials: CredentialsProvider
)
