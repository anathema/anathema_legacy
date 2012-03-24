package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.impl.model.ProxyTextualDescription;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.concept.IEditMotivationListener;
import net.sf.anathema.character.model.concept.IMotivation;
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

  @Override
  public ITextualDescription getDescription() {
    return persistenceDescription;
  }

  @Override
  public ITextualDescription getEditableDescription() {
    return editableDescription;
  }

  @Override
  public void beginEdit() {
    switchDescription(1);
    control.forAllDo(new IClosure<IEditMotivationListener>() {
      @Override
      public void execute(IEditMotivationListener input) {
        input.editBegun();
      }
    });
  }

  @Override
  public void cancelEdit() {
    editableDescription.setCurrentDescription(0);
    fireEditEnded();
  }

  @Override
  public void endEditXPSpending(String xpMessage) {
    experiencePoints.addEntry(xpMessage, -2);
    endEdit();
  }

  @Override
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
      @Override
      public void execute(IEditMotivationListener input) {
        input.editEnded();
      }
    });
  }

  @Override
  public void addEditingListener(IEditMotivationListener listener) {
    control.addListener(listener);
  }
}