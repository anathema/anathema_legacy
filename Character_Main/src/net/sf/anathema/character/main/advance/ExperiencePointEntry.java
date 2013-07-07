package net.sf.anathema.character.main.advance;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;
import org.jmock.example.announcer.Announcer;

public class ExperiencePointEntry implements IExperiencePointEntry {

  private final Announcer<ObjectValueListener> changeControl = Announcer.to(ObjectValueListener.class);
  private final ITextualDescription description = new SimpleTextualDescription();
  private int experiencePoints = 0;

  public ExperiencePointEntry() {
    description.addTextChangedListener(new ObjectValueListener<String>() {
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
  public void addChangeListener(ObjectValueListener<IExperiencePointEntry> listener) {
    changeControl.addListener(listener);
  }

  @Override
  public void removeChangeListener(ObjectValueListener<IExperiencePointEntry> listener) {
    changeControl.removeListener(listener);
  }
}