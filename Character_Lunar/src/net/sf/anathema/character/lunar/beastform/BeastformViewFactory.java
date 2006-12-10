package net.sf.anathema.character.lunar.beastform;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.lunar.beastform.presenter.BeastformPresenter;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.lunar.beastform.view.BeastformView;
import net.sf.anathema.character.lunar.beastform.view.IBeastformViewProperties;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class BeastformViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, final IResources resources, CharacterType type) {
    MarkerIntValueDisplayFactory intValueDisplayFactory = new MarkerIntValueDisplayFactory(resources, type);
    BeastformView view = new BeastformView(intValueDisplayFactory, new IBeastformViewProperties() {
      public String getCharmString() {
        return resources.getString("Lunar.DeadlyBeastmanTransformation"); //$NON-NLS-1$
      }

      public String getAttributesString() {
        return resources.getString("Lunar.DeadlyBeastmanTransformation.Attributes.Label"); //$NON-NLS-1$
      }

      public String getGiftsString() {
        return resources.getString("Lunar.DeadlyBeastmanTransformation.Gifts.Label"); //$NON-NLS-1$
      }
    });
    new BeastformPresenter(resources, view, (IBeastformModel) model).initPresentation();
    return view;
  }
}