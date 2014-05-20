package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueView;

public class BooleanValuePresentation {

  public void initPresentation(final ToggleTool tool, final BooleanValueModel model) {
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        model.setValue(!model.getValue());
      }
    });
    model.addChangeListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        BooleanValuePresentation.this.selectBasedOnModelState(newValue, tool);
      }
    });
    selectBasedOnModelState(model.getValue(), tool);
  }

  private void selectBasedOnModelState(boolean newValue, ToggleTool tool) {
    if (newValue) {
      tool.select();
    } else {
      tool.deselect();
    }
  }

  public void initPresentation(final BooleanValueView view, final BooleanValueModel model) {
    view.addChangeListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        model.setValue(newValue);
      }
    });
    model.addChangeListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        view.setSelected(newValue);
      }
    });
    view.setSelected(model.getValue());
  }
}