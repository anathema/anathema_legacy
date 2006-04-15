package net.sf.anathema.charmentry.module;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.charmentry.view.BasicDataView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class CharmEntryModuleView implements IItemView {

  private final JPanel panel = new JPanel(new GridDialogLayout(1, false));
  private final String printName;
  private final Icon icon;

  public CharmEntryModuleView(String printName, Icon icon) {
    this.printName = printName;
    this.icon = icon;
  }

  public BasicDataView addCharmEntryView() {
    BasicDataView view = new BasicDataView();
    panel.add(view.getContent());
    return view;
  }

  public JComponent getComponent() {
    return panel;

  }

  public void setName(String newName) {
    // Nothing to do
  }

  public String getName() {
    return printName;
  }

  public Icon getIcon() {
    return icon;
  }

  public void addNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    // Nothing to do
  }

  public void removeNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }
}