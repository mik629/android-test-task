package com.github.kinopoisk.app.presentation.ui

import android.content.Context
import android.os.Bundle
import com.github.kinopoisk.app.App
import com.github.kinopoisk.app.R
import com.github.kinopoisk.app.di.DaggerAppPresenterComponent
import com.github.kinopoisk.app.presentation.mvp.AppPresenter
import moxy.MvpAppCompatActivity
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class AppActivity : MvpAppCompatActivity(), MvpView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @InjectPresenter
    lateinit var appPresenter: AppPresenter

    private val navigator = SupportAppNavigator(this, R.id.container)

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    @ProvidePresenter
    internal fun provideAppPresenter(): AppPresenter =
            DaggerAppPresenterComponent.builder()
                    .appComponent(App.appComponent)
                    .build()
                    .presenter

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity)

        if (savedInstanceState == null) {
            appPresenter.coldStart()
        }
    }

    override fun attachBaseContext(newBase: Context) {
        App.appComponent.inject(this)
        super.attachBaseContext(newBase)
    }
}
