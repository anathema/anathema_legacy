package net.sf.anathema.character.sidereal.colleges;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeModel;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeViewProperties;
import net.sf.anathema.character.sidereal.colleges.presenter.SiderealCollegePresenter;
import net.sf.anathema.character.sidereal.colleges.presenter.SiderealCollegeViewProperties;
import net.sf.anathema.character.sidereal.colleges.view.SiderealCollegeView;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.resources.IResources;

public class SiderealCollegeViewFactory implements IAdditionalViewFactory {

  public ISimpleTabView createView(IAdditionalModel model, IResources resources, CharacterType type) {
    ISiderealCollegeViewProperties properties = new SiderealCollegeViewProperties(resources);
    SiderealCollegeView view = new SiderealCollegeView(properties);
    new SiderealCollegePresenter(resources, view, ((ISiderealCollegeModel) model)).initPresentation();
    return view;
  }
}