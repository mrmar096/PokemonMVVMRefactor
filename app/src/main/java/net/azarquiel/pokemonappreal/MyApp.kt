package net.azarquiel.pokemonappreal
import android.app.Application
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by pacopulido on 14/11/17.
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        // Realm
        val config = RealmConfiguration.Builder()
                .name("pokemon.realm")
                .schemaVersion(1)
                .assetFile("pokemon.realm")
                .build()
        Realm.setDefaultConfiguration(config)

        // Para stetho de Realm
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build())
    }
}
