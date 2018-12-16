package fi.exa.cthulhuhelpper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import fi.exa.cthulhuhelpper.R
import fi.exa.cthulhuhelpper.adapter.ConfigAdapter
import fi.exa.cthulhuhelpper.model.Difficulty
import fi.exa.cthulhuhelpper.model.TokenConfigurationBuilder
import fi.exa.cthulhuhelpper.model.TokenConfigurationHolder
import fi.exa.cthulhuhelpper.viewmodel.TokenViewModel
import kotlinx.android.synthetic.main.fragment_config.view.*
import javax.inject.Inject


class TokenConfigFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ConfigAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tokenViewModel = ViewModelProviders.of(this, viewModelFactory).get(TokenViewModel::class.java)
        tokenViewModel.getTokenConfig()
                .observe(this, Observer { t -> t?.let {
                    c -> viewAdapter.updateAdapterValues(c.orderedTokenConfiguration(), asDifficulty(c)) } })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewManager = LinearLayoutManager(context)
        viewAdapter = ConfigAdapter({ t, v -> tokenViewModel.updateTokenConfig(t, v)},
                {difficulty -> tokenViewModel.setDifficulty(difficulty) })

        val view = inflater.inflate(R.layout.fragment_config, container, false)
        view.config_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return view
    }

    private fun asDifficulty(tokenConfigurationHolder: TokenConfigurationHolder): Difficulty? {
        return TokenConfigurationBuilder.deduceDifficulty(tokenConfigurationHolder)
    }
}