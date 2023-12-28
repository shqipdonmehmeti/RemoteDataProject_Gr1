package com.example.remotedataproject.ui.gallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.remotedataproject.databinding.FragmentGalleryBinding
import com.example.remotedataproject.helpers.Helpers
import com.example.remotedataproject.helpers.Helpers.provideSharedPreferences
import com.example.remotedataproject.helpers.Helpers.saveStringToSharedPrefs

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSaveData.setOnClickListener {
          saveStringToSharedPrefs(requireContext(),"name",binding.etName.text.toString())
        }
    }


}