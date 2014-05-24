package net.sf.anathema.hero.spells.model;

import com.google.common.collect.Iterables;
import net.sf.anathema.character.magic.spells.CircleType;
import net.sf.anathema.lib.control.ObjectValueListener;
import org.jmock.example.announcer.Announcer;

import java.util.Collection;

public class CircleModel {

  private final Announcer<ObjectValueListener> announcer = Announcer.to(ObjectValueListener.class);
  private final Collection<CircleType> circles;
  private CircleType selectedCircle;

  public CircleModel(Collection<CircleType> circles) {
    this.circles = circles;
    this.selectedCircle = Iterables.getFirst(circles, null);
  }

  public final Collection<CircleType> getCircles() {
    return circles;
  }

  @SuppressWarnings("unchecked")
  public void selectCircle(CircleType circleType) {
    this.selectedCircle = circleType;
    announcer.announce().valueChanged(circleType);
  }

  public CircleType getSelectedCircle() {
    return selectedCircle;
  }

  public void addSelectionListener(ObjectValueListener<CircleType> objectValueListener) {
    announcer.addListener(objectValueListener);
  }
}
