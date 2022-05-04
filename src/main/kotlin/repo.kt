import java.util.concurrent.atomic.AtomicInteger

data class Post (
        val id: UInt = 0U,
        val owner_id: UInt,
        val from_id: UInt,
        val created_by: UInt,
        val date: UInt,
        val text: String,
        val reply_owner_id: UInt? = null,
        val reply_post_id: UInt? = null,
        val friends_only: Boolean = false,
        val comments: Comments? = null,
        val copyright: Copyright? = null,
        val likes: Likes? = null,
        val reposts: Reposts? = null,
        val views: Views? = null,
        val post_type: String = "post",
        val signer_id: UInt = 0U,
        val can_pin: Boolean = true,
        val can_delete: Boolean = true,
        val can_edit: Boolean = true,
        val is_pinned: Boolean = false,
        val marked_as_ads: Boolean = false,
        val is_favorite: Boolean = false,
        val donut: Donut? = null,
        val postponed_id: UInt = 0U,
        val attachements: Array<Attachement>? = null
        ) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Post

                if (id != other.id) return false
                if (owner_id != other.owner_id) return false
                if (from_id != other.from_id) return false
                if (created_by != other.created_by) return false
                if (date != other.date) return false
                if (text != other.text) return false
                if (reply_owner_id != other.reply_owner_id) return false
                if (reply_post_id != other.reply_post_id) return false
                if (friends_only != other.friends_only) return false
                if (comments != other.comments) return false
                if (copyright != other.copyright) return false
                if (likes != other.likes) return false
                if (reposts != other.reposts) return false
                if (views != other.views) return false
                if (post_type != other.post_type) return false
                if (signer_id != other.signer_id) return false
                if (can_pin != other.can_pin) return false
                if (can_delete != other.can_delete) return false
                if (can_edit != other.can_edit) return false
                if (is_pinned != other.is_pinned) return false
                if (marked_as_ads != other.marked_as_ads) return false
                if (is_favorite != other.is_favorite) return false
                if (donut != other.donut) return false
                if (postponed_id != other.postponed_id) return false
                if (!attachements.contentEquals(other.attachements)) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + owner_id.hashCode()
                result = 31 * result + from_id.hashCode()
                result = 31 * result + created_by.hashCode()
                result = 31 * result + date.hashCode()
                result = 31 * result + text.hashCode()
                result = 31 * result + (reply_owner_id?.hashCode() ?: 0)
                result = 31 * result + (reply_post_id?.hashCode() ?: 0)
                result = 31 * result + friends_only.hashCode()
                result = 31 * result + (comments?.hashCode() ?: 0)
                result = 31 * result + (copyright?.hashCode() ?: 0)
                result = 31 * result + (likes?.hashCode() ?: 0)
                result = 31 * result + (reposts?.hashCode() ?: 0)
                result = 31 * result + (views?.hashCode() ?: 0)
                result = 31 * result + post_type.hashCode()
                result = 31 * result + signer_id.hashCode()
                result = 31 * result + can_pin.hashCode()
                result = 31 * result + can_delete.hashCode()
                result = 31 * result + can_edit.hashCode()
                result = 31 * result + is_pinned.hashCode()
                result = 31 * result + marked_as_ads.hashCode()
                result = 31 * result + is_favorite.hashCode()
                result = 31 * result + (donut?.hashCode() ?: 0)
                result = 31 * result + postponed_id.hashCode()
                result = 31 * result + attachements.contentHashCode()
                return result
        }
}

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