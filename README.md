# Tiny drawable tint compatibility library

* use for tinting `TextView`s `drawableLeft|drawableRight|...` resources
* use white drawables for best results

## DEPENDENCY
`your-app/build.gradle`


```gradle
// NOTE: this has to be OUTSIDE the *buildscript* section

repositories {
    // ...
    maven {
        url "https://raw.github.com/zoltanbalint/TextViewDrawableCompat/mvn"
    }
}

// ...

dependencies {
    // ...
    compile 'com.zoltanbalint:textview-drawable-tint-compat:1.0.0'
}
```

## USAGE

**IMPORTANT: use only _WHITE_ drawables for correct tinting**

```xml
<!-- ... -->
    <com.zoltanbalint.textviewcompat.TextViewDC
        android:drawableTop="@drawable/abc_ic_menu_cut_mtrl_alpha"
        app:drawableTint="@color/my_green"
        android:text="Hello World!"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
<!-- ... -->
```