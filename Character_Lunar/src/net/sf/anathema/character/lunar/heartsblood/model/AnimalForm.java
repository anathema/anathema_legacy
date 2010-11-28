package net.sf.anathema.character.lunar.heartsblood.model;

import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;

public class AnimalForm implements IAnimalForm {

  private final String name;
  private final int strength;
  private final int stamina;
  private final boolean experienceLearned;

  public AnimalForm(String name, int strength, int stamina, boolean experienceLearned) {
    this.name = name;
    this.strength = strength;
    this.stamina = stamina;
    this.experienceLearned = experienceLearned;
  }

  public String getName() {
    return name;
  }

  public int getStrength() {
    return strength;
  }

  public int getStamina() {
    return stamina;
  }

  public boolean isCreationLearned() {
    return !experienceLearned;
  }
}