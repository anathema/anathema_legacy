package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.framework.ui.IdentifierConfiguration;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.magic.display.AbstractMagicLearnProperties;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellLegality;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheck;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class CombinedSpellAndMagicProperties extends AbstractMagicLearnProperties implements SpellViewProperties {

  private final SpellsModel spellConfiguration;
  private final SpellTooltipBuilder tooltipBuilder;
  private final ExperienceModel experienceModel;

  public CombinedSpellAndMagicProperties(Resources resources,
                                         MagicDescriptionProvider magicDescriptionProvider, SpellsModel spellConfiguration, ExperienceModel experience) {
    super(resources);
    this.spellConfiguration = spellConfiguration;
    this.tooltipBuilder = new SpellTooltipBuilder(resources, this, magicDescriptionProvider);
    this.experienceModel = experience;
  }

  @Override
  public String getCircleLabel() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Circle");
  }

  @Override
  public AgnosticUIConfiguration getAvailableMagicRenderer() {
    return new SpellUiConfiguration(getResources(), tooltipBuilder);
  }

  @Override
  public LegalityCheck getLegalityCheck() {
    return new SpellLegality(spellConfiguration);
  }

  @Override
  public AgnosticUIConfiguration<Identifier> getCircleSelectionRenderer() {
    return new IdentifierConfiguration(getResources());
  }

  @Override
  public boolean isMagicSelectionAvailable(List selection) {
    return !selection.isEmpty() && spellConfiguration.isSpellAllowed((Spell) selection.get(0));
  }

  @Override
  public String getAddButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.Spells.AddToolTip");
  }

  @Override
  public String getRemoveButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.Spells.RemoveToolTip");
  }

  @Override
  public boolean isRemoveAllowed(List list) {
    boolean enabled = !list.isEmpty();
    if (enabled && experienceModel.isExperienced()) {
      for (Object spellObject : list) {
        Spell spell = (Spell) spellObject;
        if (spellConfiguration.isLearnedOnCreation(spell)) {
          enabled = false;
          break;
        }
      }
    }
    return enabled;
  }
}