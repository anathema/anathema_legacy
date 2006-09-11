package net.sf.anathema.framework.value;

import javax.swing.JButton;

public class NoFocusButton extends JButton {

  public NoFocusButton() {
    super();
    setFocusable(false);
  }
}