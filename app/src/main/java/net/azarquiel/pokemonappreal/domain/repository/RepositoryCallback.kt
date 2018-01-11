package net.azarquiel.pokemonappreal.domain.repository

/**
 * Created by mrmar on 11/1/18.
 */
interface RepositoryCallback<in T>{
    fun success(data :T?)
    fun fail(t : Throwable)
}