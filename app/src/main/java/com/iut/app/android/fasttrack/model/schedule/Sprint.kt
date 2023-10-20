package com.iut.app.android.fasttrack.model.schedule

data class Sprint(
    val date: String,
    val time: String
) {

    companion object {
        private var lastContactId = 0
        fun createContactsList(numContacts: Int): ArrayList<Sprint> {
            val contacts = ArrayList<Sprint>()
            for (i in 1..numContacts) {
                contacts.add(Sprint("Pilote " + ++lastContactId, "lat " + ++lastContactId))
            }
            return contacts
        }
    }
}