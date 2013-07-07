package net.sf.anathema.character.main.charm.special;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.main.magic.charms.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.main.library.trait.DefaultTrait;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.character.main.charm.CharmSpecialist;

public class TraitCapModifyingCharmConfiguration implements ITraitCapModifyingCharmConfiguration {
  private CharmSpecialist specialist;
  private final ITraitCapModifyingCharm specialCharm;
  private final CharmsModel config;
  private final ICharm charm;

  public TraitCapModifyingCharmConfiguration(CharmSpecialist specialist, CharmsModel config, ICharm charm,
                                             ITraitCapModifyingCharm specialCharm) {
    this.specialist = specialist;
    this.specialCharm = specialCharm;
    this.config = config;
    this.charm = charm;
  }

  @Override
  public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
    //nothing to do
  }

  @Override
  public void learn(boolean experienced) {
    applyModifier();
  }

  public void applyModifier() {
    DefaultTrait trait = (DefaultTrait) specialist.getTraits().getTrait(specialCharm.getTraitType());
    trait.applyCapModifier(specialCharm.getModifier());
  }

  @Override
  public void forget() {
    DefaultTrait trait = (DefaultTrait) specialist.getTraits().getTrait(specialCharm.getTraitType());
    trait.applyCapModifier(-specialCharm.getModifier());
  }

  @Override
  public ICharm getCharm() {
    return charm;
  }

  @Override
  public int getCreationLearnCount() {
    return config.isLearned(charm) ? 1 : 0;
  }

  @Override
  public int getCurrentLearnCount() {
    return config.isLearned(charm) ? 1 : 0;
  }
}