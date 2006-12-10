package net.sf.anathema.character.library.trait.view;

import javax.swing.JPanel;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.lib.gui.layout.GroupedColumnPanel;

public class GroupedTraitView {

  private final GroupedColumnPanel panel;

  public GroupedTraitView(int columnCount) {
    panel = new GroupedColumnPanel(columnCount);
  }

  public IToggleButtonTraitView<SimpleTraitView> addTraitView(
      String labelText,
      int value,
      int maxValue,
      boolean selected,
      IIconToggleButtonProperties properties,
      IIntValueDisplayFactory factory) {
    SimpleTraitView view = new SimpleTraitView(factory, labelText, value, maxValue);
    FrontToggleButtonTraitViewWrapper<SimpleTraitView> traitView = new FrontToggleButtonTraitViewWrapper<SimpleTraitView>(
        view,
        properties,
        selected);
    traitView.addComponents(panel.getCurrentColumn());
    return traitView;
  }

  public void startNewGroup(String groupLabel) {
    panel.startNewGroup(groupLabel);
  }

  public void addOverallView(JPanel container) {
    panel.addOverallView(container);
  }
}