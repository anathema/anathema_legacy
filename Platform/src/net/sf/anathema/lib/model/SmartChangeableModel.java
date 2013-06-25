package net.sf.anathema.lib.model;

import com.google.common.base.Objects;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import org.jmock.example.announcer.Announcer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class SmartChangeableModel implements Cloneable, IChangeableModel {

  private transient Announcer<ChangeListener> listeners = Announcer.to(ChangeListener.class);
  private transient ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  @Override
  protected Object clone() {
    try {
      SmartChangeableModel clone = (SmartChangeableModel) super.clone();
      clone.listeners = Announcer.to(ChangeListener.class);
      clone.readWriteLock = new ReentrantReadWriteLock();
      return clone;
    }
    catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public void removeChangeListener(ChangeListener listener) {
    listeners.removeListener(listener);
  }

  protected void fireChangeEvent() {
    listeners.announce().changeOccurred();
  }

  protected <T> T getValue(IProperty<T> property) {
    Lock readLock = readWriteLock.readLock();
    readLock.lock();
    try {
      return property.get();
    }
    finally {
      readLock.unlock();
    }
  }

  protected final <T> void setValue(IProperty<T> property, T value) {
    Lock writeLock = readWriteLock.writeLock();
    writeLock.lock();
    try {
      if (Objects.equal(property.get(), value)) {
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