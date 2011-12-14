package net.sf.anathema.acceptance.fixture.character.traits;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterColumnFixture;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.lib.registry.IIdentificateRegistry;

public class CheckBackgroundFixture extends AbstractCharacterColumnFixture {

  public String backgroundType;
  public int value;

  public final int value() {
    ITrait foundTrait = getTrait();
    return foundTrait == null ? 0 : foundTrait.getCurrentValue();
  }

  protected final ITrait getTrait() {
    @SuppressWarnings("unchecked")
    ICharacter character = new CharacterSummary(summary).getCharacter();
    ICharacterStatistics statistics = character.getStatistics();
    return statistics.getTraitConfiguration().getTrait(getTraitType());
  }

  public boolean isAvailable() {
    return ArrayUtilities.contains(getCharacterStatistics().getTraitConfiguration()
        .getBackgrounds()
        .getAllAvailableBackgroundTemplates(), getTraitType());
  }

  private IBackgroundTemplate getTraitType() {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = getCharacterGenerics().getBackgroundRegistry();
    if (backgroundRegistry.idRegistered(backgroundType)) {
      return backgroundRegistry.getById(backgroundType);
    }
    return new CustomizedBackgroundTemplate(backgroundType);
  }
}