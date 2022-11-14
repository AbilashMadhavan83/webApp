package com.example.webapp.ui.retrofit.retrieveData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webapp.data.model.Post
import com.example.webapp.databinding.ListItemBinding
import com.example.webapp.ui.retrofit.shared.interfaces.IPost


class RetrieveDataAdapter(
    private var posts: List<Post>,
    private val iDestination: IPost
)
    : RecyclerView.Adapter<RetrieveDataAdapter.retrieveDataViewHolder>(){

    inner class retrieveDataViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): retrieveDataViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return retrieveDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: retrieveDataViewHolder, position: Int) {
        with(holder){
            with(posts[position]){
                binding.txvId.text = "Id : "+this.id.toString()
                binding.txvUserId.text = "User id : "+this.userId.toString()
                binding.txvTitle.text = "Title : "+this.title.toString()
                binding.txvBody.text = "Body : "+this.body.toString()
                binding.lytName.setOnClickListener {
                    iDestination.onCellClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

}


