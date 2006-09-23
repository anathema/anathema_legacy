package net.sf.anathema.character.library.trait.view;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class FrontToggleButtonTraitViewWrapper<K extends ITraitView< ? >> extends
    AbstractToggleButtonTraitViewWrapper<K> implements IToggleButtonTraitView<K>, IGridDialogPanelContent {

  public FrontToggleButtonTraitViewWrapper(K view, IIconToggleButtonProperties properties, boolean selected) {
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

  public void addComponents(IGridDialogPanel abilityPanel) {
    abilityPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        addComponents(panel);
      }
    });
  }
}