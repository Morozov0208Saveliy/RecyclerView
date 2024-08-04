package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setupSwipeToDelete()
    }


    fun init() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
            adapter.setChatList(Chat.chatList)
            recyclerView.addItemDecoration(ChatItemDecorator())
        }
    }


    private fun setupSwipeToDelete() {
        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    adapter.deleteItem(viewHolder.adapterPosition)
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    viewHolder.itemView.apply {
                        ContextCompat.getDrawable(context, R.drawable.arcive_icon)?.apply {
                            val background = ColorDrawable()
                            background.color = ContextCompat.getColor(context, R.color.blue)

                            val iconMargin = ((bottom - top) - intrinsicHeight) / 2
                            val iconTop = top + iconMargin / 2
                            val iconBottom = iconTop + intrinsicHeight
                            val iconRight = right - iconMargin
                            val iconLeft = iconRight - intrinsicWidth

                            setBounds(iconLeft, iconTop, iconRight, iconBottom)
                            background.setBounds(right + dX.toInt(), top, right, bottom)

                            background.draw(c)
                            draw(c)
                        }
                        val textPaint = Paint().apply {
                            color = Color.WHITE
                            textSize = 25f
                        }
                        val text = "Archive"
                        val textWidth = textPaint.measureText(text)
                        val textX = right - textWidth - 30f
                        val textY = bottom - 20f
                        c.drawText(text, textX, textY, textPaint)
                    }

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            })

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}