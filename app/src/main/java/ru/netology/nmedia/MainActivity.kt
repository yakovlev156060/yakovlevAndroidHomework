package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

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
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likes = 5,
            shares =10,
            likedByMe = true
        )
        with(binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = post.likes.toString()
            like.setImageResource(if(post.likedByMe ) R.drawable.ic_liked_24dp else R.drawable.ic_baseline_favorite_border_24dp)



            like.setOnClickListener{
                if(post.likedByMe) {
                    post.likes--
                }
                else{
                    post.likes++
                }
                post.likedByMe = !post.likedByMe

                likeCount?.text = post.likes.toString()
                like.setImageResource(if(post.likedByMe ) R.drawable.ic_liked_24dp else R.drawable.ic_baseline_favorite_border_24dp)

                likeCount?.text=post.likes.toString()
            }

            share.setOnClickListener{
                post.shares++
                shareCount?.text=thousands(post.shares)
            }
        }
        println(getString(R.string.nmedia))
    }
}