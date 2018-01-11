package net.azarquiel.pokemonappreal.presentation.common.view.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.pokemonappreal.data.local.preferences.PokemonPreferences
import net.azarquiel.pokemonappreal.data.local.preferences.PokemonPreferencesImpl
import net.azarquiel.pokemonappreal.infraestructure.Navigator
import net.azarquiel.pokemonappreal.infraestructure.NavigatorImpl

/**
 * Created by mrmar on 10/1/18.
 */
abstract class BaseActivity : AppCompatActivity(){

    @LayoutRes abstract fun getContentView() :Int

    @MenuRes protected open fun getMenu() : Int = -1

    abstract fun bindData(contentView: Int)

    protected lateinit var navigator: Navigator

    protected lateinit var realm: Realm

    protected lateinit var pokemonPrefs: PokemonPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(getContentView())
        setSupportActionBar(toolbar)
        initializeComponents()
        bindData(getContentView())
        super.onCreate(savedInstanceState)
    }

    private fun initializeComponents() {
        navigator = NavigatorImpl(this)
        realm = Realm.getDefaultInstance()
        pokemonPrefs= PokemonPreferencesImpl(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(getMenu()==-1)return true
        menuInflater.inflate(getMenu(), menu)
        return true
    }
}