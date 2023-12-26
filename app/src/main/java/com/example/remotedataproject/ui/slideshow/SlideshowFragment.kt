package com.example.remotedataproject.ui.slideshow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remotedataproject.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private lateinit var binding: FragmentSlideshowBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGetData.setOnClickListener {
            val sharedPrefs = requireContext().getSharedPreferences("shared_prefs",Context.MODE_PRIVATE)
            val name = sharedPrefs.getString("name","")
            binding.textSlideshow.text = name
        }
    }

}