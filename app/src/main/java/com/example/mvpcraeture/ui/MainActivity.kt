package com.example.mvpcraeture.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvpcraeture.R
import com.example.mvpcraeture.creaturehelper.SharedHelper
import com.example.mvpcraeture.adapter.CreatureAdapter
import com.example.mvpcraeture.adapter.CreatureListener
import com.example.mvpcraeture.application.MyApp
import com.example.mvpcraeture.model.Creature
import com.example.mvpcraeture.presenter.AllCreatureContract
import com.example.mvpcraeture.presenter.AllCreaturePresenter
import com.example.mvpcraeture.repository.CreateRepositoryImpl
import com.example.mvpcraeture.room.CreatureDAO
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AllCreatureContract.View {

    private val TAG = MainActivity::class.java.name

    @Inject
    @JvmField
    var sharedHelper: SharedHelper? = null

    @Inject
    @JvmField
    var dao: CreatureDAO? = null

    private val presenter = AllCreaturePresenter(CreateRepositoryImpl(dao))

    private var adapter: CreatureAdapter? = null

    private val creatureListener = object : CreatureListener {
        override fun onCreatureClicked(creature: Creature) {
            presenter.clearCreature(creature)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (this.applicationContext as MyApp).appComponent?.injectMain(this)

        presenter.setView(this)

        configureUI()
        setObserver()
    }

    private fun configureUI() {
        fb_action.setOnClickListener {
            startActivity(Intent(this, CreatureActivity::class.java))
        }
    }

    private fun setObserver() {
        dao?.getAllCreatures()!!.observe(this, Observer {
            setUpRecycleView(it)
        })

        dao?.getRows()!!.observe(this, Observer {
            Log.d(TAG, "The DB size is : $it")
        })
    }

    private fun setUpRecycleView(listOfCreature: MutableList<Creature>) {
        adapter = CreatureAdapter(listOfCreature, sharedHelper).apply {
            this.listener = creatureListener
        }
        rv_creatures.adapter = adapter
        adapter?.notifyDataSetChanged()
        Log.d(TAG, "SEE SIZE : ${listOfCreature.size}")
    }

    override fun showCreatureCleared() {
        val name = sharedHelper?.getCreatureName()
        Toast.makeText(this, "Creature $name was deleted", Toast.LENGTH_LONG).show()
    }

    override fun showCreatureClearError() {
        Toast.makeText(this, "Error deleting creature", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.removeListener()
    }
}
