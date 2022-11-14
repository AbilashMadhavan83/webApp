package com.example.webapp.ui.retrofit.loadImageUsingGlide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webapp.app.App
import com.example.webapp.data.model.PhotosItem
import com.example.webapp.data.model.productsItem
import com.example.webapp.databinding.FragmentLoadImageUsingGlideBinding
import com.example.webapp.ui.retrofit.shared.interfaces.IPhoto
import com.example.webapp.ui.retrofit.shared.interfaces.IProducts
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModel
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModelFactory

class LoadImageUsingGlideFragment : Fragment(),IProducts {

    private var  _binding: FragmentLoadImageUsingGlideBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: ImageAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels() {
        SharedViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoadImageUsingGlideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fun loadProducts(list: List<productsItem>?) {

            val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this.requireContext(),2)
            adapter = list?.let { ImageAdapter(it,this) }!!

            with(binding){
                this.recyclerView.layoutManager = layoutManager
                this.recyclerView.adapter = adapter
                this.recyclerView.setHasFixedSize(true)
            }

        }
        sharedViewModel.products.observe({ lifecycle }, ::loadProducts)

//        fun loadPhotos(list: List<PhotosItem>?) {
//            val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this.requireContext(),2)
//            adapter = list?.let { ImageAdapter(it,this) }!!
//
//            with(binding){
//                this.recyclerView.layoutManager = layoutManager
//                this.recyclerView.adapter = adapter
//                this.recyclerView.setHasFixedSize(true)
//            }
//        }
//        sharedViewModel.photos.observe({ lifecycle }, ::loadPhotos)


    }


    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(photosItem: productsItem) {
        TODO("Not yet implemented")
    }

}