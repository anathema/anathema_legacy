package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class SpellLegalityRenderer extends LegalityCheckListCellRenderer {

  private final Resources resources;
  private final SpellTooltipBuilder tooltipBuilder;

  public SpellLegalityRenderer(Resources resources, final ISpellConfiguration spellConfiguration, SpellTooltipBuilder tooltipBuilder) {
    super(new SpellLegality(spellConfiguration));
    this.resources = resources;
    this.tooltipBuilder = tooltipBuilder;
  }

  @Override
  protected String getPrintName(Object value) {
    return resources.getString(((Identifier) value).getId());
  }

  @Override
  protected String getToolTip(Object value) {
    return tooltipBuilder.createTooltip((ISpell) value);
  }
}