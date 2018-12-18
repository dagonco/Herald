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

package com.dagonco.herald

import com.dagonco.herald.feature.task.model.Priority
import com.dagonco.herald.feature.task.model.Status
import com.dagonco.herald.feature.task.model.Task

object Repository {

    private var taskList = mutableListOf<Task>().apply { addAll(setDefaultTasks()) }

    fun getTasks() = taskList
    fun storeTask(task: Task) = taskList.add(task)

    private fun setDefaultTasks(): List<Task> {
        return mutableListOf<Task>().apply {
            add(
                Task(
                    "Salir a comprar fresas",
                    1545519600000,
                    Priority.LOW,
                    Status.TO_DO
                )
            )
            add(
                Task(
                    "Envolver los regalos de Navidad",
                    1545260400000,
                    Priority.MEDIUM,
                    Status.IN_PROGRESS
                )
            )
            add(
                Task(
                    "Comprar los regalos de Navidad",
                    1545174000000,
                    Priority.HIGH,
                    Status.DONE
                )
            )
            add(
                Task(
                    "Hacer tarta de fresa",
                    1544828400000,
                    Priority.LOW,
                    Status.TO_DO
                )
            )
            add(
                Task(
                    "Ir a cortarme el pelo",
                    1545519600000,
                    Priority.MEDIUM,
                    Status.TO_DO
                )
            )
            add(
                Task(
                    "Enviar postales Navideñas",
                    1544828400000,
                    Priority.HIGH,
                    Status.IN_PROGRESS
                )
            )
        }
    }
}