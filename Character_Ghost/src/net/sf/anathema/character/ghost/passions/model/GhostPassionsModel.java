package net.sf.anathema.character.ghost.passions.model;

import com.google.common.base.Strings;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.ghost.passions.GhostPassionsTemplate;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.specialties.DefaultTraitReference;
import net.sf.anathema.character.library.trait.specialties.ITraitReferencesChangeListener;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GhostPassionsModel extends AbstractAdditionalModelAdapter implements IGhostPassionsModel {

  private final Map<ITraitType, ISubTraitContainer> passionByType = new HashMap<>();
  private final Map<ITraitReference, ISubTraitContainer> passionsByTrait = new HashMap<>();
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final ICharacterModelContext context;
  private final GhostPassionsTemplate template;
  private String currentName;
  private ITraitReference currentType;
  private IIntValueChangedListener passionChangeListener;

  @Override
  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Advantages;
  }

  public GhostPassionsModel(GhostPassionsTemplate template, ICharacterModelContext context) {
    this.context = context;
    this.template = template;
    ITraitType[] traitTypes = VirtueType.values();
    for (IGenericTrait trait : context.getTraitCollection().getTraits(traitTypes)) {
      ITraitReference reference = new DefaultTraitReference((ITrait) trait);
      PassionsContainer container = addPassionsContainer(reference);
      container.addSubTraitListener(new ISubTraitListener() {
        @Override
        public void subTraitValueChanged() {
          //nothing to do
        }

        @Override
        public void subTraitAdded(ISubTrait subTrait) {
          //nothing to do
        }

        @Override
        public void subTraitRemoved(ISubTrait subTrait) {
          control.announce().changeOccurred();
        }
      });
      passionByType.put(trait.getType(), container);
    }
    this.passionChangeListener = new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        control.announce().changeOccurred();
      }
    };
  }

  @Override
  public String getTemplateId() {
    return template.getId();
  }

  private int getAge() {
    IGenericTrait ageTrait = context.getTraitCollection().getTrait(new CustomizedBackgroundTemplate("Age"));
    return ageTrait == null ? 0 : ageTrait.getCurrentValue();
  }

  @Override
  public int getCurrentTotalPassions() {
    int total = 0;
    for (VirtueType virtue : VirtueType.values()) {
      total += getPassionContainer(virtue).getCurrentDotTotal();
    }
    return total;
  }

  @Override
  public int getMaxTotalPassions() {
    int total = 0;
    for (VirtueType virtue : VirtueType.values()) {
      total += getCurrentVirtueRating(virtue);
    }
    return total - getAge();
  }

  @Override
  public int getCurrentVirtueRating(VirtueType type) {
    return context.getTraitCollection().getTrait(type).getCurrentValue();
  }

  private PassionsContainer addPassionsContainer(ITraitReference reference) {
    IGenericTrait trait = context.getTraitCollection().getTrait(reference.getTraitType());
    ITraitContext traitContext = context.getTraitContext();
    PassionsContainer passionsContainer = new PassionsContainer(trait, reference, traitContext, this);
    passionsByTrait.put(reference, passionsContainer);
    return passionsContainer;
  }

  @Override
  public ISubTraitContainer getPassionContainer(ITraitReference trait) {
    return passionsByTrait.get(trait);
  }

  @Override
  public ISubTraitContainer getPassionContainer(ITraitType traitType) {
    return passionByType.get(traitType);
  }

  @Override
  public ITraitReference[] getAllTraits() {
    Set<ITraitReference> keySet = passionsByTrait.keySet();
    return keySet.toArray(new ITraitReference[keySet.size()]);
  }

  @Override
  public ITraitReference[] getAllEligibleTraits() {
    List<ITraitReference> keySet = new ArrayList<>(passionsByTrait.keySet());
    List<ITraitReference> toRemove = new ArrayList<>();
    for (ITraitReference trait : keySet) {
      if (!getPassionContainer(trait.getTraitType()).isNewSubTraitAllowed() && !toRemove.contains(trait)) {
        toRemove.add(trait);
      }
    }
    keySet.removeAll(toRemove);
    return keySet.toArray(new ITraitReference[keySet.size()]);
  }

  @Override
  public void setCurrentPassionName(String newPassionName) {
    this.currentName = newPassionName;
    control.announce().changeOccurred();
  }

  @Override
  public void setCurrentTrait(ITraitReference newValue) {
    if (currentType == newValue) {
      return;
    }
    this.currentType = newValue;
    control.announce().changeOccurred();
  }

  @Override
  public void commitSelection() {
    ISubTrait passion = getPassionContainer(currentType).addSubTrait(currentName);
    if (passion != null && passion.getCurrentValue() == 0) {
      passion.setCurrentValue(1);
    }
    if (passion != null) {
      passion.addCurrentValueListener(passionChangeListener);
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
    return context.getBasicCharacterContext().isExperienced();
  }

  @Override
  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  @Override
  public void addTraitListChangeListener(ITraitReferencesChangeListener listener) {
    //nothing to do
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    control.addListener(listener);
  }
}