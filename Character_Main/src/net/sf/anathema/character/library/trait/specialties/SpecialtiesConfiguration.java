package net.sf.anathema.character.library.trait.specialties;

import com.google.common.base.Strings;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpecialtiesConfiguration implements ISpecialtiesConfiguration {

  private final Map<TraitType, ISubTraitContainer> specialtiesByType = new HashMap<>();
  private final Map<ITraitReference, ISubTraitContainer> specialtiesByTrait = new HashMap<>();
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final Announcer<ITraitReferencesChangeListener> traitControl = Announcer.to(ITraitReferencesChangeListener.class);
  private Hero hero;
  private final InitializationContext context;
  private String currentName;
  private ITraitReference currentType;

  public SpecialtiesConfiguration(Hero hero, ITraitTypeGroup[] groups, InitializationContext context) {
    this.hero = hero;
    this.context = context;
    TraitType[] traitTypes = TraitTypeGroup.getAllTraitTypes(groups);
    for (Trait trait : TraitModelFetcher.fetch(hero).getTraits(traitTypes)) {
      ITraitReference reference = new DefaultTraitReference(trait);
      SpecialtiesContainer container = addSpecialtiesContainer(reference);
      specialtiesByType.put(trait.getType(), container);
     }
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
  public void addSelectionChangeListener(IChangeListener listener) {
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
  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  @Override
  public void addTraitListChangeListener(ITraitReferencesChangeListener listener) {
    traitControl.addListener(listener);
  }
}