package net.sf.anathema.character.intimacies;

import javax.swing.Icon;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.xml.presentation.CharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.intimacies.presenter.IntimaciesPresenter;
import net.sf.anathema.character.intimacies.view.IntimaciesView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.resources.IResources;

public class IntimaciesViewFactory implements IAdditionalViewFactory {

  public ISimpleTabView createView(IAdditionalModel model, final IResources resources, CharacterType type) {
    String ballResource = new CharacterTemplateResourceProvider().getBallResource(type);
    IIconToggleButtonProperties properties = new IIconToggleButtonProperties() {
      public Icon createStandardIcon() {
        return new BasicUi(resources).getMediumLockIcon();
      }

      public Icon createUnselectedIcon() {
        return null;
      }

      public String getToolTipText() {
        return resources.getString("Intimacies.LockButton.Tooltip"); //$NON-NLS-1$
      }
    };
    IntimaciesView view = new IntimaciesView(
        new IntValueDisplayFactory(resources, resources.getImageIcon(ballResource)),
        properties);
    new IntimaciesPresenter((IIntimaciesModel) model, view, resources).initPresentation();
    return view;
  }
}