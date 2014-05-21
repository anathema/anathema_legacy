package net.sf.anathema.hero.attributes.display;

import net.sf.anathema.hero.traits.display.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.dependencies.Weight;

import static net.sf.anathema.hero.display.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 0)
public class AttributesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public AttributesInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    String attributeHeader = environment.getString("CardView.AttributeConfiguration.Title");
    GroupedFavorableTraitConfigurationView attributeView =
            sectionView.addView(attributeHeader, GroupedFavorableTraitConfigurationView.class);
    new AttributesPresenter(hero, environment, attributeView).initPresentation();
  }
}