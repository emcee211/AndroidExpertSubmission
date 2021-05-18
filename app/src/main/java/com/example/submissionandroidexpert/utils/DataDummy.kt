package com.example.submissionandroidexpert.utils

import com.example.submissionandroidexpert.data.model.*
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.data.source.remote.response.GenresItemResponse
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowResponse
import java.util.ArrayList

object DataDummy {
    fun generateMovies(): ArrayList<MovieResponse> {
        val movies = ArrayList<MovieResponse>()

        movies.add(
            MovieResponse(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "Mortal Kombat",
                "Mortal Kombat",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "2021-04-07",
                7612.95,
                7.9,
                460465,
                false,
                1882
            )
        )
        movies.add(
            MovieResponse(
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "en",
                "Godzilla vs. Kong",
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "2021-03-24",
                3593.413,
                8.2,
                399566,
                false,
                5146
            )
        )
        movies.add(
            MovieResponse(
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "ja",
                "劇場版「鬼滅の刃」無限列車編",
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "/3FVe3OAdgz060JaxIAaUl5lo6cx.jpg",
                "2020-10-16",
                2767.274,
                8.39,
                635302,
                false,
                745
            )
        )
        movies.add(
            MovieResponse(
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "en",
                "Vanquish",
                "Vanquish",
                "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                "/mb3fcmQzXd8aUf7r6izZfMHSJmz.jpg",
                "2021-04-16",
                3484.138,
                6.2,
                804435,
                false,
                45
            )
        )
        movies.add(
            MovieResponse(
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "en",
                "Nobody",
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
                "2021-03-18",
                2516.939,
                8.5,
                615457,
                false,
                1015
            )
        )
        return movies
    }

    fun generateOneDetailMovie(): MovieDetailResponse {
        val listGenre: List<GenresItemResponse> = listOf<GenresItemResponse>(
            GenresItemResponse(
                "Fantasy",
                14
            ),
            GenresItemResponse(
                "Action",
                28
            ),
            GenresItemResponse(
                "Adventure",
                12
            ),
            GenresItemResponse(
                "Science Fiction",
                878
            ),
            GenresItemResponse(
                "Thriller",
                53
            )
        )
        return MovieDetailResponse(
            "en",
            "tt0293429",
            "Mortal Kombat",
            "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
            50115000,
            listGenre,
            7612.95,
            460465,
            1911,
            20000000,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "Mortal Kombat",
            110,
            "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
            "2021-04-07",
            7.9,
            "Get over here.",
            false,
            "https://www.mortalkombatmovie.net",
            "Released",
        )
    }

    fun emptyDetailMovie(): MovieDetailResponse {
        val listGenre: List<GenresItemResponse> = listOf<GenresItemResponse>(
            GenresItemResponse(
                "",
                0
            )
        )
        return MovieDetailResponse(
            "",
            "",
            "",
            "",
            0,
            listGenre,
            0.0,
            0,
            0,
            0,
            "",
            "",
            0,
            "",
            "",
            0.0,
            "",
            false,
            "",
            "",
        )
    }

    fun generateTVShows(): ArrayList<TvShowResponse> {
        val tvshows = ArrayList<TvShowResponse>()

        tvshows.add(
            TvShowResponse(
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "en",
                listOf(
                    10765,
                    10759,
                    18,
                    10768
                ),
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                listOf("US"),
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                2965.392,
                7.9,
                "The Falcon and the Winter Soldier",
                88396,
                5132
            )
        )

        tvshows.add(
            TvShowResponse(
                "2021-03-26",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "en",
                listOf(
                    16,
                    10759,
                    18,
                    10765
                ),
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                listOf("US"),
                "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                "Invincible",
                1694.777,
                8.9,
                "Invincible",
                95557,
                1033
            )
        )
        tvshows.add(
            TvShowResponse(
                "2017-09-25",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "en",
                listOf(
                    18
                ),
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                listOf("US"),
                "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                "The Good Doctor",
                1455.593,
                8.6,
                "The Good Doctor",
                71712,
                8206
            )
        )
        tvshows.add(
            TvShowResponse(
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "en",
                listOf(
                    18,
                    10765
                ),
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                listOf("US"),
                "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                "The Flash",
                1290.011,
                7.7,
                "The Flash",
                60735,
                7477
            )
        )
        tvshows.add(
            TvShowResponse(
                "2021-03-31",
                "Real people's terrifying tales of the chilling, unexplained and paranormal come to life with dramatic reenactments in this reality series.",
                "es",
                listOf(
                    10764
                ),
                "/7au3qp7xw4fQ8eHEsrzWkFMVNm4.jpg",
                listOf("US"),
                "/lEZLrd3N9nIk5fnCL30GsboCEmB.jpg",
                "The Falcon and the Winter Soldier",
                1114.845,
                7.4,
                "Haunted: Latinoamérica",
                120587,
                199
            )
        )

        return tvshows
    }

    fun emptyDetailTvShow(): TvShowDetailResponse {
        val genres = listOf<GenresItemResponse>(
            GenresItemResponse(
                "",
                0
            )
        )
        return TvShowDetailResponse(
            "",
            0,
            "",
            "",
            genres,
            0.0,
            0,
            0,
            0,
            "",
            "",
            listOf<String>(
                ""
            ),
            "",
            listOf<String>(""),
            "",
            0.0,
            "",
            "",
            listOf<Int>(0),
            "",
            "",
            ""
        )
    }

    fun generateOneDetailTvShow(): TvShowDetailResponse {
        val genres = listOf<GenresItemResponse>(
            GenresItemResponse(
                "Sci-Fi & Fantasy",
                10765
            ),
            GenresItemResponse(
                "Action & Adventure",
                10759
            ),
            GenresItemResponse(
                "Drama",
                18
            ),
            GenresItemResponse(
                "War & Politics",
                10768
            ),
        )
        return TvShowDetailResponse(
            "en",
            6,
            "Miniseries",
            "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            genres,
            2965.392,
            88396,
            1,
            5148,
            "2021-03-19",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            listOf<String>(
                "en",
                "fr"
            ),
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            listOf<String>("US"),
            "The Falcon and the Winter Soldier",
            7.9,
            "The Falcon and the Winter Soldier",
            "Honor the shield.",
            listOf<Int>(50),
            "2021-04-23",
            "https://www.disneyplus.com/series/the-falcon-and-the-winter-soldier/4gglDBMx8icA",
            "Ended"
        )
    }

    //generate as entity
    fun generateMovieEntities(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                460465,
                "Mortal Kombat",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                7.9
            )
        )
        movies.add(
            MovieEntity(
                399566,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                8.2
            )
        )
        movies.add(
            MovieEntity(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                8.39
            )
        )
        movies.add(
            MovieEntity(
                804435,
                "Vanquish",
                "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                6.2
            )
        )
        movies.add(
            MovieEntity(
                615457,
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                8.5
            )
        )
        return movies
    }

    fun generateTVShowEntities(): ArrayList<TvShowEntity> {
        val tvshows = ArrayList<TvShowEntity>()

        tvshows.add(
            TvShowEntity(
                88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                7.9
            )
        )

        tvshows.add(
            TvShowEntity(
                95557,
                "Invincible",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                8.9
            )
        )

        tvshows.add(
            TvShowEntity(
                71712,
                "The Good Doctor",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                8.6
            )
        )
        tvshows.add(
            TvShowEntity(
                60735,
                "The Flash",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                7.7
            )
        )
        tvshows.add(
            TvShowEntity(
                120587,
                "Haunted: Latinoamérica",
                "/7au3qp7xw4fQ8eHEsrzWkFMVNm4.jpg",
                7.4
            )
        )

        return tvshows
    }

    fun generateOneDetailMovieEntity(): MovieEntity {
        val listGenre: List<GenreEntity> = listOf<GenreEntity>(
            GenreEntity(
                14,
                "Fantasy"
            ),
            GenreEntity(
                28,
                "Action"
            ),
            GenreEntity(
                12,
                "Adventure"
            ),
            GenreEntity(
                878,
                "Science Fiction"
            ),
            GenreEntity(
                53,
                "Thriller"
            )
        )
        return MovieEntity(
            460465,
            "Mortal Kombat",
            "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
            7.9,
            110,
            MappingHelper.mapGenreArrayToString(listGenre),
            "2021-04-07",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
        )
    }

    fun generateOneDetailTvShowEntity(): TvShowEntity {
        val genres = listOf<GenreEntity>(
            GenreEntity(
                10765,
                "Sci-Fi & Fantasy"
            ),
            GenreEntity(
                10759,
                "Action & Adventure"
            ),
            GenreEntity(
                18,
                "Drama"
            ),
            GenreEntity(
                10768,
                "War & Politics"
            ),
        )
        return TvShowEntity(
            88396,
            "The Falcon and the Winter Soldier",
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            7.9,
            "Ended",
            1,
            MappingHelper.mapGenreArrayToString(genres),
            "2021-03-19",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
        )
    }

}