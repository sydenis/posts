import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Before
    fun setUp() {
        WallService.clear()

        val post = Post(
            id = 0U, owner_id = 1U, from_id = 1U, created_by = 1U, date = 1U,
            text ="test-1",
            reply_owner_id = null, reply_post_id = null,
            comments = null, copyright = null, likes = null,
            reposts = null, views = null, donut = null
        )
        WallService.add(post)
    }

    @Test
    fun add() {
        val post = WallService.add(
            Post(
            id = 0U, owner_id = 2U, from_id = 2U, created_by = 2U, date = 2U,
            text ="test-2",
            reply_owner_id = null, reply_post_id = null,
            comments = null, copyright = null, likes = null,
            reposts = null, views = null, donut = null)
        )

        val expected = 2U
        val actual = post.id

        assertEquals(expected, actual)
    }

    @Test
    fun update_existing() {
        val updPost = WallService.get(0).copy(text = "updated")

        val expected = true
        val actual = WallService.update(updPost)

        assertEquals(expected, actual)
    }

    @Test
    fun update_no_existing() {
        val updPost = Post(
            id = 3U, owner_id = 3U, from_id = 3U, created_by = 3U, date = 3U,
            text ="test-3",
            reply_owner_id = null, reply_post_id = null,
            comments = null, copyright = null, likes = null,
            reposts = null, views = null, donut = null
        )

        val expected = false
        val actual = WallService.update(updPost)

        assertEquals(expected, actual)
    }
}