package com.example.submissionandroidexpert.core.di

import androidx.room.Room
import com.example.submissionandroidexpert.core.data.source.local.LocalDataSource
import com.example.submissionandroidexpert.core.data.source.local.room.MovieListDatabase
import com.example.submissionandroidexpert.core.data.source.remote.RemoteDataSource
import com.example.submissionandroidexpert.core.data.source.remote.response.MovieListAPI
import com.example.submissionandroidexpert.core.data.source.repository.MovieListRepository
import com.example.submissionandroidexpert.core.domain.repository.IMovieListRepository
import com.example.submissionandroidexpert.core.utils.AppExecutors
import com.example.submissionandroidexpert.core.utils.Constant
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieListDatabase>().movieListDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("MovieList".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieListDatabase::class.java, "MovieList.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(MovieListAPI::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieListRepository> { MovieListRepository(get(), get(), get()) }
}