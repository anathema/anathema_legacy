package net.sf.anathema.charmentry.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.lib.resources.IResources;

public class AttributeRequirementPresenter implements ICharmEntrySubPresenter {

  private final CharmEntryModel model;
  private final IAttributeRequirementsView view;
  private final IResources resources;

  public AttributeRequirementPresenter(CharmEntryModel model, IAttributeRequirementsView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    final JToggleButton button = view.addCheckBox(resources.getString("CharmEntry.Attribute.RequiresExcellency")); //$NON-NLS-1$
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.setRequiresExcellency(button.isSelected());
      }
    });
  }

  public void charmAdded(IConfigurableCharmData charmData) {
    // Nothing to do
  }
}