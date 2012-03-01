package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmtree.presenter.CharmDye;

import java.awt.*;

public class CascadeCharmDye implements CharmDye {

  private final ICascadeView view;

  public CascadeCharmDye(ICascadeView view) {
    this.view = view;
  }

  public void setCharmVisuals(ICharm charm) {
    view.setCharmVisuals(charm.getId(), Color.WHITE);
  }
}