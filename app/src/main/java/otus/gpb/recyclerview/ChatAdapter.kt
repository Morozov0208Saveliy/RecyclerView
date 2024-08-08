package otus.gpb.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private var chatList = mutableListOf<Chat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int = chatList.size

    fun setChatList(list: MutableList<Chat>) {
        Log.d("ChatAdapter", "New chat list set with size: ${list.size}")
        chatList = list
        notifyDataSetChanged()
    }

    fun addChatList(newChatList: List<Chat>) {
        val startPosition = chatList.size
        chatList.addAll(newChatList)
        notifyItemRangeInserted(startPosition, newChatList.size)
    }

    fun deleteItem(position: Int) {
        if (position >= 0 && position < chatList.size) {
            chatList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    class ChatViewHolder(private val binding: ChatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat) = with(binding) {
            avatar.setImageResource(chat.avatar)
            imageInChat.setImageResource(chat.imageInChat)
            textViewNickName.text = chat.name
            textViewGroupNickName.text = chat.group
            textViewMessage.text = chat.message
            textViewMesTime.text = chat.lastTime

            textViewGroupNickName.visibility = if (chat.group.isEmpty()) View.GONE else View.VISIBLE
            muted.visibility = if (chat.mute) View.VISIBLE else View.GONE
            verified.visibility = if (chat.verified) View.VISIBLE else View.GONE
            scam.visibility = if (chat.scam) View.VISIBLE else View.GONE

            if (chat.check) {
                messegeCheckOne.visibility = View.VISIBLE
                messegeDoubleCheck.visibility = View.GONE
            } else {
                messegeCheckOne.visibility = View.GONE
                messegeDoubleCheck.visibility = View.VISIBLE
            }

            imageInChat.visibility = if (chat.imageInChat == 0) View.GONE else View.VISIBLE
        }
    }
}
