package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.impl.model.ProxyTextualDescription;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.concept.IEditMotivationListener;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class Motivation implements IMotivation {

  private final SimpleTextualDescription persistenceDescription = new SimpleTextualDescription();
  private final ProxyTextualDescription editableDescription;
  private final IExperiencePointConfiguration experiencePoints;
  private final GenericControl<IEditMotivationListener> control = new GenericControl<IEditMotivationListener>();

  public Motivation(IExperiencePointConfiguration experiencePoints) {
    this.experiencePoints = experiencePoints;
    this.editableDescription = new ProxyTextualDescription(persistenceDescription, new SimpleTextualDescription());
  }

  public ITextualDescription getDescription() {
    return persistenceDescription;
  }

  public ITextualDescription getEditableDescription() {
    return editableDescription;
  }

  public void accept(IWillpowerRegainingConceptVisitor visitor) {
    visitor.accept(this);
  }

  public void beginEdit() {
    switchDescription(1);
    control.forAllDo(new IClosure<IEditMotivationListener>() {
      public void execute(IEditMotivationListener input) {
        input.editBegun();
      }
    });
  }

  public void cancelEdit() {
    editableDescription.setCurrentDescription(0);
    fireEditEnded();
  }

  public void endEditXPSpending(String xpMessage) {
    experiencePoints.addEntry(xpMessage, -2);
    endEdit();
  }

  public void endEdit() {
    switchDescription(0);
    fireEditEnded();
  }

  private void switchDescription(int newDescriptionIndex) {
    String text = editableDescription.getText();
    editableDescription.setCurrentDescription(newDescriptionIndex);
    editableDescription.setText(text);
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