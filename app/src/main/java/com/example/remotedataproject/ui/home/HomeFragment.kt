package com.example.remotedataproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.remotedataproject.R
import com.example.remotedataproject.adapter.PostAdapter
import com.example.remotedataproject.databinding.FragmentHomeBinding
import com.example.remotedataproject.helpers.Helpers.provideRetrofitInstance
import com.example.remotedataproject.model.Post
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var listOfPosts: List<Post>

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
        onClick()
    }

    private fun onClick() {
        binding.btnCallApi.setOnClickListener {
            getAllPosts()
        }
        binding.lvPost.setOnItemClickListener { parent, view, position, id ->
            getOnePost(position)
        }
    }

    private fun getAllPosts() {
        binding.progressBar.visibility = View.VISIBLE

            provideRetrofitInstance().getAllPosts().enqueue(object : Callback<List<Post>?> {
                override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                    binding.progressBar.visibility = View.GONE
                    if (response.isSuccessful && response.body() != null) {
                        listOfPosts = response.body()!!
                        val postAdapter = PostAdapter(requireContext(), listOfPosts)
                        binding.lvPost.adapter = postAdapter

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Response is not successful",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Failure due to internet connection / server timeout / etc",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })


    }

    private fun getOnePost(position: Int) {
        val currentPost = listOfPosts[position]
        provideRetrofitInstance().getOnePost(currentPost.id).enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(requireContext(), "${response.body()?.body}", Toast.LENGTH_LONG)
                        .show()
//                    sendData(currentPost)
//                    val action = HomeFragmentDirections.actionNavHomeToHomeDetailsFragment()
                    findNavController().navigate(R.id.action_nav_home_to_homeDetailsFragment,sendData(currentPost))
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Response is not successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Failure due to internet connection / server timeout / etc",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun sendData(post: Post): Bundle {
        val bundle = Bundle()
        bundle.apply {
            putInt("id", post.id)
            putString("title", post.title)
            putString("body", post.body)
        }

        return bundle
    }

}
