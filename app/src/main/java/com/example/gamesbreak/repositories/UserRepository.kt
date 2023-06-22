package com.example.gamesbreak.repositories

import com.example.gamesbreak.data.User


object UserRepository: RepositoryInterface<User> {

    private val users = mutableListOf<User>()

    init {
        users.add(User(1504L, "BRIAN_BAYARRI", "abc123", "Brian", "Bayarri", 1000.50, "2022/12/10"))
        users.add(User(2802L, "AHOZ", "lock_password", "Aylen", "Hoz", 200.50, "2021/01/11"))
        users.add(User(1510L, "Diegote", "@12345", "Diego", "Gonzales", 12.0, "2018/04/15"))
        users.add(User(1996L, "Bmicaela96", "Azul1104", "Micaela" ,"Borquez", 200.50, "2023/06/02"))
    }

    override fun getByID(id: String): User? {
        for(user in users) {
            if (user.nickName == id) {
                return user
            }
        }
        return null
    }

    fun getByIdNumeric(id: Long): User? {
        for(user in users) {
            if(user.id == id) return user
        }

        return null
    }

    override fun getAll(): List<User> {
        return users
    }

    override fun removeItem(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun addItem(item: User): Boolean {
        TODO("Not yet implemented")
    }

}