package net.sf.anathema.character.main.library.trait.specialties;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.library.trait.subtrait.AbstractSubTraitContainer;
import net.sf.anathema.hero.model.Hero;

public class SpecialtiesContainer extends AbstractSubTraitContainer {

  public static final int ALLOWED_SPECIALTY_COUNT = 3;
  private final TraitType type;
  private final Hero hero;

  public SpecialtiesContainer(TraitType type, Hero hero) {
    this.type = type;
    this.hero = hero;
  }

  @Override
  public void handleAdditionOfContainedEquivalent(Specialty subTrait) {
    int maxAddition = ALLOWED_SPECIALTY_COUNT - getCurrentDotTotal();
    int addition = Math.min(1, maxAddition);
    subTrait.setCurrentValue(subTrait.getCurrentValue() + addition);
  }

  @Override
  public boolean isNewSubTraitAllowed() {
    return getCurrentDotTotal() < ALLOWED_SPECIALTY_COUNT;
  }

  @Override
  public Specialty createSubTrait(String name) {
    return new DefaultSpecialty(hero, this, type, name);
  }
}