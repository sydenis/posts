import java.util.concurrent.atomic.AtomicInteger

data class Post (
        val id: UInt = 0U,
        val owner_id: UInt,
        val from_id: UInt,
        val created_by: UInt,
        val date: UInt,
        val text: String,
        val reply_owner_id: UInt?,
        val reply_post_id: UInt?,
        val friends_only: Boolean = false,
        val comments: Comments?,
        val copyright: Copyright?,
        val likes: Likes? = Likes(),
        val reposts: Reposts?,
        val views: Views?,
        val post_type: String = "post",
        val signer_id: UInt = 0U,
        val can_pin: Boolean = true,
        val can_delete: Boolean = true,
        val can_edit: Boolean = true,
        val is_pinned: Boolean = false,
        val marked_as_ads: Boolean = false,
        val is_favorite: Boolean = false,
        val donut: Donut?,
        val postponed_id: UInt = 0U
        )

data class Comments (
        val count: UInt = 0U,
        val can_post: Boolean = true,
        val groups_can_post: Boolean = true,
        val can_close: Boolean = true,
        val can_open: Boolean = true
        )

data class Copyright (
        val id: UInt,
        val link: UInt,
        val name: String,
        val type: String
        )

data class Likes (
        val count: UInt = 0U,
        val user_likes: Boolean = true,
        val can_like: Boolean = true,
        val can_publish: Boolean = true
        )

data class Reposts (
        val count: UInt = 0U,
        val user_reposted: Boolean = true
        )

data class Views (
        val count: UInt = 0U
        )

data class Donut (
        val is_donut: Boolean = false,
        val paid_duration: UInt = 0U,
        val can_publish_free_copy: Boolean = false,
        val edit_mode: String = "all",
        val duration: UInt = 0U
        )

object WallService {
        private var counter: AtomicInteger = AtomicInteger()
        private var posts = emptyArray<Post>()

        fun add(post: Post): Post {
                val newPost = post.copy(id = counter.incrementAndGet().toUInt());
                posts += newPost
                return newPost
        }

        fun update(post: Post): Boolean {
            for ((index, oldPost) in posts.withIndex())
                if (oldPost.id == post.id) {
                   val newPost = post.copy(
                                   from_id = oldPost.from_id,
                                   date = oldPost.date)

                   posts[index] = newPost
                   return true
                }

            return false
        }

        fun get(index: Int): Post {
            return posts[index]
        }

        fun clear() {
            posts = emptyArray()
            counter.set(0)
        }
}