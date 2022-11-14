package com.example.webapp.ui.retrofit.queryMap

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webapp.app.App
import com.example.webapp.data.model.Todo
import com.example.webapp.databinding.FragmentQueryMapBinding
import com.example.webapp.ui.retrofit.queryParameters.QueryParametersFragmentDirections
import com.example.webapp.ui.retrofit.shared.interfaces.ITodo
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModel
import com.example.webapp.ui.retrofit.shared.viewModel.SharedViewModelFactory

class QueryMapFragment : Fragment(),ITodo {
    private var  _binding: FragmentQueryMapBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var adapterUserId: ArrayAdapter<String>
    lateinit var adapterId: ArrayAdapter<String>
    lateinit var adapterCompleted: ArrayAdapter<String>



    private lateinit var adapter: QueryMapAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels() {
        SharedViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQueryMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }

//        fun loadTodo(list: List<Todo>?) {
//
//            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.requireContext())
//            adapter = list?.let { QueryMapAdapter(it,this) }!!
//
//            with(binding){
//                this.recyclerView.layoutManager = layoutManager
//                this.recyclerView.adapter = adapter
//                this.recyclerView.setHasFixedSize(true)
//            }
//        }
//
//        sharedViewModel.todos.observe({ lifecycle }, ::loadTodo)

        fun loadUserId(strings: Array<String>) {
            //Toast.makeText(requireContext(),strings.toString(),Toast.LENGTH_SHORT).show()
            adapterUserId = ArrayAdapter(this.requireContext(), R.layout.simple_spinner_item, strings )
            binding.spinnerUserId.adapter = adapterUserId
        }
        sharedViewModel.toDoUserIdLst.observe({ lifecycle }, ::loadUserId)


        binding.spinnerUserId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                sharedViewModel._toDoUserIdPosition.value = position
                sharedViewModel.UpdateToDoIdLst()
                fun loadId(strings: Array<String>) {
                    adapterId = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, strings )
                    binding.spinnerId.adapter = adapterId
                }
                sharedViewModel.toDoIdLst.observe({ lifecycle }, ::loadId)

            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }

        binding.spinnerId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                sharedViewModel._toDoIdPosition.value = position
                sharedViewModel.UpdateToDoCompleted()
                fun loadCompleted(strings: Array<String>) {

                    adapterCompleted = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, strings )
                    binding.spinnerCompleted.adapter = adapterCompleted
                }

                sharedViewModel.toDoCompletedLst.observe({ lifecycle }, ::loadCompleted)

            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }

        binding.spinnerCompleted.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                sharedViewModel._toDoCompletedPosition.value  = position
                sharedViewModel.SetCompleted()

            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }

        binding.btnSearch.setOnClickListener {

            fun loadTodo(list: List<Todo>?) {

                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.requireContext())
                adapter = list?.let { QueryMapAdapter(it,this) }!!

                with(binding){
                    this.recyclerView.layoutManager = layoutManager
                    this.recyclerView.adapter = adapter
                    this.recyclerView.setHasFixedSize(true)
                }
            }

            sharedViewModel.todoList().observe({ lifecycle }, ::loadTodo)
        }

        binding.btnSearchToDo.setOnClickListener {

            fun loadTodo(list: List<Todo>?) {

                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.requireContext())
                adapter = list?.let { QueryMapAdapter(it,this) }!!

                with(binding){
                    this.recyclerView.layoutManager = layoutManager
                    this.recyclerView.adapter = adapter
                    this.recyclerView.setHasFixedSize(true)
                }
            }

            sharedViewModel.todoLst().observe({ lifecycle }, ::loadTodo)
        }

        binding.btnCancel.setOnClickListener {
            action()
        }



    }
    private fun action() {
        val action =
            QueryMapFragmentDirections.actionQueryMapFragmentToRetrieveDataFragment()
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

    override fun onCellClickListener(post: Todo) {
        TODO("Not yet implemented")
    }

}