package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin.databinding.ActivityUpdate2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityUpdate2Binding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdate2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        databaseReference = FirebaseDatabase.getInstance().getReference("StudentData")

        binding.updateButton.setOnClickListener {
            val studentName = binding.updateName.text.toString()
            val degreeType = binding.updateDegree.text.toString()
            val studentIndex = binding.updateIndex.text.toString()
            val studentGPA = binding.updateGpa.text.toString()
            updateData(studentName, degreeType, studentIndex, studentGPA)
        }
    }

    private fun updateData(studentName: String, degreeType: String, studentIndex: String, studentGPA: String) {
        databaseReference=FirebaseDatabase.getInstance().getReference("StudentData")
        val studentData = mapOf(
            "studentName" to studentName,
            "degreeType" to degreeType,
            "studentIndex" to studentIndex,
            "studentGPA" to studentGPA
        )

        databaseReference.child(studentIndex).updateChildren(studentData).addOnSuccessListener {
            binding.updateName.text.clear()
            binding.updateDegree.text.clear()
            binding.updateIndex.text.clear()
            binding.updateGpa.text.clear()
            Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Unable to Update",Toast.LENGTH_SHORT).show()
        }
    }
}
