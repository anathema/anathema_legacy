package net.sf.anathema.character.sidereal.colleges;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeModel;
import net.sf.anathema.character.sidereal.colleges.presenter.SiderealCollegePresenter;
import net.sf.anathema.character.sidereal.colleges.view.SiderealCollegeView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

public class SiderealCollegeViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType type) {
    SiderealCollegeView view = new SiderealCollegeView();
    new SiderealCollegePresenter(resources, view, (ISiderealCollegeModel) model).initPresentation();
    return view;
  }
}