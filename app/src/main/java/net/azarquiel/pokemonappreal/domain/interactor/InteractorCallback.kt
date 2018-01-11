package net.azarquiel.pokemonappreal.domain.interactor

/**
 * Created by mrmar on 11/1/18.
 */
interface InteractorCallback<in T>{
    fun before(){}
    fun success(data :T?)
    fun fail(t : Throwable)
    fun finish(){}
}