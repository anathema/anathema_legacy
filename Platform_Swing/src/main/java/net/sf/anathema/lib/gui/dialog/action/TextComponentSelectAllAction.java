package net.sf.anathema.lib.gui.dialog.action;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogMessages;

import javax.swing.text.JTextComponent;
import java.awt.Component;

public class TextComponentSelectAllAction extends SmartAction {

  private final JTextComponent textComponent;

  public TextComponentSelectAllAction(JTextComponent textComponent) {
    super(DialogMessages.SELECT_ALL);
    Preconditions.checkNotNull(textComponent);
    this.textComponent = textComponent;
  }

  @Override
  protected void execute(Component parentComponent) {
    textComponent.selectAll();
  }
}