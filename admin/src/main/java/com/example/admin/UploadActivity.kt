package com.example.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.UploadButton.setOnClickListener {
            val studentName = binding.uploadName.text.toString()
            val degreeType = binding.uploadDegree.text.toString()
            val studentIndex = binding.uploadIndex.text.toString()
            val studentGPA = binding.uploadgpa.text.toString()

            // Initialize the database reference
            databaseReference = FirebaseDatabase.getInstance().getReference("StudentData")

            // Create a StudentData object
            val studentData = StudentData(studentName, degreeType, studentIndex, studentGPA)

            // Store the student data in the database
            databaseReference.child(studentIndex).setValue(studentData)
                .addOnSuccessListener {
                    binding.uploadName.text.clear()
                    binding.uploadDegree.text.clear()
                    binding.uploadIndex.text.clear()
                    binding.uploadgpa.text.clear()

                    Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show()
                    var intent=Intent(this@UploadActivity,MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


                }
        }
    }
}
