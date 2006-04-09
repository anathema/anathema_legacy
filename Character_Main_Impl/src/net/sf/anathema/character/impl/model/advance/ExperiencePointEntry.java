package net.sf.anathema.character.impl.model.advance;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class ExperiencePointEntry implements IExperiencePointEntry {

  private final GenericControl<ChangeListener> changeControl = new GenericControl<ChangeListener>();
  private final ISimpleTextualDescription description = new SimpleTextualDescription();
  private int experiencePoints = 0;

  public ExperiencePointEntry() {
    description.addTextChangedListener(new IStringValueChangedListener() {
      public void valueChanged(String newValue) {
        fireChangeEvent();
      }
    });
  }

  private void fireChangeEvent() {
    changeControl.forAllDo(new IClosure<ChangeListener>() {
      public void execute(ChangeListener input) {
        input.stateChanged(new ChangeEvent(this));
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
    fireChangeEvent();
  }

  public ISimpleTextualDescription getTextualDescription() {
    return description;
  }

  public void addChangeListener(ChangeListener listener) {
    changeControl.addListener(listener);
  }

  public void removeChangeListener(ChangeListener listener) {
    changeControl.removeListener(listener);
  }
}