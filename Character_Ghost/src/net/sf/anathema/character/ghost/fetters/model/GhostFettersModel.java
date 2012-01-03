package net.sf.anathema.character.ghost.fetters.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.ghost.fetters.GhostFettersTemplate;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class GhostFettersModel extends AbstractAdditionalModelAdapter implements IGhostFettersModel
{
  private final List<Fetter> fetters;
  IIntValueChangedListener listener;
  private final ChangeControl control = new ChangeControl();
  private final ICharacterModelContext context;
  private final GhostFettersTemplate template;
  private String currentName;
  
  private final int[] baseFreeFetterDotsPerAge = { 5, 4, 4, 3, 3, 2 };
  
  private static final int FETTER_XP_MULTIPLIER = 3;
  private static final int FETTER_BONUS_POINT_COST = 3;
  private final int maxFreeRating;

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public GhostFettersModel(
	  GhostFettersTemplate template,
      final ICharacterModelContext context) {
    this.context = context;
    this.template = template;
    fetters = new ArrayList<Fetter>();
    
    maxFreeRating = 3;
  }
  
  public String getTemplateId()
  {
	  return template.getId();
  }
  
  public int getCurrentVirtueRating(VirtueType type)
  {
	  return context.getTraitCollection().getTrait(type).getCurrentValue();
  }

  public void setCurrentFetterName(String newFetterName) {
    this.currentName = newFetterName;
    control.fireChangedEvent();
  }
  
  private int getAge()
  {
	  IGenericTrait ageTrait = context.getTraitCollection().getTrait(new CustomizedBackgroundTemplate("Age"));
	  return ageTrait == null ? 0 : ageTrait.getCurrentValue();
  }
  
  public int getMaxFetterDots()
  {
	  IGenericTrait essence = context.getTraitCollection().getTrait(OtherTraitType.Essence);
	  IGenericTrait will = context.getTraitCollection().getTrait(OtherTraitType.Willpower);
	  return essence.getCurrentValue() + will.getCurrentValue() - getAge();
  }
  
  public int getCurrentFetterDots()
  {
	  int total = 0;
	  for (Fetter fetter : fetters)
		  total += fetter.getCurrentValue();
	  
	  return total;
  }
  
  public int getFreeDotAllotment()
  {
	  return baseFreeFetterDotsPerAge[getAge()]; 
  }
  
  public int getFreeDotsSpent()
  {
	  int spent = 0;
	  for (Fetter fetter : fetters)
		  spent += Math.min(maxFreeRating, fetter.getCreationValue());
	  return Math.min(spent, getFreeDotAllotment());
  }
  
  public int getBonusPointsSpent()
  {
	  int freeDotsRemaining = getFreeDotAllotment();
	  int bonusPointDots = 0;
	  for (Fetter fetter : fetters)
	  {
		  int dots = fetter.getCreationValue();
		  int freeDotsSpent = Math.min(freeDotsRemaining,
				  Math.min(fetter.getCreationValue(), maxFreeRating));
		  freeDotsRemaining -= freeDotsSpent;
		  dots -= freeDotsSpent;
		  bonusPointDots += dots;
	  }
	  return FETTER_BONUS_POINT_COST * bonusPointDots;
  }
  
  public int getXPSpent()
  {
	  int xpMult = 0;
	  for (Fetter fetter : fetters)
		  xpMult += getXPMultiplier(fetter);
	  return FETTER_XP_MULTIPLIER * xpMult;
  }
  
  public IAdditionalModelBonusPointCalculator getBonusPointCalculator()
  {
	return new FetterModelBonusPointCalculator(this);
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator()
  {
	return new FetterModelExperiencePointCalculator(this);
  }
  
  private int getXPMultiplier(Fetter fetter)
  {
	  int total = 1;
	  for (int i = fetter.getCreationValue();
	  	   i < fetter.getExperiencedValue();
	  	   i++)
		  total *= i;
	  return total == 1 ? 0 : total;		  
  }
  
  public Fetter[] getFetters()
  {
	  Fetter[] fetterSet = new Fetter[fetters.size()];
	  fetters.toArray(fetterSet);
	  return fetterSet;
  }

  public Fetter commitSelection() {
	Fetter newFetter = new Fetter(currentName, context.getTraitContext(), this);
	fetters.add(newFetter);
	if (listener != null)
	newFetter.addCurrentValueListener(listener);
	newFetter.addCurrentValueListener(new IIntValueChangedListener()
	{

		@Override
		public void valueChanged(int newValue) {
			control.fireChangedEvent();
		}
		
	});
	newFetter.setCurrentValue(1);
	return newFetter;
  }
  
  public void removeFetter(Fetter fetter)
  {
	  fetters.remove(fetter);
  }

  public void clear() {
    currentName = null;
    control.fireChangedEvent();
  }

  public boolean isEntryComplete() {
    return !StringUtilities.isNullOrEmpty(currentName);
  }

  public boolean isExperienced() {
    return context.getBasicCharacterContext().isExperienced();
  }
 
  public void setValueChangedListener(IIntValueChangedListener listener) {
	    this.listener = listener;
	    for (Fetter fetter : fetters)
	    	fetter.addCurrentValueListener(listener);
	  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }
  
  public void addSelectionChangeListener(IChangeListener listener) {
	    control.addChangeListener(listener);
	  }

	@Override
	public void addChangeListener(IChangeListener listener) {
		control.addChangeListener(listener);
		
	}
}