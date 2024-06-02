package practice.omdb.junseokseo.ui.model


sealed class UiTransaction

class HomeFragmentEvent : UiTransaction()

class DetailFragmentEvent(
    val id: String,
) : UiTransaction()
