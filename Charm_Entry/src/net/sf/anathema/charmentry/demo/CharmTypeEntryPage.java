package net.sf.anathema.charmentry.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.demo.view.IReflexiveCharmSpecialsView;
import net.sf.anathema.charmentry.model.IReflexiveSpecialsEntryModel;
import net.sf.anathema.charmentry.presenter.ISimpleSpecialsEntryModel;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public class CharmTypeEntryPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final ICharmTypeEntryPageProperties properties;
  private final IBasicMessage defaultMessage;
  private ICharmTypeEntryView view;

  public CharmTypeEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new CharmTypeEntryPageProperties(resources);
    this.defaultMessage = new BasicMessage(properties.getCharmTypeMessage());
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new PrerequisitesEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFullfilled() {
        return getPageModel().getCharmType() != null;
      }
    });
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().addModelListener(inputListener);
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.getCharmTypeEntryView();
    initTypeView();
    initAnnotationView();
    initSimpleSpecialsView();
    initReflexiveView();
  }

  private void initAnnotationView() {
    final JToggleButton button = view.addCheckBoxRow(properties.getSpecialModelLabel());
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getPageModel().setSpecialModelEnabled(button.isSelected());
      }
    });
    getPageModel().addModelListener(new IChangeListener() {
      public void changeOccured() {
        final boolean available = getPageModel().isSpecialModelAvailable();
        if (!available) {
          button.setSelected(false);
          getPageModel().setSpecialModelEnabled(false);
        }
        button.setEnabled(available);
      }
    });
    getPageModel().setSpecialModelEnabled(getPageModel().isSpecialModelAvailable());
  }

  private void initTypeView() {
    IObjectSelectionView typeView = view.addComboBoxRow(properties.getTypeLabel(), new IdentificateSelectCellRenderer(
        "", //$NON-NLS-1$
        resources), getPageModel().getCharmTypes());
    typeView.addObjectSelectionChangedListener(new IObjectValueChangedListener<CharmType>() {
      public void valueChanged(CharmType newValue) {
        getPageModel().setCharmType(newValue);
      }
    });
  }

  private void initReflexiveView() {
    final IReflexiveSpecialsEntryModel reflexiveModel = getPageModel().getReflexiveSpecialsModel();
    final IReflexiveCharmSpecialsView reflexiveView = view.addReflexiveCharmSpecialsView(
        properties.getReflexiveStepLabel(),
        properties.getDefaultStepLabel(),
        properties.getDefensiveStepLabel(),
        properties.getSplitStepLabel());
    reflexiveView.addStepListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        reflexiveModel.setStep(newValue);
      }
    });
    reflexiveView.addDefenseStepListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        reflexiveModel.setDefenseStep(newValue);
      }
    });
    reflexiveView.addSplitListener(new IBooleanValueChangedListener() {
      public void valueChanged(boolean splitEnabled) {
        reflexiveModel.setSplitEnabled(splitEnabled);
      }
    });
    getPageModel().addModelListener(new IChangeListener() {
      public void changeOccured() {
        reflexiveView.setEnabled(reflexiveModel.isActive());
      }
    });
    reflexiveView.setEnabled(reflexiveModel.isActive());
  }

  private void initSimpleSpecialsView() {
    final ISimpleSpecialsEntryModel simpleModel = getPageModel().getSimpleSpecialsModel();
    final ISimpleCharmSpecialsView simpleView = view.addSimpleCharmSpecialsView(
        properties.getModifiersLabel(),
        properties.getSpeedLabel(),
        properties.getDefenseLabel());
    simpleView.addSpeedValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        simpleModel.setSpeed(newValue);
      }
    });
    simpleView.addDefenseValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        simpleModel.setDefenseValue(newValue);
      }
    });
    getPageModel().addModelListener(new IChangeListener() {
      public void changeOccured() {
        simpleView.setEnabled(simpleModel.isActive());
      }
    });
    simpleView.setEnabled(simpleModel.isActive());
  }

  public boolean canFinish() {
    return false;
  }

  public String getDescription() {
    return properties.getPageHeader();
  }

  public IBasicMessage getMessage() {
    return defaultMessage;
  }

  public IPageContent getPageContent() {
    return view;
  }

  private ICharmTypeEntryModel getPageModel() {
    return model.getCharmTypeModel();
  }
}