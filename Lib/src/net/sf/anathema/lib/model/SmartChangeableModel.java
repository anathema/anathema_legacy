package net.sf.anathema.lib.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.util.ObjectUtilities;
import org.jmock.example.announcer.Announcer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class SmartChangeableModel implements Cloneable, IChangeableModel {

  private transient Announcer<IChangeListener> listeners = Announcer.to(IChangeListener.class);
  private transient ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  @Override
  protected Object clone() {
    try {
      final SmartChangeableModel clone = (SmartChangeableModel) super.clone();
      clone.listeners = Announcer.to(IChangeListener.class);
      clone.readWriteLock = new ReentrantReadWriteLock();
      return clone;
    }
    catch (final CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }

  @Override
  public void addChangeListener(final IChangeListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public void removeChangeListener(final IChangeListener listener) {
    listeners.removeListener(listener);
  }

  protected void fireChangeEvent() {
    listeners.announce().changeOccurred();
  }

  protected <T> T getValue(final IProperty<T> property) {
    final Lock readLock = readWriteLock.readLock();
    readLock.lock();
    try {
      return property.getValue();
    }
    finally {
      readLock.unlock();
    }
  }

  protected final <T> void setValue(final IProperty<T> property, final T value) {
    final Lock writeLock = readWriteLock.writeLock();
    writeLock.lock();
    try {
      if (ObjectUtilities.equals(property.getValue(), value)) {
        return;
      }
      property.setValue(value);
    }
    finally {
      writeLock.unlock();
    }
    fireChangeEvent();
  }

}