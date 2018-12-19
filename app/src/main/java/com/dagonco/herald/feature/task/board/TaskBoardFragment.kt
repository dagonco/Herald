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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dagonco.herald.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_task_board.*

class TaskBoardFragment : Fragment(), ViewPager.OnPageChangeListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initTabLayout()
    }

    private fun initTabLayout() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                viewPager.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.run { viewPager.currentItem = position }
                viewPager.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
            }
        })
    }

    private fun initViewPager() {
        val fragmentManager = fragmentManager
        if (fragmentManager != null) {
            viewPager.run {
                adapter = TaskBoardPagerAdapter(fragmentManager)
                addOnPageChangeListener(this@TaskBoardFragment)
            }
        }
    }

    override fun onPageSelected(position: Int) {
        tabLayout.getTabAt(position)?.select()
    }

    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
}
