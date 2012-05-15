package net.sf.anathema.lib.gui.dialog.action;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogMessages;

import javax.swing.text.JTextComponent;
import java.awt.Component;

public class TextComponentSelectAllAction extends SmartAction {

  private final JTextComponent textComponent;

  public TextComponentSelectAllAction(final JTextComponent textComponent) {
    super(DialogMessages.SELECT_ALL);
    Ensure.ensureArgumentNotNull(textComponent);
    this.textComponent = textComponent;
  }

  @Override
  protected void execute(final Component parentComponent) {
    textComponent.selectAll();
  }
}