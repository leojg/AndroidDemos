package example.lgcode.ktpopularshows.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TVShow(
    val id: Int,
    @SerializedName("name")
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val image: String,
    @SerializedName("backdrop_path")
    val heroImage: String,
    @SerializedName("vote_average")
    val voteAverage: Float
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(image)
        parcel.writeString(heroImage)
        parcel.writeFloat(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TVShow> {
        override fun createFromParcel(parcel: Parcel): TVShow {
            return TVShow(parcel)
        }

        override fun newArray(size: Int): Array<TVShow?> {
            return arrayOfNulls(size)
        }
    }
}