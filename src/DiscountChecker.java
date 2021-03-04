import java.util.List;

//Imports for class

/**
 * Class for storing orders on a particular bill
 * 
 * @author Rose Ulldemolins
 * 
 */

public class DiscountChecker {

    private List<Order> orderList;
    private double orderPrice;

    /**
     * Constructor for discount checker
     * 
     * @param ordersToBeChecked A list of orders to be checked for a discount
     */
    public DiscountChecker(List<Order> ordersToBeChecked, double orderPrice) {
        this.orderList = ordersToBeChecked;
        this.orderPrice = orderPrice;
    }

    /**
     * 
     * @return double
     */
    public double overallDiscount() {
        if (mealDeal() > 0) {
            return mealDeal();
        } else if (spendOver() > 0) {
            return spendOver();
        } else if (afternoonTea() > 0) {
            return afternoonTea();
        } else if (categoryOfTheDay() > 0) {
            return categoryOfTheDay();
        } else
            return 0.0;

    }

    /**
     * Calculates the total discount if a bill has a cold drink, main and snack so
     * that it costs £5
     * 
     * @return double
     */
    public double mealDeal() {
        double mealTotal = 0;
        boolean drinkAdded = false;
        boolean mainAdded = false;
        boolean snackAdded = false;
        for (Order order : orderList) {
            if (order.getItem().getItemCat() == Item.ItemCat.COLDDRINK && !drinkAdded) {
                drinkAdded = true;
                mealTotal += order.getItem().getItemPrice();
            } else if (order.getItem().getItemCat() == Item.ItemCat.MAIN && !mainAdded) {
                mainAdded = true;
                mealTotal += order.getItem().getItemPrice();
            } else if (order.getItem().getItemCat() == Item.ItemCat.SNACKS && !snackAdded) {
                snackAdded = true;
                mealTotal += order.getItem().getItemPrice();
            }
        }
        double discount = mealTotal - 5;
        return Math.round(discount * 100.0) / 100.0;
    }

    /**
     * Calculates the total discount if an order is over £10 so that they get £2 off
     * 
     * @return double
     */
    public double spendOver() {
        double discount = 0.0;
        if (orderPrice >= 10) {
            discount = 2.0;
        }
        return discount;
    }

    /**
     * Calculate the total discount if they have 2 hot drinks and 2 snacks on an
     * order
     * 
     * @return double
     */
    public

            double afternoonTea() {
        int hotDrinkCount = 0;
        int snacksCount = 0;
        double hotDrinksTotal = 0.0;
        double snacksTotal = 0.0;
        for (Order order : orderList) {
            if (order.getItem().getItemCat() == Item.ItemCat.HOTDRINK && hotDrinkCount < 2) {
                hotDrinksTotal += order.getItem().getItemPrice();
                ++hotDrinkCount;
            } else if (order.getItem().getItemCat() == Item.ItemCat.SNACKS && snacksCount < 2) {
                snacksTotal += order.getItem().getItemPrice();
                ++snacksCount;
            }
        }

        double discount = hotDrinksTotal + snacksTotal - 6;
        return Math.round(discount * 100.0) / 100.0;
    };

    /**
     * Calculates the amount of discount when hot drinks are 20%
     * 
     * @return double
     */
    public double categoryOfTheDay() {
        double hotDrinksTotal = 0.0;
        for (Order order : orderList) {
            if (order.getItem().getItemCat() == Item.ItemCat.HOTDRINK) {
                hotDrinksTotal += order.getItem().getItemPrice();
            }
        }
        double discount = hotDrinksTotal * 0.2;
        return Math.round(discount * 100.0) / 100.0;
    }

}
