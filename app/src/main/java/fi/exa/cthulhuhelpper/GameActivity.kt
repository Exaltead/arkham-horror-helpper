package fi.exa.cthulhuhelpper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import fi.exa.cthulhuhelpper.fragment.GameActivityFragment
import fi.exa.cthulhuhelpper.fragment.TokenConfigFragment

import kotlinx.android.synthetic.main.activity_game.*
import javax.inject.Inject

class GameActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var  dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector()  = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_game)
        setSupportActionBar(toolbar)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.token_container, GameActivityFragment())
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_config -> setConfigFragment()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setConfigFragment(): Boolean{
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.token_container, TokenConfigFragment())
                .addToBackStack(null)
                .commit()
        return true
    }
}
