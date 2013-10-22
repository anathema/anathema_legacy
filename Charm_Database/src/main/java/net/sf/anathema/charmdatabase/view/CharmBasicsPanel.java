package net.sf.anathema.charmdatabase.view;

import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface CharmBasicsPanel {

  ITextView addNameView(String label);

  ObjectSelectionView<Identifier> addTypeView(String string, AbstractUIConfiguration<Identifier> ui);
  
  ObjectSelectionView<Identifier> addGroupView(String string, AbstractUIConfiguration<Identifier> ui);
  
  ObjectSelectionView<Identifier> addTraitView(String string, AbstractUIConfiguration<Identifier> ui);
}