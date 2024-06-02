package practice.omdb.junseokseo.api.model

data class SearchDTO(
    val Search: List<MovieDTO>,
    val Response: String,
    val totalResult: String,
)

data class MovieDTO(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)