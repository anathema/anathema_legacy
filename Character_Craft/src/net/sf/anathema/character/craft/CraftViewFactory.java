package net.sf.anathema.character.craft;

import net.sf.anathema.character.craft.presenter.CraftPresenter;
import net.sf.anathema.character.craft.presenter.ICraftModel;
import net.sf.anathema.character.craft.view.CraftView;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.resources.IResources;

public class CraftViewFactory implements IAdditionalViewFactory {

  public ISimpleTabView createView(IAdditionalModel model, final IResources resources, CharacterType type) {
    ICraftModel craftModel = ((ICraftAdditionalModel) model).getCraftModel();
    int maximum = craftModel.getAbsoluteMaximum();
    CraftView view = new CraftView(new MarkerIntValueDisplayFactory(resources, type), maximum);
    new CraftPresenter(craftModel, view, resources).initPresentation();
    return view;
  }
}