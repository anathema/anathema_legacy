package net.sf.anathema.character.main.presenter.magic.spells;

import net.sf.anathema.character.main.magic.view.AbstractMagicLearnProperties;
import net.sf.anathema.character.main.magic.ISpell;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.spells.SpellsModelFetcher;
import net.sf.anathema.character.main.view.magic.ISpellViewProperties;
import net.sf.anathema.framework.ui.IdentifierConfiguration;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheck;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class SpellViewProperties extends AbstractMagicLearnProperties implements ISpellViewProperties {

  private final net.sf.anathema.hero.spells.SpellModel spellConfiguration;
  private final Hero hero;
  private final SpellTooltipBuilder tooltipBuilder;

  public SpellViewProperties(Resources resources, Hero hero,
                             MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    this.hero = hero;
    this.spellConfiguration = SpellsModelFetcher.fetch(hero);
    this.tooltipBuilder = new SpellTooltipBuilder(resources, this, magicDescriptionProvider);
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
  public boolean isMagicSelectionAvailable(Object selection) {
    return selection != null && spellConfiguration.isSpellAllowed((ISpell) selection);
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
    if (enabled && ExperienceModelFetcher.fetch(hero).isExperienced()) {
      for (Object spellObject : list) {
        ISpell spell = (ISpell) spellObject;
        if (spellConfiguration.isLearnedOnCreation(spell)) {
          enabled = false;
          break;
        }
      }
    }
    return enabled;
  }
}