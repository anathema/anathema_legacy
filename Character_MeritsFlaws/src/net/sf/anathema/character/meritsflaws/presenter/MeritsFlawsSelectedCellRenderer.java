package net.sf.anathema.character.meritsflaws.presenter;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.model.perk.PerkType;
import net.sf.anathema.lib.resources.IResources;

public class MeritsFlawsSelectedCellRenderer extends DefaultListCellRenderer {

  private static final long serialVersionUID = 1992736886421420171L;
  private final IResources resources;
  private final IMeritsFlawsModel model;

  public MeritsFlawsSelectedCellRenderer(IResources resources, IMeritsFlawsModel model) {
    this.resources = resources;
    this.model = model;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    IQualitySelection<IPerk> selection = (IQualitySelection<IPerk>) value;
    IPerk perk = selection.getQuality();
    String perkName = resources.getString(perk.getType().getId() + "." //$NON-NLS-1$
        + perk.getCategory().getId()
        + "." //$NON-NLS-1$
        + perk.getId());
    String printName = perkName + " (" + selection.getPointValue() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    Component renderComponent = super.getListCellRendererComponent(list, printName, index, isSelected, cellHasFocus);
    if (selection.getQuality().getType() == PerkType.Flaw) {
      renderComponent.setForeground(Color.RED.darker());
    }
    renderComponent.setEnabled(model.isActive(selection));
    return renderComponent;
  }
}