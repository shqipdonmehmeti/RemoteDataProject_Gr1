package com.example.remotedataproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.remotedataproject.adapter.PostAdapter
import com.example.remotedataproject.databinding.FragmentHomeBinding
import com.example.remotedataproject.helpers.Helpers.provideRetrofitInstance
import com.example.remotedataproject.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCallApi.setOnClickListener {

            provideRetrofitInstance().getAllPosts().enqueue(object : Callback<List<Post>?> {
                override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                    Log.d("TAG", "response status code ${response.code()}: ")
                    Log.d("TAG", "response body ${response.body()}: ")

                    val listOfPosts = response.body()
                    if (listOfPosts != null) {
                        val postAdapter = PostAdapter(requireContext(), listOfPosts)
                        binding.lvPost.adapter = postAdapter
                    }

                }

                override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                    Log.d("TAG", "error: ${t.message}")
                }
            })
        }
    }
}
