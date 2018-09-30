# CodeEditor [![](https://jitpack.io/v/ahmadaghazadeh/CodeEditor.svg)](https://jitpack.io/#ahmadaghazadeh/CodeEditor)

This is a text/code editor meant for integration as a modular component of the overall UI.
The aim is to provide a powerful editor that can be used just like any other View.

Ace text editor has been used for this purpose because it is feature-rich, fast, and easy to modify and embed in applications.


Please note that this library is currently supported on android API 15 and above.

Integration with existing project
---

### Setup

##### build.gradle (project)
```groovy
allprojects {
    repositories {
        ...
        maven {
            url 'https://jitpack.io'
        }
    }
}
```

#### build.gradle (app)
```groovy
dependencies {
    ...
    compile 'com.github.ahmadaghazadeh:CodeEditor:1.0.0'
}
```

### Basic Usage
#### XML
```xml
...
<com.susmit.aceeditor.AceEditor
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/editor"/>
...
```

#### Java
Demo Activity:
```java
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editor = findViewById(R.id.editor);
        
        //call this to set up themes or modes at time of creation of view.
        //If you are setting the theme or mode through another view's action,
        //call setTheme and/or setMode directly
        editor.setOnLoadedEditorListener(new OnLoadedEditorListener() {
            @Override
            public void onCreate() {
                editor.setTheme(AceEditor.Theme.TERMINAL);
                editor.setMode(AceEditor.Mode.C_Cpp);
            }
        });
        
        //Since a WebView is used for the content, you need to set the following listener to process the text
        //It is also used to retrive other values, such as selected text or number of lines
        editor.setResultReceivedListener(new ResultReceivedListener() {
            @Override
            public void onReceived(String text, int FLAG_VALUE) {
                if(FLAG_VALUE == AceEditor.Request.VALUE_TEXT)
                {
                    Toast.makeText(MainActivity.this, "Typed text:\n\n" + text, Toast.LENGTH_SHORT).show();
                }
            }
        }); 
    }
}
```