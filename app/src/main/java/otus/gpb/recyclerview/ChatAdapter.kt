package otus.gpb.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.Chat.Companion.chatList
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        Log.d("ChatAdapter", "Binding view at position: $position with chat: ${chatList[position].name}")
        holder.bind(chatList[position])
    }


    override fun getItemCount(): Int {
        return chatList.size
    }

    fun setChatList(list: MutableList<Chat>) {
        Log.d("ChatAdapter", "New chat list set with size: ${list.size}")
        chatList.clear()
        chatList.addAll(list)
        notifyDataSetChanged()
    }


    fun deleteItem(position: Int) {
        chatList.removeAt(position)
        notifyItemRemoved(position)
    }

    class ChatViewHolder(private val binding:ChatItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat) = with(binding) {
            avatar.setImageResource(chat.avatar)
            textViewNickName.text = chat.name
            textViewGroupNickName.text = chat.group
            textViewMessage.text = chat.message
            textViewMesTime.text = chat.lastTime


            if (chat.group.isEmpty()) {
                textViewGroupNickName.visibility = View.GONE
            } else {
                textViewGroupNickName.visibility = View.VISIBLE
            }

            if (chat.mute)
                muted.visibility = View.VISIBLE
            else
                muted.visibility = View.GONE

            if (chat.verified)
                verified.visibility = View.VISIBLE
            else
                verified.visibility = View.GONE

            if (chat.scam)
                scam.visibility = View.VISIBLE
            else
                scam.visibility = View.GONE

            if (chat.check) {
                messegeCheckOne.visibility = View.VISIBLE
                messegeDoubleCheck.visibility = View.GONE
            } else {
                messegeCheckOne.visibility = View.GONE
                messegeDoubleCheck.visibility = View.VISIBLE
            }
        }
    }
}
