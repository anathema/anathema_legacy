package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JList;
import java.awt.Component;

public abstract class LegalityCharmRenderer extends LegalityCheckListCellRenderer {

  private final ICharmInfoStringBuilder charmInfoStringProvider;

  public LegalityCharmRenderer(IResources resources, ICharmInfoStringBuilder charmInfoStringProvider) {
    super(resources);
    this.charmInfoStringProvider = charmInfoStringProvider;
  }

  @Override
  public Component getListCellRendererComponent(JList list, final Object value, int index, boolean isSelected,
                                                boolean cellHasFocus) {
    final JComponent renderComponent = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected,
            cellHasFocus);
    String tooltipString = charmInfoStringProvider.getInfoString((ICharm) value, null);
    renderComponent.setToolTipText(tooltipString);
    return renderComponent;
  }
}