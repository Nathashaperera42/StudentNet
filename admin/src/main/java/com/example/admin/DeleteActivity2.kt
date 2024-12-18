package com.example.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admin.databinding.ActivityDelete2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityDelete2Binding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDelete2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.DeleteButton.setOnClickListener {
            val studentIndex = binding.DeleteIndex.text.toString()
            if (studentIndex.isNotEmpty())
                deleteData(studentIndex)
            else
                Toast.makeText(this, "Please enter the index number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData(studentIndex: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("StudentData")
        databaseReference.child(studentIndex).removeValue().addOnSuccessListener {
            binding.DeleteIndex.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}
