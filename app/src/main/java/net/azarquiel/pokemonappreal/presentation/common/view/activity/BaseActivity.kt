package net.azarquiel.pokemonappreal.presentation.common.view.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import net.azarquiel.pokemonappreal.data.local.preferences.PokemonPreferences
import net.azarquiel.pokemonappreal.data.local.preferences.PokemonPreferencesImpl
import net.azarquiel.pokemonappreal.infraestructure.Navigator
import net.azarquiel.pokemonappreal.infraestructure.NavigatorImpl

/**
 * Created by mrmar on 10/1/18.
 */
abstract class BaseActivity : AppCompatActivity(){

    @LayoutRes abstract fun getContentView() :Int

    abstract fun bindData(contentView: Int)

    protected lateinit var navigator: Navigator

    protected lateinit var realm: Realm

    protected lateinit var pokemonPrefs: PokemonPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(getContentView())
        initializeComponents()
        bindData(getContentView())
        super.onCreate(savedInstanceState)
    }

    private fun initializeComponents() {
        navigator = NavigatorImpl(this)
        realm = Realm.getDefaultInstance()
        pokemonPrefs= PokemonPreferencesImpl(this)
    }

}