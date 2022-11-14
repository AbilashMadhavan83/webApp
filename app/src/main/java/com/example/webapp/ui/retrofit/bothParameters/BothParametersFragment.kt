package com.example.webapp.ui.retrofit.bothParameters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.webapp.R
import com.example.webapp.databinding.FragmentBothParametersBinding
import com.example.webapp.databinding.FragmentRetrieveDataBinding

class BothParametersFragment : Fragment() {

    private var  binding: FragmentBothParametersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentBothParametersBinding.inflate(inflater, container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}