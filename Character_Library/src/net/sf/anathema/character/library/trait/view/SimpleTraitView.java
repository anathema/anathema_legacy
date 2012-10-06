package net.sf.anathema.character.library.trait.view;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.framework.value.IntegerViewFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class SimpleTraitView extends AbstractTraitView implements ITraitView<SimpleTraitView> {
  private final JLabel label;
  private final Component displayComponent;
  private final CC dotAlignment;
  private final CC labelAlignment;
  private JPanel traitViewPanel;

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue) {
    this(factory, labelText, value, maxValue, null, new CC().alignX("right"));
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue,
                         IModifiableCapTrait trait) {
    this(factory, labelText, value, maxValue, trait, new CC().alignX("right"));
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue,
                         CC dotAlignment) {
    this(factory, labelText, value, maxValue, null, dotAlignment, new CC().growX().pushX());
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue,
                         IModifiableCapTrait trait, CC dotAlignment) {
    this(factory, labelText, value, maxValue, trait, dotAlignment, new CC().growX().pushX());
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue,
                         IModifiableCapTrait trait, CC dotAlignment, CC labelAlignment) {
    super(factory, labelText, value, maxValue, trait);
    Preconditions.checkArgument(getLabelText() != null, "Label-Text must be set.");
    this.label = new JLabel(getLabelText());
    this.displayComponent = maxValue > 0 ? getValueDisplay().getComponent() : null;
    this.dotAlignment = dotAlignment;
    this.labelAlignment = labelAlignment;
  }

  @Override
  public void addComponents(JPanel panel) {
    this.traitViewPanel = panel;
    panel.add(label, labelAlignment);
    if (displayComponent != null) {
      panel.add(displayComponent, dotAlignment);
    }
  }

  @Override
  public void delete() {
    traitViewPanel.remove(label);
    traitViewPanel.remove(displayComponent);
    traitViewPanel.revalidate();
  }

  @Override
  public SimpleTraitView getInnerView() {
    return this;
  }
}