package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.spells.CircleType;
import net.sf.anathema.lib.control.ObjectValueListener;
import org.jmock.example.announcer.Announcer;

public abstract class CircleModel {

  private final Announcer<ObjectValueListener> announcer = Announcer.to(ObjectValueListener.class);
  private CircleType selectedCircle;
  private CircleType[] circles;

  protected CircleModel(CircleType[] circles) {
    this.circles = circles;
    this.selectedCircle = circles[0];
  }

  public final CircleType[] getCircles() {
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
