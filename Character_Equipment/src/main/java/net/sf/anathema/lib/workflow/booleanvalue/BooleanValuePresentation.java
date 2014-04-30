package net.sf.anathema.lib.workflow.booleanvalue;

import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;

public class BooleanValuePresentation {

  public void initPresentation(final ToggleTool tool, final BooleanValueModel model) {
    tool.setCommand(() -> model.setValue(!model.getValue()));
    model.addChangeListener(newValue -> selectBasedOnModelState(newValue, tool));
    selectBasedOnModelState(model.getValue(), tool);
  }

  private void selectBasedOnModelState(boolean newValue, ToggleTool tool) {
    if (newValue) {
      tool.select();
    } else {
      tool.deselect();
    }
  }

  public void initPresentation(final IBooleanValueView view, final BooleanValueModel model) {
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