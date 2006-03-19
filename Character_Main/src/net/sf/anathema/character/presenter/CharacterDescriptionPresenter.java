package net.sf.anathema.character.presenter;

import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.character.presenter.description.NameGeneratorAction;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;

public class CharacterDescriptionPresenter {

  private final ICharacterDescription description;
  private final ICharacterDescriptionView descriptionView;
  private final IResources resources;

  public CharacterDescriptionPresenter(
      IResources resources,
      ICharacterDescription description,
      ICharacterDescriptionView descriptionView) {
    this.resources = resources;
    this.description = description;
    this.descriptionView = descriptionView;
  }

  public void init() {
    initNameLineView(0);
    initAreaView("CharacterDescription.Label.Periphrase", 2, description.getPeriphrase()); //$NON-NLS-1$
    initAreaView("CharacterDescription.Label.Characterization", 7, description.getCharacterization()); //$NON-NLS-1$
    initAreaView("CharacterDescription.Label.PhysicalDescription", 5, description.getPhysicalDescription()); //$NON-NLS-1$
    initAreaView("CharacterDescription.Label.Notes", 5, description.getNotes()); //$NON-NLS-1$
    descriptionView.initGui(null);
  }

  private void initNameLineView(int row) {
    initLineView("CharacterDescription.Label.Name", description.getName()); //$NON-NLS-1$
    descriptionView.addEditAction(new NameGeneratorAction(
        resources.getImageIcon("util/question-mark-purple.gif"), //$NON-NLS-1$
        resources.getString("CharacterDescription.Tooltip.RealmName"), //$NON-NLS-1$
        description.getName(),
        new RealmNameGenerator()), row);
    descriptionView.addEditAction(new NameGeneratorAction(
        resources.getImageIcon("util/question-mark.gif"), //$NON-NLS-1$
        resources.getString("CharacterDescription.Tooltip.ThresholdName"), //$NON-NLS-1$
        description.getName(),
        new ThresholdNameGenerator()), row);
  }

  private void initLineView(String labelResourceKey, final ISimpleTextualDescription textualDescription) {
    ITextView textView = descriptionView.addLineView(resources.getString(labelResourceKey));
    TextualPresentation.initView(textView, textualDescription);
  }

  private void initAreaView(String labelResourceKey, int rows, final ISimpleTextualDescription textualDescription) {
    ITextView textView = descriptionView.addAreaView(resources.getString(labelResourceKey), rows);
    TextualPresentation.initView(textView, textualDescription);
  }
}
