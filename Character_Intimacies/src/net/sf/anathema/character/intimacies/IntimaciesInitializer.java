package net.sf.anathema.character.intimacies;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesView;
import net.sf.anathema.character.intimacies.presenter.IntimaciesPresenter;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CoreModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

@RegisteredInitializer(CharacterModelGroup.SpiritualTraits)
@Weight(weight = 300)
public class IntimaciesInitializer implements CoreModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public IntimaciesInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, final Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Intimacies");
    IIntimaciesView view = sectionView.addView(viewName, IIntimaciesView.class, character.getCharacterType());
    IIconToggleButtonProperties properties = new IntimaciesProperties(resources);
    view.initGui(properties);
    IIntimaciesAdditionalModel additionalModel = (IIntimaciesAdditionalModel) character.getExtendedConfiguration().getAdditionalModel(IntimaciesTemplate.ID);
    new IntimaciesPresenter(additionalModel.getIntimaciesModel(), additionalModel, view, resources).initPresentation();
  }

  private static class IntimaciesProperties implements IIconToggleButtonProperties {
    private final Resources resources;

    public IntimaciesProperties(Resources resources) {
      this.resources = resources;
    }

    @Override
    public Icon createStandardIcon() {
      return new CharacterUI().getLinkIcon();
    }

    @Override
    public Icon createUnselectedIcon() {
      return null;
    }

    @Override
    public String getToolTipText() {
      return resources.getString("Intimacies.LockButton.Tooltip");
    }
  }
}