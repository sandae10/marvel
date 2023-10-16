// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

/*def getProps(String propName) {
    def propsFile = rootProject.file('local.properties')
    if (propsFile.exists()) {
        def props = new Properties()
        props.load(new FileInputStream(propsFile))
        return props[propName]
    } else {
        return ""
    }
}+/
