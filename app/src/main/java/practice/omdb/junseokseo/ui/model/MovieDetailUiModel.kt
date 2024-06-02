package practice.omdb.junseokseo.ui.model

import practice.omdb.junseokseo.api.model.DetailDTO
import practice.omdb.junseokseo.api.model.RatingDto
import java.util.UUID

data class MovieDetailUiModel(
    val title: String,
    val poster: String,
    val metaScore: String,
    val imdb: String,
    val genre: String,
    val plot: String,
    val releasedDate: String,
    val actors: String,
    val ratings: List<Rating>,
    val awards: String,
    val boxOffice: String
)

data class Rating(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val score: String
)

fun DetailDTO.toMovieDetailUiModel() = MovieDetailUiModel(
    title = Title,
    metaScore = Metascore,
    imdb = imdbRating,
    genre = Genre,
    releasedDate = Released,
    plot = Plot,
    actors = Actors,
    ratings = Ratings.toRating(),
    awards = Awards,
    boxOffice = BoxOffice,
    poster = Poster
)

fun RatingDto.toRating() = Rating(
    title = Source,
    score = Value
)

fun List<RatingDto>.toRating(): List<Rating> {
    val list = MutableList(size = this.size) { index ->
        this[index].toRating()
    }
    return list
}