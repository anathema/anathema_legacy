package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.model.concept.IEditMotivationListener;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class Motivation implements IMotivation {

  private final ITextualDescription description = new SimpleTextualDescription();
  private final IExperiencePointConfiguration experiencePoints;
  private final GenericControl<IEditMotivationListener> control = new GenericControl<IEditMotivationListener>();

  public Motivation(IExperiencePointConfiguration experiencePoints) {
    this.experiencePoints = experiencePoints;
  }

  public ITextualDescription getDescription() {
    return description;
  }

  public void accept(IWillpowerRegainingConceptVisitor visitor) {
    visitor.accept(this);
  }

  public void beginEdit() {
    control.forAllDo(new IClosure<IEditMotivationListener>() {
      public void execute(IEditMotivationListener input) {
        input.editBegun();
      }
    });
  }

  public void endEditXPSpending() {
    IExperiencePointEntry entry = experiencePoints.addEntry();
    entry.getTextualDescription().setText("Changed motivation");
    entry.setExperiencePoints(2);
    fireEditEnded();
  }

  public void endEdit() {
    fireEditEnded();
  }

  private void fireEditEnded() {
    control.forAllDo(new IClosure<IEditMotivationListener>() {
      public void execute(IEditMotivationListener input) {
        input.editEnded();
      }
    });
  }

  public void addEditingListener(IEditMotivationListener listener) {
    control.addListener(listener);
  }
}