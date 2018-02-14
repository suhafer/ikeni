[![](https://jitpack.io/v/suhafer/ikeni.svg)](https://jitpack.io/#suhafer/ikeni)

# Ikeni
Ikeni is simple app review utility library that implement the right way to ask users to review your app.

## Installation
Ikeni can installed by adding the following dependency to your build.gradle file:
```
repositories {
  jcenter()
  maven { url "https://jitpack.io" }
}
```
```
dependencies {
  compile 'com.github.suhafer:ikeni:latest-release'
}
```
## Usage
###### Basic
You can start using ikeni by access it from your activity/fragment.
```
Ikeni.can.showExperienceDialog(this)
```
Then implement `FeedbackInterface` to receive feedback and handle it.
```
public class MainActivity extends AppCompatActivity implements FeedbackInterface
{
...
    @Override
    public void feedbackListener (String feedback)
    {
        //handle the feedback
    }
...
```

You can handle rate/feedback dialog by yourself with implement our `DialogInterface` & handle it result with our tag as identifier.
```
public class MainActivity extends AppCompatActivity implements DialogInterface
{
...
    @Override
    public void acceptListener (int tag)
    {
        if (tag == Ikeni.TAG_FEEDBACK) {
            //do something
        }
        else if (tag == Ikeni.TAG_RATE) {
            //do something
        }
    }

    @Override
    public void refuseListener (int tag)
    {
        if (tag == Ikeni.TAG_FEEDBACK) {
            //do something
        }
        else if (tag == Ikeni.TAG_RATE) {
            //do something
        }
    }
```
Please note, if you use `DialogInterface` then you can't access our default feedback form.


###### Available Methods
Ikeni
* `showExperienceDialog(Context context)`
* `goToPlayStore(Context context)`

FeedbackInterface
* `feedbackListener (String feedback)`

DialogInterface
* `acceptListener (int tag)`
* `refuseListener (int tag)`
---
