package net.sf.anathema.character.lunar.beastform.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformView;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SecondEditionBeastformView implements IBeastformView {
  private final JPanel spiritNamePanel = new JPanel(new MigLayout(withoutInsets()));
  private final LineTextView spiritNameBox = new LineTextView(45);
  private final JPanel spiritAttributePanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
  private final JPanel beastmanAttributePanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
  private final JPanel giftPanel = new JPanel(new MigLayout(fillWithoutInsets()));
  private final IntegerViewFactory intValueDisplayFactory;
  private final JPanel content = new JPanel();
  private final JPanel overviewPanel = new JPanel();
  private final IBeastformViewProperties properties;

  public SecondEditionBeastformView(IntegerViewFactory intValueDisplayFactory, IBeastformViewProperties properties) {
    this.intValueDisplayFactory = intValueDisplayFactory;
    this.properties = properties;
  }

  @Override
  public JComponent getComponent() {
    JPanel spiritPanel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
    spiritPanel.add(spiritNamePanel);
    PromptSupport.setPrompt(properties.getSpiritFormBoxInitialString(), spiritNameBox.getTextComponent());
    spiritNamePanel.add(spiritNameBox.getComponent());
    spiritAttributePanel.setBorder(new TitledBorder(properties.getAttributesString()));
    spiritPanel.add(spiritAttributePanel);
    spiritPanel.setBorder(BorderFactory.createTitledBorder(properties.getSpiritFormBoxString()));
    JPanel beastmanPanel = new JPanel();
    beastmanPanel.setLayout(new BoxLayout(beastmanPanel, BoxLayout.Y_AXIS));
    JPanel beastAttributePane = new JPanel(new MigLayout(fillWithoutInsets()));
    beastAttributePane.add(beastmanAttributePanel);
    beastmanAttributePanel.setBorder(new TitledBorder(properties.getAttributesString()));
    beastmanPanel.add(beastAttributePane);
    giftPanel.setBorder(new TitledBorder(properties.getGiftsString()));
    JPanel mutationPanel = new JPanel(new MigLayout(fillWithoutInsets()));
    mutationPanel.add(giftPanel);
    mutationPanel.add(overviewPanel, new CC().alignY("top"));
    beastmanPanel.add(mutationPanel);
    beastmanPanel.setBorder(BorderFactory.createTitledBorder(properties.getDBTBoxString()));
    content.add(spiritPanel);
    content.add(beastmanPanel);
    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
    return content;
  }

  @Override
  public SimpleTraitView addAttributeValueView(String label, int value, int maxValue) {
    SimpleTraitView traitView = new SimpleTraitView(intValueDisplayFactory, label, value, maxValue,
            new CC().alignX("left"));
    traitView.addComponents(beastmanAttributePanel);
    return traitView;
  }

  public void setSpiritListener(ObjectValueListener<String> listener) {
    spiritNameBox.addTextChangedListener(listener);
  }

  public SimpleTraitView addSpiritAttributeValueView(String label, int value, int maxValue) {
    SimpleTraitView traitView = new SimpleTraitView(intValueDisplayFactory, label, value, maxValue,
            new CC().alignX("left"));
    traitView.addComponents(spiritAttributePanel);
    return traitView;
  }

  @Override
  public void addMutationsView(IMutationsView mutationView) {
    giftPanel.add(mutationView.getComponent());
  }
}