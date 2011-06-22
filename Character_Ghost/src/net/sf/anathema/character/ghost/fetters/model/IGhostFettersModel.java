package net.sf.anathema.character.ghost.fetters.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IGhostFettersModel extends IAdditionalModel
{
  public Fetter[] getFetters();
  
  public void setCurrentFetterName(String newFetterName);

  public Fetter commitSelection();

  public void clear();

  public boolean isEntryComplete();

  public boolean isExperienced();
  
  public void removeFetter(Fetter fetter);
  
  public void setValueChangedListener(IIntValueChangedListener listener);

  public void addCharacterChangeListener(ICharacterChangeListener listener);
  
  public void addSelectionChangeListener(IChangeListener listener);

  public int getFreeDotAllotment();
  
  public int getFreeDotsSpent();
  
  public int getBonusPointsSpent();
  
  public int getXPSpent();
  
  public int getMaxFetterDots();
  
  public int getCurrentFetterDots();
}