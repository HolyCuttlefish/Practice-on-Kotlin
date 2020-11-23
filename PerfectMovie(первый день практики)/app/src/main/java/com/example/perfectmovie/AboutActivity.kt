package com.example.perfectmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.perfectmovie.Film
import com.example.perfectmovie.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val item : Film? = intent.getParcelableExtra<Film>("OBJECT")

        Picasso.get().load(item?.urlToImage).fit().placeholder(R.color.black).error(R.color.black).into(ImageFilm)

        TitleFilm.text = "Название: " + item?.title

        DescriptionFilm.text = "Описание: " + item?.overview

        ReleaseDateFilm.text = "Дата выхода: " + item?.releaseDate

        RatingFilm.text = "Рейтинг: " + item?.voteVerage
    }
}