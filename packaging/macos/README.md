# Anathema — macOS DMG installer (Apple Silicon)

`build-dmg.sh` produces a **self-contained, double-click `.dmg`** for Anathema on Apple
Silicon (arm64) Macs:

```
dist/Anathema-6.0.0-arm64.dmg
```

The DMG bundles its own Java 8 + JavaFX runtime, so end users do **not** need Java installed.
Open the DMG, drag **Anathema** to **Applications**, done.

## For end users — first launch (important)

The DMG is **not** signed with an Apple Developer ID (this is a free, open-source build), so
the first time you open it macOS Gatekeeper will warn that the developer "cannot be verified."
This is expected. Either:

- **Right-click** (or Control-click) `Anathema.app` in Applications → **Open** → **Open**; or
- run once in Terminal:
  ```sh
  xattr -dr com.apple.quarantine /Applications/Anathema.app
  ```

After the first launch it opens normally with a double-click. Anathema stores its character
data in `~/.anathema/repository` (configurable in the app's preferences).

## For maintainers — building the DMG

### One command
```sh
packaging/macos/build-dmg.sh
```

That's it. The first run downloads the pinned toolchain (~250 MB) into
`~/.cache/anathema-dmg-toolchain`; later runs reuse it.

### Requirements
- An **Apple Silicon** Mac with **Rosetta 2** (`softwareupdate --install-rosetta`).
- A **JDK 17+** installed somewhere (its `jpackage` builds the DMG). The script auto-detects
  any JDK under `/Library/Java/JavaVirtualMachines`; override with `JPACKAGE=/path/to/jpackage`.
- Internet access on first run (toolchain + Gradle dependency downloads).

### What the script does, and why
Anathema's own build is **Gradle 2.2.1 (2014)** and only runs on **JDK 8**, and the original
macOS release pipeline (Oracle AppBundler + an Oracle JRE download that no longer exists) is
dead. So the script:

1. **Builds the jars** with a JavaFX-bundled **Zulu 8 JDK (x64)** run **under Rosetta 2** —
   Gradle 2.2.1 ships no arm64 native libraries. Only the jar-producing tasks are run
   (`:Anathema:jar copyExternalDependencies copyAnathemaModules`); the broken Win/Mac/Linux
   release tasks are skipped. The jars are architecture-independent.
   - JavaFX is required by several modules (e.g. `Platform_FX`) and is **absent from Temurin
     8**, which is why a Zulu "FX" build is used.
2. **Stages** the main jar + all dependency/plugin jars into `build/jpackage-input/` and adds
   a `Class-Path` manifest to `anathema.jar` so the JVM loads every sibling jar.
3. **Packages** with `jpackage --type dmg`, bundling a JavaFX-bundled **Zulu 8 JRE (arm64)**
   as the runtime and `Development_Distribution/Mac/sungear.icns` as the icon. `jpackage`
   ad-hoc signs the `.app` (required for arm64 to launch); the DMG stays unsigned.

### Configuration (environment overrides)
| Variable | Default | Purpose |
|---|---|---|
| `APP_VERSION` | from `gradle.properties` | Version in the app/DMG |
| `DEST` | `dist/` | Output directory |
| `JPACKAGE` | auto-detected | Path to a JDK 17+ `jpackage` |
| `BUILD_JDK_HOME` | Zulu 8 FX x64 (auto-downloaded) | JDK used to compile (under Rosetta) |
| `BUNDLE_JRE_HOME` | Zulu 8 FX arm64 (auto-downloaded) | Runtime bundled into the app |
| `ANATHEMA_TOOLCHAIN` | `~/.cache/anathema-dmg-toolchain` | Toolchain download cache |

## Source compatibility changes

Anathema was written against the 2014-era JavaFX 8 it shipped (Oracle JRE `1.8.0_05`). Running
on a current, redistributable OpenJFX 8 (Azul Zulu) required these source changes:

1. **Internal JavaFX API rename.** `com.sun.javafx.Utils` moved to `com.sun.javafx.util.Utils`:
   - `Platform_FX/.../dot/DotSelectionSpinnerSkin.java` import updated.

2. **ControlsFX upgrade 8.0.6 → 8.40.18.** ControlsFX 8.0.6 calls early-JavaFX-8 internal APIs
   that modern OpenJFX 8 removed (notably `com.sun.javafx.scene.traversal.TraversalEngine(Parent,
   boolean)`), so it cannot run on any maintained JavaFX. Upgrading to the last JavaFX-8 release
   (8.40.18) fixes this but removes `org.controlsfx.dialog.Dialog`/`Dialogs` and
   `org.controlsfx.control.action.AbstractAction`. To keep the upgrade minimal:
   - A tiny compat layer reproduces the slice of the old dialog API the app used, backed by plain
     JavaFX: `Platform_FX/.../framework/fx/compat/{Dialog,Dialogs,DialogStyle}.java`.
   - The 9 dialog files switched their `org.controlsfx.dialog.*` imports to that compat package.
   - `AbstractAction` subclasses became `org.controlsfx.control.action.Action` (which is still
     present): `ConfigurableControlsFxAction` and `FxEditStatsDialog`.
   - `NotificationPane` is unchanged between the two ControlsFX versions.

3. **Default repository location.** The default was the *relative* path `./repository/`, which a
   Finder-launched `.app` (working directory `/`) cannot create. Changed to the per-user
   `%USER_HOME%/.anathema/repository/` (the `%USER_HOME%` token is already expanded by
   `RepositoryLocationResolver`): `Platform/.../repository/preferences/RepositoryPreferenceModel.java`.

## Notes & limitations
- **Apple Silicon only.** For Intel Macs, build with the x64 Zulu FX JRE as `BUNDLE_JRE_HOME`
  and run `jpackage` from an x64 JDK; or produce both and ship two DMGs.
- **Unsigned / not notarized.** Requires the one-time Gatekeeper step above. For a true
  one-click experience, sign & notarize with an Apple Developer ID and add
  `--mac-sign --mac-signing-key-user-name "Developer ID Application: …"` plus `notarytool`.
