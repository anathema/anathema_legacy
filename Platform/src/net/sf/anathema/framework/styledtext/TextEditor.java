package net.sf.anathema.framework.styledtext;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

import net.sf.anathema.lib.gui.IView;

public class TextEditor implements IStyledTextView, IView {

  private JPanel content;
  private final StyledDocument document;
  private final JTextPane textPane = new JTextPane();
  private final Dimension preferredSize;
  private final JToggleButton[] toolBarButtons;

  public TextEditor(StyledDocument document, ITextEditorProperties properties, Dimension preferredSize) {
    this.document = document;
    this.preferredSize = preferredSize;
    this.toolBarButtons = initToolbarButtons(properties);
    addBindings();
  }

  private void addBindings() {
    InputMap inputMap = textPane.getInputMap();
    inputMap.put(
        KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, Event.SHIFT_MASK),
        DefaultEditorKit.deletePrevCharAction);

    // styles
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK), "font-bold"); //$NON-NLS-1$
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK), "font-bold"); //$NON-NLS-1$
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK), "font-italic"); //$NON-NLS-1$
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, Event.CTRL_MASK), "font-italic"); //$NON-NLS-1$
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK), "font-underline"); //$NON-NLS-1$
    // selection
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_BEGIN), DefaultEditorKit.selectionBeginAction);
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_DOWN), DefaultEditorKit.selectionDownAction);
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_END), DefaultEditorKit.selectionEndAction);
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_UP), DefaultEditorKit.selectionUpAction);
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_A), DefaultEditorKit.selectAllAction);
  }

  private KeyStroke createSelectKeyStroke(int key) {
    return KeyStroke.getKeyStroke(key, Event.CTRL_MASK | Event.SHIFT_MASK);
  }

  public JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new BorderLayout());
      content.add(createStyleToolBar(), BorderLayout.EAST);
      content.add(initDisplayComponent(), BorderLayout.CENTER);
    }
    return content;
  }

  private JComponent initDisplayComponent() {
    textPane.setDocument(document);
    textPane.setCaretPosition(0);
    textPane.setMargin(new Insets(5, 5, 5, 5));
    JScrollPane scrollPane = new JScrollPane(textPane);
    scrollPane.setPreferredSize(preferredSize);
    return scrollPane;
  }

  private JToolBar createStyleToolBar() {
    JToolBar toolBar = new JToolBar(SwingConstants.VERTICAL);
    toolBar.setFloatable(false);
    for (final JToggleButton button : toolBarButtons) {
      toolBar.add(button);
    }
    return toolBar;
  }

  private JToggleButton[] initToolbarButtons(ITextEditorProperties properties) {
    Action boldAction = new StyledEditorKit.BoldAction();
    properties.initBoldAction(boldAction);
    JToggleButton boldButton = initStyleToggleButton(boldAction, StyleConstants.Bold);

    Action italicAction = new StyledEditorKit.ItalicAction();
    properties.initItalicAction(italicAction);
    JToggleButton italicButton = initStyleToggleButton(italicAction, StyleConstants.Italic);

    Action underlineAction = new StyledEditorKit.UnderlineAction();
    properties.initUnderlineAction(underlineAction);
    JToggleButton underlineButton = initStyleToggleButton(underlineAction, StyleConstants.Underline);

    return new JToggleButton[] { boldButton, italicButton, underlineButton };
  }

  private JToggleButton initStyleToggleButton(final Action action, final Object styleConstant) {
    final JToggleButton button = new JToggleButton(action);
    button.setText(null);
    textPane.addCaretListener(new CaretListener() {
      public void caretUpdate(CaretEvent e) {
        selectButton(styleConstant, button);
      }
    });
    textPane.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        button.setSelected(textPane.getInputAttributes().containsAttribute(styleConstant, true));
      }
    });
    textPane.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        selectButton(styleConstant, button);
      }
    });
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        textPane.requestFocus();
      }
    });
    return button;
  }

  private void selectButton(final Object styleConstant, final JToggleButton button) {
    boolean selected = false;
    if (textPane.getSelectedText() == null) {
      int index = textPane.getCaretPosition();
      selected = document.getCharacterElement(index - 1).getAttributes().containsAttribute(styleConstant, true);
    }
    else {
      for (int index = textPane.getSelectionStart(); index < textPane.getSelectionEnd(); index++) {
        AttributeSet attributes = document.getCharacterElement(index).getAttributes();
        selected = selected || attributes.containsAttribute(styleConstant, true);
      }
    }
    button.setSelected(selected);
  }

  public void setEnabled(boolean enabled) {
    textPane.setEnabled(enabled);
    for (JToggleButton button : toolBarButtons) {
      button.setEnabled(enabled);
    }
  }
}