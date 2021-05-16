package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore store = mock(TorpedoStore.class);
  private GT4500 ship;

  @BeforeEach
  public void init(){
    this.ship = new GT4500(store, store);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    // Set the behavior of the mock: if it is called with
    // parameter "1" then return true.
    when(store.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(store, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(store.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(store, times(2)).fire(1);
  }

}
