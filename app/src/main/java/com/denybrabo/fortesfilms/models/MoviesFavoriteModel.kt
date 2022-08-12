package com.denybrabo.fortesfilms.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "favorite_table")
class MoviesFavoriteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var title: String,
    var year: String,
    var rated: String,
    var released: String,
    var runtime: String,
    var genre: String,
    var director: String,
    var writer: String,
    var actors: String,
    var plot: String,
    var poster: String
): Parcelable, Serializable {
}