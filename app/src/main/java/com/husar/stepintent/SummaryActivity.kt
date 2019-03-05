package com.husar.stepintent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.husar.stepintent.model.Person

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        //  Set title
        val title = "Summary"
        setTitle(title)

        // Enable back arrow
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        // Get Person from previous activity
        val person: Person = intent.getParcelableExtra("person")

        println(person.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
