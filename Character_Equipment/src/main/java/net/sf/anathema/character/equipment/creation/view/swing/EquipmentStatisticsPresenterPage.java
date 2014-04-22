package net.sf.anathema.character.equipment.creation.view.swing;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.AbstractEquipmentStatisticsProperties;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class EquipmentStatisticsPresenterPage<M extends IEquipmentStatisticsModel, P extends AbstractEquipmentStatisticsProperties> extends
        AbstractDialogPage {

  private final P properties;
  private final M pageModel;
  private final IEquipmentStatisticsCreationModel overallModel;
  private IWeaponStatisticsView view;

  public EquipmentStatisticsPresenterPage(
          P properties,
          IEquipmentStatisticsCreationModel overallModel,
          M pageModel,
          IWeaponStatisticsView view) {
    super(properties.getDefaultMessage().getText());
    this.properties = properties;
    this.pageModel = pageModel;
    this.overallModel = overallModel;
    this.view = view;
  }

  public IEquipmentStatisticsCreationModel getOverallModel() {
    return overallModel;
  }

  private boolean isNameDefined() {
    return !pageModel.getName().isEmpty();
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    if (!isNameDefined()) {
      return properties.getUndefinedNameMessage();
    }
    if (!isNameUnique()) {
      return properties.getDuplicateNameMessage();
    }
    return properties.getDefaultMessage();
  }

  private boolean isNameUnique() {
    return overallModel.isNameUnique(pageModel.getName().getText());
  }

  @Override
  public String getTitle() {
    return properties.getPageDescription();
  }

  @Override
  public final String getDescription() {
    return properties.getPageDescription();
  }

  @Override
  public boolean canFinish() {
    return isNameDefined() && isNameUnique();
  }

  @Override
  public final JComponent createContent() {
    return view.getComponent();
  }

  @Override
  public void setInputValidListener(ChangeListener inputValidListener) {
    pageModel.getName().addTextChangedListener(new CheckInputListener(new Runnable() {
      @Override
      public void run() {
        inputValidListener.changeOccurred();
      }
    }));
  }

  public final void addView(AdditiveView view) {
    this.view.addView(view);
  }

  public final void addLabelledComponentRow(final String[] labels, final Component[] contents) {
    Preconditions.checkArgument(labels.length == contents.length, "Same number of labels and content items required");
    addView(new AdditiveView() {
      @Override
      public void addTo(JPanel panel) {
        for (int index = 0; index < contents.length; index++) {
          panel.add(new JLabel(labels[index]));
          panel.add(contents[index], new CC().growX().pushX());
        }
      }
    });
  }

  public final IntegerSpinner initIntegerSpinner(IIntValueModel intModel) {
    IntegerSpinner spinner = new IntegerSpinner(intModel.getValue());
    new IntValuePresentation().initView(spinner, intModel);
    return spinner;
  }
}