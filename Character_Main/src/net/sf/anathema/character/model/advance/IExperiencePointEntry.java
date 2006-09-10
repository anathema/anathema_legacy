package net.sf.anathema.character.model.advance;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IExperiencePointEntry {

  public int getExperiencePoints();

  public void setExperiencePoints(int points);

  public ITextualDescription getTextualDescription();

  public void addChangeListener(IObjectValueChangedListener<IExperiencePointEntry> entryChangeListener);

  public void removeChangeListener(IObjectValueChangedListener<IExperiencePointEntry> entryChangeListener);
}