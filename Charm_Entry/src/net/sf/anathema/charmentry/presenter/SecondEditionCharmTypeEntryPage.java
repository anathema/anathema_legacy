package net.sf.anathema.charmentry.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionCharmTypeEntryPage extends CharmTypeEntryPage {

  public SecondEditionCharmTypeEntryPage(
      IResources resources,
      ICharmEntryModel model,
      ICharmEntryViewFactory viewFactory) {
    super(resources, model, viewFactory);
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    super.addFollowUpPages(inputListener);
    addFollowupPage(
        new SimpleSpecialEntryPage(getResources(), getModel(), getViewFactory()),
        inputListener,
        new ICondition() {
          public boolean isFullfilled() {
            return isSimpleCharm();
          }
        });
    addFollowupPage(
        new ReflexiveSpecialEntryPage(getResources(), getModel(), getViewFactory()),
        inputListener,
        new ICondition() {
          public boolean isFullfilled() {
            return isReflexiveWithModel();
          }
        });
  }

  @Override
  protected boolean isSpecialCharmType() {
    return super.isSpecialCharmType() || isSimpleCharm() || isReflexiveWithModel();
  }

  private boolean isSimpleCharm() {
    return getPageModel().getCharmType() == CharmType.Simple;
  }

  private boolean isReflexiveWithModel() {
    return getPageModel().getCharmType() == CharmType.Reflexive && getPageModel().isSpecialModelEnabled();
  }

  @Override
  protected void initPageContent() {
    super.initPageContent();
    initAnnotationView();
  }

  private void initAnnotationView() {
    final JToggleButton button = getPageContent().addCheckBoxRow(getProperties().getSpecialModelLabel());
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
        if (getPageModel().getCharmType() == CharmType.Simple) {
          button.setSelected(true);
          getPageModel().setSpecialModelEnabled(true);
          button.setEnabled(false);
          return;
        }
        button.setEnabled(available);
      }
    });
    getPageModel().setSpecialModelEnabled(getPageModel().isSpecialModelAvailable());
  }
}