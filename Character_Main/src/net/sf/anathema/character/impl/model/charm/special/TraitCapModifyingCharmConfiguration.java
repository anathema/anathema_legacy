package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.magic.charms.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.impl.model.charm.CharmSpecialist;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.model.charm.CharmModel;
import net.sf.anathema.character.model.charm.special.ITraitCapModifyingCharmConfiguration;

public class TraitCapModifyingCharmConfiguration implements ITraitCapModifyingCharmConfiguration {
  private CharmSpecialist specialist;
  private final ITraitCapModifyingCharm specialCharm;
  private final CharmModel config;
  private final ICharm charm;

  public TraitCapModifyingCharmConfiguration(CharmSpecialist specialist, CharmModel config, ICharm charm,
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