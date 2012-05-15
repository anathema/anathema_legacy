package net.sf.anathema.lib.gui.dialog.widgets;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.model.ObjectModel;
import net.sf.anathema.lib.provider.Provider;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class TextComponentKeyListener extends KeyAdapter {

  private final TextContent content;
  private final ObjectModel<TextSelection> selectionModel;
  private final Provider<Toolkit> toolkitProvider;

  public TextComponentKeyListener(
      TextContent content,
      ObjectModel<TextSelection> selectionModel,
      Provider<Toolkit> toolkitProvider) {
    Preconditions.checkNotNull(content);
    Preconditions.checkNotNull(selectionModel);
    Preconditions.checkNotNull(toolkitProvider);
    this.content = content;
    this.selectionModel = selectionModel;
    this.toolkitProvider = toolkitProvider;
  }

  @Override
  public void keyReleased(KeyEvent e) {
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
    TextSelection selection = selectionModel.getValue();
    TextPosition start = selection == null ? new TextPosition(0, 0) : selection.startPosition;
    TextPosition end = selection == null
        ? content.getLastTextPosition()
        : selection.endPosition;
    String text = content.getText(start, end);
    writeToSystemClipboard(text);
  }

  private void writeToSystemClipboard(String string) {
    StringSelection stringSelection = new StringSelection(string);
    Clipboard clipboard = toolkitProvider.getObject().getSystemClipboard();
    clipboard.setContents(stringSelection, stringSelection);
  }

  private void selectAll() {
    TextPosition start = new TextPosition(0, 0);
    TextPosition end = content.getLastTextPosition();
    selectionModel.setValue(TextSelection.createSelection(start, end));
  }
}