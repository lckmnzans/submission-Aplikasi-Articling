package com.example.submission_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSmartphone: RecyclerView
    private val list = ArrayList<Smartphone>()
    private fun showSelectedItem(smartphone: Smartphone) {
        //TODO implicit intent switch to detail activity of selected intent
        Toast.makeText(this, "Anda memilih " + smartphone.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Smartphone"

        rvSmartphone = findViewById(R.id.rv_smartphone)
        rvSmartphone.setHasFixedSize(true)

        list.addAll(getListSmartphones())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        selectMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun selectMenu(selectedMenu: Int) {
        when (selectedMenu) {
            R.id.about -> {
                val intentAbout =Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentAbout)
            }
        }
    }

    @SuppressLint("Recycle")
    private fun getListSmartphones(): ArrayList<Smartphone> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataDesc = resources.getStringArray(R.array.data_description)
        val dataSpecification = resources.getStringArray(R.array.data_specification)
        val dataImage = resources.obtainTypedArray(R.array.data_image)
        val dataLink = resources.getStringArray(R.array.data_link)
        val listSmartphone = ArrayList<Smartphone>()
        for (i in dataName.indices) {
            val smartphone = Smartphone(dataName[i], dataPrice[i], dataDesc[i], dataSpecification[i], dataImage.getResourceId(i, -1), dataLink[i])
            listSmartphone.add(smartphone)
        }
        return listSmartphone
    }

    private fun showRecyclerList() {
        rvSmartphone.layoutManager = LinearLayoutManager(this)
        val listSmartphoneAdapter = ListSmartphoneAdapter(list)
        rvSmartphone.adapter = listSmartphoneAdapter

        listSmartphoneAdapter.setOnItemClickCallback(object: ListSmartphoneAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Smartphone) {
                showSelectedItem(data)
                val item = Smartphone(data.name, data.price, data.desc ,data.specification, data.image, data.link)
                val moveObjIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveObjIntent.putExtra(DetailActivity.EXTRA_ITEM, item)
                startActivity(moveObjIntent)
            }
        })
    }
}