package net.sf.anathema.character.lunar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.view.VirtueFlawView;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawPresenter;
import net.sf.anathema.character.lunar.virtueflaw.model.LunarVirtueFlawModel;
import net.sf.anathema.character.lunar.virtueflaw.presenter.ILunarVirtueFlawModel;
import net.sf.anathema.character.lunar.virtueflaw.presenter.ILunarVirtueFlawView;
import net.sf.anathema.character.lunar.virtueflaw.presenter.ComplexLunarVirtueFlawPresenter;
import net.sf.anathema.character.lunar.virtueflaw.view.LunarVirtueFlawView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class LunarVirtueFlawViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
	  IView virtueFlawView = null;
	  IIntValueDisplayFactory factory = new MarkerIntValueDisplayFactory(resources, type);
	  if (model instanceof LunarVirtueFlawModel)
	  {
		  virtueFlawView = new LunarVirtueFlawView(factory);
		  new ComplexLunarVirtueFlawPresenter(resources, (ILunarVirtueFlawView) virtueFlawView,
				  (ILunarVirtueFlawModel) model).initPresentation();
	  }
	  else
	  {
		  virtueFlawView = new VirtueFlawView(factory);
		  new LunarVirtueFlawPresenter(resources, (IVirtueFlawView)virtueFlawView,
				  (IVirtueFlawModel) model).initPresentation();
	  }
    return virtueFlawView;
  }
}