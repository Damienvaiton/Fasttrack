package com.iut.app.android.fasttrack.model.dataclass.schedule

data class Location(
    val country: String,
    val lat: String,
    val locality: String,
    val long: String
) {

    companion object {
        private var lastContactId = 0
        fun createContactsList(numContacts: Int): ArrayList<Location> {
            val contacts = ArrayList<Location>()
            for (i in 1..numContacts) {
                contacts.add(Location("Country " + ++lastContactId, "lat " + ++lastContactId, "locality " + ++lastContactId, "long " + ++lastContactId))
            }
            return contacts
        }
    }
}