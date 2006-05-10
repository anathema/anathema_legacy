package net.sf.anathema.charmentry.demo;

import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.sf.anathema.charmentry.demo.model.WizardCharmEntryModel;
import net.sf.anathema.charmentry.demo.page.HeaderDataEntryPage;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;

import de.jdemo.extensions.SwingDemoCase;

public class CharmEntryWizardDemo extends SwingDemoCase {
  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    ICharmEntryModel model = new WizardCharmEntryModel();
    ICharmEntryViewFactory viewFactory = new CharmEntryViewFactory(resources);
    HeaderDataEntryPage startPage = new HeaderDataEntryPage(resources, model, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(createParentComponent(), startPage);
    show(dialog.getConfiguredDialog().getWindow());
  }
}
