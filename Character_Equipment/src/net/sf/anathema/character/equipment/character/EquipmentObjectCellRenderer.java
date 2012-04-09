package net.sf.anathema.character.equipment.character;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;

import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public class EquipmentObjectCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

	private final IEquipmentTemplateProvider templateProvider;
	
	public EquipmentObjectCellRenderer(IEquipmentTemplateProvider provider) {
		templateProvider = provider;
	}
	
@Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
	 
    String templateId = (String) value;
    JComponent component = (JComponent) super.getListCellRendererComponent(list, templateId, index, isSelected, cellHasFocus);
	IEquipmentTemplate template = templateProvider.loadTemplate(templateId);
	if (template != null) {
		component.setToolTipText(template.getTooltipDescription());
	}
    return component;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof EquipmentObjectCellRenderer;
  }

  @Override
  public int hashCode() {
    return 1;
  }
}