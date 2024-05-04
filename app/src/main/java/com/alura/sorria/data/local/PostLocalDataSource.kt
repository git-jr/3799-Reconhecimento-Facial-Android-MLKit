package com.alura.sorria.data.local

import com.alura.sorria.data.model.BasePost
import com.alura.sorria.data.model.User
import com.alura.sorria.domain.model.Post
import com.alura.sorria.sampleData.foodPics
import com.alura.sorria.sampleData.profilesPics
import com.alura.sorria.sampleData.randomPics
import java.util.UUID

class PostLocalDataSource {
    val users = listOf(
        User(
            name = "Rich",
            userName = "rich.com",
            avatar = profilesPics[0],
            followers = 5000000,
            following = 100,
            posts = 1000,
            isFollowing = false,
            isMe = false
        ),
        User(
            name = "Duda",
            userName = "duda123",
            avatar = profilesPics[1],
            followers = 10000,
            following = 10,
            posts = 500,
            isFollowing = false,
            isMe = false
        ),
        User(
            name = "Derli",
            userName = "therli",
            avatar = profilesPics[2],
            followers = 5000,
            following = 20,
            posts = 200,
            isFollowing = false,
            isMe = false
        ),
        User(
            name = "De Fault",
            userName = "default42",
            avatar = profilesPics[3],
            followers = 1234,
            following = 56,
            posts = 800,
            isFollowing = false,
            isMe = false
        ),
        User(
            name = "Charlie",
            userName = "charliex3",
            avatar = profilesPics[4],
            followers = 50000,
            following = 30,
            posts = 600,
            isFollowing = false,
            isMe = false
        )
    )

    val basePosts = listOf(
        BasePost(
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget ultricies aliquam, nunc nisl aliquet nunc, quis aliquam nisl",
            likes = 616,
            comments = 110,
            time = "1h",
            user = users[1],
            isLiked = false
        ),
        BasePost(
            description = "Plural Strings em ação",
            likes = 1,
            comments = 10,
            time = "1h",
            user = users.first(),
            isLiked = true
        ),
        BasePost(
            description = "Algum outro exemplo de conteudo para o post",
            likes = 5500,
            comments = 100,
            time = "1h",
            user = users[1],
            isLiked = false
        ),
        BasePost(
            description = "The carousel runs around so fast...",
            likes = 100,
            comments = 10,
            time = "1h",
            user = users[2],
            isLiked = true
        ),
        BasePost(
            description = "Texto de exemplo para o post",
            likes = 5464,
            comments = 87,
            time = "1h",
            user = users[3],
            isLiked = false
        )
    )

    val posts = listOf(
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[0],
            images = foodPics.subList(1, 2),
        ),
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[1],
            images = randomPics.subList(2, 4),
        ),
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[2],
            images = foodPics.subList(2, 4),
        ),
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[3],
            images = randomPics.subList(3, 5),
        ),
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[4],
            images = foodPics.take(5),
        ),
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[0],
            images = randomPics.take(1),
        ),
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[1],
            images = randomPics.subList(13, 14),
        ),
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[2],
            images = randomPics.subList(12, 13),
        ),
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[3],
            images = foodPics.subList(6, 7),
        ),
        Post(
            id = UUID.randomUUID().toString(),
            basePost = basePosts[4],
            images = randomPics.subList(11, 12),
        )
    )
}