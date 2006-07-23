package net.sf.anathema.character.equipment.creation;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.IWeaponDamageModel;
import net.sf.anathema.character.equipment.creation.properties.OffensiveStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;

public abstract class AbstractOffensiveStatisticsPresenterPage<O extends IOffensiveStatisticsModel, P extends OffensiveStatisticsProperties> extends
    AbstractEquipmentStatisticsPresenterPage<O, P> {

  public AbstractOffensiveStatisticsPresenterPage(
      IResources resources,
      P properties,
      IEquipmentStatisticsCreationModel overallModel,
      O pageModel,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, properties, overallModel, pageModel, viewFactory);
  }

  @Override
  protected final void addAdditionalContent() {
    addIndividualRows();
    initAccuracyAndRateRow();
    initWeaponDamageRow(getPageModel().getWeaponDamageModel());
  }

  protected abstract void addIndividualRows();

  private void initAccuracyAndRateRow() {
    String[] labels = new String[] { getProperties().getAccuracyLabel(), getProperties().getRateLabel() };
    addLabelledComponentRow(labels, new Component[] {
        initIntegerSpinner(getPageModel().getAccuracyModel()).getComponent(),
        initIntegerSpinner(getPageModel().getRateModel(), 1).getComponent() });
  }

  protected final void addLabelledComponentRow(final String[] labels, final Component[] contents) {
    Ensure.ensureArgumentTrue("Same number of labels required", labels.length == contents.length); //$NON-NLS-1$
    getPageContent().addDialogComponent(new IDialogComponent() {
      public void fillInto(JPanel panel, int columnCount) {
        for (int index = 0; index < contents.length; index++) {
          panel.add(new JLabel(labels[index]));
          panel.add(contents[index], GridDialogLayoutData.FILL_HORIZONTAL);
        }
      }

      public int getColumnCount() {
        return contents.length * 2;
      }
    });
  }

  private void initWeaponDamageRow(final IWeaponDamageModel damageModel) {
    final IWeaponDamageView damageView = getViewFactory().createWeaponDamageView();
    new WeaponDamagePresenter(getResources(), damageModel, damageView).initPresentation();
    getPageContent().addDialogComponent(damageView);
  }

  protected final IntegerSpinner initIntegerSpinner(IIntValueModel intModel) {
    final IntegerSpinner spinner = new IntegerSpinner(intModel.getValue());
    new IntValuePresentation().initView(spinner, intModel);
    return spinner;
  }

  protected final IntegerSpinner initIntegerSpinner(IIntValueModel intModel, int minimumValue) {
    final IntegerSpinner spinner = initIntegerSpinner(intModel);
    spinner.setMinimum(minimumValue);
    return spinner;
  }
}