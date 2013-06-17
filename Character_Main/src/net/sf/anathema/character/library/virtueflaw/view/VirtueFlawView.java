package net.sf.anathema.character.library.virtueflaw.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.generic.framework.util.ExperienceUtilities;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.swing.selection.ObjectSelectionView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.ui.ConfigurableListCellRenderer;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.SwingTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.Container;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class VirtueFlawView implements IVirtueFlawView, IView {
  private final JPanel virtueFlawPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2)));
  private final IntegerViewFactory intValueDisplayFactory;

  public VirtueFlawView(IntegerViewFactory factory) {
    this.intValueDisplayFactory = factory;
  }

  @Override
  public ITextView addTextView(String labelText, int columns) {
    LineTextView textView = new LineTextView(columns);
    fillIntoVirtueFlawPanel(labelText, textView);
    return textView;
  }

  @Override
  public IIntValueView addLimitValueView(String label, int value, int maxValue) {
    SimpleTraitView traitView = new SimpleTraitView(intValueDisplayFactory, label, value, maxValue, null, new CC().alignX("left"), new CC());
    traitView.addComponents(virtueFlawPanel);
    return traitView;
  }

  protected void fillIntoVirtueFlawPanel(String labelText, SwingTextView textView) {
    new LabelTextView(labelText, textView).addToMigPanel(virtueFlawPanel);
  }

  @Override
  public void setEnabled(boolean enabled) {
    net.sf.anathema.lib.gui.swing.GuiUtilities.setEnabled(virtueFlawPanel, enabled);
    handleSpecialComponents(virtueFlawPanel, enabled);
  }

  private void handleSpecialComponents(Container container, boolean enabled) {
    for (Component component : container.getComponents()) {
      if (component instanceof Container) {
        handleSpecialComponents((Container) component, enabled);
      }
    }
    ExperienceUtilities.setLabelColor(container, enabled);
  }

  @Override
  public JComponent getComponent() {
    return virtueFlawPanel;
  }

  @Override
  public IObjectSelectionView<TraitType> addVirtueFlawRootSelectionView(String labelText, AgnosticUIConfiguration uiConfiguration) {
    ListCellRenderer renderer = new ConfigurableListCellRenderer(uiConfiguration);
    ObjectSelectionView<TraitType> rootSelectionView = new ObjectSelectionView<>(labelText, renderer, new TraitType[0]);
    rootSelectionView.addTo(virtueFlawPanel, new CC().growX());
    return rootSelectionView;
  }
}