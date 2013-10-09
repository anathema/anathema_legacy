package net.sf.anathema.charmdatabase.view.fx;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.charmdatabase.presenter.DirectUi;
import net.sf.anathema.charmdatabase.presenter.TypeUi;
import net.sf.anathema.charmdatabase.view.CharmBasicsPanel;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;

public class FxCharmBasicsPanel extends AbstractFxContainerPanel implements CharmBasicsPanel {

  public FxCharmBasicsPanel(SelectionViewFactory selectionFactory) {
	super(selectionFactory, new LC().wrapAfter(2).fill().insets("4"), new AC(), new AC().index(1).shrinkPrio(200));
  }

  @Override
  public ITextView addNameView(String label) {
	return addSingleTextView(label, new CC().growX().pushY().span());
  }

  @Override
  public ObjectSelectionView<Identifier> addTypeView(String label, DirectUi ui) {
	  return addSelectionView(label, ui, new CC().grow());
  }

  @Override
  public ObjectSelectionView<Identifier> addGroupView(String label, TypeUi ui) {
	  return addSelectionView(label, ui, new CC().grow());
  }
}