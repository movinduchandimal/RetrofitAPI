package com.example.myapplication03

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication03.adapter.PhotoAdapter
import com.example.myapplication03.api.RetrofitAPIHandler
import com.example.myapplication03.database.Photo
import com.example.myapplication03.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.FieldPosition

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val retrofitAPIHandler = RetrofitAPIHandler.create()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        val photos = retrofitAPIHandler.getPhotos()

        photos.enqueue(object : Callback<List<Photo>>{
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val photoBody = response.body()
                val adapter = PhotoAdapter(photoBody!!,this,{position -> onListItemClick(position)})
                binding.recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Snackbar.make(view,"Failure",Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show()
                Log.i("TAG", "Failure")
            }

        })

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onListItemClick(position: Int){
        Snackbar.make(requireView(),"Clicked ${position+1}",Snackbar.LENGTH_LONG)
            .setAction("Act",null).show()
        Log.i("TAG","onListItemClick: $position clicked")
    }
}