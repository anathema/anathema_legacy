package net.sf.anathema.gis.platform;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.gis.main.impl.view.AnathemaGisView;
import net.sf.anathema.gis.main.view.IAnathemaGisView;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;

public class GisModuleView implements IItemView {

  private final JPanel panel = new JPanel(new BorderLayout());
  private final String printName;

  public GisModuleView(String printName) {
    this.printName = printName;
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
    return null;
  }

  public void addNameChangedListener(IStringValueChangedListener nameListener) {
    // Nothing to do
  }

  public void removeNameChangedListener(IStringValueChangedListener nameListener) {
    // Nothing to do
  }

  public void dispose() {
    // Nothing to do
  }

  public IAnathemaGisView addGisView() {
    AnathemaGisView anathemaGisView = new AnathemaGisView();
    panel.add(anathemaGisView.getContent());
    return anathemaGisView;
  }
}