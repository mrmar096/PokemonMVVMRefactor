package net.azarquiel.pokemonappreal.presentation.pokemondetail.view

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import net.azarquiel.pokemonappreal.R
import net.azarquiel.pokemonappreal.data.local.realm.repository.local.PokemonRepositoryImpl
import net.azarquiel.pokemonappreal.databinding.ActivityPokemonDetailBinding
import net.azarquiel.pokemonappreal.domain.interactor.local.PokemonInteractorImpl
import net.azarquiel.pokemonappreal.presentation.common.view.activity.BaseActivity
import net.azarquiel.pokemonappreal.presentation.pokemondetail.viewmodel.PokemonDetailViewmodel
import net.azarquiel.pokemonappreal.presentation.pokemonmain.view.MainActivity

class PokemonDetailActivity : BaseActivity() {


    override fun getContentView(): Int  = R.layout.activity_pokemon_detail

    private var pokemon_id: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        pokemon_id = intent.getLongExtra("pokemon_id",-1)
        super.onCreate(savedInstanceState)
    }

    override fun bindData(contentView: Int) {
        val binding : ActivityPokemonDetailBinding = DataBindingUtil.setContentView(this,contentView)
        val viewmodel = PokemonDetailViewmodel(navigator, pokemonPrefs, PokemonInteractorImpl(PokemonRepositoryImpl(realm)))
        binding.viewmodel = viewmodel
        viewmodel.fillPokemonView(pokemon_id)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK, Intent(this, MainActivity::class.java))
        super.onBackPressed()
    }

}
