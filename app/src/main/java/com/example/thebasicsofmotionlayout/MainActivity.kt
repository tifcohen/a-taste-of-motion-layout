package com.example.thebasicsofmotionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewAdapter = DummyRecyclerAdapter()

        scrollable_content.apply {
            adapter = viewAdapter
        }


        motion_layout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                (1 - progress).also {
                    label1.run {
//                        alpha = it
                        scaleX = it
                        scaleY = it
                    }
                    label2.alpha = it
                    label3.alpha = it
                }
            }
        })
    }

    private fun createList(): List<String> {
        val list = mutableListOf<String>()
        for (i in 0..20) {
            list.add("dummy item ${i + 1}")
        }
        return list
    }
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

class DummyRecyclerAdapter : RecyclerView.Adapter<DummyRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyRecyclerAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_single_item, parent, false) as ConstraintLayout)
    }

    override fun getItemCount(): Int {
        return 100
    }

    override fun onBindViewHolder(holder: DummyRecyclerAdapter.ViewHolder, position: Int) = Unit

    class ViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout)
}
