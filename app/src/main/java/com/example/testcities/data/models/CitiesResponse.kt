package com.example.testcities.data.models

data class CitiesResponse(
    val data: Array<City>,
    val links: Array<Link>,
    val metaData: MetaData
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CitiesResponse

        if (!data.contentEquals(other.data)) return false
        if (!links.contentEquals(other.links)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + links.contentHashCode()
        return result
    }
}