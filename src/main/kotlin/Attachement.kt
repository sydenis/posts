open class Attachement(val type: String)

data class photoAttachement(val photo: Photo) : Attachement("photo")

data class graffityAttachement(val graffity: Graffity) : Attachement("graffity")

data class appAttachement(val app: App) : Attachement("app")


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
