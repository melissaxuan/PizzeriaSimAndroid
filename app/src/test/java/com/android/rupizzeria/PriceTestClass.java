package com.android.rupizzeria;


import org.junit.Test;

import projects.rupizzeria.rupizzeria.util.Size;
import projects.rupizzeria.rupizzeria.util.Topping;
import projects.rupizzeria.rupizzeria.pizza.impl.BuildYourOwn;
import static org.junit.Assert.assertEquals;

/**
 * Junit test class for the price method in BuildYourOwn class.
 * @author Michael Ehresman
 */
public class PriceTestClass {
  double testPrice=0;
  double checkPrice=0;
  double failsafe = 0.001;


    /**
     * Test the price() method in the BuildYourOwn class
     */
    @Test
    public void testisValid() {
        BuildYourOwn test1 = new BuildYourOwn();//tests the standard price of build your own(Should be true)
        testPrice = 8.99;
        checkPrice = test1.price();
        assertEquals("The price of test1 is incorrect",testPrice,checkPrice,failsafe);

        BuildYourOwn test2 = new BuildYourOwn();//tests the price of medium-sized with 1 topping(Should be true)
        test2.setSize(Size.MEDIUM);
        test2.addTopping(Topping.HAM);
        testPrice = 12.68;
        checkPrice = test2.price();
        assertEquals("The price of test2 is incorrect",testPrice,checkPrice,failsafe);

        BuildYourOwn test3 = new BuildYourOwn();//tests the price of Medium-sized with 4 toppings(Should be true)
        test3.setSize(Size.MEDIUM);
        test3.addTopping(Topping.PEPPERONI);
        test3.addTopping(Topping.OXTAIL);
        test3.addTopping(Topping.BLACK_OLIVES);
        test3.addTopping(Topping.PROVOLONE);
        testPrice = 17.75;
        checkPrice = test3.price();
        assertEquals("The price of test3 is incorrect",testPrice,checkPrice,failsafe);

        BuildYourOwn test4 = new BuildYourOwn();//tests the price of small-sized with 7 toppings(Should be true)
        test4.addTopping(Topping.HAM);
        test4.addTopping(Topping.CHEDDAR);
        test4.addTopping(Topping.ONION);
        test4.addTopping(Topping.PEPPERONI);
        test4.addTopping(Topping.OXTAIL);
        test4.addTopping(Topping.BLACK_OLIVES);
        test4.addTopping(Topping.PROVOLONE);
        testPrice = 20.82;
        checkPrice = test4.price();
        assertEquals("The price of test4 is incorrect",testPrice,checkPrice,failsafe);

        BuildYourOwn test5 = new BuildYourOwn();//tests the price of large-sized with 2 toppings(Should be False)
        test5.setSize(Size.LARGE);
        test5.addTopping(Topping.BEEF);
        test5.addTopping(Topping.CHEDDAR);
        testPrice = 16.57;//should be off by $0.20 real price is 16.37
        checkPrice = test5.price();
        assertEquals("The price of test5 is incorrect",testPrice,checkPrice,failsafe);

    }
}
