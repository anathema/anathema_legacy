package net.sf.anathema.character.solar.display;

import net.sf.anathema.character.library.virtueflaw.model.DescriptiveVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.model.DescriptiveVirtueFlawModelImpl;
import net.sf.anathema.character.library.virtueflaw.model.GreatCurseFetcher;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawView;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.solar.model.SolarCharacterType;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.SpiritualTraits)
@Weight(weight = 200)
public class SolarVirtueFlawInitializer implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public SolarVirtueFlawInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    if (!(character.getTemplate().getTemplateType().getCharacterType() instanceof SolarCharacterType)) {
      return;
    }
    String viewName = resources.getString("AdditionalTemplateView.TabName.SolarVirtueFlaw");
    IDescriptiveVirtueFlawView view = sectionView.addView(viewName, IDescriptiveVirtueFlawView.class, character.getTemplate().getTemplateType().getCharacterType());
    DescriptiveVirtueFlawModel virtueFlawModel = (DescriptiveVirtueFlawModel) GreatCurseFetcher.fetch(character);
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(character,resources, view, virtueFlawModel);
    presenter.initPresentation();
  }
}