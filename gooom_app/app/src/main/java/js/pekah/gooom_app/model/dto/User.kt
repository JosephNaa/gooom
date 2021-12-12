package js.pekah.gooom_app.model.dto

data class User(
    var id: String,
    var password: String,
    var email: String
) {
    constructor(): this("", "")
    constructor(id: String, password: String): this(id, password, "")
}