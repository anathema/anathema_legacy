package net.sf.anathema.character.library.trait.view;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;

public class FrontToggleButtonTraitViewWrapper extends AbstractToggleButtonTraitViewWrapper implements
    IToggleButtonTraitView,
    IGridDialogPanelContent {

  public FrontToggleButtonTraitViewWrapper(ITraitView view, IIconToggleButtonProperties properties, boolean selected) {
    super(view, properties, selected);
  }

  @Override
  public void addComponents(JPanel panel) {
    super.addComponents(panel);
    GridDialogLayoutData gridLayoutData = new GridDialogLayoutData();
    gridLayoutData.setHorizontalIndent(5);
    panel.add(getButton().getComponent(), gridLayoutData);
    addInnerView(panel);
  }
}