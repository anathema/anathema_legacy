package net.sf.anathema.character.impl.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.impl.view.advantage.EssencePanelView;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.view.AdvantageView;
import net.sf.anathema.character.view.AdvantageViewProperties;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class BasicAdvantageView implements AdvantageView, IView {
  private final JPanel virtuePanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
  private final JPanel willpowerPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
  private final EssencePanelView essencePanelView;
  private final IntegerViewFactory guiConfiguration;
  private final JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT));

  public BasicAdvantageView(IntegerViewFactory intValueDisplayFactory) {
    this.guiConfiguration = intValueDisplayFactory;
    this.essencePanelView = new EssencePanelView(intValueDisplayFactory);
  }

  @Override
  public final void initGui(AdvantageViewProperties properties) {
    JPanel innerPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2)));
    content.add(innerPanel);
    addTitledPanel(properties.getVirtueTitle(), innerPanel, virtuePanel, new CC().spanY(2).growY().pushY());
    addTitledPanel(properties.getWillpowerTitle(), innerPanel, willpowerPanel, new CC().growX().pushX().alignY("top"));
    addTitledPanel(properties.getEssenceTitle(), innerPanel, essencePanelView.getComponent(), new CC().growX().pushX().alignY("bottom"));
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
  public IIntValueView addEssenceView(String labelText, int value, int maxValue, Trait trait) {
    return essencePanelView.addEssenceView(labelText, value, maxValue, trait);
  }

  @Override
  public IValueView<String> addPoolView(String labelText, String value) {
    return essencePanelView.addPoolView(labelText, value);
  }

  private JComponent addTitledPanel(String title, JPanel container, JComponent component, CC constraint) {
    component.setBorder(new TitledBorder(title));
    container.add(component, constraint);
    return component;
  }
}