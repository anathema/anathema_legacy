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

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue) {
    this(factory, labelText, value, maxValue, new NullUpperBounds());
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue, Trait trait) {
    this(factory, labelText, value, maxValue, new TraitUpperBounds(trait));
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue,
                         TwoUpperBounds upperBounds) {
    this(factory, labelText, value, maxValue, upperBounds, new CC().alignX("right"), new CC().growX().pushX());
  }

  public SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue,
                         TwoUpperBounds upperBounds, CC dotAlignment, CC labelAlignment) {
    this.valueDisplay = factory.createIntValueDisplay(maxValue, value, upperBounds);
    this.label = new JLabel(labelText);
    this.displayComponent = valueDisplay.getComponent();
    this.dotAlignment = dotAlignment;
    this.labelAlignment = labelAlignment;
  }

  @Override
  public void addComponents(JPanel panel) {
    panel.add(label, labelAlignment);
    panel.add(displayComponent, dotAlignment);
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

  @Override
  public void setMaximum(int maximalValue) {
    valueDisplay.setMaximum(maximalValue);
  }
}