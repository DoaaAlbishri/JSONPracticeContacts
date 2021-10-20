package com.example.jsonpracticecontacts

import com.google.gson.annotations.SerializedName

class UserDetails {

    var data: List<Datum>? = null

    class Datum {

        @SerializedName("name")
        var name: String? = null

        @SerializedName("location")
        var location: String? = null

        @SerializedName("mobile")
        var mobile: String? = null

        @SerializedName("email")
        var email: String? = null

        constructor(name: String?,location: String?,mobile: String?,email: String?) {
            this.name = name
            this.mobile = mobile
            this.email=email
            this.location=location
        }


    }
}