package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawView;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CoreModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.solar.SolarCharacterType;
import net.sf.anathema.character.solar.virtueflaw.presenter.SolarVirtueFlawPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(CharacterModelGroup.SpiritualTraits)
@Weight(weight = 200)
public class SolarVirtueFlawInitializer implements CoreModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public SolarVirtueFlawInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    if (!(character.getCharacterType() instanceof SolarCharacterType)) {
      return;
    }
    String viewName = resources.getString("AdditionalTemplateView.TabName.SolarVirtueFlaw");
    IDescriptiveVirtueFlawView view = sectionView.addView(viewName, IDescriptiveVirtueFlawView.class, character.getCharacterType());
    IDescriptiveVirtueFlawModel virtueFlawModel = (IDescriptiveVirtueFlawModel) character.getExtendedConfiguration().getAdditionalModel(SolarVirtueFlawTemplate.ID);
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(resources, view, virtueFlawModel);
    presenter.initPresentation();
  }
}