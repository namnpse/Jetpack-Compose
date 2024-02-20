package com.namnp.jetpack_compose.xml_compose_migration

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import com.namnp.jetpack_compose.databinding.FragmentSettingsBinding
import com.namnp.jetpack_compose.practice.components.ImageCardBuilder

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

       /* setContent { // for Compose Activity
            JetpackComposeTheme {
                ImageCardBuilder()
            }
        }*/

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ImageCardBuilder() // call Composable View

                // or put traditional Android views like android.widget.TextView,... here
                AndroidView(
                    factory = {
                        TextView(it) // android.widget.TextView, not Compose TextView
                    }
                ) { textView ->
                    textView.apply {
                        text = "This is an XML view in a composable"
                        setTextColor(Color.BLACK)
                        textSize = 20f
                        gravity = Gravity.CENTER
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}