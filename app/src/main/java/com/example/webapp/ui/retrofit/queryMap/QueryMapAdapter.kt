package com.example.webapp.ui.retrofit.queryMap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webapp.data.model.Todo
import com.example.webapp.databinding.ListItemBinding
import com.example.webapp.databinding.TodoItemBinding
import com.example.webapp.ui.retrofit.shared.interfaces.ITodo


class QueryMapAdapter(
    private var todos: List<Todo>,
    private val iTodo: ITodo
)
    : RecyclerView.Adapter<QueryMapAdapter.QueryMapViewHolder>(){

    inner class QueryMapViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryMapViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QueryMapViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QueryMapViewHolder, position: Int) {
        with(holder){
            with(todos[position]){
                binding.txvId.text = "Id : "+this.id.toString()
                binding.txvUserId.text = "User id : "+this.userId.toString()
                binding.txvTitle.text = "Title : "+this.title.toString()
                binding.txvCompleted.text= "Completed : "+this.completed .toString()
                binding.lytName.setOnClickListener {
                    iTodo.onCellClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}


