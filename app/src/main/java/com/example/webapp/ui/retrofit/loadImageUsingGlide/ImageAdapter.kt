package com.example.webapp.ui.retrofit.loadImageUsingGlide
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webapp.data.model.productsItem
import com.example.webapp.databinding.ListPhotosBinding
import com.example.webapp.ui.retrofit.shared.interfaces.IProducts


class ImageAdapter(
    private var products: List<productsItem>,
    private val iProducts: IProducts
)
    : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    inner class ImageViewHolder(val binding: ListPhotosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ListPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        with(holder){
            with(products[position]){
                binding.txvAlbumId.text = "Id : "+this.id

                Glide.with(binding.imageView.context)
                    .load(this.image)
                    .override(300, 200)
                    .fitCenter()
                    .centerCrop()
                    .into(binding.imageView)

                        /**
                    .override(100, 150)
                    .fitCenter()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder()
                    .centerCrop()
                         */

                binding.imageView.setOnClickListener {
                    iProducts.onCellClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

}



