package com.example.perfectmovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.perfectmovie.Film
import com.example.perfectmovie.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class Adapter( val item : MutableList<Film>, val fragmentOne : Boolean, val listener: (Film) -> Unit) : RecyclerView.Adapter<Adapter.MainHolder>() {

    private var show : Boolean = fragmentOne

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.groupobject, parent, false))

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: Adapter.MainHolder, position: Int) {
        holder.bind(item.get(position))
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val ImageFilm : ImageView = itemView.findViewById(R.id.ImageFilm)

        private val TitleFilm : TextView = itemView.findViewById(R.id.TitleFilm)

        private val RatingFilm : TextView = itemView.findViewById(R.id.RatingFilm)

        private val ReleaseDataFilm : TextView = itemView.findViewById(R.id.ReleaseDateFilm)

        private val ProgrssB : ProgressBar = itemView.findViewById(R.id.ProgBar)

        fun bind(item : Film) {
            Picasso.get().load(item.urlToImage).fit().placeholder(R.color.black).error(R.color.black).into(ImageFilm, object : Callback {
                override fun onSuccess() {
                    ProgrssB.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {

                }
            })

            if(show) {
                ReleaseDataFilm.text = "Дата выхода: ${item.releaseDate}"
            }
            else{
                ReleaseDataFilm.text = ""
            }

            TitleFilm.text = "Название: ${item.title}"

            RatingFilm.text = "Рейтинг: ${item.voteVerage}"


            itemView.setOnClickListener{listener(item)}
        }
    }
}