# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Android debug APK
./gradlew :composeApp:assembleDebug

# Android release APK
./gradlew :composeApp:assembleRelease

# Run all common (shared) tests
./gradlew :composeApp:cleanTestDebugUnitTest :composeApp:testDebugUnitTest

# Run a specific test class
./gradlew :composeApp:testDebugUnitTest --tests "com.bbeniful.lifewithshunt.ExampleTest"

# Run connected Android device tests
./gradlew :composeApp:connectedAndroidTest

# Build iOS framework (for Xcode integration)
./gradlew :composeApp:assembleXCFramework
```

iOS must be built and run from Xcode — open `iosApp/iosApp.xcodeproj`.

## Architecture

Clean Architecture + MVVM (with MVI-style unidirectional state) across a Kotlin Multiplatform Compose project targeting Android and iOS.

ViewModels hold a single immutable `UIState` data class exposed as `StateFlow`, updated via `_uiState.update { it.copy(...) }`. The UI calls ViewModel methods directly (MVVM-style) rather than dispatching intents. Empty `sealed interface *Intent` stubs exist in some features but are not yet used.

### Module Layout

```
composeApp/          # App entry points (Android: MainActivity, iOS: iosApp)
core/
  data/             # Room DB, DataStore, repository implementations, mappers
  domain/           # Repository interfaces, domain models, use cases
  di/               # Global Koin modules (AppModule, DataModule)
  presentation/     # Navigator, Container (KoinAppContainer), shared UI components
feature/
  <name>/
    api/nav/        # Serializable navigation route objects
    impl/data/      # Data sources, repository implementations, mappers
    impl/domain/    # Use cases, repository interfaces
    impl/presentation/ # ViewModel, UIState, Composable screens
    di/             # Koin module (viewModelOf, singleOf bindings)
build-logic/
  convention/       # Gradle convention plugins (ComposePlugin, KtorPlugin, Nav3Plugin, etc.)
```

### Key Architectural Patterns

**Navigation:** Custom `Navigator` in `core/presentation` maintains a mutable backstack of serializable route objects. Routes are defined in `feature/*/api/nav` modules. Navigation3 (`androidx.navigation3`) renders the backstack.

**DI:** Koin 4.x. Feature modules each declare a Koin module in their `di/` submodule. All modules are assembled in `composeApp/src/commonMain/kotlin/.../App.kt`. Use `koinViewModel()` in Composables and `koinInject()` for non-ViewModel dependencies.

**Data persistence:**
- **Room** (`LifeWithShuntDatabase` in `core/data`) for structured data (symptoms).
- **DataStore** for user/shunt settings — platform-specific path resolution via expect/actual.

**Platform-specific code:** Use `expect`/`actual` declarations. Platform implementations live in `androidMain`/`iosMain` source sets (e.g., `LocationProvider`).

**Convention plugins:** Rather than repeating dependency declarations, modules apply plugins like `com.bbeniful.kmp.compose`, `com.bbeniful.kmp.ktor`, `com.bbeniful.kmp.nav3withkoin` from `build-logic/convention`.

### Dependency Versions (libs.versions.toml)

| Library | Version |
|---|---|
| Kotlin | 2.3.20 |
| Compose Multiplatform | 1.10.3 |
| Koin BOM | 4.2.0 |
| Ktor | 3.4.1 |
| Room | 2.8.4 |
| DataStore | 1.1.1 |
| Navigation3 | 1.0.0-alpha06 |
| Coroutines | 1.10.2 |

### Adding a New Feature

1. Create `feature/<name>/{api/nav, impl/{data,domain,presentation}, di}` modules following the existing feature pattern.
2. Apply the appropriate convention plugin(s) in each module's `build.gradle.kts`.
3. Register the feature's Koin module in `App.kt`.
4. Add the feature's navigation route and screen to the Navigation3 backstack handler in `Container.kt`.
