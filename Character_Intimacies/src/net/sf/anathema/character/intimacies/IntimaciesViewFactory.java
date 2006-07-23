package net.sf.anathema.character.intimacies;

import javax.swing.Icon;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.resources.CharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.intimacies.presenter.IntimaciesPresenter;
import net.sf.anathema.character.intimacies.view.IntimaciesView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.resources.IResources;

public class IntimaciesViewFactory implements IAdditionalViewFactory {

  public ISimpleTabView createView(IAdditionalModel model, final IResources resources, CharacterType type) {
    IIconToggleButtonProperties properties = new IIconToggleButtonProperties() {
      public Icon createStandardIcon() {
        return new CharacterUI(resources).getMediumLockIcon();
      }

      public Icon createUnselectedIcon() {
        return null;
      }

      public String getToolTipText() {
        return resources.getString("Intimacies.LockButton.Tooltip"); //$NON-NLS-1$
      }
    };
    Icon ballResource = new CharacterTemplateResourceProvider(resources).getMediumBallResource(type);
    IntimaciesView view = new IntimaciesView(new IntValueDisplayFactory(resources, ballResource), properties);
    new IntimaciesPresenter(((IIntimaciesAdditionalModel) model).getIntimaciesModel(), model, view, resources).initPresentation();
    return view;
  }
}