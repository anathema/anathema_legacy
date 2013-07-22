package net.sf.anathema.framework.ui;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Coordinates implements Iterable<Coordinate> {
  private final List<Coordinate> coordinates = new ArrayList<>();

  public void add(Coordinate coordinate) {
    coordinates.add(coordinate);
  }

  public void translate(int dx, int dy) {
    List<Coordinate> newCoordinates = new ArrayList<>();
    for (Coordinate coordinate : coordinates) {
      Coordinate newCoordinate = coordinate.translate(dx, dy);
      newCoordinates.add(newCoordinate);
    }
    coordinates.clear();
    coordinates.addAll(newCoordinates);
  }

  @Override
  public Iterator<Coordinate> iterator() {
    return coordinates.iterator();
  }

  public Coordinate getPointOfOrigin() {
    return coordinates.get(0);
  }

  public Coordinate getPenultimatePoint() {
    return coordinates.get(coordinates.size() - 2);
  }

  public Coordinate getUltimatePoint() {
    return coordinates.get(coordinates.size() - 1);
  }

  public int count() {
    return coordinates.size();
  }

  public Coordinate get(int index) {
    return coordinates.get(index);
  }


  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return coordinates.hashCode();
  }
}