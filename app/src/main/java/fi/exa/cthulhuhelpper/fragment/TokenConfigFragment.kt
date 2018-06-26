package fi.exa.cthulhuhelpper.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fi.exa.cthulhuhelpper.R
import fi.exa.cthulhuhelpper.adapter.ConfigAdapter
import fi.exa.cthulhuhelpper.viewmodel.TokenViewModel
import kotlinx.android.synthetic.main.fragment_config.view.*


class TokenConfigFragment: Fragment() {

    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ConfigAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        tokenViewModel = ViewModelProviders.of(activity).get(TokenViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewManager = LinearLayoutManager(context)
        viewAdapter = ConfigAdapter { t, v -> tokenViewModel.updateTokenConfig(t, v)}
        tokenViewModel.getTokenConfig()
                .observe(this, Observer { t -> t?.let {
                    c -> viewAdapter.updateAdapterValues(c.tokens) } })

        val view = inflater.inflate(R.layout.fragment_config, container, false)
        view.config_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        return view
    }
}