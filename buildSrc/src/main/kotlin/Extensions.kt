import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import kotlin.math.pow

val Project.minSdk: Int
    get() = intProperty("minSdk")

val Project.targetSdk: Int
    get() = intProperty("targetSdk")

val Project.compileSdk: Int
    get() = intProperty("compileSdk")

val Project.buildTools: String
    get() = stringProperty("buildTools")

val Project.appId: String
    get() = stringProperty("appId")

val Project.versionName: String
    get() = stringProperty("VERSION_NAME")

val Project.versionCode: Int
    get() = versionName
        .takeWhile { it.isDigit() || it == '.' }
        .split('.')
        .map { it.toInt() }
        .reversed()
        .sumByIndexed { index, unit ->
            // 1.2.3 -> 102030
            (unit * 10.0.pow(2 * index + 1)).toInt()
        }

private fun Project.intProperty(name: String): Int {
    return (property(name) as String).toInt()
}

private fun Project.stringProperty(name: String): String {
    return property(name) as String
}

private inline fun <T> List<T>.sumByIndexed(selector: (Int, T) -> Int): Int {
    var index = 0
    var sum = 0
    for (element in this) {
        sum += selector(index++, element)
    }
    return sum
}

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? {
    return add("testImplementation", dependencyNotation)
}

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? {
    return add("androidTestImplementation", dependencyNotation)
}

private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? {
    return add("implementation", dependencyNotation)
}

//添加默认选项 所有module都需要依赖
fun DependencyHandler.addDefaultDependencies() {
    implementation(Libs.ANDROIDX_KOTLIN_STDLIB)
    implementation(Libs.ANDROIDX_CORE)
    implementation(Libs.ANDROIDX_APPCOMPAT)
    implementation(Libs.ANDROIDX_MATERIAL)
    implementation(Libs.ANDROIDX_CONSTRAINTLAYOUT)
    testImplementation(Libs.ANDROIDX_TEST_JUNIT)
    androidTestImplementation(Libs.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Libs.ANDROIDX_TEST_ESPRESSO)
}