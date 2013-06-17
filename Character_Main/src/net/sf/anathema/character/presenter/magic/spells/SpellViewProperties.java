package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.framework.magic.view.AbstractMagicLearnProperties;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.view.magic.ISpellViewProperties;
import net.sf.anathema.framework.ui.IdentifierConfiguration;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheck;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import javax.swing.JList;

public class SpellViewProperties extends AbstractMagicLearnProperties implements ISpellViewProperties {

  private final ISpellConfiguration spellConfiguration;
  private final ICharacter character;
  private final SpellTooltipBuilder tooltipBuilder;

  public SpellViewProperties(Resources resources, ICharacter character,
                             MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    this.character = character;
    this.spellConfiguration = character.getSpells();
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
  public boolean isRempveAllowed(JList list) {
    boolean enabled = !list.isSelectionEmpty();
    if (enabled && ExperienceModelFetcher.fetch(character).isExperienced()) {
      for (Object spellObject : list.getSelectedValuesList()) {
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