package com.husar.stepintent

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.husar.stepintent.model.Person
import com.husar.stepintent.validator.ValidatorService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  Set title
        val title = "Step 1/2"
        setTitle(title)

        setButtonOnClickListener(button_next)
    }

    private fun setButtonOnClickListener(button: Button) {
        button.setOnClickListener {
            val (firstName, surname) = collectData(input_name, input_surname)
            // validate
            if (validate(firstName, surname)) {
                val person = producePerson(firstName, surname)
                sendPersonToNextStep(person)
            }
        }
    }

    private fun collectData(nameInput: EditText, surnameInput: EditText): Pair<String, String> {
        val firstName = nameInput.text.toString().trim()
        val surname = surnameInput.text.toString().trim()
        return Pair(firstName, surname)
    }

    private fun validate(name: String, surname: String): Boolean {
        input_layout_name.isErrorEnabled = false
        input_layout_surname.isErrorEnabled = false

        val validator = ValidatorService()
        var validated = true

        // validate name input
        if (validator.isNullorEmpty(name)) {
            input_layout_name.setError("Name can not be empty")
            validated = false
        } else if (!validator.minCharacters(name)) {
            input_layout_name.setError("Name no less then 3 characters")
            validated = false
        } else if (!validator.isFirstLetterUppercase(name)) {
            input_layout_name.setError("Capital")
            validated = false
        }

        // validate surname input
        if (validator.isNullorEmpty(surname)) {
            input_layout_surname.setError("Surname can not be empty")
            validated = false
        } else if (!validator.minCharacters(surname)) {
            input_layout_surname.setError("Surname no less then 3 characters")
            validated = false
        } else if (!validator.isFirstLetterUppercase(surname)) {
            input_layout_surname.setError("Name must be at least characters")
            validated = false
        }
        return validated
    }

    private var producePerson: (String, String) -> Person = {firstName, surname -> Person(firstName, surname)}

    private fun sendPersonToNextStep(person: Person) {
        val intent = Intent(this, SecondActivity::class.java)
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
                input_name.text!!.clear()
                input_surname.text!!.clear()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
