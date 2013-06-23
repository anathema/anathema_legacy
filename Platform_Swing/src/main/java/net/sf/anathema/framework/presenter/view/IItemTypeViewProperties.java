package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.workflow.wizard.selection.ItemTemplateFactory;

public interface IItemTypeViewProperties {

  RelativePath getIcon();

  ItemTemplateFactory getNewItemWizardFactory();

  String getLabelKey();

  AgnosticUIConfiguration getItemTypeUI();
}