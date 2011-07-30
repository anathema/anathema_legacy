package net.sf.anathema.character.generic.magic.spells;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.lib.util.IIdentificate;

//TODO: Find a way to operate with circletypes without breaking compareTo
public enum CircleType implements IIdentificate {

  Terrestrial {
    @Override
    public void accept(ICircleTypeVisitor visitor) {
      visitor.visitTerrestrial(this);
    }

    @Override
    public CircleType[] getComparableCircles() {
      return getSorceryCircles();
    }
    
    public boolean isSorceryCircle() {
      return true;
    }
    
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
    
    public boolean isSorceryCircle() {
      return true;
    }
    
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
    
    public boolean isSorceryCircle() {
      return true;
    }
    
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
    
    public boolean isSorceryCircle() {
      return false;
    }
    
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
    
    public boolean isSorceryCircle() {
      return false;
    }
    
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
    
    public boolean isSorceryCircle() {
      return false;
    }
    
    public boolean isNecromancyCircle() {
      return true;
    }
  };

  public String getId() {
    return name();
  }

  public abstract void accept(ICircleTypeVisitor visitor);

  public abstract CircleType[] getComparableCircles();
  
  public abstract boolean isSorceryCircle();
  
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
    List<CircleType> circleList = new ArrayList<CircleType>();
    for (CircleType circle : allCircles) {
      if (circle.compareTo(maximumCircle) <= 0) {
        circleList.add(circle);
      }
    }
    return circleList.toArray(new CircleType[circleList.size()]);
  }
}