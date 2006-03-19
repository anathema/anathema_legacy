package net.sf.anathema.character.impl.model.advance;

import javax.swing.event.ChangeListener;

import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.lib.control.ChangeControl;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class ExperiencePointEntry implements IExperiencePointEntry {

  private final ChangeControl changeControl = new ChangeControl(this);
  private final ISimpleTextualDescription description = new SimpleTextualDescription();
  private int experiencePoints = 0;

  public ExperiencePointEntry() {
    description.addTextChangedListener(new IStringValueChangedListener() {
      public void valueChanged(String newValue) {
        changeControl.fireChangedEvent();
      }
    });
  }

  public int getExperiencePoints() {
    return experiencePoints;
  }

  public void setExperiencePoints(int value) {
    if (experiencePoints == value) {
      return;
    }
    this.experiencePoints = value;
    changeControl.fireChangedEvent();
  }

  public ISimpleTextualDescription getTextualDescription() {
    return description;
  }

  public void addChangeListener(ChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  public void removeChangeListener(ChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }
}