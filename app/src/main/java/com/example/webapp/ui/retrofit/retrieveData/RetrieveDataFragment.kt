package com.example.webapp.ui.retrofit.retrieveData

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webapp.R
import com.example.webapp.app.App
import com.example.webapp.data.model.Post
import com.example.webapp.databinding.FragmentRetrieveDataBinding
import com.example.webapp.ui.retrofit.pathParameters.PathParametersFragmentDirections
import com.example.webapp.ui.retrofit.shared.interfaces.IPost
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModel
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModelFactory

class RetrieveDataFragment : Fragment(), IPost {

    private var  binding:FragmentRetrieveDataBinding ? = null

    private lateinit var adapter: RetrieveDataAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory((requireActivity().application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentRetrieveDataBinding.inflate(inflater, container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



            fun loadPosts(list: List<Post>?) {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.requireContext())
            adapter = list?.let { RetrieveDataAdapter(it,this) }!!

            with(binding){
                this?.recyclerView?.layoutManager = layoutManager
                this?.recyclerView?.adapter = adapter
                this?.recyclerView?.setHasFixedSize(true)
            }



        }
        sharedViewModel.posts.observe({ lifecycle }, ::loadPosts)




    }




    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onCellClickListener(post: Post) {
        TODO("Not yet implemented")
    }



}