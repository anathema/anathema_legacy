package net.sf.anathema.character.library.trait.specialties;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SpecialtyConfiguration implements ISpecialtyConfiguration {

  private final Map<ITraitType, ISubTraitContainer> specialtiesByTrait = new HashMap<ITraitType, ISubTraitContainer>();
  private final ITraitType[] traitTypes;
  private String currentName;
  private ITraitType currentType;
  private final ChangeControl control = new ChangeControl();

  public SpecialtyConfiguration(ITraitCollection traitCollection, ITraitTypeGroup[] groups) {
    this.traitTypes = TraitTypeGroup.getAllTraitTypes(groups);
    for (ITrait ability : traitCollection.getTraits(getAllTraitTypes())) {
      specialtiesByTrait.put(ability.getType(), ability.createSpecialtiesContainer());
    }
  }

  public ISubTraitContainer getSpecialtiesContainer(ITraitType traitType) {
    return specialtiesByTrait.get(traitType);
  }

  public ITraitType[] getAllTraitTypes() {
    return traitTypes;
  }

  public void setCurrentSpecialtyName(String newSpecialtyName) {
    this.currentName = newSpecialtyName;
    control.fireChangedEvent();
  }

  public void setCurrentTraitType(ITraitType newValue) {
    this.currentType = newValue;
    control.fireChangedEvent();
  }

  public void commitSelection() {
    specialtiesByTrait.get(currentType).addSubTrait(currentName);
  }

  public void clear() {
    currentName = null;
    currentType = null;
    control.fireChangedEvent();
  }

  public void addCurrentSelectionListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public boolean isEntryComplete() {
    return !StringUtilities.isNullOrEmpty(currentName) && currentType != null;
  }
}