package com.example.hw4l7.di

import android.content.Context
import com.example.hw4l7.di.modules.MainApiModule
import com.example.hw4l7.di.modules.PreferencesModule
import com.example.hw4l7.ui.cart.CartActivity
import com.example.hw4l7.ui.catalog.CatalogActivity
import com.example.hw4l7.ui.checkout.CheckoutActivity
import com.example.hw4l7.ui.detailed.DetailedActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        PreferencesModule::class,
        MainApiModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(activity: CatalogActivity)
    fun inject(activity: DetailedActivity)
    fun inject(activity: CartActivity)
    fun inject(activity: CheckoutActivity)
}