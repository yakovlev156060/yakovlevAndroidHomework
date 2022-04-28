package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this){
            post->with(binding){
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = post.likes.toString()
                like.setImageResource(if(post.likedByMe ) R.drawable.ic_liked_24dp else R.drawable.ic_baseline_favorite_border_24dp)
                shareCount.text= thousands(post.shares).toString()
                binding.like.setOnClickListener{
                    viewModel.like()
                }
                binding.share.setOnClickListener{
                    viewModel.share()
                }
            }
        }

        println(getString(R.string.nmedia))
    }
}