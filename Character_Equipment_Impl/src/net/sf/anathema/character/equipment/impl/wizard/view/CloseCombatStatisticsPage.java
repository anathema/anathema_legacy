package net.sf.anathema.character.equipment.impl.wizard.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.wizard.IWizardConfiguration;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.equipment.impl.wizard.model.CloseCombatStatisticsModel;
import net.sf.anathema.character.equipment.impl.wizard.properties.ICloseCombatStatisticsProperties;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class CloseCombatStatisticsPage extends AbstractAnathemaWizardPage {

  private final CloseCombatStatisticsModel model;
  private final ICloseCombatStatisticsProperties properties;

  public CloseCombatStatisticsPage(
      IWizardConfiguration wizard,
      CloseCombatStatisticsModel model,
      ICloseCombatStatisticsProperties properties) {
    super("description", new BasicMessage("Basic Message"), wizard);
    this.model = model;
    this.properties = properties;
  }

  @Override
  protected IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  @Override
  protected JComponent createContent() {
    JPanel panel = new JPanel(new GridDialogLayout(4, false));
    StandardPanelBuilder builder = new StandardPanelBuilder();
    initNameView(builder);
    initSpeedView(builder);
    
    return panel;
  }

  private void initNameView(StandardPanelBuilder builder) {
    ITextView view = builder.addLineTextView(properties.getNameString(), 20);
    view.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.getNameModel().setValue(newValue);
      }      
    });
  }

  private void initSpeedView(StandardPanelBuilder builder) {
    IntegerSpinner spinner = builder.addIntegerSpinner(properties.getSpeedString(), 0);
    spinner.setMinimum(1);
    spinner.setMaximum(null);
    spinner.addChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        model.getSpeedModel().setValue(newValue);
      }      
    });
  }

  public boolean canFinish() {
    return isComplete();
  }

  public void requestFocus() {
  }

  @Override
  public boolean isComplete() {
    return model.isComplete();
  }
}