package net.sf.anathema.hero.magic.display.special;

import net.sf.anathema.character.main.library.trait.view.TraitView;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.swing.hero.traitview.ConfigurableLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import static net.sf.anathema.swing.hero.traitview.ConfigurableLayout.Right;

public class SimpleTraitView implements TraitView {
  public static SimpleTraitView RightAlignedWithoutUpperBounds(IntegerViewFactory factory, String labelText, int value, int maxValue) {
    return new SimpleTraitView(factory, labelText, value, maxValue);
  }

  private final ConfigurableLayout layout = Right();
  private final IIntValueDisplay valueDisplay;
  private final String labelText;

  private SimpleTraitView(IntegerViewFactory factory, String labelText, int value, int maxValue) {
    this.labelText = labelText;
    this.valueDisplay = factory.createIntValueDisplay(maxValue, value);
  }

  @Override
  public void addComponents(JPanel panel) {
    layout.addLabel(new JLabel(labelText)).addDisplay(valueDisplay.getComponent()).toPanel(panel);
  }

  @Override
  public void setValue(int newValue) {
    valueDisplay.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IntValueChangedListener listener) {
    valueDisplay.addIntValueChangedListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IntValueChangedListener listener) {
    valueDisplay.removeIntValueChangedListener(listener);
  }
}