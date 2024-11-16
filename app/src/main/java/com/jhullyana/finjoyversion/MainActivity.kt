package com.jhullyana.finjoyversion

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.jhullyana.finjoyversion.data.GastoDatabase.Companion.abrirBancoDeDados
import com.jhullyana.finjoyversion.data.LocalRepository
import com.jhullyana.finjoyversion.data.RemoteRepository
import com.jhullyana.finjoyversion.ui.theme.Gastos.GastoNavHost
import com.jhullyana.finjoyversion.ui.theme.Gastos.GastoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        var mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id_123")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "example_item")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        enableEdgeToEdge()

        val db = abrirBancoDeDados(this)
        val localRepository = LocalRepository(db.gastoDao())
        val remoteRepository = RemoteRepository()

        val viewModel: GastoViewModel

        // Função para verificar se há internet
        fun isConnectedToInternet(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

            return networkCapabilities != null && networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        if (isConnectedToInternet(this)) {
            // Se houver conexão com a internet, use o remoteRepository
            viewModel = GastoViewModel(remoteRepository)
        } else {
            // Se não houver conexão, use o localRepository
            viewModel = GastoViewModel(localRepository)
        }

        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            GastoNavHost(viewModel = viewModel, drawerState = drawerState)
                }
            }
        }

