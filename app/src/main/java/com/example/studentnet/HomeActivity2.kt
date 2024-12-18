package com.example.studentnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studentnet.databinding.ActivityHome2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityHome2Binding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val studentIndex = binding.searchPhone.text.toString()
            if (studentIndex.isNotEmpty()) {
                readData(studentIndex)
            } else {
                Toast.makeText(this, "Please enter an index number", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun readData(studentIndex: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("StudentData")
        databaseReference.child(studentIndex).get().addOnSuccessListener {
            if (it.exists()) {
                val studentName = it.child("studentName").value
                val degreeType = it.child("degreeType").value
                val studentGPA = it.child("studentGPA").value

                Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()
                binding.searchPhone.text.clear()
                binding.readName.text = studentName.toString()
                binding.readDegree.text = degreeType.toString()
                binding.readgpa.text = studentGPA.toString()
            } else {
                Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
        }
    }
}
