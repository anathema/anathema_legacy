package net.sf.anathema.character.library.trait.view;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.framework.value.IntegerViewFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class SimpleTraitView extends AbstractTraitView implements ITraitView {
  private final JLabel label;
  private final Component displayComponent;
  private final CC dotAlignment;
  private final CC labelAlignment;
  private JPanel traitViewPanel;

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue) {
    this(factory, labelText, value, maxValue, null);
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue, Trait trait) {
    this(factory, labelText, value, maxValue, trait, new CC().alignX("right"), new CC().growX().pushX());
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue, Trait trait, CC dotAlignment,
                         CC labelAlignment) {
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
}