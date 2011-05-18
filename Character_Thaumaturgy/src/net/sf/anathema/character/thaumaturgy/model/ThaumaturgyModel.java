package net.sf.anathema.character.thaumaturgy.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class ThaumaturgyModel implements IThaumaturgyModel {

  private final List<IThaumaturgyMagic> degrees = new ArrayList<IThaumaturgyMagic>();
  private final List<IThaumaturgyMagic> procedures = new ArrayList<IThaumaturgyMagic>();
  private final ChangeControl control = new ChangeControl();
  private final ICharacterModelContext context;
  private final IDefaultTrait procedureControl;
  private String currentArtName;
  private String currentProcedureName;
  private ThaumaturgyMagicType currentType = ThaumaturgyMagicType.Degree;
  
  private final ChangeControl bonusControl = new ChangeControl();
  private final IChangeListener thaumaturgyChangeListener = new IChangeListener() {
	    public void changeOccured() {
	      bonusControl.fireChangedEvent();
	    }
	  };
  
  public ThaumaturgyModel(
      IGenericTraitCollection traitCollection,
      final ICharacterModelContext context) {
    this.context = context;
    procedureControl = new ThaumaturgyProcedureControl(context.getTraitCollection(), context.getTraitContext());    
  }
  
  public String[] getArts()
  {
	  return ThaumaturgyProvider.getArts();
  }
  
  public String[] getProcedures(String art)
  {
	  if (art == null)
		  return new String[0];
	  return ThaumaturgyProvider.getProcedures(art);
  }
  
  public List<IThaumaturgyMagic> getLearnedDegrees()
  {
	  return degrees;
  }
  
  public List<IThaumaturgyMagic> getLearnedProcedures()
  {
	  return procedures;
  }
  
  public boolean isFavored()
  {
	  return context.getTraitCollection().getFavorableTrait(AbilityType.Occult).isCasteOrFavored();
  }
  
  public void recalculate()
  {
	  thaumaturgyChangeListener.changeOccured();
  }
  
  public void setCurrentArt(String name) {
    this.currentArtName = name;
    control.fireChangedEvent();
  }
  
  public void setCurrentProcedure(String name) {
	    this.currentProcedureName = name;
	    control.fireChangedEvent();
	  }
  
  public void setCurrentType(ThaumaturgyMagicType type) {
	    this.currentType = type;
	    control.fireChangedEvent();
	  }
  
  public IDefaultTrait getProcedureControl()
  {
	  return procedureControl;
  }

  public IThaumaturgyMagic commitSelection()
  {
	  IThaumaturgyMagic newMagic = null;
	  if (currentType == ThaumaturgyMagicType.Degree)
	  {
		  for (IThaumaturgyMagic currentDegrees : degrees)
			  if (currentDegrees.getArt().equals(currentArtName))
				  return null;
		  
		  newMagic = new ThaumaturgyDegree(currentArtName, context.getTraitCollection(), context.getTraitContext());
		  degrees.add(newMagic);
		  currentArtName = null;
	  }
	  if (currentType == ThaumaturgyMagicType.Procedure)
	  {
		  for (IThaumaturgyMagic currentProcedures : procedures)
			  if (currentProcedures.getArt().equals(currentArtName) &&
				  currentProcedures.getProcedure().equals(currentProcedureName))
				  return null;
		  
		  int value = procedureControl.getCurrentValue();
		  newMagic = new ThaumaturgyProcedure(currentArtName, currentProcedureName,
				  value,
				  context.getTraitCollection(), context.getTraitContext());
		  
		  if (isExperienced())
			  newMagic.setExperiencedValue(value);
		  else
			  newMagic.setCreationValue(value);
		  
		  procedureControl.setCurrentValue(0);
		  procedures.add(newMagic);
		  currentArtName = null;
		  currentProcedureName = null;
	  }
	  return newMagic;
  }
  
  public void learnMagic(IThaumaturgyMagic magic)
  {
	  if (magic instanceof ThaumaturgyDegree)
		  degrees.add(magic);
	  if (magic instanceof ThaumaturgyProcedure)
		  procedures.add(magic);
  }
  
  public List<IThaumaturgyMagic> checkRedundantProcedures()
  {
	  List<IThaumaturgyMagic> redundant = new ArrayList<IThaumaturgyMagic>();
	  for (IThaumaturgyMagic procedure : procedures)
		  for (IThaumaturgyMagic art : degrees)
			  if (art.getArt().equals(procedure.getArt()) &&
				  art.getCurrentValue() >= procedure.getCurrentValue())
				  redundant.add(procedure);
	  procedures.removeAll(redundant);
	  return redundant;
  }
  
  public void removeMagic(IThaumaturgyMagic magic)
  {
	  degrees.remove(magic);
	  procedures.remove(magic);
  }

  public void clear() {
    currentArtName = null;
    currentProcedureName = null;
    control.fireChangedEvent();
  }

  public void addSelectionChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public boolean isEntryComplete() {
	switch (currentType)
	{
	default:
	case Degree:
		return !StringUtilities.isNullOrEmpty(currentArtName);
	case Procedure:
		return !StringUtilities.isNullOrEmpty(currentArtName) &&
			!StringUtilities.isNullOrEmpty(currentProcedureName) &&
			procedureControl.getCurrentValue() > 0;
	}
  }
  
  public void addChangeListener(IChangeListener listener) {
	    bonusControl.addChangeListener(listener);
	  }

  public boolean isExperienced() {
    return context.getBasicCharacterContext().isExperienced();
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }
}