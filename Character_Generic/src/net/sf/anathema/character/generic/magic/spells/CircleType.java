package net.sf.anathema.character.generic.magic.spells;

import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.List;

public enum CircleType implements Identified {

  Terrestrial {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitTerrestrial(this);
    }

    @Override
    public CircleType[] getComparableCircles() {
      return getSorceryCircles();
    }
    
    @Override
    public boolean isSorceryCircle() {
      return true;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return false;
    }
  },
  Celestial {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitCelestial(this);
    }

    @Override
    public CircleType[] getComparableCircles() {
      return getSorceryCircles();
    }
    
    @Override
    public boolean isSorceryCircle() {
      return true;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return false;
    }
  },
  Solar {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitSolar(this);
    }

    @Override
    public CircleType[] getComparableCircles() {
      return getSorceryCircles();
    }
    
    @Override
    public boolean isSorceryCircle() {
      return true;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return false;
    }
  },
  Shadowlands {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitShadowland(this);
    }

    @Override
    public CircleType[] getComparableCircles() {
      return getNecromancyCircles();
    }
    
    @Override
    public boolean isSorceryCircle() {
      return false;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return true;
    }
  },
  Labyrinth {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitLabyrinth(this);
    }

    @Override
    public CircleType[] getComparableCircles() {
      return getNecromancyCircles();
    }
    
    @Override
    public boolean isSorceryCircle() {
      return false;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return true;
    }
  },
  Void {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitVoid(this);
    }

    @Override
    public CircleType[] getComparableCircles() {
      return getNecromancyCircles();
    }
    
    @Override
    public boolean isSorceryCircle() {
      return false;
    }
    
    @Override
    public boolean isNecromancyCircle() {
      return true;
    }
  };

  @Override
  public String getId() {
    return name();
  }

  public abstract void accept(ICircleTypeVisitor visitor);

  public abstract CircleType[] getComparableCircles();
  
  public abstract boolean isSorceryCircle();
  
  @SuppressWarnings("UnusedDeclaration")
  public abstract boolean isNecromancyCircle();

  public static CircleType[] getSorceryCircles() {
    return new CircleType[] { Terrestrial, Celestial, Solar };
  }

  public static CircleType[] getNecromancyCircles() {
    return new CircleType[] { Shadowlands, Labyrinth, Void };
  }

  public static CircleType[] getSorceryCirclesUpTo(CircleType maximumCircle) {
    CircleType[] sorceryCircles = getSorceryCircles();
    return getCirclesUpTo(maximumCircle, sorceryCircles);
  }

  public static CircleType[] getNecromancyCirclesUpTo(CircleType maximumCircle) {
    CircleType[] necromancyCircles = getNecromancyCircles();
    return getCirclesUpTo(maximumCircle, necromancyCircles);
  }

  private static CircleType[] getCirclesUpTo(CircleType maximumCircle, CircleType[] allCircles) {
    if (maximumCircle == null) {
      return new CircleType[0];
    }
    List<CircleType> circleList = new ArrayList<>();
    for (CircleType circle : allCircles) {
      if (circle.compareTo(maximumCircle) <= 0) {
        circleList.add(circle);
      }
    }
    return circleList.toArray(new CircleType[circleList.size()]);
  }
}