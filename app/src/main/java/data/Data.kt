package data

import com.google.firebase.database.FirebaseDatabase


object DataHelper {

    init {
        val database = FirebaseDatabase.getInstance().reference
        database.child("currentId").setValue(1)
    }

    // TODO: 02.04.2021 add fetch
    fun data(): MutableList<TimeTableInfo> {
        return mutableListOf(
            TimeTableInfo(
                "вторник",
                "2 апреля",
                mutableListOf(
                    SubjectsInfo(
                        name = "Матеша",
                        time = "11:40",
                        description = "Решить интеграл"
                    ),
                    SubjectsInfo(
                        name = "СМПМ",
                        time = "11:40",
                        description = "Запостить мем про интеграл"
                    ),
                )
            ),
            TimeTableInfo(
                "среда",
                "3 апреля",
                mutableListOf(
                    SubjectsInfo(
                        name = "Жизнь",
                        time = "15:00",
                        description = "Сходить в бар"
                    ),
                )
            ),
            TimeTableInfo(
                "пятница",
                "4 апреля",
                mutableListOf(
                    SubjectsInfo(
                        name = "Жизнь",
                        time = "15:00",
                        description = "Сходить в бар"
                    ),
                )
            ),
            TimeTableInfo(
                "суббота",
                "5 апреля",
                mutableListOf(
                    SubjectsInfo(
                        name = "Жизнь",
                        time = "15:00",
                        description = "Сходить в бар"
                    ),
                    SubjectsInfo(
                        name = "Жизнь",
                        time = "16:00",
                        description = "Сходить в бар"
                    ),
                    SubjectsInfo(
                        name = "Жизнь",
                        time = "17:00",
                        description = "Сходить в бар"
                    ),
                )
            ),
        )
    }


    // TODO: 03.04.2021 fetch
    fun groups(): List<Group> {
        return listOf(
            Group("W3160", listOf()),
            Group("W3105", listOf()),
            Group("W3165", listOf()),
            Group("Дотеры", listOf()),
            Group("Возможно", listOf()),
        )
    }
}

data class TimeTableInfo(
    val dayName: String,
    val date: String,
    val projects: MutableList<SubjectsInfo>
)

data class SubjectsInfo(
    val name: String,
    val time: String,
    val description: String
)

data class Group(
    val name: String,
    val users: List<User>
)

data class User(
    val name: String,
    val id: String
)
