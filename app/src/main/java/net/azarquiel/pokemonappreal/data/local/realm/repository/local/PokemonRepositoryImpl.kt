package net.azarquiel.pokemonappreal.data.local.realm.repository.local

import io.realm.OrderedCollectionChangeSet
import io.realm.Realm
import io.realm.RealmResults
import net.azarquiel.pokemonappreal.data.local.realm.model.*
import net.azarquiel.pokemonappreal.domain.repository.RepositoryCallback
import net.azarquiel.pokemonappreal.domain.repository.exception.PokemonResultsException
import net.azarquiel.pokemonappreal.domain.repository.local.PokemonRepository

/**
 * Created by mrmar on 11/1/18.
 */
/**
 * Visitar Link Queries para hacer BBDD relacionales
 * https://realm.io/docs/java/latest
 */
class PokemonRepositoryImpl(val realm: Realm) : PokemonRepository {

    override fun searchTypesByIdPokemon(id: Long, callback: RepositoryCallback<MutableList<String>>) {
        realm.where(PokemonTypesRealm::class.java).equalTo("pokemon_id",id).findAll().forEach {
            realm.where(TypeRealm::class.java).equalTo("type_id",it.pokemon_type_id).findAll().addChangeListener {
                t: RealmResults<TypeRealm>?, _: OrderedCollectionChangeSet? ->
                if(t == null)
                    callback.fail(PokemonResultsException("Los resultados han devuelto null value"))
                else
                    callback.success(t.map{ it.name }.toMutableList())
                t?.removeAllChangeListeners()
            }
        }
    }

    override fun searchAbilitiesByIdPokemon(id: Long, callback: RepositoryCallback<MutableList<String>>) {
        realm.where(PokemonAbilitiesRealm::class.java).equalTo("pokemon_id",id).findAll().forEach {
            realm.where(AbilityRealm::class.java).equalTo("ability_id",it.ability_id).findAll().addChangeListener {
                t: RealmResults<AbilityRealm>?, _: OrderedCollectionChangeSet? ->
                if(t == null)
                    callback.fail(PokemonResultsException("Los resultados han devuelto null value"))
                else
                    callback.success(t.map{ it.name }.toMutableList())
                t?.removeAllChangeListeners()
            }
        }
    }

    override fun getPokemonById(id: Long, callback: RepositoryCallback<PokemonRealmModel>) {
        val pokemonRealm = realm.where(PokemonRealm::class.java).equalTo("pokemon_id", id).findFirst()
        var pokemonTypes = mutableListOf<String>()
        var pokemonAbilities = mutableListOf<String>()
        if (pokemonRealm == null){
            callback.fail(PokemonResultsException("Los resultados han devuelto null value"))
            return
        }

        realm.where(PokemonTypesRealm::class.java).equalTo("pokemon_id", id).findAll().forEach {
            val results=realm.where(TypeRealm::class.java).equalTo("type_id", it.type_id).findAll()
            if (results == null){ callback.fail(PokemonResultsException("Los resultados han devuelto null value")); return}
            pokemonTypes.addAll(results.map { it.name })
        }
        realm.where(PokemonAbilitiesRealm::class.java).equalTo("pokemon_id", id).findAll().forEach {
            val results=realm.where(AbilityRealm::class.java).equalTo("ability_id", it.ability_id).findAll()
            if (results == null){ callback.fail(PokemonResultsException("Los resultados han devuelto null value")); return}
            pokemonAbilities.addAll(results.map { it.name })
        }
        callback.success(
                PokemonRealmModel(id,pokemonRealm.name,pokemonAbilities,pokemonTypes)
        )
    }
}