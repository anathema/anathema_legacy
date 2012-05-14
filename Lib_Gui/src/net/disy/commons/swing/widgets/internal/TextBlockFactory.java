/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.widgets.internal;

import net.disy.commons.core.util.Ensure;

import java.util.ArrayList;
import java.util.List;

public class TextBlockFactory {

  public static List<TextBlock> createTextBlocks(final String text) {
    Ensure.ensureArgumentNotNull(text);
    int endIndex = 0;
    int startIndex = 0;
    final ArrayList<TextBlock> blocks = new ArrayList<TextBlock>();
    while (endIndex < text.length()) {
      final TextBlockDelimiter delimiter = TextBlockDelimiter.forCharacterOrNull(text
          .charAt(endIndex));
      if (delimiter != null) {
        final int actualEndIndex = delimiter.isAdditionalCharacterIncluded()
            ? endIndex + 1
            : endIndex;
        final String blockText = text.substring(startIndex, actualEndIndex);
        if (endIndex == text.length() - 1) {
          blocks.add(new TextBlock(blockText, TextBlockDelimiter.END_OF_TEXT));
        }
        else {
          blocks.add(new TextBlock(blockText, delimiter));
        }
        ++endIndex;
        startIndex = endIndex;
      }
      else {
        ++endIndex;
      }
    }
    if (endIndex > startIndex) {
      blocks
          .add(new TextBlock(text.substring(startIndex, endIndex), TextBlockDelimiter.END_OF_TEXT));
    }
    return blocks;
  }
}