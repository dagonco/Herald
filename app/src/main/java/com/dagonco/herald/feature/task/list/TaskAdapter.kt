/*
 * Copyright (C) 2018 Dagonco.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.dagonco.herald.feature.task.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dagonco.herald.R
import com.dagonco.herald.feature.task.model.Priority.*
import com.dagonco.herald.feature.task.model.Status.*
import com.dagonco.herald.feature.task.model.Task
import kotlinx.android.synthetic.main.item_layout_task.view.*
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(private val dataSet: List<Task>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout_task,
            parent,
            false
        ) as ConstraintLayout
        return TaskViewHolder(rootLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tasksViewHolder = holder as TaskViewHolder
        val task = dataSet[position]
        tasksViewHolder.render(task)
    }

    override fun getItemCount() = dataSet.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SimpleDateFormat")
        fun render(task: Task) {
            val value = Date().apply { time = task.timeInMillis }
            val format = SimpleDateFormat("EEEE, dd / MMMM / yyyy")
                .format(value)
                .replace("/", "de")

            with(itemView) {
                titleTextView.text = task.title
                dateTextView.text = format

                val stringResource = when (task.priority) {
                    LOW -> R.string.task_priority_1
                    MEDIUM -> R.string.task_priority_2
                    HIGH -> R.string.task_priority_3
                }
                priorityTextView.text = context.getString(stringResource)


                changeStatusButton.apply {
                    val imageResource = when (task.status) {
                        TO_DO, IN_PROGRESS -> R.drawable.ic_check_done
                        DONE -> R.drawable.ic_delete
                    }
                    setImageResource(imageResource)
                    setOnClickListener {
                        // Something
                    }
                }
            }
        }
    }
}