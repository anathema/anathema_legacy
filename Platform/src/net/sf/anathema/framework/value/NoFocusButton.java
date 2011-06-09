package net.sf.anathema.framework.value;

import javax.swing.JButton;

public class NoFocusButton extends JButton {

  private static final long serialVersionUID = 6680633198323861296L;

  public NoFocusButton() {
    super();
    setFocusable(false);
  }
}