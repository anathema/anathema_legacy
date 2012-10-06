package net.sf.anathema.character.craft.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.removableentry.view.AbstractRemovableEntryView;
import net.sf.anathema.character.library.selection.IRemovableStringEntriesView;
import net.sf.anathema.character.library.selection.IStringSelectionView;
import net.sf.anathema.character.library.selection.StringSelectionView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.library.trait.view.RearButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.IView;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class CraftView extends AbstractRemovableEntryView<IRemovableTraitView<SimpleTraitView>> implements
    IView,
    IRemovableStringEntriesView<SimpleTraitView> {
  private final IntegerViewFactory factory;
  private final JPanel mainPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));
  private final JPanel entryPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(2).fillX()));
  private final int traitMaximum;

  public CraftView(IntegerViewFactory factory, int maximum) {
    this.factory = factory;
    this.traitMaximum = maximum;
  }

  @Override
  public JComponent getComponent() {
    mainPanel.add(entryPanel, new CC().growX().alignY("top"));
    return mainPanel;
  }

  @Override
  public IStringSelectionView addSelectionView(String labelText, Icon addIcon) {
    return new StringSelectionView(mainPanel, labelText, addIcon);
  }

  @Override
  public IRemovableTraitView<SimpleTraitView> addEntryView(Icon removeIcon, IModifiableCapTrait trait, String string) {
    SimpleTraitView view = new SimpleTraitView(factory, string, 0, traitMaximum, trait, new CC().growX());
    RearButtonTraitViewWrapper<SimpleTraitView> traitView = new RearButtonTraitViewWrapper<SimpleTraitView>(
        view,
        removeIcon);
    traitView.addComponents(entryPanel);
    return traitView;
  }
}