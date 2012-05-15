/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.widgets;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.model.ObjectModel;
import net.sf.anathema.lib.provider.IProvider;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class TextComponentKeyListener extends KeyAdapter {

  private final TextContent content;
  private final ObjectModel<TextSelection> selectionModel;
  private final IProvider<Toolkit> toolkitProvider;

  public TextComponentKeyListener(
      final TextContent content,
      final ObjectModel<TextSelection> selectionModel,
      final IProvider<Toolkit> toolkitProvider) {
    Preconditions.checkNotNull(content);
    Preconditions.checkNotNull(selectionModel);
    Preconditions.checkNotNull(toolkitProvider);
    this.content = content;
    this.selectionModel = selectionModel;
    this.toolkitProvider = toolkitProvider;
  }

  @Override
  public void keyReleased(final KeyEvent e) {
    if (content.isEmpty()) {
      return;
    }
    if (!e.isControlDown() || e.isShiftDown() || e.isMetaDown()) {
      return;
    }
    if (e.getKeyCode() == 'A') {
      selectAll();
      e.consume();
      return;
    }
    if (e.getKeyCode() == 'C') {
      copyToClipboard();
      e.consume();
      return;
    }
  }

  private void copyToClipboard() {
    final TextSelection selection = selectionModel.getValue();
    final TextPosition start = selection == null ? new TextPosition(0, 0) : selection.startPosition;
    final TextPosition end = selection == null
        ? content.getLastTextPosition()
        : selection.endPosition;
    final String text = content.getText(start, end);
    writeToSystemClipboard(text);
  }

  private void writeToSystemClipboard(final String string) {
    final StringSelection stringSelection = new StringSelection(string);
    final Clipboard clipboard = toolkitProvider.getObject().getSystemClipboard();
    clipboard.setContents(stringSelection, stringSelection);
  }

  private void selectAll() {
    final TextPosition start = new TextPosition(0, 0);
    final TextPosition end = content.getLastTextPosition();
    selectionModel.setValue(TextSelection.createSelection(start, end));
  }
}