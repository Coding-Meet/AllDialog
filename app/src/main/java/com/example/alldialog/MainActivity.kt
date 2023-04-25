package com.example.alldialog

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBtn = findViewById<Button>(R.id.progressBtn)
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.progress_layout)
        val progressDialog = builder.create()
        progressBtn.setOnClickListener {
            progressDialog.show()
        }

        val loadingBtn = findViewById<Button>(R.id.loadingBtn)
        loadingBtn.setOnClickListener {
            val loadingDialog = Dialog(this)
            loadingDialog.setContentView(R.layout.progress_layout)
            loadingDialog.window!!.setLayout(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            loadingDialog.show()
        }

        val alertBtn = findViewById<Button>(R.id.alertBtn)
        alertBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this, "Clicked Yes", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(this, "Clicked No", Toast.LENGTH_LONG).show()
                }
                .setNeutralButton("Cancel") { _, _ ->
                    Toast.makeText(this, "Clicked Cancel", Toast.LENGTH_LONG).show()
                }
                .setCancelable(false)
                .create()
                .show()

        }
        val materialAlertBtn = findViewById<Button>(R.id.materialAlertBtn)
        materialAlertBtn.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this, "Clicked Yes", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(this, "Clicked No", Toast.LENGTH_LONG).show()
                }
                .setNeutralButton("Cancel") { _, _ ->
                    Toast.makeText(this, "Clicked Cancel", Toast.LENGTH_LONG).show()
                }
                .setCancelable(false)
                .create()
                .show()

        }

        val themeBtn = findViewById<Button>(R.id.themeBtn)
        themeBtn.setOnClickListener {
            var checkedItem = 2   // 2 is default item set
            val items = arrayOf("Light", "Dark", "Auto (System Default)")
            MaterialAlertDialogBuilder(this)
                .setTitle("Theme")
                .setPositiveButton("Ok") { _, _ ->
                    when (checkedItem) {
                        0 -> {
                            Toast.makeText(this, "Light Mode", Toast.LENGTH_LONG).show()
                        }
                        1 -> {
                            Toast.makeText(this, "Dark Mode", Toast.LENGTH_LONG).show()

                        }
                        else -> {
                            Toast.makeText(this, "Auto (System Default)", Toast.LENGTH_LONG).show()

                        }
                    }
                }
                .setSingleChoiceItems(items, checkedItem) { _, selectedItemIndex ->
                    checkedItem = selectedItemIndex
                }
                .setCancelable(false)
                .show()
        }


        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.custom_dialog)
        customDialog.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )


        val cancelBtn = customDialog.findViewById<Button>(R.id.cancelBtn)
        val saveBtn = customDialog.findViewById<Button>(R.id.saveBtn)
        val edMessage = customDialog.findViewById<EditText>(R.id.edMessage)

        cancelBtn.setOnClickListener {
            Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show()
            customDialog.dismiss()
        }
        saveBtn.setOnClickListener {
            Toast.makeText(this, edMessage.text.toString(), Toast.LENGTH_LONG).show()
            customDialog.dismiss()
        }

        val customBtn = findViewById<Button>(R.id.customBtn)
        customBtn.setOnClickListener {
            customDialog.show()

        }


        val checkBoxDialogBtn = findViewById<Button>(R.id.checkBoxDialogBtn)
        val unCheckedTxt = findViewById<TextView>(R.id.unCheckedTxt)
        val checkedTxt = findViewById<TextView>(R.id.checkedTxt)

        checkBoxDialogBtn.setOnClickListener {
            val checkBoxBuilder = AlertDialog.Builder(this@MainActivity)
            checkBoxBuilder.setTitle("Choose Hobbies")
            val hobbiesList = arrayListOf(
                HobbyModel("Gaming", false), // here by default false checked
                HobbyModel("Swing", true), // here by default true checked
                HobbyModel("Dancing", false), // here by default false checked
                HobbyModel("Singing", true), // here by default true checked
                HobbyModel("Listening", false), // here by default false checked
            )

            val onlyHobbiesNameList = hobbiesList.map { it.name }.toTypedArray()

            // here return ischecked is true list
            val onlyHobbiesIsCheckedList = hobbiesList.map { it.isChecked }.toBooleanArray()

            checkBoxBuilder.setMultiChoiceItems(
                onlyHobbiesNameList,
                onlyHobbiesIsCheckedList
            ) { _, position, isChecked ->
                hobbiesList[position].isChecked = isChecked
            }

            checkBoxBuilder.setPositiveButton("Ok") { dialog, which ->
                val unCheckedHobbiesList = hobbiesList.filter { !it.isChecked }.map { it.name }
                val checkedHobbiesList = hobbiesList.filter { it.isChecked }.map { it.name }

                unCheckedTxt.text = "unCheckedHobbiesList=$unCheckedHobbiesList"
                checkedTxt.text = "checkedHobbiesList=$checkedHobbiesList"
            }

            checkBoxBuilder.setNegativeButton("Cancel",null)

            val dialog = checkBoxBuilder.create()
            dialog.show()
        }
    }

    data class HobbyModel(
        val name: String,
        var isChecked: Boolean = false,
    )
}