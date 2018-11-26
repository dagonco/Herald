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

package com.dagonco.herald.feature.task.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dagonco.herald.feature.task.list.TaskListFragment

class TaskBoardPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int = 3

    override fun getItem(item: Int): Fragment {

        val board = when (item) {
            0 -> TaskListFragment.Board.TO_DO
            1 -> TaskListFragment.Board.IN_PROGRESS
            2 -> TaskListFragment.Board.DONE
            else -> TaskListFragment.Board.UNKNOWN
        }

        val fragment = TaskListFragment()
        fragment.arguments = Bundle().apply {
            putSerializable(TaskListFragment.BOARD_SECTION, board)
        }
        return fragment
    }
}
