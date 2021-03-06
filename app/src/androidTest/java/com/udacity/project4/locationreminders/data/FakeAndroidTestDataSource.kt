package com.udacity.project4.locationreminders.data

import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result

//Use FakeAndroidTestDataSource that acts as a test double to the LocalDataSource
class FakeAndroidTestDataSource(var reminders: MutableList<ReminderDTO>? = mutableListOf()) :
    ReminderDataSource {

    override suspend fun getReminders(): Result<List<ReminderDTO>> {
        reminders?.let { return Result.Success((it)) }
        return Result.Error(
            message = "Reminder not found",
            statusCode = 123
        )
    }

    override suspend fun saveReminder(reminder: ReminderDTO) {
        reminders?.add(reminder)
    }

    override suspend fun deleteReminder(reminder: ReminderDTO) {
        reminders?.remove(reminder)
    }

    override suspend fun getReminder(id: String): Result<ReminderDTO> {
        reminders?.firstOrNull { it.id == id }?.let { return  Result.Success(it) }
        return Result.Error(
            message = "Reminder not found",
            statusCode = 123
        )
    }

    override suspend fun deleteAllReminders() {
        reminders?.clear()
    }


}
