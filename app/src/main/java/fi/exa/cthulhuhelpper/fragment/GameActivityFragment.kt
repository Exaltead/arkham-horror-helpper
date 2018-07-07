package fi.exa.cthulhuhelpper.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fi.exa.cthulhuhelpper.R
import fi.exa.cthulhuhelpper.injection.Injectable
import fi.exa.cthulhuhelpper.model.CthulhuToken
import fi.exa.cthulhuhelpper.viewmodel.TokenViewModel
import kotlinx.android.synthetic.main.fragment_token.*
import javax.inject.Inject


class GameActivityFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var tokenViewModel: TokenViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tokenViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(TokenViewModel::class.java)
        tokenViewModel.getToken().observe(this, Observer { token -> updateShownToken(token) })
        token_text.setOnClickListener { _ -> tokenViewModel.newToken()}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_token, container, false)
    }

    private fun updateShownToken(token: CthulhuToken?){
        token?.let { t -> token_text.text = t.stringName }
    }
}
