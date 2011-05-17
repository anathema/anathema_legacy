package net.sf.anathema.character.thaumaturgy.model;

import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IThaumaturgyModel
{
  public String[] getArts();
  
  public String[] getProcedures(String art);
  
  public IDefaultTrait getProcedureControl();

  public void setCurrentArt(String art);

  public void setCurrentProcedure(String procedure);
  
  public void setCurrentType(ThaumaturgyMagicType type);
  
  public List<IThaumaturgyMagic> getLearnedArts();
  
  public List<IThaumaturgyMagic> getLearnedProcedures();
  
  public void learnMagic(IThaumaturgyMagic magic);
  
  public boolean isFavored();
  
  public void recalculate();
  
  public IThaumaturgyMagic commitSelection();
  
  public void removeMagic(IThaumaturgyMagic magic);

  public void clear();
  
  public List<IThaumaturgyMagic> checkRedundantProcedures();

  public boolean isEntryComplete();

  public boolean isExperienced();
  
  public void addChangeListener(IChangeListener listener);

  public void addCharacterChangeListener(ICharacterChangeListener listener);

  public void addSelectionChangeListener(IChangeListener listener);
}