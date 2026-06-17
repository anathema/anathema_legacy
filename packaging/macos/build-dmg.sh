#!/usr/bin/env bash
#
# build-dmg.sh — Build a self-contained, double-click macOS .dmg installer for Anathema.
#
# Produces:  dist/Anathema-<version>-arm64.dmg   (Apple Silicon, bundled Java 8 + JavaFX runtime)
#
# The DMG is UNSIGNED (no Apple Developer ID). The app itself is ad-hoc signed by jpackage so
# it launches on Apple Silicon; end users clear the download quarantine once — see README.md.
#
# Why this is not just "gradlew build":
#   * Anathema's build is Gradle 2.2.1 (2014) and only runs on JDK 8.
#   * On Apple Silicon, Gradle 2.2.1 has no native libs, so the build runs under an Intel
#     JDK 8 via Rosetta 2. The resulting jars are architecture-independent.
#   * Several modules (Platform_FX, ...) require JavaFX, which is absent from Temurin 8, so we
#     build with a JavaFX-bundled Zulu 8 JDK and ship a JavaFX-bundled Zulu 8 arm64 JRE.
#   * The original release pipeline (AppBundler + Oracle JRE download) is dead; we use jpackage.
#
# All three toolchain components are auto-downloaded into ~/.cache/anathema-dmg-toolchain if
# absent. Override any path via the env vars below.

set -euo pipefail

# ---------------------------------------------------------------------------
# Configuration (override via environment)
# ---------------------------------------------------------------------------
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"

APP_NAME="Anathema"
MAIN_CLASS="net.sf.anathema.AnathemaBootLoader"
MAIN_JAR="anathema.jar"
IDENTIFIER="net.sf.anathema"
ICON="$REPO_ROOT/Development_Distribution/Mac/sungear.icns"
DEST="${DEST:-$REPO_ROOT/dist}"

# Version: read from gradle.properties unless APP_VERSION is set.
if [[ -z "${APP_VERSION:-}" ]]; then
  vmaj=$(awk -F= '/^version_major/{gsub(/[ \r]/,"",$2);print $2}' "$REPO_ROOT/gradle.properties")
  vmin=$(awk -F= '/^version_minor/{gsub(/[ \r]/,"",$2);print $2}' "$REPO_ROOT/gradle.properties")
  vrev=$(awk -F= '/^version_revision/{gsub(/[ \r]/,"",$2);print $2}' "$REPO_ROOT/gradle.properties")
  APP_VERSION="${vmaj:-6}.${vmin:-0}.${vrev:-0}"
fi

TC="${ANATHEMA_TOOLCHAIN:-$HOME/.cache/anathema-dmg-toolchain}"

# JavaFX-bundled Zulu 8 (pinned). Build JDK is x64 (runs under Rosetta for Gradle 2.2.1);
# the bundled runtime is the arm64 JRE that ships inside the app.
BUILD_JDK_URL="https://cdn.azul.com/zulu/bin/zulu8.94.0.17-ca-fx-jdk8.0.492-macosx_x64.tar.gz"
BUNDLE_JRE_URL="https://cdn.azul.com/zulu/bin/zulu8.94.0.17-ca-fx-jre8.0.492-macosx_aarch64.tar.gz"
BUILD_JDK_HOME="${BUILD_JDK_HOME:-$TC/zulufx8-jdk-x64/zulu8.94.0.17-ca-fx-jdk8.0.492-macosx_x64/Contents/Home}"
BUNDLE_JRE_HOME="${BUNDLE_JRE_HOME:-$TC/zulufx8-jre/zulu8.94.0.17-ca-fx-jre8.0.492-macosx_aarch64/Contents/Home}"

log() { printf '\033[1;36m==>\033[0m %s\n' "$*"; }
die() { printf '\033[1;31mERROR:\033[0m %s\n' "$*" >&2; exit 1; }

# ---------------------------------------------------------------------------
# Toolchain
# ---------------------------------------------------------------------------
fetch_runtime() { # url, dest_parent_dir, expected_home
  local url="$1" dest="$2" home="$3"
  [[ -x "$home/bin/java" ]] && return 0
  log "Downloading $(basename "$url") ..."
  mkdir -p "$dest"
  local tgz="$dest/$(basename "$url")"
  curl -fsSL -o "$tgz" "$url"
  tar xzf "$tgz" -C "$dest"
  [[ -x "$home/bin/java" ]] || die "Runtime not found at $home after extraction"
}

find_jpackage() {
  if [[ -n "${JPACKAGE:-}" ]]; then echo "$JPACKAGE"; return; fi
  # Prefer the highest-versioned installed JDK that ships jpackage (JDK 14+).
  local home
  for home in $(ls -d /Library/Java/JavaVirtualMachines/*/Contents/Home 2>/dev/null | sort -rV); do
    if [[ -x "$home/bin/jpackage" ]]; then echo "$home/bin/jpackage"; return; fi
  done
  local jp="$(/usr/libexec/java_home 2>/dev/null)/bin/jpackage"
  [[ -x "$jp" ]] && { echo "$jp"; return; }
  die "jpackage not found. Install a JDK 17+ (e.g. 'brew install --cask temurin') or set JPACKAGE=..."
}

# ---------------------------------------------------------------------------
# Build
# ---------------------------------------------------------------------------
log "Anathema $APP_VERSION  →  $DEST/$APP_NAME-$APP_VERSION-arm64.dmg"
[[ -f "$ICON" ]] || die "Icon not found: $ICON"

fetch_runtime "$BUILD_JDK_URL"  "$TC/zulufx8-jdk-x64" "$BUILD_JDK_HOME"
fetch_runtime "$BUNDLE_JRE_URL" "$TC/zulufx8-jre"     "$BUNDLE_JRE_HOME"
JPACKAGE="$(find_jpackage)"
log "Build JDK : $BUILD_JDK_HOME"
log "Bundle JRE: $BUNDLE_JRE_HOME"
log "jpackage  : $JPACKAGE"

log "Building application jars with Gradle (Intel JDK 8 under Rosetta) ..."
cd "$REPO_ROOT"
chmod +x ./gradlew
JAVA_HOME="$BUILD_JDK_HOME" arch -x86_64 ./gradlew --no-daemon clean \
  :Anathema:jar copyExternalDependencies copyAnathemaModules

[[ -f "Anathema/build/libs/$MAIN_JAR" ]] || die "Main jar not built: Anathema/build/libs/$MAIN_JAR"

# ---------------------------------------------------------------------------
# Stage jars into a single input directory and wire up the classpath
# ---------------------------------------------------------------------------
INPUT="$REPO_ROOT/build/jpackage-input"
log "Staging jars into $INPUT ..."
rm -rf "$INPUT"; mkdir -p "$INPUT"
cp "Anathema/build/libs/$MAIN_JAR" "$INPUT/"
[[ -d build/dependencies ]] && cp build/dependencies/*.jar "$INPUT/" 2>/dev/null || true
[[ -d build/plugins ]]      && cp build/plugins/*.jar      "$INPUT/" 2>/dev/null || true
log "Staged $(ls "$INPUT"/*.jar | wc -l | tr -d ' ') jars."

# jpackage launches the app via the main jar; sibling jars are pulled in through the main
# jar's Class-Path manifest attribute (written with correct 72-byte line wrapping).
log "Writing Class-Path manifest into $MAIN_JAR ..."
python3 "$SCRIPT_DIR/_set_classpath.py" "$INPUT/$MAIN_JAR"

# ---------------------------------------------------------------------------
# Build the DMG with jpackage
# ---------------------------------------------------------------------------
log "Running jpackage ..."
rm -rf "$DEST"; mkdir -p "$DEST"
"$JPACKAGE" \
  --type dmg \
  --name "$APP_NAME" \
  --app-version "$APP_VERSION" \
  --input "$INPUT" \
  --main-jar "$MAIN_JAR" \
  --main-class "$MAIN_CLASS" \
  --runtime-image "$BUNDLE_JRE_HOME" \
  --icon "$ICON" \
  --mac-package-identifier "$IDENTIFIER" \
  --java-options "-Dapple.laf.useScreenMenuBar=true" \
  --dest "$DEST"

# jpackage names the file "<App>-<version>.dmg"; add an arch suffix for clarity.
SRC_DMG="$DEST/$APP_NAME-$APP_VERSION.dmg"
OUT_DMG="$DEST/$APP_NAME-$APP_VERSION-arm64.dmg"
[[ -f "$SRC_DMG" ]] && mv -f "$SRC_DMG" "$OUT_DMG"

log "Done: $OUT_DMG"
ls -lh "$OUT_DMG"
