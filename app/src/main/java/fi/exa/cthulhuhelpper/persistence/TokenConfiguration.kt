package fi.exa.cthulhuhelpper.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import fi.exa.cthulhuhelpper.model.CthulhuToken

@Entity(tableName = "token_configuration")
class TokenConfiguration() {
    @PrimaryKey
    @TypeConverters(TokenTypeConverter::class)
    var token: CthulhuToken = CthulhuToken.FAILURE

    @ColumnInfo(name = "count")
    var count: Int = 0

    constructor(givenToken: CthulhuToken, givenCount: Int): this(){
        token = givenToken
        count = givenCount
    }
}