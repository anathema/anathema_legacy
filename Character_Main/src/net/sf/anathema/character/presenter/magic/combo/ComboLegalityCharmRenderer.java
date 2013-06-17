package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.charmtree.builder.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

public class ComboLegalityCharmRenderer extends LegalityCheckListCellRenderer {

  public ComboLegalityCharmRenderer(Resources resources, ICharmInfoStringBuilder charmInfoStringBuilder, final IComboConfiguration comboConfiguration) {
    super(new ComboLegality(comboConfiguration), new CharmUIConfiguration(resources, charmInfoStringBuilder));
  }
}