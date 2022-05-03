data class Attachement(
    val type: String,
    val content: Any
)

data class Photo (
    val id: UInt,
    val ownerId: UInt,
    val photo130: String,
    val photo604: String
)

data class Graffity (
    val id: UInt,
    val ownerId: UInt,
    val photo130: String,
    val photo604: String
)

data class App (
    val id: UInt,
    val name: String,
    val photo130: String,
    val photo604: String
)
