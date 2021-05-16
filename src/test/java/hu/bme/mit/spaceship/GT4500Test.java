package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore store1 = mock(TorpedoStore.class);
  private TorpedoStore store2 = mock(TorpedoStore.class);
  private GT4500 ship;

  @BeforeEach
  public void init(){
    this.ship = new GT4500(store1, store2);
  }

  @Test
  public void fireTorpedo_Single_Full_Success(){
    // Arrange
    // Set the behavior of the mock: if it is called with
    // parameter "1" then return true.
    when(store1.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(store1, times(1)).fire(1);
    verify(store2, times(0)).fire(1);
  }

   @Test
  public void fireTorpedo_Single_Half_Success(){
    // Arrange
    when(store1.isEmpty()).thenReturn(true);
    when(store2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(store1, times(1)).isEmpty();
    verify(store2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Failure(){
    // Arrange
    when(store1.isEmpty()).thenReturn(true);
    when(store2.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(store1, times(1)).isEmpty();
    verify(store2, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(store1.fire(1)).thenReturn(true);
    when(store2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(store1, times(1)).fire(1);
    verify(store2, times(1)).fire(1);
  }

   @Test
  public void fireTorpedo_All_Failure(){
    // Arrange
    when(store1.isEmpty()).thenReturn(true);
    
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(store1, times(1)).isEmpty();
  }

}
