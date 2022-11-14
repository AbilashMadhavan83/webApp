package com.example.webapp.ui.retrofit.queryParameters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webapp.data.model.Comment
import com.example.webapp.databinding.CommentListBinding
import com.example.webapp.ui.retrofit.shared.interfaces.IComment


class QueryParametersAdapter(
    private var coments: List<Comment>,
    private val iComment: IComment
)
    : RecyclerView.Adapter<QueryParametersAdapter.QueryParametersViewHolder>(){

    inner class QueryParametersViewHolder(val binding: CommentListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryParametersViewHolder {
        val binding = CommentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QueryParametersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QueryParametersViewHolder, position: Int) {
        with(holder){
            with(coments[position]){

                binding.txvPostId.text = "PostId : "+this.postId.toString()
                binding.txvId.text = "Id : "+this.id.toString()
                binding.txvName.text = "Name : "+this.name.toString()
                binding.txvEmail.text = "Email : "+this.email.toString()
                binding.txvBody.text = "Body : "+this.body.toString()
                binding.lytName.setOnClickListener {
                    iComment.onCellClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return coments.size
    }


}


