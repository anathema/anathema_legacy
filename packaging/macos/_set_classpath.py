#!/usr/bin/env python3
"""Add a Class-Path manifest attribute to a jar, listing every *other* jar in its directory.

jpackage launches Anathema via its main jar; the JVM pulls in the sibling dependency/plugin
jars through this Class-Path attribute. JAR manifests cap every physical line at 72 bytes, so
the (long) Class-Path value is wrapped with continuation lines per the manifest spec — the
JDK 8 jar tool refuses to even read an over-long line, so we write the wrapped manifest here.

Usage: _set_classpath.py <path-to-main-jar>
"""
import os
import shutil
import sys
import zipfile

MANIFEST = "META-INF/MANIFEST.MF"


def wrap72(logical_line: str) -> bytes:
    """Wrap a logical manifest line into <=72-byte physical lines (continuation = leading space)."""
    b = logical_line.encode("utf-8")
    out = bytearray(b[:72])
    b = b[72:]
    while b:
        out += b"\r\n " + b[:71]  # leading space counts toward the 72-byte limit
        b = b[71:]
    out += b"\r\n"
    return bytes(out)


def main() -> None:
    jar_path = sys.argv[1]
    input_dir = os.path.dirname(os.path.abspath(jar_path))
    main_jar = os.path.basename(jar_path)

    names = sorted(
        f for f in os.listdir(input_dir) if f.endswith(".jar") and f != main_jar
    )
    cp_logical = "Class-Path: " + " ".join(names)

    with zipfile.ZipFile(jar_path, "r") as z:
        existing = z.read(MANIFEST).decode("utf-8")
        others = [(i, z.read(i.filename)) for i in z.infolist() if i.filename != MANIFEST]

    # Keep existing main attributes, drop any prior Class-Path (and its continuation lines).
    kept, in_classpath = [], False
    for line in existing.split("\n"):
        raw = line.rstrip("\r")
        if raw.startswith(" "):
            if in_classpath:
                continue
            kept.append(raw)
            continue
        in_classpath = raw.lower().startswith("class-path:")
        if in_classpath or raw == "":
            continue
        kept.append(raw)

    manifest_bytes = (
        ("\r\n".join(kept)).encode("utf-8") + b"\r\n" + wrap72(cp_logical) + b"\r\n"
    )

    tmp = jar_path + ".tmp"
    with zipfile.ZipFile(tmp, "w", zipfile.ZIP_DEFLATED) as z:
        z.writestr(zipfile.ZipInfo(MANIFEST), manifest_bytes)
        for info, data in others:
            z.writestr(info, data)
    shutil.move(tmp, jar_path)
    print(f"Class-Path set: {len(names)} jars")


if __name__ == "__main__":
    main()
