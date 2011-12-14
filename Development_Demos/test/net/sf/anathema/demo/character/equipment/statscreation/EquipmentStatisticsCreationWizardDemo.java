package net.sf.anathema.demo.character.equipment.statscreation;

import de.jdemo.junit.DemoAsTestRunner;
import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.EquipmentTypeChoicePresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.creation.EquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.creation.model.EquipmentStatisticsCreationModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import de.jdemo.extensions.SwingDemoCase;
import org.junit.runner.RunWith;

@RunWith(DemoAsTestRunner.class)
public class EquipmentStatisticsCreationWizardDemo extends SwingDemoCase {

  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel(new String[0], ExaltedRuleSet.SecondEdition);
    IEquipmentStatisticsCreationViewFactory viewFactory = new EquipmentStatisticsCreationViewFactory();
    EquipmentTypeChoicePresenterPage startPage = new EquipmentTypeChoicePresenterPage(resources, model, null, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(createParentComponent(), startPage);
    show(dialog.getConfiguredDialog().getWindow());
  }
}