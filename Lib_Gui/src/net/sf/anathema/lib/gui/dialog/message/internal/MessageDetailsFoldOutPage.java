package net.sf.anathema.lib.gui.dialog.message.internal;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.action.AbstractCopyAction;
import net.sf.anathema.lib.gui.dialog.action.TextComponentSelectAllAction;
import net.sf.anathema.lib.gui.dialog.foldout.AbstractFoldOutPage;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    textArea.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        if (!e.isMetaDown()) {
          return;
        }
        showPopupMenu(textArea, e.getPoint());
      }
    });
    return textArea;
  }

  private static void showPopupMenu(final JTextArea textArea, Point point) {
    JPopupMenu menu = new JPopupMenu();
    SmartAction copyAction = new AbstractCopyAction() {
      @Override
      protected void execute(Component parentComponent) {
        boolean hasSelection = textArea.getSelectedText() != null;
        if (!hasSelection) {
          textArea.selectAll();
        }
        textArea.copy();
      }
    };
    SmartAction selectAllAction = new TextComponentSelectAllAction(textArea);
    menu.add(copyAction);
    menu.add(selectAllAction);
    menu.show(textArea, point.x, point.y);
  }

  @Override
  public void requestFocus() {
    textArea.requestFocus();
  }
}