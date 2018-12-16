package fi.exa.cthulhuhelpper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import fi.exa.cthulhuhelpper.R
import fi.exa.cthulhuhelpper.databinding.FragmentGameBinding
import fi.exa.cthulhuhelpper.model.CthulhuToken
import fi.exa.cthulhuhelpper.viewmodel.PlayerViewModel
import fi.exa.cthulhuhelpper.viewmodel.TokenViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import javax.inject.Inject


class GameActivityFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        tokenViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TokenViewModel::class.java)
        playerViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PlayerViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tokenViewModel.getToken().observe(this, Observer { token -> updateShownToken(token) })
        token_text.setOnClickListener {
            if(!tokenViewModel.newToken()){
                DifficultyDialog().show(fragmentManager, "Difficulty")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game,
                container, false)
        binding.viewmodel = playerViewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    private fun updateShownToken(token: CthulhuToken?){
        token?.let { t -> token_text.text = t.stringName }
    }
}
