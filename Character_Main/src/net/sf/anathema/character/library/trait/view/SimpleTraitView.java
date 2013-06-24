package net.sf.anathema.character.library.trait.view;

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
    this.valueDisplay = factory.createIntValueDisplay(maxValue, value, bounds);
    this.label = new JLabel(getLabelText());
    this.displayComponent = getValueDisplay().getComponent();
    this.dotAlignment = dotAlignment;
    this.labelAlignment = labelAlignment;
  }

  @Override
  public void addComponents(JPanel panel) {
    panel.add(label, labelAlignment);
    panel.add(displayComponent, dotAlignment);
  }

  private TwoUpperBounds createBounds(Trait trait) {
    if (trait == null) {
      return new NullUpperBounds();
    }
    return new TraitUpperBounds(trait);
  }

  @Override
  public void setValue(int newValue) {
    valueDisplay.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    valueDisplay.addIntValueChangedListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    valueDisplay.removeIntValueChangedListener(listener);
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