package net.sf.anathema.hero.attributes.display;

import net.sf.anathema.character.presenter.AttributesPresenter;
import net.sf.anathema.character.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.NaturalTraits;

@RegisteredInitializer(NaturalTraits)
@Weight(weight = 0)
public class AttributesInitializer implements HeroModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public AttributesInitializer(IApplicationModel applicationModel) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    String attributeHeader = resources.getString("CardView.AttributeConfiguration.Title");
    IGroupedFavorableTraitConfigurationView attributeView =
            sectionView.addView(attributeHeader, IGroupedFavorableTraitConfigurationView.class, hero.getTemplate().getTemplateType().getCharacterType());
    new AttributesPresenter(hero, resources, attributeView).initPresentation();
  }
}