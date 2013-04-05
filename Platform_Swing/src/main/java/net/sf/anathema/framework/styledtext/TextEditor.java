package net.sf.anathema.framework.styledtext;

import net.sf.anathema.framework.styledtext.model.IStyledTextChangeListener;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.StyledText;
import net.sf.anathema.framework.styledtext.presentation.SwingStyledText;
import net.sf.anathema.lib.gui.IView;

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
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextEditor implements IStyledTextView, IView {

  private JPanel content;
  private final StyledDocument document;
  private final JTextPane textPane = new JTextPane();
  private final JToggleButton[] toolBarButtons;
  private final StyledText styledText;

  public TextEditor(StyledDocument document, ITextEditorProperties properties) {
    this.document = document;
    this.styledText = new SwingStyledText(document);
    this.toolBarButtons = initToolbarButtons(properties);
    addBindings();
  }

  private void addBindings() {
    InputMap inputMap = textPane.getInputMap();
    inputMap.put(
        KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.SHIFT_MASK),
        DefaultEditorKit.deletePrevCharAction);

    int keyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    // styles
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, keyMask), "font-bold");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, keyMask), "font-bold");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, keyMask), "font-italic");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, keyMask), "font-italic");
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, keyMask), "font-underline");
    // selection
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_BEGIN), DefaultEditorKit.selectionBeginAction);
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_DOWN), DefaultEditorKit.selectionDownAction);
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_END), DefaultEditorKit.selectionEndAction);
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_UP), DefaultEditorKit.selectionUpAction);
    inputMap.put(createSelectKeyStroke(KeyEvent.VK_A), DefaultEditorKit.selectAllAction);
  }

  private KeyStroke createSelectKeyStroke(int key) {
    return KeyStroke.getKeyStroke(key, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);
  }

  @Override
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
    return new JScrollPane(textPane);
  }

  private JToolBar createStyleToolBar() {
    JToolBar toolBar = new JToolBar(SwingConstants.VERTICAL);
    toolBar.setFloatable(false);
    for (JToggleButton button : toolBarButtons) {
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

  private JToggleButton initStyleToggleButton(Action action, final Object styleConstant) {
    final JToggleButton button = new JToggleButton(action);
    button.setText(null);
    textPane.addCaretListener(new CaretListener() {
      @Override
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
      @Override
      public void actionPerformed(ActionEvent e) {
        textPane.requestFocus();
      }
    });
    return button;
  }

  private void selectButton(Object styleConstant, JToggleButton button) {
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

  @Override
  public void setEnabled(boolean enabled) {
    textPane.setEnabled(enabled);
    for (JToggleButton button : toolBarButtons) {
      button.setEnabled(enabled);
    }
  }

  @Override
  public void setText(ITextPart[] textParts) {
    styledText.setText(textParts);
  }

  @Override
  public void addStyledTextListener(IStyledTextChangeListener listener) {
    styledText.addStyledTextListener(listener);
  }
}