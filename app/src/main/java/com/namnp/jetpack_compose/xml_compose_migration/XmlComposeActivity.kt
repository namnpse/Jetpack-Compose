package com.namnp.jetpack_compose.xml_compose_migration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.namnp.jetpack_compose.databinding.ActivityXmlComposeBinding

class XmlComposeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityXmlComposeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityXmlComposeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}