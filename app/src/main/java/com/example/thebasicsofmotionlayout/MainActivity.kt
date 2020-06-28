package com.example.thebasicsofmotionlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewAdapter = CustomAdapter(createList())

        scrollable_content.apply {
            this.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = viewAdapter
        }


        motion_layout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
//                Toast.makeText(this@MainActivity, "onTransitionCompleted", Toast.LENGTH_SHORT).show()
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
//                Toast.makeText(this@MainActivity, "onTransitionStarted", Toast.LENGTH_SHORT).show()
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
//                progress_display.text = "${(progress*100).toInt()}%"
            }
        })

        btn1.setOnClickListener {
            motion_layout.progress = 0.25f
        }
        btn2.setOnClickListener {
            motion_layout.progress = 0.5f
        }
        btn3.setOnClickListener {
            motion_layout.progress = 0.75f
        }
    }

    private fun createList(): List<String> = List(30) {"Account ${it+1}:\nBalance: ${(it+1)*((it+1)*10)/3}$"}

}

class CustomAdapter(private val itemsList: List<String>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    class CustomViewHolder(val label: AppCompatTextView) : RecyclerView.ViewHolder(label)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_single_item,
            parent,
            false
        ) as AppCompatTextView
        return CustomViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.label.text = itemsList[position]
    }
}