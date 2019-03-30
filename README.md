
[![](https://jitpack.io/v/ShabanKamell/ViewBadger.svg)](https://jitpack.io/#ShabanKamell/ViewBadger)

# ViewBager
###  Add `BADGE` to any Android view at runtime.

<img src="https://github.com/ShabanKamell/ViewBadger/blob/master/blob/master/raw/screenshot.png" height="500">

# Features

 - [ ] Optimized badge for TabLayout
 - [ ] Optimized badge for BottomNavigationView.
 - [ ] Add badge to any view.

# Installation
[![](https://jitpack.io/v/ShabanKamell/ViewBadger.svg)](https://jitpack.io/#ShabanKamell/ViewBadger)

```groovy
allprojects {
	repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}

dependencies {
	implementation 'com.github.ShabanKamell:ViewBadger:1.1.0'
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
```java
BadgeView badge = new ViewBadger().setupWithView(
                getView().findViewById(R.id.iv),
                getContext()
        );

        badge.setText("111");
        badge.setOnClickListener(v -> viewPager.setCurrentItem(index));
        badge.show(true);
```
## Credit
[jgilfelt/android-viewbadger](https://github.com/jgilfelt/android-viewbadger)



### See 'app' module for the full code.

# License

## Apache license 2.0
