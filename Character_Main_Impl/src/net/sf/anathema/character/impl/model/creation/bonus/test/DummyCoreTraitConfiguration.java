package net.sf.anathema.character.impl.model.creation.bonus.test;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedAttributeTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.util.IIdentificate;

public class DummyCoreTraitConfiguration extends AbstractTraitCollection implements ICoreTraitConfiguration {

  private IIdentifiedTraitTypeGroup[] abilityTypeGroups;

  public IBackgroundConfiguration getBackgrounds() {
    throw new NotYetImplementedException();
  }

  private IIdentifiedTraitTypeGroup getAttributeTypeGroup(final AttributeGroupType type) {
    IIdentifiedCasteTraitTypeGroup[] allAttributeTypeGroups = getAttributeTypeGroups();
    return ArrayUtilities.find(new Predicate<IIdentifiedCasteTraitTypeGroup>() {
      @Override
      public boolean evaluate(IIdentifiedCasteTraitTypeGroup group) {
        return group.getGroupId() == type;
      }
    }, allAttributeTypeGroups);
  }

  public ITrait[] getAllTraits(AttributeGroupType groupType) {
    IIdentifiedTraitTypeGroup attributeTypeGroup = getAttributeTypeGroup(groupType);
    TraitGroup traitGroup = new TraitGroup(this, attributeTypeGroup);
    return traitGroup.getGroupTraits();
  }

  public boolean containsAllTraits(AttributeGroupType attributeGroupType, ITrait[] traits) {
    for (ITrait trait : traits) {
      if (!ArrayUtilities.contains(getAllTraits(attributeGroupType), trait)) {
        return false;
      }
    }
    return true;
  }

  public void addTestTrait(ITrait trait) {
    addTrait(trait);
  }

  public void setAbilityTypeGroups(IIdentifiedTraitTypeGroup[] abilityTypeGroups) {
    this.abilityTypeGroups = abilityTypeGroups;
  }

  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return abilityTypeGroups;
  }

  public final IIdentifiedCasteTraitTypeGroup[] getAttributeTypeGroups() {
    return new IIdentifiedCasteTraitTypeGroup[] {
        new IdentifiedAttributeTypeGroup(AttributeGroupType.Physical),
        new IdentifiedAttributeTypeGroup(AttributeGroupType.Social),
        new IdentifiedAttributeTypeGroup(AttributeGroupType.Mental) };
  }

  public IIdentificate getAbilityGroupId(AbilityType abilityType) {
    for (IIdentifiedTraitTypeGroup group : getAbilityTypeGroups()) {
      if (group.contains(abilityType)) {
        return group.getGroupId();
      }
    }
    throw new IllegalStateException("Ability type in no group: " + abilityType); //$NON-NLS-1$
  }
}