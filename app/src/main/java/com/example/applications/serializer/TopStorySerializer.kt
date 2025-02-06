package com.example.applications.serializer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TopStorySerializer(
    val section: String,
    val subsection: String,
    val title: String,
    val abstract: String,
//    val url: String,
//    val uri: String,
//    val byline: String,
//    val kicker: String,
//    val multimedia: String,
//    @SerialName("item_type") val itemType: String,
//    @SerialName("updated_date") val updatedDate: String,
//    @SerialName("created_date") val createdDate: String,
//    @SerialName("published_date") val publishedDate: String,
//    @SerialName("material_type_facet") val materialTypeFacet: String,
//    @SerialName("des_facet") val desFacet: List<String>,
//    @SerialName("org_facet") val orgFacet: String,
//    @SerialName("per_facet") val perFacet: String,
//    @SerialName("geo_facet") val geoFacet: String,
//    @SerialName("short_url") val shortUrl: String,
) {
}