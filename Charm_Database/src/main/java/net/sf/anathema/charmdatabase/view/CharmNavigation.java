package net.sf.anathema.charmdatabase.view;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface CharmNavigation {
  VetoableObjectSelectionView<Charm> getTemplateListView();

  Tool addEditTemplateTool();
  
  ITextView addTextualFilter(String label);
}