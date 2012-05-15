/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
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

  private FontStyle(final String name, final FontStyleProperty... properties) {
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

  public static FontStyle getByName(final String name) {
    for (final FontStyle fontStyle : values()) {
      if (name.equals(fontStyle.getName())) {
        return fontStyle;
      }
    }
    throw new IllegalArgumentException("No font style defined for name " + name); //$NON-NLS-1$
  }

  public static FontStyle getStyle(final boolean isBold, final boolean isItalic) {
    final Set<FontStyleProperty> properties = new HashSet<FontStyleProperty>();
    if (isBold) {
      properties.add(FontStyleProperty.BOLD);
    }
    if (isItalic) {
      properties.add(FontStyleProperty.ITALICS);
    }
    return getStyle(properties);
  }

  public static FontStyle getStyle(final Set<FontStyleProperty> properties) {
    for (final FontStyle style : values()) {
      if (style.properties.equals(properties)) {
        return style;
      }
    }
    throw new UnreachableCodeReachedException("(ip)"); //$NON-NLS-1$
  }
}