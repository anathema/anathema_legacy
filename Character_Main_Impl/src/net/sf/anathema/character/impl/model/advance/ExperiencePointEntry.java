package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class ExperiencePointEntry implements IExperiencePointEntry {

  private final GenericControl<IObjectValueChangedListener<IExperiencePointEntry>> changeControl = new GenericControl<IObjectValueChangedListener<IExperiencePointEntry>>();
  private final ITextualDescription description = new SimpleTextualDescription();
  private int experiencePoints = 0;

  public ExperiencePointEntry() {
    description.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        fireChangeEvent();
      }
    });
  }

  private void fireChangeEvent() {
    changeControl.forAllDo(new IClosure<IObjectValueChangedListener<IExperiencePointEntry>>() {
      public void execute(IObjectValueChangedListener<IExperiencePointEntry> input) {
        input.valueChanged(ExperiencePointEntry.this);
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

  public ITextualDescription getTextualDescription() {
    return description;
  }

  public void addChangeListener(IObjectValueChangedListener<IExperiencePointEntry> listener) {
    changeControl.addListener(listener);
  }

  public void removeChangeListener(IObjectValueChangedListener<IExperiencePointEntry> listener) {
    changeControl.removeListener(listener);
  }
}