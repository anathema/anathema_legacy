package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmtree.presenter.AbstractCharmDye;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;

import java.awt.*;

public class CascadeCharmDye extends AbstractCharmDye {

  private final ICascadeView view;

  public CascadeCharmDye(ICascadeView view, CharmGroupInformer informer) {
    super(informer);
    this.view = view;
  }

  public void setCharmVisuals(ICharm charm) {
    view.setCharmVisuals(charm.getId(), Color.WHITE);
  }
}