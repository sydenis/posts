import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Before
    fun setUp() {
        WallService.clear()
        val post = Post(0U, 1U, 1U, 1U, 1U, "test-1")
        WallService.add(post)
    }

    @Test
    fun add() {
        val post = WallService.add(
            Post(0U, 2U, 2U, 2U, 2U, "test-2")
        )

        val expected = 2U
        val actual = post.id

        assertEquals(expected, actual)
    }

    @Test
    fun update_existing() {
        val updPost = WallService.get(0)?.copy(text = "updated")

        val expected = true
        val actual = WallService.update(updPost)

        assertEquals(expected, actual)
    }

    @Test
    fun update_no_existing() {
        val updPost = Post(
            0U, 3U, 3U, 3U, 3U, "test-3"
        )

        val expected = false
        val actual = WallService.update(updPost)

        assertEquals(expected, actual)
    }

    @Test
    fun createComment_success() {
        val expected = WallService.get(0).id
        val comment = Comment(post_id = expected)

        WallService.createComment(comment)
    }

    @Test (expected = PostNotFoundException::class)
    fun createComment_fail() {
        val expected = 999U
        val comment = Comment(post_id = expected)

        WallService.createComment(comment)
    }


}