package com.np.retrofit.utils.base_pkgs

import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T: ViewBinding> Fragment.viewBinding() = FragmentViewBindDelegate(T::class.java, this)

@MainThread
class FragmentViewBindDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    fragment: Fragment
): ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment){
            viewLifecycleOwner -> viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    binding = null
                }
            })
        }
    }

    @Suppress("kotlin:S6530","UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if(binding == null) {
            binding = bindingClass.getMethod("bind", View::class.java).invoke(null, thisRef.requireView()) as T
        }
        return binding!!
    }

}
