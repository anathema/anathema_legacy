package net.sf.anathema.character.library.trait.view;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class RearToggleButtonTraitViewWrapper<K extends ITraitView< ? >> extends
    AbstractToggleButtonTraitViewWrapper<K> implements IToggleButtonTraitView<K> {

  public RearToggleButtonTraitViewWrapper(K view, IIconToggleButtonProperties properties, boolean selected) {
    super(view, properties, selected);
  }

  @Override
  public void addComponents(JPanel panel) {
    super.addComponents(panel);
    addInnerView(panel);
    GridDialogLayoutData gridLayoutData = new GridDialogLayoutData();
    gridLayoutData.setHorizontalAlignment(GridAlignment.END);
    JComponent button = getButton().getComponent();
    panel.add(button, gridLayoutData);
  }
}