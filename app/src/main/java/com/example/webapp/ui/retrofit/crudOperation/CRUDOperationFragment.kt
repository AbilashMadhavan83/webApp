package com.example.webapp.ui.retrofit.crudOperation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.webapp.R
import com.example.webapp.app.App
import com.example.webapp.databinding.FragmentCRUDOperationBinding
import com.example.webapp.databinding.FragmentLoadImageUsingGlideBinding
import com.example.webapp.ui.retrofit.queryMap.QueryMapFragmentDirections
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModel
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModelFactory

class CRUDOperationFragment : Fragment() {

    private var  _binding: FragmentCRUDOperationBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory((requireActivity().application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCRUDOperationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }


        fun loadUsers(strings: Array<String>) {
            val adapter: ArrayAdapter<String> =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, strings )
            binding.spinnerUserId.adapter = adapter
        }
        sharedViewModel.userId.observe({ lifecycle }, ::loadUsers)

        binding.spinnerUserId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                sharedViewModel.UserID.value = sharedViewModel.userId.value!![position].toInt()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }


        binding.btnPost.setOnClickListener {
            sharedViewModel.AddPost()

        }

        binding.btnCancel.setOnClickListener {
            action()

            sharedViewModel.responsePost.value = null
        }

        binding.btnPUT.setOnClickListener {
            sharedViewModel.updatePost()
        }

        binding.btnPATCH.setOnClickListener {
            sharedViewModel.patchPost()

        }

        binding.btnCancelUpdate.setOnClickListener {
            action()
        }

        binding.btnDelete.setOnClickListener {
            sharedViewModel.deletePost()
        }





    }

    private fun action() {
        val action =
            CRUDOperationFragmentDirections.actionCRUDOperationFragmentToRetrieveDataFragment()
        findNavController().navigate(action)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}