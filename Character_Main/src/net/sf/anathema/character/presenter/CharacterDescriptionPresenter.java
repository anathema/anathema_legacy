package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.character.presenter.description.NameGeneratorAction;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;

public class CharacterDescriptionPresenter implements IPresenter{

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

  public void initPresentation() {
    TextualPresentation presentation = new TextualPresentation();
    initNameLineView(0, presentation);
    initLineView("CharacterDescription.Label.Player", description.getPlayer(), presentation); //$NON-NLS-1$
    initAreaView("CharacterDescription.Label.Periphrasis", 2, description.getPeriphrase(), presentation); //$NON-NLS-1$
    initAreaView("CharacterDescription.Label.Characterization", 7, description.getCharacterization(), presentation); //$NON-NLS-1$
    initAreaView(
        "CharacterDescription.Label.PhysicalDescription", 5, description.getPhysicalDescription(), presentation); //$NON-NLS-1$
    initAreaView("CharacterDescription.Label.Notes", 5, description.getNotes(), presentation); //$NON-NLS-1$
    descriptionView.initGui(null);
  }

  private void initNameLineView(int row, TextualPresentation presentation) {
    initLineView("CharacterDescription.Label.Name", description.getName(), presentation); //$NON-NLS-1$
    CharacterUI characterUI = new CharacterUI(resources);
    descriptionView.addEditAction(new NameGeneratorAction(
        characterUI.getRandomRealmNameIcon(),
        resources.getString("CharacterDescription.Tooltip.RealmName"), //$NON-NLS-1$
        description.getName(),
        new RealmNameGenerator()), row);
    descriptionView.addEditAction(new NameGeneratorAction(
        characterUI.getRandomThresholdNameIcon(),
        resources.getString("CharacterDescription.Tooltip.ThresholdName"), //$NON-NLS-1$
        description.getName(),
        new ThresholdNameGenerator()), row);
  }

  private void initLineView(
      String labelResourceKey,
      final ITextualDescription textualDescription,
      TextualPresentation presentation) {
    ITextView textView = descriptionView.addLineView(resources.getString(labelResourceKey));
    presentation.initView(textView, textualDescription);
  }

  private void initAreaView(
      String labelResourceKey,
      int rows,
      final ITextualDescription textualDescription,
      TextualPresentation presentation) {
    ITextView textView = descriptionView.addAreaView(resources.getString(labelResourceKey), rows);
    presentation.initView(textView, textualDescription);
  }
}
