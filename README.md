
[![](https://jitpack.io/v/ShabanKamell/ViewBadger.svg)](https://jitpack.io/#ShabanKamell/ViewBadger)

# ViewBadger
###  Add `BADGE` to any Android view at runtime.

<img src="https://github.com/ShabanKamell/ViewBadger/blob/master/blob/master/raw/screenshot.png" height="500">

# Features

 - [ ] Optimized badge for TabLayout
 - [ ] Optimized badge for BottomNavigationView.
 - [ ] Add badge to any view.

# Installation
### Last Version [![](https://jitpack.io/v/ShabanKamell/ViewBadger.svg)](https://jitpack.io/#ShabanKamell/ViewBadger)

```groovy
allprojects {
	repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}

dependencies {
	implementation 'com.github.ShabanKamell:ViewBadger:LAST.VERSION'
}
```
## Usage

#### BottomNavigationView
```java
BadgeView badge = new ViewBadger().setupWithViewBottomNavigation(
                bottomNavigationView,
                1,
                this
        );

        badge.setText("1111");
        badge.show(true);
```

#### TabLayout
```java
BadgeView badge = new ViewBadger().setupWithTabLayout(
                tabLayout,
                index,
                TabLayoutMode.WITH_TITLE_AND_ICON,
                getContext()
        );

        badge.setText("111");
        badge.setOnClickListener(v -> viewPager.setCurrentItem(index));
        badge.show(true);
```

#### Any View

#### Wrap the view with BadgeTarget

```xml
<com.sha.viewbadger.BadgeTarget
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content">

                    <ImageView
                        android:id="@+id/ivSquareLarge"
                        android:layout_width="100dp"
                        android:layout_height="100dp"
                        android:layout_gravity="center"
                        android:background="@drawable/btn_background"
                        />

                </com.sha.viewbadger.BadgeTarget>
```
#### Show the badge

```java
BadgeView badge = new ViewBadger().setupWithView(
                getView().findViewById(view),
                R.id.root,
                isRound,
                getContext()
        );

        badge.setText("33");
        badge.setOnClickListener(v -> viewPager.setCurrentItem(index));
        badge.setPosition(position);
        badge.show(true);
```
## Credit
[jgilfelt/android-viewbadger](https://github.com/jgilfelt/android-viewbadger)



### See 'app' module for the full code.

# License

## Apache license 2.0
