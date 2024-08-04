package otus.gpb.recyclerview


class Chat(
    val id: Int = 0,
    val name: String = "Name",
    val group: String = "",
    val avatar: Int,
    val message: String = "",
    val lastTime: String = "Fri",
    val mute: Boolean = false,
    val check: Boolean = false,
    val messCount: Int = 0,
    val scam: Boolean = false,
    val verified: Boolean = false,
    val isViewed: Boolean = false,
    val imageInChat: Int = 0
) {
    companion object {
        val pizza = Chat(
            id = 1,
            name = "Pizza",
            group = "jija",
            avatar = R.drawable.pizza_avatar,
            message = "Yes, they are necessary",
            lastTime = "11:38 AM",
            mute = true,
            imageInChat = R.drawable.image_in_chat
        )
        val ilon = Chat(
            id = 2,
            name = "Elon Musk",
            avatar = R.drawable.ilon,
            message = "I love /r/Reddit!",
            lastTime = "12:44 AM",
            mute = true,
        )
        val pasha = Chat(
            id = 3,
            name = "Pasha",
            avatar = R.drawable.pasha,
            message = "How are u?",
            mute = true,
            verified = true
        )
        val telegramSupport = Chat(
            id = 4,
            name = "Telegram Support",
            group = "Support",
            avatar = R.drawable.telegram_support_av,
            message = "Yes it happened.",
            lastTime = "Thu",
            messCount = 1,
            verified = true
        )
        val karina = Chat(
            id = 5,
            name = "Karina",
            avatar = R.drawable.karina,
            message = "Okay",
            lastTime = "Wed",
            check = true
        )
        val marlin = Chat(
            id = 6,
            name = "Marlin",
            avatar = R.drawable.marlin,
            message = "Will it ever happen",
            lastTime = "May 02",
            isViewed = true,
            scam = true
        )


        var chatList = mutableListOf(pizza, ilon, pasha, telegramSupport, karina, marlin)
    }
}
