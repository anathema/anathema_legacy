package net.sf.anathema.character.meritsflaws.view;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsAdditionalModel;
import net.sf.anathema.character.meritsflaws.presenter.MeritsFlawsPresenter;
import net.sf.anathema.character.meritsflaws.presenter.MeritsFlawsViewProperties;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class MeritsFlawsViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
    IMeritsFlawsAdditionalModel meritsFlawsModel = (IMeritsFlawsAdditionalModel) model;
    MeritsFlawsViewProperties properties = new MeritsFlawsViewProperties(
        meritsFlawsModel.getMeritsFlawsModel(),
        resources);
    MeritsFlawsTabView meritsFlawsTabView = new MeritsFlawsTabView(properties);
    new MeritsFlawsPresenter(meritsFlawsTabView, meritsFlawsModel, resources).initPresentation();
    return meritsFlawsTabView;
  }
}