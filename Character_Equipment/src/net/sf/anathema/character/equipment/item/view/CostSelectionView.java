package net.sf.anathema.character.equipment.item.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CostSelectionView implements AdditiveView {
  private final ISelectableIntValueView<String> selection;
  protected final JLabel label;

  public CostSelectionView(String labelText, String[] backgrounds, IntegerViewFactory factory) {
    this.label = new JLabel(labelText);
    this.selection = new SelectableIntValueView<String>(factory, 0, 6);
    selection.setSelectableValues(backgrounds);
  }

  @Override
  public void addTo(JPanel panel, CC data) {
    panel.add(label);
    selection.addTo(panel, data);
  }

  public void setValue(ItemCost cost) {
    if (cost == null) {
      selection.setSelectedObject(null);
      selection.setValue(0);
    } else {
      selection.setSelectedObject(cost.getType());
      selection.setValue(cost.getValue());
    }
  }

  public void addSelectionChangedListener(ISelectionIntValueChangedListener<String> listener) {
    selection.addSelectionChangedListener(listener);
  }
}
