package net.sf.anathema.gis.platform;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.gis.main.impl.view.AnathemaGisView;
import net.sf.anathema.gis.main.view.IAnathemaGisView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class GisModuleView implements IItemView {

  private final JPanel panel = new JPanel(new BorderLayout());
  private final String printName;

  public GisModuleView(String printName) {
    this.printName = printName;
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }

  @Override
  public void setName(String newName) {
    // Nothing to do
  }

  @Override
  public String getName() {
    return printName;
  }

  @Override
  public Icon getIcon() {
    return null;
  }

  @Override
  public void addNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    // Nothing to do
  }

  @Override
  public void removeNameChangedListener(IObjectValueChangedListener<String> nameListener) {
    // Nothing to do
  }

  @Override
  public void dispose() {
    // Nothing to do
  }

  public IAnathemaGisView addGisView() {
    AnathemaGisView anathemaGisView = new AnathemaGisView();
    panel.add(anathemaGisView.getComponent());
    return anathemaGisView;
  }
}