package net.azarquiel.pokemonappreal.data.local.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

/**
 * Created by pacopulido on 26/10/17.
 */
open class AbilityRealm(
        @PrimaryKey
        var ability_id: Long = 0,
        var name: String = "",
        var description: String = ""
) : RealmObject()

open class PokemonAbilitiesRealm(
        @PrimaryKey
        var pokemon_ability_id: Long = 0,
        var pokemon_id: Long = 0,
        var ability_id: Long = 0
) : RealmObject()

open class PokemonRealm(
        @PrimaryKey
        var pokemon_id: Long = 0,
        var name: String = ""
) : RealmObject(), Serializable

open class PokemonTypesRealm(
        @PrimaryKey
        var pokemon_type_id: Long = 0,
        var pokemon_id: Long = 0,
        var type_id: Long = 0
) : RealmObject()

open class TypeRealm(
        @PrimaryKey
        var type_id: Long = 0,
        var name: String = ""
) : RealmObject()


