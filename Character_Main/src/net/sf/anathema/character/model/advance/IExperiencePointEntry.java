package net.sf.anathema.character.model.advance;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IExperiencePointEntry {

  public int getExperiencePoints();

  public void setExperiencePoints(int points);

  public ITextualDescription getTextualDescription();

  public void addChangeListener(ObjectValueListener<IExperiencePointEntry> entryChangeListener);

  public void removeChangeListener(ObjectValueListener<IExperiencePointEntry> entryChangeListener);
}