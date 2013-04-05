package net.sf.anathema.lib.gui.dialog.message.internal;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.dialog.foldout.AbstractFoldOutPage;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

public final class MessageDetailsFoldOutPage extends AbstractFoldOutPage {
  private static final Font FIXEDWIDTH_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 11);

  private final String detailText;
  private JTextArea textArea;

  public MessageDetailsFoldOutPage(String detailText) {
    Preconditions.checkNotNull(detailText);
    this.detailText = detailText;
  }

  @Override
  protected JComponent createContent() {
    textArea = createMessageDetailsTextArea(detailText);
    return new JScrollPane(textArea);
  }

  private static JTextArea createMessageDetailsTextArea(String detailsText) {
    final JTextArea textArea = new JTextArea(12, 70);
    textArea.setEditable(false);
    textArea.setFont(FIXEDWIDTH_FONT);
    textArea.setText(detailsText);
    textArea.setCaretPosition(0);
    return textArea;
  }

  @Override
  public void requestFocus() {
    textArea.requestFocus();
  }
}