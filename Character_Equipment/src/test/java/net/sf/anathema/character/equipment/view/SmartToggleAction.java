package net.sf.anathema.character.equipment.view;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.model.IModifiableBooleanModel;

import java.awt.Component;

public class SmartToggleAction extends SmartAction {

  private final IModifiableBooleanModel model;

  public SmartToggleAction(IModifiableBooleanModel model, String name) {
    super(name);
    this.model = model;
  }

  public IModifiableBooleanModel getSelectionModel() {
    return model;
  }

  @Override
  protected void execute(Component parentComponent) {
    model.setValue(!model.getValue());
  }
}