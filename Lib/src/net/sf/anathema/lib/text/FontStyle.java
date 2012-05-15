package net.sf.anathema.lib.text;

import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum FontStyle {

  PLAIN("Plain"),
  ITALIC("Italic", FontStyleProperty.ITALICS),
  BOLD("Bold", FontStyleProperty.BOLD),
  BOLD_ITALIC("Bold italic", FontStyleProperty.BOLD, FontStyleProperty.ITALICS);

  private final String name;
  private final Set<FontStyleProperty> properties;

  private FontStyle(String name, FontStyleProperty... properties) {
    this.name = name;
    this.properties = new HashSet<FontStyleProperty>(Arrays.asList(properties));
  }

  public boolean isItalic() {
    return properties.contains(FontStyleProperty.ITALICS);
  }

  public boolean isBold() {
    return properties.contains(FontStyleProperty.BOLD);
  }

  public boolean isPlain() {
    return !isBold() && !isItalic();
  }

  public String getName() {
    return name;
  }

  public static FontStyle getByName(String name) {
    for (FontStyle fontStyle : values()) {
      if (name.equals(fontStyle.getName())) {
        return fontStyle;
      }
    }
    throw new IllegalArgumentException("No font style defined for name " + name); //$NON-NLS-1$
  }

  public static FontStyle getStyle(boolean isBold, boolean isItalic) {
    Set<FontStyleProperty> properties = new HashSet<FontStyleProperty>();
    if (isBold) {
      properties.add(FontStyleProperty.BOLD);
    }
    if (isItalic) {
      properties.add(FontStyleProperty.ITALICS);
    }
    return getStyle(properties);
  }

  public static FontStyle getStyle(Set<FontStyleProperty> properties) {
    for (FontStyle style : values()) {
      if (style.properties.equals(properties)) {
        return style;
      }
    }
    throw new UnreachableCodeReachedException("(ip)"); //$NON-NLS-1$
  }
}