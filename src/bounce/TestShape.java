package bounce;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * A class that implements test cases aimed at identifying bugs in the
 * implementations of classes Shape and RectangleShape.
 * 
 * @author Ian Warren
 * 
 */
public class TestShape extends TestCase {
	// Fixture object that is used by the tests.
	private MockPainter _painter;

	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so
	 * that there no side effects from running individual tests.
	 */
	@Before
	public void setUp() {
		_painter = new MockPainter();
	}

	/**
	 * Test to ensure that the OvalShape instance paints itself correctly. The
	 * move method is tested in testSimpleMove so it is not tested again here as
	 * this would be a redundant test.
	 */
	@Test
	public void testOvalShape() {
		OvalShape shape = new OvalShape(100, 20, 12, 15);
		shape.paint(_painter);
		OvalShape shape1 = new OvalShape();
		shape1.paint(_painter);
		OvalShape shape2 = new OvalShape(100, 20, 12, 15, 13, 21);
		shape2.paint(_painter);
		assertEquals("(oval 100,20,25,35)(oval 0,0,25,35)(oval 100,20,13,21)",
				_painter.toString());
	}

	/**
	 * Test to ensure that the OvalShape instance paints itself correctly. The
	 * move method is tested in testSimpleMove so it is not tested again here as
	 * this would be a redundant test.
	 */
	@Test
	public void testGemShape() {
		GemShape shape = new GemShape(100, 20, 12, 15);
		shape.paint(_painter);

		GemShape shape1 = new GemShape();
		shape1.paint(_painter);

		GemShape shape2 = new GemShape(100, 20, 12, 15, 13, 21); // Small
																	// GemShape
		shape2.paint(_painter);

		GemShape shape3 = new GemShape(100, 20, 12, 15, 45, 45); // Regular
																	// GemShape
		shape3.paint(_painter);

		assertEquals(
				"(line 112,20,125,37)(line 125,37,112,55)(line 112,55,100,37)(line 100,37,112,20)(line 12,0,25,17)(line 25,17,12,35)(line 12,35,0,17)(line 0,17,12,0)(line 106,20,113,30)(line 113,30,106,41)(line 106,41,100,30)(line 100,30,106,20)(line 120,20,125,20)(line 125,20,145,42)(line 145,42,125,65)(line 120,65,125,65)(line 120,65,100,42)(line 100,42,120,20)",
				_painter.toString());
	}

	/**
	 * Test to perform bounce movements off of the the vertical and horizontal
	 * walls and to ensure that the DynamicRectangleShapes color is correct
	 * after the movement.
	 */
	@Test
	public void testDynamicRectangleShape() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		assertEquals(
				"(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 100,20,25,35)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 110,35,25,35)(fillRect 110, 35, 25, 35)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 98,50,25,35)(fillRect 98, 50, 25, 35)(setColor null )",
				_painter.toString());
	}

	@Test
	public void testDynamicRectangleShapeBounceOffTop() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 2, 5, -2);
		shape.paint(_painter);
		shape.move(1000, 1000);
		shape.paint(_painter);
		shape.move(1000, 1000);
		shape.paint(_painter);
		assertEquals(
				"(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 100,2,25,35)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 105,0,25,35)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 110,2,25,35)(setColor null )",
				_painter.toString());
	}

	@Test
	public void testDynamicRectangleShapeBounceOffBottom() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 70, 5, 10,
				10, 20);
		shape.paint(_painter);
		shape.move(1000, 100);
		shape.paint(_painter);
		shape.move(1000, 100);
		shape.paint(_painter);
		assertEquals(
				"(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 100,70,10,20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 105,80,10,20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 110,70,10,20)(setColor null )",
				_painter.toString());
	}

	@Test
	public void testDynamicRectangleShapeBounceOffRightSide() {
		DynamicRectangleShape shape = new DynamicRectangleShape(80, 70, 20, 10,
				10, 20);
		shape.paint(_painter);
		shape.move(100, 1000);
		shape.paint(_painter);
		shape.move(100, 1000);
		shape.paint(_painter);
		assertEquals(
				"(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 80,70,10,20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 90,80,10,20)(fillRect 90, 80, 10, 20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 70,90,10,20)(fillRect 70, 90, 10, 20)(setColor null )",
				_painter.toString());
	}

	@Test
	public void testDynamicRectangleShapeBounceOffLeftSide() {
		DynamicRectangleShape shape = new DynamicRectangleShape(20, 70, -20,
				10, 10, 20);
		shape.paint(_painter);
		shape.move(100, 1000);
		shape.paint(_painter);
		shape.move(100, 1000);
		shape.paint(_painter);
		assertEquals(
				"(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 20,70,10,20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 0,80,10,20)(fillRect 0, 80, 10, 20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 20,90,10,20)(fillRect 20, 90, 10, 20)(setColor null )",
				_painter.toString());
	}

	@Test
	public void testDynamicRectangleShapeBounceOffTopLeftCorner() {
		DynamicRectangleShape shape = new DynamicRectangleShape(5, 5, -5, -5,
				10, 20);
		shape.paint(_painter);
		shape.move(100, 1000);
		shape.paint(_painter);
		shape.move(100, 1000);
		shape.paint(_painter);
		assertEquals(
				"(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 5,5,10,20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 0,0,10,20)(fillRect 0, 0, 10, 20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 5,5,10,20)(fillRect 5, 5, 10, 20)(setColor null )",
				_painter.toString());
	}

	@Test
	public void testDynamicRectangleShapeBounceOffTopRightCorner() {
		DynamicRectangleShape shape = new DynamicRectangleShape(85, 95, 5, -5,
				10, 20);
		shape.paint(_painter);
		shape.move(100, 1000);
		shape.paint(_painter);
		shape.move(100, 1000);
		shape.paint(_painter);
		assertEquals(
				"(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 85,95,10,20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 90,90,10,20)(fillRect 90, 90, 10, 20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 85,85,10,20)(fillRect 85, 85, 10, 20)(setColor null )",
				_painter.toString());
	}

	@Test
	public void testDynamicRectangleShapeBounceOffBottomLeftCorner() {
		DynamicRectangleShape shape = new DynamicRectangleShape(5, 25, -5, 5,
				10, 20);
		shape.paint(_painter);
		shape.move(1000, 100);
		shape.paint(_painter);
		shape.move(1000, 100);
		shape.paint(_painter);
		assertEquals(
				"(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 5,25,10,20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 0,30,10,20)(fillRect 0, 30, 10, 20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 5,35,10,20)(fillRect 5, 35, 10, 20)(setColor null )",
				_painter.toString());
	}

	@Test
	public void testDynamicRectangleShapeBounceOffBottomRigthCorner() {
		DynamicRectangleShape shape = new DynamicRectangleShape(85, 75, 5, 5,
				10, 20);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals(
				"(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 85,75,10,20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 90,80,10,20)(fillRect 90, 80, 10, 20)(setColor null )(getColor)(setColor java.awt.Color[r=0,g=0,b=0] )(rectangle 85,75,10,20)(fillRect 85, 75, 10, 20)(setColor null )",
				_painter.toString());
	}

	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 112,35,25,35)",
				_painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 110,35,25,35)"
				+ "(rectangle 98,50,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		RectangleShape shape = new RectangleShape(10, 20, -12, 15);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		assertEquals("(rectangle 10,20,25,35)(rectangle 0,35,25,35)"
				+ "(rectangle 12,50,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the bottom right corner and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndRight() {
		RectangleShape shape = new RectangleShape(10, 90, -12, 15);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		assertEquals("(rectangle 10,90,25,35)(rectangle 0,100,25,35)"
				+ "(rectangle 12,85,25,35)", _painter.toString());
	}
}
