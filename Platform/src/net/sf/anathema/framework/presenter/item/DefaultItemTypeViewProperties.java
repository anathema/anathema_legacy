package net.sf.anathema.framework.presenter.item;

import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.lib.resources.IResources;

public final class DefaultItemTypeViewProperties implements IItemTypeViewProperties {
  final IResources resources;
  final String printNameKey;

  public DefaultItemTypeViewProperties(IResources resources, String printNameKey) {
    this.resources = resources;
    this.printNameKey = printNameKey;    
  }

  public String getPrintName() {
    return resources.getString(printNameKey); 
  }
}