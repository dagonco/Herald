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

package com.dagonco.herald.feature.dialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.dagonco.herald.R
import com.dagonco.herald.Repository
import com.dagonco.herald.core.getFormatted
import com.dagonco.herald.feature.task.model.Priority
import com.dagonco.herald.feature.task.model.Priority.*
import com.dagonco.herald.feature.task.model.Task
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_add_task_dialog.*
import java.util.*

class AddTaskFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var onTaskStoredListener: OnTaskStoredListener? = null


    private var prioritySelected: Priority = LOW
    private var dateSelected = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_task_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lowPriorityButton.markPriorityStyleButton()
        initButtonsListeners()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnTaskStoredListener) {
            onTaskStoredListener = context
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateSelected.run {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        taskDateInputLayout.text =
                "${getString(R.string.task_date_input_layout_hint)}\n${Date(dateSelected.timeInMillis).getFormatted()}"
    }

    private fun initButtonsListeners() {
        changeStatusButton.setOnClickListener { showDatePicker() }
        lowPriorityButton.setOnClickListener { onPriorityClicked(LOW) }
        mediumPriorityButton.setOnClickListener { onPriorityClicked(MEDIUM) }
        highPriorityButton.setOnClickListener { onPriorityClicked(HIGH) }
        saveButton.setOnClickListener { storeTask() }
    }

    private fun onPriorityClicked(priority: Priority) {
        prioritySelected = priority

        lowPriorityButton.resetPriorityStyleButton()
        mediumPriorityButton.resetPriorityStyleButton()
        highPriorityButton.resetPriorityStyleButton()

        when (priority) {
            LOW -> lowPriorityButton
            MEDIUM -> mediumPriorityButton
            HIGH -> highPriorityButton
        }.markPriorityStyleButton()
    }

    private fun showDatePicker() {
        val dayOfMonth = dateSelected.get(Calendar.DAY_OF_MONTH)
        val month = dateSelected.get(Calendar.MONTH)
        val year = dateSelected.get(Calendar.YEAR)
        DatePickerDialog(requireContext(), this, year, month, dayOfMonth).show()
    }

    private fun storeTask() {
        val title = taskNameEditText.text?.toString() ?: ""
        Repository.storeTask(Task(title, dateSelected.timeInMillis, prioritySelected))
        onTaskStoredListener?.onTaskStored()
    }


    private fun MaterialButton.resetPriorityStyleButton() = run {
        strokeColor = ColorStateList.valueOf(Color.BLACK)
        setTextColor(Color.BLACK)
    }

    private fun MaterialButton.markPriorityStyleButton() = run {
        strokeColor = ColorStateList.valueOf(Color.MAGENTA)
        setTextColor(Color.MAGENTA)
    }

    interface OnTaskStoredListener {
        fun onTaskStored()
    }
}