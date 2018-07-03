package fi.exa.cthulhuhelpper.persistence


import android.arch.persistence.room.TypeConverter
import fi.exa.cthulhuhelpper.model.CthulhuToken


class TokenTypeConverter {
    @TypeConverter
    fun tokenToString(token: CthulhuToken) = token.name

    @TypeConverter
    fun stringToToken(string: String) = CthulhuToken.valueOf(string)
}