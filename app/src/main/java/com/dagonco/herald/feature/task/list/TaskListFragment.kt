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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.dagonco.herald.R
import com.dagonco.herald.Repository
import com.dagonco.herald.feature.task.model.Status
import com.dagonco.herald.feature.task.model.Task
import kotlinx.android.synthetic.main.fragment_task_list.*

class TaskListFragment : Fragment() {

    companion object {
        const val BOARD_SECTION = "board_section"
    }

    enum class Board {
        TO_DO, IN_PROGRESS, DONE
    }

    private lateinit var boardSection: Board
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)
        boardSection = arguments?.getSerializable(BOARD_SECTION) as Board
        return rootView
    }

    override fun onStart() {
        super.onStart()
        taskAdapter = TaskAdapter(getSectionTasks())
        initializeRecyclerView()
    }

    private fun getSectionTasks(): List<Task> {
        val filterStatus = when (boardSection) {
            Board.TO_DO -> Status.TO_DO
            Board.IN_PROGRESS -> Status.IN_PROGRESS
            Board.DONE -> Status.DONE
        }
        return Repository.getTasks().filter { it.status == filterStatus }
    }

    private fun initializeRecyclerView() {

        with(recyclerView) {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayout.HORIZONTAL
                )
            )
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }
}