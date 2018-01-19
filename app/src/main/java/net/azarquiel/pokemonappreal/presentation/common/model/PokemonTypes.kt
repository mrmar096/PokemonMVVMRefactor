package net.azarquiel.pokemonappreal.presentation.common.model

/**
 * Created by mrmar on 14/1/18.
 */
enum class PokemonTypes(mName: String,mOrder:Int) {
    NORMAL("Normal",0),
    FIGHTING("Fighting",1),
    POISON("Poison",12),
    FLYING("Flying",3),
    BUG("Bug",4),
    GROUND("Ground",5),
    ROCK("Rock",6),
    FIRE("Fire",7),
    GHOST("Ghost",8),
    STEEL("Steel",9),
    WATER("Water",10),
    ELECTRIC("Electric",11),
    GRASS("Grass",2),
    ICE("Ice",13),
    PSYCHIC("Psychic",14),
    DARK("Dark",15),
    FAIRY("Fairy",16),
    DRAGON("Dragon",17),
    NONE("NONE",18);
    val nameType: String = mName

    val order: Int = mOrder
}