package net.sf.anathema.character.library.trait.view;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.library.intvalue.TraitUpperBounds;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.value.NullUpperBounds;
import net.sf.anathema.framework.value.TwoUpperBounds;
import net.sf.anathema.lib.control.IIntValueChangedListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

public class SimpleTraitView implements ITraitView {
  private final JLabel label;
  private final Component displayComponent;
  private final CC dotAlignment;
  private final CC labelAlignment;
  private final IIntValueDisplay valueDisplay;
  private final String labelText;
  private JPanel traitViewPanel;

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue) {
    this(factory, labelText, value, maxValue, null);
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue, Trait trait) {
    this(factory, labelText, value, maxValue, trait, new CC().alignX("right"), new CC().growX().pushX());
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue, Trait trait,
                         CC dotAlignment, CC labelAlignment) {
    this.labelText = labelText;
    TwoUpperBounds bounds = createBounds(trait);
    this.valueDisplay = maxValue > 0 ? factory.createIntValueDisplay(maxValue, value, bounds) : null;
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

  private TwoUpperBounds createBounds(Trait trait) {
    if (trait == null) {
      return new NullUpperBounds();
    }
    return new TraitUpperBounds(trait);
  }

  @Override
  public void setValue(int newValue) {
    if (valueDisplay != null) {
      valueDisplay.setValue(newValue);
    }
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    if (valueDisplay != null) {
      valueDisplay.addIntValueChangedListener(listener);
    }
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    if (valueDisplay != null) {
      valueDisplay.removeIntValueChangedListener(listener);
    }
  }

  protected String getLabelText() {
    return labelText;
  }

  protected IIntValueDisplay getValueDisplay() {
    return valueDisplay;
  }

  @Override
  public void setMaximum(int maximalValue) {
    valueDisplay.setMaximum(maximalValue);
  }
}