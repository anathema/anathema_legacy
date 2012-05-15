/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.widgets;

import net.disy.commons.core.util.Ensure;

public class TextBlock {

  public final String text;
  public final TextBlockDelimiter delimiter;

  public TextBlock(final String text, final TextBlockDelimiter delimiter) {
    Ensure.ensureArgumentNotNull(text);
    Ensure.ensureArgumentNotNull(delimiter);
    this.text = text;
    this.delimiter = delimiter;
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof TextBlock)) {
      return false;
    }
    final TextBlock other = (TextBlock) obj;
    return delimiter == other.delimiter && text.equals(other.text);
  }

  @Override
  public int hashCode() {
    return delimiter.hashCode() * 17 + text.hashCode() * 3;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" + "text='" + text + "', delimiter=" + delimiter + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }
}