package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JComponent;
import javax.swing.JList;
import java.awt.Component;

public class SpellLegalityRenderer extends LegalityCheckListCellRenderer {

  private final ISpellConfiguration spellConfiguration;
  private final SpellTooltipBuilder tooltipBuilder;

  public SpellLegalityRenderer(Resources resources, ISpellConfiguration spellConfiguration, SpellTooltipBuilder tooltipBuilder) {
    super(resources);
    this.spellConfiguration = spellConfiguration;
    this.tooltipBuilder = tooltipBuilder;
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    JComponent rendererComponent = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    String tooltip = tooltipBuilder.createTooltip((ISpell) value);
    rendererComponent.setToolTipText(tooltip);
    return rendererComponent;
  }

  @Override
  protected boolean isLegal(Object object) {
    return spellConfiguration.isSpellAllowed((ISpell) object);
  }
}