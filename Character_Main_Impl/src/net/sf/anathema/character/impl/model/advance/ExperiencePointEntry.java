package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.lib.control.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;
import org.jmock.example.announcer.Announcer;

public class ExperiencePointEntry implements IExperiencePointEntry {

  private final Announcer<IObjectValueChangedListener> changeControl = Announcer.to(IObjectValueChangedListener.class);
  private final ITextualDescription description = new SimpleTextualDescription();
  private int experiencePoints = 0;

  public ExperiencePointEntry() {
    description.addTextChangedListener(new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        fireChangeEvent();
      }
    });
  }

  private void fireChangeEvent() {
    changeControl.announce().valueChanged(ExperiencePointEntry.this);
  }

  @Override
  public int getExperiencePoints() {
    return experiencePoints;
  }

  @Override
  public void setExperiencePoints(int value) {
    if (experiencePoints == value) {
      return;
    }
    this.experiencePoints = value;
    fireChangeEvent();
  }

  @Override
  public ITextualDescription getTextualDescription() {
    return description;
  }

  @Override
  public void addChangeListener(IObjectValueChangedListener<IExperiencePointEntry> listener) {
    changeControl.addListener(listener);
  }

  @Override
  public void removeChangeListener(IObjectValueChangedListener<IExperiencePointEntry> listener) {
    changeControl.removeListener(listener);
  }
}