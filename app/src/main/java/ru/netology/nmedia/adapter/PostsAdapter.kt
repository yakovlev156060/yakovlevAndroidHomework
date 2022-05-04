package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias OnLikeListener=(Post)->Unit
typealias OnShareListener=(Post)->Unit

fun thousands(n:Long):String{
    val m:String
    when{
        (n<1000) -> m=n.toString()
        ((n>=1000)&&(n<10_000)) -> m=(n/1000).toString()+"."+(n/100-((n/1000)*10)).toString()+"K"
        ((n>=10_000)&&(n<1_000_000)) ->m=(n/1000).toString()+"K"
        else -> {
            var tmp:Long=n/1000
            m=(tmp/1000).toString()+"."+(tmp/100-((tmp/1000)*10)).toString()+"M"
        }
    }
    return m
}

class PostsAdapter(private val likeListener: OnLikeListener,private val shareListener: OnShareListener):
    ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view,likeListener,shareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class PostViewHolder(private val binding: CardPostBinding,
                     private val likeListener: OnLikeListener,private val shareListener: OnLikeListener)
    :RecyclerView.ViewHolder(binding.root){
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(if (post.likedByMe) R.drawable.ic_liked_24dp else R.drawable.ic_baseline_favorite_border_24dp)
            likeCount.text = thousands(post.likes).toString()
            shareCount.text = thousands(post.shares).toString()
            like.setOnClickListener {
                likeListener(post)
            }
            share.setOnClickListener{
                shareListener(post)
            }
        }
    }

}

class PostDiffCallback: DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}
