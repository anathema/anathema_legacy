package net.sf.anathema.hero.specialties.model;

import com.google.common.base.Strings;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesListener;
import net.sf.anathema.character.main.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.main.library.trait.specialties.Specialty;
import net.sf.anathema.character.main.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.specialties.advance.experience.SpecialtyExperienceModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpecialtiesModelImpl implements SpecialtiesModel, HeroModel {

  private final Map<TraitType, ISubTraitContainer> specialtiesByType = new HashMap<>();
  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private Hero hero;
  private String currentName;
  private TraitType currentType;

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
    for (Trait trait : AbilityModelFetcher.fetch(hero).getAll()) {
      SpecialtiesContainer specialtiesContainer = new SpecialtiesContainer(trait.getType(), hero);
      specialtiesByType.put(trait.getType(), specialtiesContainer);
    }
    addExperiencePoints(hero);
  }

  private void addExperiencePoints(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    if (pointsModel == null) {
      return;
    }
    pointsModel.addToExperienceOverview(new SpecialtyExperienceModel(hero));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (Trait ability : AbilityModelFetcher.fetch(hero).getAll()) {
      getSpecialtiesContainer(ability.getType()).addSubTraitListener(new SpecialtiesListener(announcer));
    }
  }

  @Override
  public Identifier getId() {
    return SpecialtiesModel.ID;
  }

  @Override
  public ISubTraitContainer getSpecialtiesContainer(TraitType traitType) {
    return specialtiesByType.get(traitType);
  }

  @Override
  public TraitType[] getAllParentTraits() {
    Set<TraitType> keySet = specialtiesByType.keySet();
    return keySet.toArray(new TraitType[keySet.size()]);
  }

  @Override
  public TraitType[] getAllEligibleParentTraits() {
    List<TraitType> eligibleTypes = new ArrayList<>(specialtiesByType.keySet());
    Set<TraitType> toRemove = new HashSet<>();
    for (TraitType type : eligibleTypes) {
      if (!getSpecialtiesContainer(type).isNewSubTraitAllowed()) {
        toRemove.add(type);
      }
    }
    eligibleTypes.removeAll(toRemove);
    return eligibleTypes.toArray(new TraitType[eligibleTypes.size()]);
  }

  @Override
  public void setCurrentSpecialtyName(String newSpecialtyName) {
    this.currentName = newSpecialtyName;
    control.announce().changeOccurred();
  }

  @Override
  public void setCurrentTrait(TraitType newValue) {
    this.currentType = newValue;
    control.announce().changeOccurred();
  }

  @Override
  public void commitSelection() {
    Specialty specialty = getSpecialtiesContainer(currentType).addSubTrait(currentName);
    if (specialty != null && specialty.getCurrentValue() == 0) {
      specialty.setCurrentValue(1);
    }
  }

  @Override
  public void clear() {
    currentName = null;
    currentType = null;
    control.announce().changeOccurred();
  }

  @Override
  public void addSelectionChangeListener(ChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public TraitType getCurrentTrait() {
    return currentType;
  }

  @Override
  public String getCurrentName() {
    return currentName;
  }

  @Override
  public boolean isEntryComplete() {
    return !Strings.isNullOrEmpty(currentName) && currentType != null;
  }

  @Override
  public boolean isExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }
}