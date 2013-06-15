package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.main.description.model.ICharacterDescription;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.IMultiComponentLine;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.IIntegerView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;

public class CharacterDescriptionPresenter {

  private final ICharacterDescription description;
  private final ICharacterConcept characterConcept;
  private final ICharacterDescriptionView descriptionView;
  private final boolean hasAnima;
  private final Resources resources;

  public CharacterDescriptionPresenter(DescriptionDetails descriptionDetails, Resources resources, ICharacterDescriptionView descriptionView) {
    this.resources = resources;
    this.description = descriptionDetails.getDescription();
    this.characterConcept = descriptionDetails.getCharacterConcept();
    this.hasAnima = descriptionDetails.isHasAnima();
    this.descriptionView = descriptionView;
  }

  public void initPresentation() {
    TextualPresentation presentation = new TextualPresentation();
    initNameLineView(presentation);
    initLineView("CharacterDescription.Label.Player", description.getPlayer(), presentation);
    initLineView("Label.Concept", description.getConcept(), presentation);
    initAreaView("CharacterDescription.Label.Characterization", description.getCharacterization(), presentation);
    initAreaView("CharacterDescription.Label.PhysicalDescription", description.getPhysicalDescription(), presentation);
    initMinorTraits(presentation);
    if (hasAnima) {
      initLineView("CharacterDescription.Label.Anima", description.getAnima(), presentation);
    }
    initAreaView("CharacterDescription.Label.Notes", description.getNotes(), presentation);
  }

  private void initNameLineView(TextualPresentation presentation) {
    initLineView("CharacterDescription.Label.Name", description.getName(), presentation);
    addRealmNameTool();
    addThresholdNameTool();
  }

  private void addThresholdNameTool() {
    Tool thresholdNameTool = descriptionView.addEditAction();
    thresholdNameTool.setIcon(new CharacterUI().getRandomThresholdNameIconPath());
    thresholdNameTool.setTooltip(resources.getString("CharacterDescription.Tooltip.ThresholdName"));
    thresholdNameTool.setCommand(new NameGeneratorCommand(description.getName(), new ThresholdNameGenerator()));
  }

  private void addRealmNameTool() {
    Tool realmNameTool = descriptionView.addEditAction();
    realmNameTool.setIcon(new CharacterUI().getRandomRealmNameIconPath());
    realmNameTool.setTooltip(resources.getString("CharacterDescription.Tooltip.RealmName"));
    realmNameTool.setCommand(new NameGeneratorCommand(description.getName(), new RealmNameGenerator()));
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