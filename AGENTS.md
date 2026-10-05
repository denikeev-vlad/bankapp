# Repository Guidelines

## Project Structure & Module Organization

The repository contains one Android application module, `app`. Kotlin sources live under `app/src/main/java/ru/vladkempo/bankapp/`:

- `data/`: API DTOs, mapping, and repository implementations.
- `domain/`: models, repository interfaces, and use cases.
- `presentation/`: Compose screens, navigation, themes, UI state, and ViewModels.
- `di/`: Hilt dependency injection modules; `core/utils/`: shared utilities.

Keep business logic in `domain`, data access in `data`, and rendering in `presentation`. Android resources and launcher assets belong in `app/src/main/res/`. Local tests live in `app/src/test/`; device tests live in `app/src/androidTest/`. Dependency versions are centralized where possible in `gradle/libs.versions.toml`.

## Build, Test, and Development Commands

Run the Gradle wrapper from the repository root. On Windows:

- `.\gradlew.bat :app:assembleDebug`: build the debug APK.
- `.\gradlew.bat :app:installDebug`: install on a connected device or emulator; launch the app from its launcher.
- `.\gradlew.bat :app:testDebugUnitTest`: run local unit tests.
- `.\gradlew.bat :app:connectedDebugAndroidTest`: run instrumented tests on a device or emulator.
- `.\gradlew.bat :app:lintDebug`: run Android Lint.

On Unix, substitute `./gradlew`. Configure the Android SDK through Android Studio or ignored `local.properties`. The project compiles against SDK 36 and supports API 29 onward.

## Coding Style & Naming Conventions

Use four-space indentation and Kotlin idioms. Name classes, files, and composables in `PascalCase`, functions and properties in `camelCase`, and resources in `snake_case`. Follow existing suffixes such as `ViewModel`, `UseCase`, and `RepositoryImpl`. Expose immutable state from ViewModels and keep mutable flows private. Use Android Studio formatting; no dedicated ktlint or Detekt configuration is present.

## Testing Guidelines

Local tests use JUnit 4; instrumented tests use AndroidJUnit4, with Espresso and Compose testing dependencies available. Name test classes `<Subject>Test` and methods descriptively. Add regression tests for changed behavior, especially pagination, mapping, and authentication. Existing tests are starter examples; no coverage threshold is configured.

## Commit & Pull Request Guidelines

History mixes plain descriptions and underscore-separated messages. Prefer concise, imperative summaries, such as `Fix operation pagination`. Existing branches use `feature/` and `hotfix/` prefixes. PRs should explain the change, link relevant issues, list validation performed, and include screenshots for UI changes.

## Security & Configuration

Keep credentials and local SDK paths out of Git. `NetworkModule` contains a placeholder API URL and body-level HTTP logging; review endpoint configuration and sensitive-data logging before using real banking data.
