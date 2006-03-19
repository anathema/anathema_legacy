package net.sf.anathema.framework.value;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class NoFocusButton extends JButton {

  public NoFocusButton() {
    super();
    init();
  }

  private void init() {
    setFocusable(false);
  }

  public NoFocusButton(String text) {
    super(text);
    init();
  }

  public NoFocusButton(String text, Icon icon) {
    super(text, icon);
    init();
  }

  public NoFocusButton(Action a) {
    super(a);
    init();
  }

  public NoFocusButton(Icon icon) {
    super(icon);
    init();
  }
}