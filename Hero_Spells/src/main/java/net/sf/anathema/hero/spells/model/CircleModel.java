package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.spells.CircleType;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ObjectValueListener;
import org.jmock.example.announcer.Announcer;

public abstract class CircleModel {

  private final Hero hero;
  private final Announcer<ObjectValueListener> announcer = Announcer.to(ObjectValueListener.class);
  private CircleType selectedCircle;

  protected CircleModel(Hero hero) {
    this.hero = hero;
    this.selectedCircle = getCircles()[0];
  }

  public abstract CircleType[] getCircles();

  protected final ISpellMagicTemplate getSpellMagicTemplate() {
    return hero.getTemplate().getMagicTemplate().getSpellMagic();
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
