package net.sf.anathema.character.main.presenter.magic.spells;

import net.sf.anathema.character.main.magic.ISpell;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class SpellUiConfiguration extends AbstractUIConfiguration {

  private final Resources resources;
  private final SpellTooltipBuilder tooltipBuilder;

  public SpellUiConfiguration(Resources resources, SpellTooltipBuilder tooltipBuilder) {
    this.resources = resources;
    this.tooltipBuilder = tooltipBuilder;
  }

  @Override
  public String getLabel(Object value) {
    return resources.getString(((Identifier) value).getId());
  }

  @Override
  public String getToolTipText(Object value) {
    return tooltipBuilder.createTooltip((ISpell) value);
  }
}