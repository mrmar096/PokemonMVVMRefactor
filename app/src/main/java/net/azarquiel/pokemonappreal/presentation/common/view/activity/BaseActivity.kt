package net.azarquiel.pokemonappreal.presentation.common.view.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by mrmar on 10/1/18.
 */
abstract class BaseActivity : AppCompatActivity(){

    @LayoutRes abstract fun getContentView() :Int

    @MenuRes protected open fun getMenu() : Int = -1

    abstract fun bindData(contentView: Int)

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(getContentView())
        setSupportActionBar(toolbar)
        bindData(getContentView())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(getMenu()==-1)return true
        menuInflater.inflate(getMenu(), menu)
        return true
    }
}