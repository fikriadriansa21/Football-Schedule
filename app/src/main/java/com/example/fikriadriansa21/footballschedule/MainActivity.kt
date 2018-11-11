package com.example.fikriadriansa21.footballschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.example.fikriadriansa21.footballschedule.adapter.MainAdapter
import com.example.fikriadriansa21.footballschedule.api.ApiRepository
import com.example.fikriadriansa21.footballschedule.model.League
import com.example.fikriadriansa21.footballschedule.presenter.MainPresenter
import com.example.fikriadriansa21.footballschedule.view.MainView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.onRefresh


class MainActivity : AppCompatActivity(), MainView {

    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var listLeague: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
//    private lateinit var idLeague: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listLeague= findViewById(R.id.rvListLeague)
        progressBar = findViewById(R.id.progressBarLeague)
        swipeRefresh = findViewById(R.id.swipeRefreshLeague)

        listLeague.layoutManager = LinearLayoutManager(baseContext)
        adapter = MainAdapter(leagues)
        listLeague.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this,request,gson)

        swipeRefresh.onRefresh {
            presenter.getLeagueList()
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLeagueList(data: List<League>) {
        swipeRefresh.isRefreshing = false
        leagues.clear()
        leagues.addAll(data)
        adapter.notifyDataSetChanged()
    }
}


