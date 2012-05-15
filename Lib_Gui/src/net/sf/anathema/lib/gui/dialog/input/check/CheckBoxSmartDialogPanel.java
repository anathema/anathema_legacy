package net.sf.anathema.lib.gui.dialog.input.check;

import com.google.common.base.Preconditions;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.action.ActionWidgetFactory;
import net.sf.anathema.lib.gui.action.SmartToggleAction;
import net.sf.anathema.lib.gui.dialog.input.AbstractSmartDialogPanel;
import net.sf.anathema.lib.model.BooleanModel;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckBoxSmartDialogPanel extends AbstractSmartDialogPanel {

  private final JCheckBox checkBox;
  private final BooleanModel model;

  public CheckBoxSmartDialogPanel(BooleanModel model, String label) {
    this.model = model;
    Preconditions.checkNotNull(model);
    checkBox = ActionWidgetFactory.createCheckBox(new SmartToggleAction(model, label));
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    model.addChangeListener(listener);
  }

  @Override
  public void requestFocus() {
    checkBox.requestFocus();
  }

  @Override
  public void fillInto(JPanel panel, int columnCount) {
    GridDialogLayoutData layoutData = GridDialogLayoutDataFactory
        .createHorizontalSpanData(columnCount);
    panel.add(checkBox, layoutData);
  }

  @Override
  public int getColumnCount() {
    return 1;
  }
}