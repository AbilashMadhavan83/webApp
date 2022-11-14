package com.example.webapp.ui.retrofit.pathParameters
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webapp.R
import com.example.webapp.app.App
import com.example.webapp.data.model.Post
import com.example.webapp.databinding.FragmentPathParametersBinding
import com.example.webapp.ui.retrofit.retrieveData.RetrieveDataFragmentDirections
import com.example.webapp.ui.retrofit.shared.interfaces.IPost
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModel
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PathParametersFragment : Fragment(),IPost {

    private var  binding: FragmentPathParametersBinding? = null
    private var dialog:String?=null
    private lateinit var adapter: PathParametersAdapter

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact

    private val sharedViewModel: SharedViewModel by activityViewModels() {
        SharedViewModelFactory((activity?.application as App).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentPathParametersBinding.inflate(inflater, container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }

        sharedViewModel._postId.value = null
        setErrorTextField(false)
        binding?.btnSearch?.setOnClickListener {
            onSearchPosts()
            fun chkResponse(s: String?) {

                if (!s.isNullOrEmpty()) {
                    setErrorTextField(true)
                    dialog = s
                    //Toast.makeText(requireContext(),s.toString(),Toast.LENGTH_SHORT).show()
                    binding?.recyclerView?.adapter = null
                    showDialog()
                    sharedViewModel.responseStatus()
            }
            }
            sharedViewModel.response().observe({ lifecycle }, ::chkResponse)
        }
    }

    /*
    * Sets and resets the text field error status.
    */
    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding?.txtPostId?.isErrorEnabled = true
            binding?.txtPostId?.error = getString(R.string.try_again)
        } else {
            binding?.txtPostId?.isErrorEnabled = false
            binding?.inputEditTextPostId?.text = null
        }
    }

    private fun onSearchPosts() {

        fun loadPosts(list: List<Post>?) {

            //println("List contains elements")
            val layoutManager: RecyclerView.LayoutManager =
                LinearLayoutManager(this.requireContext())
            adapter = list?.let { PathParametersAdapter(it, this) }!!

            with(binding) {
                this?.recyclerView?.layoutManager = layoutManager
                this?.recyclerView?.adapter = adapter
                this?.recyclerView?.setHasFixedSize(true)
            }


        }

        sharedViewModel.getPost().observe({ lifecycle }, ::loadPosts)

    }

    /*
     * Creates and shows an AlertDialog with final score.
     */
    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.app_name))
            .setMessage(dialog)
            .setCancelable(false)
            .setNegativeButton(getString(R.string.back)) { _, _ ->
                setErrorTextField(false)
                action()
            }
            .setPositiveButton(getString(R.string._continue)) { _, _ ->
                setErrorTextField(false)
                sharedViewModel._postId.value = null
            }
            .show()
    }

    private fun action() {
        val action =
            PathParametersFragmentDirections.actionPathParametersFragmentToRetrieveDataFragment()
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

    override fun onCellClickListener(post: Post) {
        TODO("Not yet implemented")
    }

}