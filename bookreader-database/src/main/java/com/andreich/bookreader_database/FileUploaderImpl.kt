package com.andreich.bookreader_database

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.content.asByteStream
import aws.smithy.kotlin.runtime.net.url.Url
import java.io.File

class FileUploaderImpl : FileUploader {

    private val config = ClientConfig(
        bucketName = "andrewandreich-firststorage",
        region = "ru-central1",
        url = Url.parse("https://storage.yandexcloud.net/"),
        credentials = StaticCredentialsProvider(
            credentials = Credentials(
                accessKeyId = "",
                secretAccessKey = "",
            )
        )
    )

    override suspend fun invoke(file: File): Result<Unit> {
        val client = S3Client {
            region = config.region
            endpointUrl = config.url
            credentialsProvider = config.credentials
        }
        return runCatching {
            client.use { s3 ->
                s3.putObject(
                    PutObjectRequest {
                        bucket = config.bucketName
                        key = file.name
                        metadata = mapOf()
                        body = file.asByteStream()
                    }
                )
            }
        }
    }
}