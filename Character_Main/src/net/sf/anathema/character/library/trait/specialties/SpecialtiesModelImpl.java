package net.sf.anathema.character.library.trait.specialties;

import com.google.common.base.Strings;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.model.advance.models.SpecialtyExperienceModel;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
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
  private final Map<ITraitReference, ISubTraitContainer> specialtiesByTrait = new HashMap<>();
  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private final Announcer<ITraitReferencesChangeListener> traitControl = Announcer.to(ITraitReferencesChangeListener.class);
  private Hero hero;
  private String currentName;
  private ITraitReference currentType;

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    this.hero = hero;
    for (Trait trait : AbilityModelFetcher.fetch(hero).getAll()) {
      ITraitReference reference = new DefaultTraitReference(trait);
      SpecialtiesContainer container = addSpecialtiesContainer(reference);
      specialtiesByType.put(trait.getType(), container);
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

  private SpecialtiesContainer addSpecialtiesContainer(ITraitReference reference) {
    SpecialtiesContainer specialtiesContainer = new SpecialtiesContainer(reference, hero);
    specialtiesByTrait.put(reference, specialtiesContainer);
    return specialtiesContainer;
  }

  @Override
  public ISubTraitContainer getSpecialtiesContainer(ITraitReference trait) {
    return specialtiesByTrait.get(trait);
  }

  @Override
  public ISubTraitContainer getSpecialtiesContainer(TraitType traitType) {
    return specialtiesByType.get(traitType);
  }

  @Override
  public ITraitReference[] getAllTraits() {
    Set<ITraitReference> keySet = specialtiesByTrait.keySet();
    return keySet.toArray(new ITraitReference[keySet.size()]);
  }

  @Override
  public ITraitReference[] getAllEligibleTraits() {
    List<ITraitReference> keySet = new ArrayList<>(specialtiesByTrait.keySet());
    Set<ITraitReference> toRemove = new HashSet<>();
    for (ITraitReference trait : keySet) {
      if (!getSpecialtiesContainer(trait.getTraitType()).isNewSubTraitAllowed()) {
        toRemove.add(trait);
      }
    }
    keySet.removeAll(toRemove);
    return keySet.toArray(new ITraitReference[keySet.size()]);
  }

  @Override
  public void setCurrentSpecialtyName(String newSpecialtyName) {
    this.currentName = newSpecialtyName;
    control.announce().changeOccurred();
  }

  @Override
  public void setCurrentTrait(ITraitReference newValue) {
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
  public boolean isEntryComplete() {
    return !Strings.isNullOrEmpty(currentName) && currentType != null;
  }

  @Override
  public boolean isExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }

  @Override
  public void addTraitListChangeListener(ITraitReferencesChangeListener listener) {
    traitControl.addListener(listener);
  }
}