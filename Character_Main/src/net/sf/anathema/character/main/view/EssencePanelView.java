package net.sf.anathema.character.main.view;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.main.library.overview.LabelledOverviewStringValueView;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.view.swing.SimpleTraitView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class EssencePanelView implements IView {

  private final JPanel panel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(2)));
  private final IntegerViewFactory guiConfiguration;

  public EssencePanelView(IntegerViewFactory guiConfiguration) {
    this.guiConfiguration = guiConfiguration;
  }

  public IIntValueView addEssenceView(String labelText, int value, int maxValue, Trait trait) {
    SimpleTraitView essenceView = SimpleTraitView.RightAlignedWithUpperBoundsForTrait(guiConfiguration, labelText,
            value, maxValue, trait);
    essenceView.addComponents(panel);
    return essenceView;
  }

  public IValueView<String> addPoolView(String labelText, String value) {
    LabelledOverviewStringValueView poolView = new LabelledOverviewStringValueView(labelText, value, false);
    poolView.addComponents(panel);
    return poolView;
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }
}