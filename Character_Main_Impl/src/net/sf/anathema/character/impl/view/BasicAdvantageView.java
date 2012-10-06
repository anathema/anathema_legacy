package net.sf.anathema.character.impl.view;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.character.impl.view.advantage.EssencePanelView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.IAdvantageViewProperties;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;

public class BasicAdvantageView implements IBasicAdvantageView, IInitializableContentView<IAdvantageViewProperties> {
  private final JPanel virtuePanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel willpowerPanel = new JPanel(new GridDialogLayout(2, false));
  private final EssencePanelView essencePanelView;
  private final IntegerViewFactory guiConfiguration;
  private final JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT));

  public BasicAdvantageView(IntegerViewFactory intValueDisplayFactory) {
    this.guiConfiguration = intValueDisplayFactory;
    this.essencePanelView = new EssencePanelView(intValueDisplayFactory);
  }

  @Override
  public final void initGui(IAdvantageViewProperties properties) {
    JPanel innerPanel = new JPanel(new GridDialogLayout(2, false));
    content.add(innerPanel);
    GridDialogLayoutData virtueData = new GridDialogLayoutData();
    virtueData.setVerticalSpan(2);
    virtueData.setVerticalAlignment(GridAlignment.FILL);
    addTitledPanel(properties.getVirtueTitle(), innerPanel, virtuePanel, virtueData);
    GridDialogLayoutData willpowerData = new GridDialogLayoutData(GridDialogLayoutData.FILL_HORIZONTAL);
    willpowerData.setVerticalAlignment(GridAlignment.BEGINNING);
    addTitledPanel(properties.getWillpowerTitle(), innerPanel, willpowerPanel, willpowerData);
    GridDialogLayoutData essenceData = new GridDialogLayoutData(GridDialogLayoutData.FILL_HORIZONTAL);
    essenceData.setVerticalAlignment(GridAlignment.END);
    addTitledPanel(properties.getEssenceTitle(), innerPanel, essencePanelView.getComponent(), essenceData);
  }

  @Override
  public final JComponent getComponent() {
    return content;
  }

  @Override
  public IIntValueView addVirtue(String labelText, int value, int maxValue) {
    SimpleTraitView virtueView = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    virtueView.addComponents(virtuePanel);
    return virtueView;
  }

  @Override
  public IIntValueView addWillpower(String labelText, int value, int maxValue) {
    SimpleTraitView willpowerView = new SimpleTraitView(guiConfiguration, labelText, value, maxValue);
    willpowerView.addComponents(willpowerPanel);
    return willpowerView;
  }

  @Override
  public IIntValueView addEssenceView(String labelText, int value, int maxValue, IModifiableCapTrait trait) {
    return essencePanelView.addEssenceView(labelText, value, maxValue, trait);
  }

  @Override
  public IValueView<String> addPoolView(String labelText, String value) {
    return essencePanelView.addPoolView(labelText, value);
  }

  private JComponent addTitledPanel(String title, JPanel container, JComponent component,
                                    IGridDialogLayoutData constraint) {
    component.setBorder(new TitledBorder(title));
    container.add(component, constraint);
    return component;
  }
}