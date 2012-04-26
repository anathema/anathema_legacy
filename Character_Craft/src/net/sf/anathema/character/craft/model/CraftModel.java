package net.sf.anathema.character.craft.model;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.craft.presenter.ICraftModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import org.jmock.example.announcer.Announcer;

import java.util.Arrays;
import java.util.List;

public class CraftModel implements ICraftModel {

  private final Announcer<IRemovableEntryListener> control = Announcer.to(IRemovableEntryListener.class);
  private String currentName;
  private final IAggregatedTrait trait;
  private final ICharacterModelContext context;

  public CraftModel(ICharacterModelContext context) {
    this.context = context;
    this.trait = (IAggregatedTrait) context.getTraitCollection().getFavorableTrait(AbilityType.Craft);
    trait.getSubTraits().addSubTraitListener(new ISubTraitListener() {
      @Override
      public void subTraitAdded(final ISubTrait subTrait) {
        control.announce().entryAdded(subTrait);
      }

      @Override
      public void subTraitRemoved(final ISubTrait subTrait) {
        control.announce().entryRemoved(subTrait);
      }

      @Override
      public void subTraitValueChanged() {
        // nothing to do;
      }
    });
  }

  @Override
  public int getAbsoluteMaximum() {
    return trait.getMaximalValue();
  }

  @Override
  public boolean isRemovable(ISubTrait craft) {
    return trait.getSubTraits().isRemovable(craft);
  }

  @Override
  public void setCurrentName(String newValue) {
    this.currentName = newValue;
    fireEntryChanged();
  }

  @Override
  public void removeEntry(final ISubTrait entry) {
    trait.getSubTraits().removeSubTrait(entry);
  }

  private boolean isEntryAllowed() {
    return !StringUtilities.isNullOrEmpty(currentName);
  }

  @Override
  public ISubTrait commitSelection() {
    return trait.getSubTraits().addSubTrait(currentName);
  }

  @Override
  public List<ISubTrait> getEntries() {
    return Arrays.asList(trait.getSubTraits().getSubTraits());
  }

  private void fireEntryChanged() {
    control.announce().entryAllowed(isEntryAllowed());
  }

  @Override
  public void addModelChangeListener(IRemovableEntryListener<ISubTrait> listener) {
    control.addListener(listener);
  }

  @Override
  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  @Override
  public boolean isExperienced() {
    return context.getBasicCharacterContext().isExperienced();
  }
}