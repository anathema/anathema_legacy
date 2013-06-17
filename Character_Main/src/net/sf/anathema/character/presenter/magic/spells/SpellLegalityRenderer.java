package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

public class SpellLegalityRenderer extends LegalityCheckListCellRenderer {

  public SpellLegalityRenderer(Resources resources, final ISpellConfiguration spellConfiguration, SpellTooltipBuilder tooltipBuilder) {
    super(new SpellLegality(spellConfiguration), new SpellUiConfiguration(resources, tooltipBuilder));
  }
}