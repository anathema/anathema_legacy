package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.MagicDescriptionContributor;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.ScreenDisplayInfoContributor;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.source.MagicSourceContributor;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.resources.Resources;

public class SpellTooltipBuilder {

  private final Resources resources;
  private final CombinedSpellAndMagicProperties properties;
  private MagicDescriptionProvider magicDescriptionProvider;

  public SpellTooltipBuilder(Resources resources, CombinedSpellAndMagicProperties properties, MagicDescriptionProvider magicDescriptionProvider) {
    this.resources = resources;
    this.properties = properties;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public void createTooltip(Spell spell, ConfigurableTooltip tooltip) {
    tooltip.appendTitleLine(resources.getString(spell.getId()));
    tooltip.appendLine(properties.getCircleLabel(), getCircleValue(spell));
    tooltip.appendLine(getCostLabel(), getCostValue(spell));
    tooltip.appendLine(getTargetLabel(), getTargetValue(spell));
    tooltip.appendLine(getSourceLabel(), getSourceValue(spell));
    new MagicDescriptionContributor(magicDescriptionProvider).buildStringForMagicWithoutSpecials(tooltip, spell);
  }

  private String getCircleValue(Spell spell) {
    return resources.getString(spell.getCircleType().getId());
  }

  private String getCostValue(Spell spell) {
    return new ScreenDisplayInfoContributor(resources).createCostString(spell);
  }

  private String getTargetValue(Spell spell) {
    if (spell.getTarget() == null) {
      return getUndefinedString();
    }
    return resources.getString("Spells.Target." + spell.getTarget());
  }

  private String getSourceValue(Spell spell) {
    return new MagicSourceContributor<Spell>(resources).createSourceString(spell);
  }

  public String getUndefinedString() {
    return resources.getString("CardView.CharmConfiguration.Spells.Target.Undefined");
  }

  private String getTargetLabel() {
    return resources.getString("CardView.CharmConfiguration.Spells.Target");
  }

  private String getSourceLabel() {
    return resources.getString("CardView.CharmConfiguration.Spells.Source");
  }

  private String getCostLabel() {
    return resources.getString("CardView.CharmConfiguration.Spells.Cost");
  }
}
