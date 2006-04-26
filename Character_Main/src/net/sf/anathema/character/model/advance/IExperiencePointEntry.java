package net.sf.anathema.character.model.advance;

import javax.swing.event.ChangeListener;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IExperiencePointEntry {

  public int getExperiencePoints();

  public void setExperiencePoints(int points);

  public ITextualDescription getTextualDescription();

  public void addChangeListener(ChangeListener listener);

  public void removeChangeListener(ChangeListener entryChangeListener);
}