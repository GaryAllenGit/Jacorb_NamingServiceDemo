import CounterPortable.*;


class CountPortableImpl extends CountPOA  {
    private int sum;

    // Constructors
    CountPortableImpl() {
        super();
        System.out.println("Count Object Created");
        sum = 0;
    }

    // get sum
    public synchronized int sum() {
        return sum;
    }

    // set sum
    public synchronized void sum(int val) {
        sum = val;
    }

    // increment method
    public synchronized int increment() {
        sum++;
        return sum;
    }
}
