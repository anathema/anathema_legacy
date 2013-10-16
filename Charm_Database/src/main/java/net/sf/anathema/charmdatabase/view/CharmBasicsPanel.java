package net.sf.anathema.charmdatabase.view;

import net.sf.anathema.charmdatabase.presenter.DirectUi;
import net.sf.anathema.charmdatabase.presenter.TypeUi;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface CharmBasicsPanel {

  ITextView addNameView(String label);

  ObjectSelectionView<Identifier> addTypeView(String string, DirectUi compositionUi);
  
  ObjectSelectionView<Identifier> addGroupView(String string, TypeUi compositionUi);
}