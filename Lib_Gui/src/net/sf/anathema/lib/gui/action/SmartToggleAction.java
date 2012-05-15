package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.model.IModifiableBooleanModel;

import java.awt.Component;

public class SmartToggleAction extends SmartAction {

  private final IModifiableBooleanModel model;

  public SmartToggleAction(final IModifiableBooleanModel model, final String name) {
    super(name);
    this.model = model;
  }

  public IModifiableBooleanModel getSelectionModel() {
    return model;
  }

  @Override
  protected void execute(final Component parentComponent) {
    model.setValue(!model.getValue());
  }
}