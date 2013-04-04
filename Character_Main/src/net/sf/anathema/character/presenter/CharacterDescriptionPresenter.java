package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.presenter.description.NameGeneratorAction;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.IMultiComponentLine;
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.ViewTabContentView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.IIntegerView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;

public class CharacterDescriptionPresenter implements IContentPresenter {

  private final ICharacterDescription description;
  private final ICharacterConcept characterConcept;
  private final ICharacterDescriptionView descriptionView;
  private final boolean hasAnima;
  private final Resources resources;

  public CharacterDescriptionPresenter(Resources resources, ICharacterDescription description, ICharacterConcept characterConcept,
                                       ICharacterDescriptionView descriptionView, boolean hasAnima) {
    this.resources = resources;
    this.description = description;
    this.characterConcept = characterConcept;
    this.descriptionView = descriptionView;
    this.hasAnima = hasAnima;
  }

  @Override
  public void initPresentation() {
    TextualPresentation presentation = new TextualPresentation();
    initNameLineView(0, presentation);
    initLineView("CharacterDescription.Label.Player", description.getPlayer(), presentation); //$NON-NLS-1$
    initLineView("Label.Concept", description.getConcept(), presentation); //$NON-NLS-1$
    initAreaView("CharacterDescription.Label.Characterization", description.getCharacterization(), presentation); //$NON-NLS-1$
    initAreaView("CharacterDescription.Label.PhysicalDescription", description.getPhysicalDescription(), presentation); //$NON-NLS-1$
    initMinorTraits(presentation);
    if (hasAnima) {
      initLineView("CharacterDescription.Label.Anima", description.getAnima(), presentation); //$NON-NLS-1$
    }
    initAreaView("CharacterDescription.Label.Notes", description.getNotes(), presentation); //$NON-NLS-1$
  }

  @Override
  public ContentView getTabContent() {
    String title = resources.getString("CardView.CharacterDescription.Title");//$NON-NLS-1$
    return new ViewTabContentView(descriptionView, new ContentProperties(title));
  }

  private void initNameLineView(int row, TextualPresentation presentation) {
    initLineView("CharacterDescription.Label.Name", description.getName(), presentation); //$NON-NLS-1$
    CharacterUI characterUI = new CharacterUI();
    descriptionView.addEditAction(
            new NameGeneratorAction(characterUI.getRandomRealmNameIcon(), resources.getString("CharacterDescription.Tooltip.RealmName"), //$NON-NLS-1$
                    description.getName(), new RealmNameGenerator()), row);
    descriptionView.addEditAction(
            new NameGeneratorAction(characterUI.getRandomThresholdNameIcon(), resources.getString("CharacterDescription.Tooltip.ThresholdName"),
                    //$NON-NLS-1$
                    description.getName(), new ThresholdNameGenerator()), row);
  }

  private void initMinorTraits(TextualPresentation presentation) {
    IMultiComponentLine componentLine = descriptionView.addMultiComponentLine();
    addField(componentLine, "CharacterDescription.Label.Sex", description.getSex(), presentation);
    addField(componentLine, "CharacterDescription.Label.Hair", description.getHair(), presentation);
    addField(componentLine, "CharacterDescription.Label.Skin", description.getSkin(), presentation);
    addField(componentLine, "CharacterDescription.Label.Eyes", description.getEyes(), presentation);
    addInteger(componentLine, "Label.Age", characterConcept.getAge());
  }

  private void addInteger(IMultiComponentLine componentLine, String label, final IIntegerDescription integerDescription) {
    String title = resources.getString(label);
    IIntegerView view = componentLine.addIntegerView(title, integerDescription);
    view.addChangeListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        integerDescription.setValue(newValue);
      }
    });
  }

  private void addField(IMultiComponentLine componentLine, String label, ITextualDescription description, TextualPresentation presentation) {
    String labelText = resources.getString(label);
    ITextView textView = componentLine.addFieldsView(labelText);
    presentation.initView(textView, description);
  }

  private void initLineView(String labelResourceKey, ITextualDescription textualDescription, TextualPresentation presentation) {
    ITextView textView = descriptionView.addLineView(resources.getString(labelResourceKey));
    presentation.initView(textView, textualDescription);
  }

  private void initAreaView(String labelResourceKey, ITextualDescription textualDescription, TextualPresentation presentation) {
    ITextView textView = descriptionView.addAreaView(resources.getString(labelResourceKey), 6);
    presentation.initView(textView, textualDescription);
  }
}
