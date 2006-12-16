package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.presenter.view.IItemTypeCreationViewProperties;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionProperties;

public interface IObjectSelectionPropertiesFactory {

  public IObjectSelectionProperties build(IResources resources, IItemTypeCreationViewProperties properties);
}