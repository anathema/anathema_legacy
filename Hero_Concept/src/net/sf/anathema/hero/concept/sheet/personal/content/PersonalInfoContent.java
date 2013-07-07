package net.sf.anathema.hero.concept.sheet.personal.content;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubContent;
import net.sf.anathema.hero.sheet.pdf.content.SubBoxContent;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonalInfoContent extends AbstractSubContent implements SubBoxContent, Iterable<PersonalInfoRow> {

  private final HeroDescription description;
  private Hero hero;
  public List<PersonalInfoRow> rows = new ArrayList<>();

  public PersonalInfoContent(Resources resources, Hero hero) {
    super(resources);
    this.hero = hero;
    this.description = HeroDescriptionFetcher.fetch(hero);
    rows.add(createFirstRow());
    rows.add(createSecondRow());
    rows.add(createThirdRow());
    if (getCharacterType().isExaltType()) {
      rows.add(createAnimaRow());
    }
  }

  private PersonalInfoRow createFirstRow() {
    ICharacterType characterType = getCharacterType();
    if (!characterType.isExaltType()) {
      TitledInfo conceptInfo = new TitledInfo(getLabel("Concept"), description.getConcept().getText(), 3);
      return new PersonalInfoRow(conceptInfo);
    }
    TitledInfo conceptInfo = new TitledInfo(getLabel("Concept"), description.getConcept().getText(), 2);
    TitledInfo casteInfo = new TitledInfo(getLabel("Caste." + characterType.getId()), getCasteContent());
    return new PersonalInfoRow(conceptInfo, casteInfo);
  }

  private PersonalInfoRow createSecondRow() {
    TitledInfo sexInfo = new TitledInfo(getLabel("Sex"), description.getSex().getText());
    TitledInfo ageInfo = new TitledInfo(getLabel("Age"), getAge());
    return new PersonalInfoRow(sexInfo, ageInfo);
  }

  private PersonalInfoRow createThirdRow() {
    TitledInfo hairInfo = new TitledInfo(getLabel("Hair"), description.getHair().getText());
    TitledInfo skinInfo = new TitledInfo(getLabel("Skin"), description.getSkin().getText());
    TitledInfo eyesInfo = new TitledInfo(getLabel("Eyes"), description.getEyes().getText());
    return new PersonalInfoRow(hairInfo, skinInfo, eyesInfo);
  }

  private PersonalInfoRow createAnimaRow() {
    TitledInfo animaInfo = new TitledInfo(getLabel("Anima"), description.getAnima().getText(), 3);
    return new PersonalInfoRow(animaInfo);
  }

  private String getAge() {
    return String.valueOf(HeroConceptFetcher.fetch(hero).getAge().getValue());
  }

  private ICharacterType getCharacterType() {
    return hero.getTemplate().getTemplateType().getCharacterType();
  }

  private String getCasteContent() {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    return getResources().getString("Caste." + casteType.getId());
  }

  private String getLabel(String key) {
    return getResources().getString("Sheet.Label." + key) + ":";
  }

  public int getNumberOfRows() {
    return rows.size();
  }

  public int getNumberOfColumns() {
    return 3;
  }

  @Override
  public String getHeader() {
    String name = description.getName().getText();
    return StringUtilities.isNullOrTrimmedEmpty(name) ? getResources().getString("Sheet.Header.PersonalInfo") : name;
  }

  public int indexOf(PersonalInfoRow row) {
    return rows.indexOf(row);
  }

  @Override
  public Iterator<PersonalInfoRow> iterator() {
    return rows.iterator();
  }
}
