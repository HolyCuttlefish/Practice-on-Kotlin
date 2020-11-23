package com.example.perfectmovie.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perfectmovie.AboutActivity
import com.example.perfectmovie.BuildConfig
import com.example.perfectmovie.Film
import com.example.perfectmovie.Movie
import com.example.perfectmovie.R
import com.example.perfectmovie.Adapter
import kotlinx.android.synthetic.main.fragment_top.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTop.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTop : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var top: Movie

    private var items: MutableList<Film> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        top = Movie(BuildConfig.UpCommingAPI, true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTop().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateRecycler()

        settingRecycler()
    }

    fun updateRecycler() {
        clearRecycler()
        top.refreshRating()
        updateItems()
    }

    fun settingRecycler() {
        RecyclerTop.layoutManager = LinearLayoutManager(this.requireContext())

        RecyclerTop.setHasFixedSize(true)

        RecyclerTop.adapter = Adapter(/*this.requireContext(),*/ items, false){

            val intent : Intent = Intent(this.requireContext(), AboutActivity::class.java)
            intent.putExtra("OBJECT", it)
            startActivity(intent)
        }
    }

    fun updateItems() {
        for (count in 1..top.getCount()) {
            items.add(Film(top.getTitle(count - 1), "https://image.tmdb.org/t/p/w200/" + top.getUrlToImage(count - 1), top.getRating(count - 1), top.getReleaseDate(count - 1), top.getDescription(count -1)))
        }
    }

    fun clearRecycler() {
        RecyclerTop.recycledViewPool.clear()

        items.clear()
    }

}