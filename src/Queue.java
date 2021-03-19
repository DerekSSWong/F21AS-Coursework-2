//Imports for class
import java.util.LinkedList;

/**
 *
 * Class for storing bills within a queue system
 *
 */

public class Queue {

    public LinkedList<Bill> QueueList = new LinkedList<Bill>();

    /**
     * Adds new bills to the end of the queue list
     * equivalent of a customer joining the back of a queue
     *
     * @param bill
     */

    public void addQueueBill(Bill bill){
        QueueList.addLast(bill);
    }

    /**
     * Removes the first bill in the queue
     * equivalent of serving the first customer
     *
     */

    public void removeQueueBill(){
        QueueList.removeFirst();
    }

    /**
     * Finds the Index of a searched bill
     *
     * @param bill
     * @return int
     */

    public int getQueueIndex(Bill bill){
        return QueueList.indexOf(bill);
    }

    /**
     * Retrieves the bill from the queue list located at an index position
     *
     * @param index
     * @return Bill
     */

    public Bill getQueueBill(int index){
        return QueueList.get(index);
    }

    /**
     * removes at a bill from the queue list at an index position
     *
     * @param index
     */

    public void removeBillAtIndex (int index){
        QueueList.remove(index);
    }
}
