package com.example.webapp.ui.retrofit.queryParameters

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webapp.app.App
import com.example.webapp.data.model.Comment
import com.example.webapp.databinding.FragmentQueryParametersBinding
import com.example.webapp.ui.retrofit.pathParameters.PathParametersFragmentDirections
import com.example.webapp.ui.retrofit.retrieveData.RetrieveDataAdapter
import com.example.webapp.ui.retrofit.shared.interfaces.IComment
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModel
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModelFactory

class QueryParametersFragment : Fragment(),IComment {

    private var  binding: FragmentQueryParametersBinding? = null
    lateinit var adapterPostId: ArrayAdapter<String>
    lateinit var selectedPostId: Array<String> // as default
    lateinit var selectedId: Array<String> // as default
    private lateinit var adapter: QueryParametersAdapter
//    private var selectedObj: Int = 0


    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact

    private val sharedViewModel: SharedViewModel by activityViewModels() {
        SharedViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentQueryParametersBinding.inflate(inflater, container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }

        fun loadPostID(listItem: List<Comment>?) {
            // access the spinner

            val postId : Array<String> = listItem?.distinctBy { it.postId }?.map { it.postId.toString()}!!.toTypedArray()
            selectedPostId = postId
            adapterPostId = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item,postId)
            binding?.spinnerPostId?.adapter  = adapterPostId

        }
        sharedViewModel.comments.observe({ lifecycle }, ::loadPostID)



        binding?.spinnerPostId?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //selectedText = muscleGroups[position]
                //Toast.makeText(requireContext(),position.toString(),Toast.LENGTH_SHORT).show()

                fun loadID(listObj: List<Comment>?) {

                    sharedViewModel._postId.value = selectedPostId[position]

                    //Toast.makeText(requireContext(), selectedPostId[position],Toast.LENGTH_SHORT).show()

                    val id : Array<String> = listObj?.filter { it -> it.postId == selectedPostId[position].toInt() }
                        ?.map { it.id.toString()}!!.toTypedArray()

                    selectedId = id

                    val adapterId: ArrayAdapter<String> =
                        ArrayAdapter(requireContext(), R.layout.simple_spinner_item, id)
                    binding?.spinnerId?.adapter = adapterId

                }
                sharedViewModel.comments.observe({ lifecycle }, ::loadID)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }

        binding?.spinnerId?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //selectedText = muscleGroups[position]
                //Toast.makeText(requireContext(),position.toString(),Toast.LENGTH_SHORT).show()

                sharedViewModel._id.value = selectedId[position]

                //Toast.makeText(requireContext(), selectedId[position],Toast.LENGTH_SHORT).show()


            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }

        binding?.btnSearch?.setOnClickListener {
            fun loadComment(list: List<Comment>?) {
                //Toast.makeText(requireContext(), list?.get(0)?.postId.toString(),Toast.LENGTH_SHORT).show()
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.requireContext())
                adapter = list?.let { QueryParametersAdapter(it,this) }!!

                with(binding){
                    this?.recyclerView?.layoutManager = layoutManager
                    this?.recyclerView?.adapter = adapter
                    this?.recyclerView?.setHasFixedSize(true)
                }
            }
            sharedViewModel.commentsList().observe({ lifecycle }, ::loadComment)
        }

        binding?.btnCancel?.setOnClickListener {
            action()
        }

    }

    private fun action() {
        val action =
            QueryParametersFragmentDirections.actionQueryParametersFragmentToRetrieveDataFragment()
        findNavController().navigate(action)
    }






    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCellClickListener(comment: Comment) {
        TODO("Not yet implemented")
    }

//    private fun goToPathParameters() {
//        val action = RetrieveDataFragmentDirections.actionRetrieveDataFragmentToPathParametersFragment()
//        findNavController().navigate(action)
//    }

}