package net.sf.anathema.hero.concept;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

public class DefaultCasteSelection implements CasteSelection {

  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private CasteType type = CasteType.NULL_CASTE_TYPE;

  @Override
  public final CasteType getType() {
    return type;
  }

  @Override
  public final void setType(CasteType type) {
    Preconditions.checkNotNull(type);
    if (this.type == type) {
      return;
    }
    this.type = type;
    fireTypeChangedEvent();
  }

  @Override
  public final void addChangeListener(ChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public boolean isNotSelected() {
    return type == CasteType.NULL_CASTE_TYPE;
  }

  private void fireTypeChangedEvent() {
    control.announce().changeOccurred();
  }
}