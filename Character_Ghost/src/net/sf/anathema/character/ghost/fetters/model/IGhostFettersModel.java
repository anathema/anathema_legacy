package net.sf.anathema.character.ghost.fetters.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;

public interface IGhostFettersModel extends IAdditionalModel
{
  Fetter[] getFetters();
  
  void setCurrentFetterName(String newFetterName);

  Fetter commitSelection();

  void clear();

  boolean isEntryComplete();

  boolean isExperienced();
  
  void removeFetter(Fetter fetter);
  
  void setValueChangedListener(IIntValueChangedListener listener);

  void addCharacterChangeListener(ICharacterChangeListener listener);
  
  void addSelectionChangeListener(IChangeListener listener);

  int getFreeDotAllotment();
  
  int getFreeDotsSpent();
  
  int getBonusPointsSpent();
  
  int getXPSpent();
  
  int getMaxFetterDots();
  
  int getCurrentFetterDots();
}