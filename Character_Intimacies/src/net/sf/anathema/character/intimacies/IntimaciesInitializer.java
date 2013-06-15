package net.sf.anathema.character.intimacies;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesView;
import net.sf.anathema.character.intimacies.presenter.IntimaciesPresenter;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class IntimaciesInitializer implements IAdditionalInitializer {

  @Override
  public void initialize(SectionView sectionView, ICharacter character, final Resources resources, IAdditionalModel model) {
    String viewName = resources.getString("AdditionalTemplateView.TabName." + model.getTemplateId());
    IIntimaciesView view = sectionView.addView(viewName, IIntimaciesView.class, character.getCharacterType());
    IIconToggleButtonProperties properties = new IntimaciesProperties(resources);
    view.initGui(properties);
    new IntimaciesPresenter(((IIntimaciesAdditionalModel) model).getIntimaciesModel(), model, view, resources).initPresentation();
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