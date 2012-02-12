package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.model.ItemManagmentModelListener;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.presenter.ModelViewMapping;
import net.sf.anathema.framework.presenter.itemmanagement.ItemViewSelectionListener;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.resources.IResources;

@BootJob
public class ItemViewBootJob implements IAnathemaBootJob {

  public void run(IResources resources, IAnathemaModel model, IAnathemaView view) {
    IModelViewMapping mapping = new ModelViewMapping();
    IItemMangementModel itemManagement = model.getItemManagement();
    itemManagement.addListener(new ItemManagmentModelListener(
        model.getViewFactoryRegistry(),
        view,
        mapping,
        new ItemActionFactory(itemManagement, resources)));
    view.addViewSelectionListener(new ItemViewSelectionListener(itemManagement, mapping));
  }
}