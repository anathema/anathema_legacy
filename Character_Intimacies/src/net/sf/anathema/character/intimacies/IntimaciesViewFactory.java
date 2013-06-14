package net.sf.anathema.character.intimacies;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesView;
import net.sf.anathema.character.intimacies.presenter.IntimaciesPresenter;
import net.sf.anathema.character.intimacies.view.IntimaciesView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;

public class IntimaciesViewFactory implements IAdditionalViewFactory {

  @Override
  public void createView(IAdditionalModel model, final Resources resources, ICharacterType type, Object view) {
    IIconToggleButtonProperties properties = new IntimaciesProperties(resources);
    IIntimaciesView intimaciesView = (IIntimaciesView) view;
    intimaciesView.initGui(properties);
    new IntimaciesPresenter(((IIntimaciesAdditionalModel) model).getIntimaciesModel(), model, intimaciesView, resources).initPresentation();
  }

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType characterType) {
    IIconToggleButtonProperties properties = new IntimaciesProperties(resources);
    IntimaciesView intimaciesView = new IntimaciesView(
            IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(characterType));
    intimaciesView.initGui(properties);
    new IntimaciesPresenter(((IIntimaciesAdditionalModel) model).getIntimaciesModel(), model, intimaciesView, resources).initPresentation();
    return intimaciesView;

  }

  @Override
  public Class getViewClass() {
    return IIntimaciesView.class;
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