package net.sf.anathema.hero.concept.display.description.presenter;

import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.IIntegerDescription;
import net.sf.anathema.character.main.presenter.DescriptionDetails;
import net.sf.anathema.character.main.presenter.NameGeneratorCommand;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.configurableview.MultiComponentLine;
import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.IIntegerView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;

public class DescriptionPresenter {

  private final HeroDescription description;
  private final HeroConcept heroConcept;
  private final ConfigurableCharacterView descriptionView;
  private final boolean hasAnima;
  private final Resources resources;

  public DescriptionPresenter(DescriptionDetails descriptionDetails, Resources resources, ConfigurableCharacterView descriptionView) {
    this.resources = resources;
    this.description = descriptionDetails.getDescription();
    this.heroConcept = descriptionDetails.getHeroConcept();
    this.hasAnima = descriptionDetails.isHasAnima();
    this.descriptionView = descriptionView;
  }

  public void initPresentation() {
    TextualPresentation presentation = new TextualPresentation();
    initNameLineView(presentation);
    initLineView("CharacterDescription.Label.Player", description.getPlayer(), presentation);
    initLineView("Label.Concept", description.getConcept(), presentation);
    if (hasAnima) {
      initLineView("CharacterDescription.Label.Anima", description.getAnima(), presentation);
    }
    initAreaView("CharacterDescription.Label.Characterization", description.getCharacterization(), presentation);
    initAreaView("CharacterDescription.Label.PhysicalDescription", description.getPhysicalDescription(), presentation);
    initMinorTraits(presentation);
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
    MultiComponentLine basicLooks = descriptionView.addMultiComponentLine();
    addField(basicLooks, "CharacterDescription.Label.Hair", description.getHair(), presentation);
    addField(basicLooks, "CharacterDescription.Label.Skin", description.getSkin(), presentation);
    addField(basicLooks, "CharacterDescription.Label.Eyes", description.getEyes(), presentation);
    MultiComponentLine sexAndAge = descriptionView.addMultiComponentLine();
    addField(sexAndAge, "CharacterDescription.Label.Sex", description.getSex(), presentation);
    addInteger(sexAndAge, "Label.Age", heroConcept.getAge());
  }

  private void addInteger(MultiComponentLine componentLine, String label, final IIntegerDescription integerDescription) {
    String title = resources.getString(label);
    IIntegerView view = componentLine.addIntegerView(title, integerDescription);
    view.addChangeListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        integerDescription.setValue(newValue);
      }
    });
  }

  private void addField(MultiComponentLine componentLine, String label, ITextualDescription description, TextualPresentation presentation) {
    String labelText = resources.getString(label);
    ITextView textView = componentLine.addFieldsView(labelText);
    presentation.initView(textView, description);
  }

  private void initLineView(String labelResourceKey, ITextualDescription textualDescription, TextualPresentation presentation) {
    ITextView textView = descriptionView.addLineView(resources.getString(labelResourceKey));
    presentation.initView(textView, textualDescription);
  }

  private void initAreaView(String labelResourceKey, ITextualDescription textualDescription, TextualPresentation presentation) {
    ITextView textView = descriptionView.addAreaView(resources.getString(labelResourceKey));
    presentation.initView(textView, textualDescription);
  }
}