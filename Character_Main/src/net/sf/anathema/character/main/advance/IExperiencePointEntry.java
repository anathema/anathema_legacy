package net.sf.anathema.character.main.advance;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IExperiencePointEntry {

  int getExperiencePoints();

  void setExperiencePoints(int points);

  ITextualDescription getTextualDescription();

  void addChangeListener(ObjectValueListener<IExperiencePointEntry> entryChangeListener);

  void removeChangeListener(ObjectValueListener<IExperiencePointEntry> entryChangeListener);
}