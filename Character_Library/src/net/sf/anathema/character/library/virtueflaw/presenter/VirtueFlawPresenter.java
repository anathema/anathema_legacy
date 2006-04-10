package net.sf.anathema.character.library.virtueflaw.presenter;

import java.awt.Component;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;
import net.sf.anathema.framework.view.AbstractSelectCellRenderer;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class VirtueFlawPresenter {

  private final IResources resources;
  private final IVirtueFlawView view;
  private final IVirtueFlawModel model;

  public VirtueFlawPresenter(IResources resources, IVirtueFlawView virtueFlawView, IVirtueFlawModel model) {
    this.resources = resources;
    this.view = virtueFlawView;
    this.model = model;
  }

  public void initPresentation() {
    initBasicPresentation();
    initAdditionalPresentation();
    initChangeableListening();
  }

  protected void initAdditionalPresentation() {
    // Nothing to do
  }

  private void initBasicPresentation() {
    IVirtueFlaw virtueFlaw = model.getVirtueFlaw();
    initRootPresentation(virtueFlaw);
    initNamePresentation(virtueFlaw);
  }

  protected void initRootPresentation(final IVirtueFlaw virtueFlaw) {
    final IObjectSelectionView rootView = view.addVirtueFlawRootSelectionView(
        resources.getString("VirtueFlaw.Root.Name"), //$NON-NLS-1$
        new AbstractSelectCellRenderer(resources) {
          @Override
          protected Object getCustomizedDisplayValue(Object value) {
            ITraitType traitType = (ITraitType) value;
            return resources.getString("VirtueType.Name." + traitType.getId()); //$NON-NLS-1$
          }

          @Override
          public Component getListCellRendererComponent(
              javax.swing.JList list,
              Object value,
              int index,
              boolean isSelected,
              boolean cellHasFocus) {
            Component listCellRendererComponent = super.getListCellRendererComponent(
                list,
                value,
                index,
                isSelected,
                cellHasFocus);
            return listCellRendererComponent;
          }
        });
    virtueFlaw.addRootChangeListener(new IChangeListener() {
      public void changeOccured() {
        rootView.setSelectedObject(virtueFlaw.getRoot());
      }
    });
    rootView.addObjectSelectionChangedListener(new IObjectValueChangedListener() {
      public void valueChanged(Object oldValue, Object newValue) {
        virtueFlaw.setRoot((VirtueType) newValue);
      }
    });
    model.addVirtueChangeListener(new VirtueChangeListener() {
      @Override
      public void configuredChangeOccured() {
        updateRootView(rootView);
      }
    });
    updateRootView(rootView);
  }

  private void updateRootView(IObjectSelectionView rootView) {
    VirtueType rootCopy = model.getVirtueFlaw().getRoot();
    rootView.setObjects(model.getFlawVirtueTypes());
    rootView.setSelectedObject(rootCopy);
  }

  protected ITextView initNamePresentation(IVirtueFlaw virtueFlaw) {
    ITextView titleView = view.addTextView(resources.getString("VirtueFlaw.Name.Name"), 30); //$NON-NLS-1$
    TextualPresentation.initView(titleView, virtueFlaw.getName());
    return titleView;
  }

  protected void initChangeableListening() {
    model.addVirtueFlawChangableListener(new IBooleanValueChangedListener() {
      public void valueChanged(boolean newValue) {
        view.setEnabled(newValue);
      }
    });
    view.setEnabled(model.isVirtueFlawChangable());
  }

  protected final IVirtueFlawModel getModel() {
    return model;
  }

  protected final IResources getResources() {
    return resources;
  }
}