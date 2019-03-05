package com.husar.stepintent

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import com.husar.stepintent.model.Person
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //  Set title
        val title = "Step 2/2"
        setTitle(title)

        // Enable back arrow
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        // Get Person from previous activity
        person = intent.getParcelableExtra("person")

        setButtonOnClickListener(button_summary)
    }

    private fun setButtonOnClickListener(button: Button) {
        button.setOnClickListener {
            val checkBoxes = listOf(input_about_poland, input_about_developer, input_about_sing, input_about_cook, input_about_tired)
            val gender = collectGender(input_gender)
            val about = collectAbout(checkBoxes)
            // validate
            upgradePerson(person, gender, about)
            // Pass person to summary
            sendPersonToNextStep(person)
        }
    }

    private fun collectGender(input: RadioGroup): String {
        val gender = findViewById<RadioButton>(input.checkedRadioButtonId)
        return gender.text.toString().trim()
    }

    private fun collectAbout(listOfCheckBoxes: List<CheckBox>): MutableList<String> {
        val about = mutableListOf<String>()
        listOfCheckBoxes.forEach { checkbox ->
            if (checkbox.isChecked) {
                about.add(checkbox.text.toString().trim())
            }
        }
        return about
    }

    private fun upgradePerson(person: Person, gender: String, about: MutableList<String>): Person {
        person.gender = gender
        person.about = about
        return person
    }

    private fun sendPersonToNextStep(person: Person) {
        val intent = Intent(this, SummaryActivity::class.java)
        intent.putExtra("person", person)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.clear -> {
                input_gender.clearCheck()
                input_about_poland.setChecked(false)
                input_about_developer.setChecked(false)
                input_about_sing.setChecked(false)
                input_about_cook.setChecked(false)
                input_about_tired.setChecked(false)
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
