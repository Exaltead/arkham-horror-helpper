package fi.exa.cthulhuhelpper.fragment


import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import fi.exa.cthulhuhelpper.R
import fi.exa.cthulhuhelpper.model.Difficulty
import fi.exa.cthulhuhelpper.viewmodel.TokenViewModel
import javax.inject.Inject

class DifficultyDialog: DialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        AndroidSupportInjection.inject(this)
        val tokenViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TokenViewModel::class.java)
        return activity?.let { fragmentActivity ->
            val builder = AlertDialog.Builder(fragmentActivity)
            val values = Difficulty.values().map { it.name }.toTypedArray()
            builder.setTitle(getString(R.string.pick_difficulty))
                    .setItems(values) { _, index ->
                        tokenViewModel.setDifficulty(Difficulty.valueOf(values[index]))
                    }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}