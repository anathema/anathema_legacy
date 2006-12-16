package net.sf.anathema.framework.presenter.action;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.framework.presenter.view.IItemTypeCreationViewProperties;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionProperties;

public class DeleteWizardPropertiesFactory implements IObjectSelectionPropertiesFactory {

  public IObjectSelectionProperties build(IResources resources, final IItemTypeCreationViewProperties properties) {
    return new DeleteItemWizardProperties(resources, new IObjectUi() {
      public Icon getIcon(Object value) {
        return properties.getPrintNameFileIcon((PrintNameFile) value);
      }

      public String getLabel(Object value) {
        return properties.getPrintNameFileLabel((PrintNameFile) value);
      }
    });
  }
}