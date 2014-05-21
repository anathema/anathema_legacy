package net.sf.anathema.hero.concept.advance;

import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.overview.IOverviewModelVisitor;
import net.sf.anathema.hero.points.overview.IValueModel;

public class CasteOverviewModel implements IValueModel<String> {
  private final Hero hero;

  public CasteOverviewModel(Hero hero) {
    this.hero = hero;
  }

  @Override
  public String getValue() {
    CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
    return "Caste." + casteType.getId();
  }

  @Override
  public String getId() {
    GenericPresentationTemplate template = new GenericPresentationTemplate(hero.getTemplate());
    return template.getCasteLabelResource();
  }

  @Override
  public void accept(IOverviewModelVisitor visitor) {
    visitor.visitStringValueModel(this);
  }

  @Override
  public String getCategoryId() {
    return "Spiritual";
  }
}
