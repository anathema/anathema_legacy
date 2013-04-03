package net.sf.anathema.character.equipment.item.view.swing;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.item.view.CostSelectionView;
import net.sf.anathema.character.equipment.item.view.SelectableIntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingCostSelectionView implements AdditiveView, CostSelectionView {
  private final SelectableIntValueView<String> selection;
  protected final JLabel label;

  public SwingCostSelectionView(String labelText, IntegerViewFactory factory) {
    this.label = new JLabel(labelText);
    this.selection = new SwingSelectableIntValueView<>(factory, 0, 6);
  }

  @Override
  public void setSelectableBackgrounds(String[] backgrounds) {
    selection.setSelectableValues(backgrounds);
  }

  @Override
  public void addTo(JPanel panel, CC data) {
    panel.add(label);
    selection.addTo(panel, data);
  }

  @Override
  public void setValue(ItemCost cost) {
    if (cost == null) {
      selection.setSelectedObject(null);
      selection.setValue(0);
    } else {
      selection.setSelectedObject(cost.getType());
      selection.setValue(cost.getValue());
    }
  }

  @Override
  public void addSelectionChangedListener(ISelectionIntValueChangedListener<String> listener) {
    selection.addSelectionChangedListener(listener);
  }
}
