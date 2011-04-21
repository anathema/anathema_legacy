package net.sf.anathema.character.ghost.passions.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
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
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class GhostPassionsModel extends AbstractAdditionalModelAdapter implements IGhostPassionsModel {

  private final Map<ITraitType, ISubTraitContainer> passionByType = new HashMap<ITraitType, ISubTraitContainer>();
  private final Map<ITraitReference, ISubTraitContainer> passionsByTrait = new HashMap<ITraitReference, ISubTraitContainer>();
  private final ChangeControl control = new ChangeControl();
  private final GenericControl<ITraitReferencesChangeListener> traitControl = new GenericControl<ITraitReferencesChangeListener>();
  private final ICharacterModelContext context;
  private final GhostPassionsTemplate template;
  private String currentName;
  private ITraitReference currentType;

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Concept;
  }

  public GhostPassionsModel(
	  GhostPassionsTemplate template,
      final ICharacterModelContext context) {
    this.context = context;
    this.template = template;
    ITraitType[] traitTypes = VirtueType.values();
    for (IGenericTrait trait : context.getTraitCollection().getTraits(traitTypes)) {
    	ITraitReference reference = new DefaultTraitReference((ITrait) trait);
        PassionsContainer container = addPassionsContainer(reference);
        passionByType.put(trait.getType(), container);
    }
  }
  
  public String getTemplateId()
  {
	  return template.getId();
  }
  
  private int getAge()
  {
	  IGenericTrait ageTrait = context.getTraitCollection().getTrait(new CustomizedBackgroundTemplate("Age"));
	  return ageTrait == null ? 0 : ageTrait.getCurrentValue();
  }
  
  public int getCurrentTotalPassions()
  {
	  int total = 0;
	  for (VirtueType virtue : VirtueType.values())
		  total += getPassionContainer(virtue).getCurrentDotTotal();
	  return total;
  }
  
  public int getMaxTotalPassions()
  {
	  int total = 0;
	  for (VirtueType virtue : VirtueType.values())
		  total += getCurrentVirtueRating(virtue);
	  return total - getAge();
  }
  
  public int getCurrentVirtueRating(VirtueType type)
  {
	  return context.getTraitCollection().getTrait(type).getCurrentValue();
  }

  private PassionsContainer addPassionsContainer(ITraitReference reference) {
    PassionsContainer passionsContainer = new PassionsContainer(context.getTraitCollection().getTrait(reference.getTraitType()),
    			reference, context.getTraitContext(), this);
    passionsByTrait.put(reference, passionsContainer);
    return passionsContainer;
  }

  public ISubTraitContainer getPassionContainer(ITraitReference trait) {
    return passionsByTrait.get(trait);
  }

  public ISubTraitContainer getPassionContainer(ITraitType traitType) {
    return passionByType.get(traitType);
  }

  public ITraitReference[] getAllTraits() {
    Set<ITraitReference> keySet = passionsByTrait.keySet();
    return keySet.toArray(new ITraitReference[keySet.size()]);
  }
  
  public ITraitReference[] getAllEligibleTraits()
  {
	   List<ITraitReference> keySet = new ArrayList<ITraitReference>(passionsByTrait.keySet());
	   List<ITraitReference> toRemove = new ArrayList<ITraitReference>();
	   
	   for (ITraitReference trait : keySet)
	   {
		   if (!getPassionContainer(trait.getTraitType()).isNewSubTraitAllowed() &&
			   !toRemove.contains(trait))
			   toRemove.add(trait);
			   
	   }
	   keySet.removeAll(toRemove);
	   
	   return keySet.toArray(new ITraitReference[keySet.size()]);
  }

  public void setCurrentPassionName(String newPassionName) {
    this.currentName = newPassionName;
    control.fireChangedEvent();
  }

  public void setCurrentTrait(ITraitReference newValue) {
    this.currentType = newValue;
    control.fireChangedEvent();
  }

  public void commitSelection() {
    ISubTrait passion = passionsByTrait.get(currentType).addSubTrait(currentName);
    if (passion != null && passion.getCurrentValue() == 0) {
      passion.setCurrentValue(1);
    }
  }

  public void clear() {
    currentName = null;
    currentType = null;
    control.fireChangedEvent();
  }

  public void addSelectionChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public boolean isEntryComplete() {
    return !StringUtilities.isNullOrEmpty(currentName) && currentType != null;
  }

  public boolean isExperienced() {
    return context.getBasicCharacterContext().isExperienced();
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  public void addTraitListChangeListener(ITraitReferencesChangeListener listener) {
    traitControl.addListener(listener);
  }

	@Override
	public void addChangeListener(IChangeListener listener) {
		// TODO Auto-generated method stub
		
	}
}