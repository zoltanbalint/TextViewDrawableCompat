# Tiny drawable tint compatibility library

* use for tinting `TextView`s `drawableLeft|drawableRight|...` resources
* separate tint colors can be specified by using `app:drawableTintLeft="@color/my_color1"`, `app:drawableTintRight="@color/my_color2"`
* the default fallback color will be the one specified in `app:drawableTint="@color/my_default_color"`
* use white drawables for best results

## LATEST VERSION: 1.1.1


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
    compile 'com.zoltanbalint:textview-drawable-tint-compat:1.1.1'
}
```

## USAGE

**IMPORTANT: use only _WHITE_ drawables for correct tinting**

```xml
<!-- ... -->
    <com.zoltanbalint.textviewcompat.TextViewDC
        android:drawableTop="@drawable/abc_ic_menu_cut_mtrl_alpha"
        android:drawableLeft="@drawable/abc_ic_menu_cut_mtrl_alpha"
        app:drawableTint="@color/my_green"
        app:drawableTintLeft="@color/my_red"
        android:text="Hello World!"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <com.zoltanbalint.textviewcompat.CheckedTextViewDC
        android:drawableTop="@drawable/abc_ic_menu_cut_mtrl_alpha"
        android:drawableLeft="@drawable/abc_ic_menu_cut_mtrl_alpha"
        app:drawableTint="@color/my_green"
        app:drawableTintLeft="@color/my_red"
        android:text="Hello World!"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <com.zoltanbalint.textviewcompat.EditTextDC
        android:drawableTop="@drawable/abc_ic_menu_cut_mtrl_alpha"
        android:drawableLeft="@drawable/abc_ic_menu_cut_mtrl_alpha"
        app:drawableTint="@color/my_green"
        app:drawableTintLeft="@color/my_red"
        android:text="Hello World!"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

<!-- ... -->
```

test line
