import java.util.concurrent.atomic.AtomicInteger

object WallService {
        private var posts = emptyArray<Post>()
        private var postIdGen: AtomicInteger = AtomicInteger()

        private var comments = emptyArray<Comment>()
        private var commentIdGen: AtomicInteger = AtomicInteger()


        fun add(post: Post): Post {
                val newPost = post.copy(id = postIdGen.incrementAndGet().toUInt());
                posts += newPost
                return newPost
        }

        fun update(post: Post?): Boolean {
            if (post != null) {

                for ((index, oldPost) in posts.withIndex())
                    if (oldPost.id == post.id) {
                        val newPost = post.copy(
                            from_id = oldPost.from_id,
                            date = oldPost.date
                        )

                        posts[index] = newPost
                        return true
                    }
            }

            return false
        }

        fun get(index: Int): Post {
           return posts[index]
        }

        fun clear() {
            posts = emptyArray()
            postIdGen.set(0)
        }

        fun getPostById (id: UInt): Post? {
           var result : Post? = null

           for (post in posts)
              if (post.id == id) {
                result = post
                break
            }

           return result
        }

        fun createComment(comment: Comment) {
            if (getPostById(comment.post_id) != null) {
                val newComment = comment.copy(id = commentIdGen.incrementAndGet().toUInt());
                comments += newComment
            } else
                throw PostNotFoundException("post_id = ${comment.post_id}")
        }
}

data class Comment(
    val id : UInt = 0U,
    val from_id : UInt = 0U,
    val date : UInt = 0U,
    val text : String = "",
    val donut: CommentDonut? = null,
    val reply_to_user: UInt = 0U,
    val reply_to_comment: UInt = 0U,
    val attachment : Attachement? = null,
//    val parents_stack array
    val thread : CommentsThread? = null,
    val post_id : UInt
)

data class CommentDonut (
    val is_don : Boolean = false,
    val placeholder : String
)

data class CommentsThread (
    val count : UInt = 0U,
    //items (array) — массив объектов комментариев к записи (только для метода wall.getComments).
    val can_post : Boolean = true,
    val show_reply_button : Boolean = true,
    val groups_can_post : Boolean = true
)


class PostNotFoundException(message:String): Exception(message)
