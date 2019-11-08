import CounterPortable.*;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;

class CountPortableClient {
	public static void main(String[] args) {
		try {
			// Initialize the ORB
			System.out.println("Initializing the ORB");
			ORB orb = ORB.init(args, null);

			// Get a reference to the Naming service
			org.omg.CORBA.Object nameServiceObj =
					orb.resolve_initial_references ("NameService");
			if (nameServiceObj == null) {
				System.out.println("nameServiceObj = null");
				return;
			}

			// Use NamingContextExt instead of NamingContext. This is
			// part of the Interoperable naming Service.
			NamingContextExt nameService = NamingContextExtHelper.narrow(nameServiceObj);
			if (nameService == null) {
				System.out.println("nameService = null");
				return;
			}

			// resolve the Count object reference in the Naming service
			String name = "countName";
			Count counter = CountHelper.narrow(nameService.resolve_str(name));

			// Set sum to initial value of 0
			System.out.println("Setting sum to 0");
			counter.sum(0);

			// Calculate Start time
			long startTime = System.currentTimeMillis();

			// Increment 1000 times
			System.out.println("Incrementing");
			for (int i = 0 ; i < 1000 ; i++ ) {
				counter.increment();
			}

			// Calculate stop time; print out statistics
			long stopTime = System.currentTimeMillis();
			System.out.println("Avg Ping = "
					+ ((stopTime - startTime)/1000f) + " msecs");
			System.out.println("Sum = " + counter.sum());

		} catch(Exception e) {
			System.err.println("Exception");
			System.err.println(e);
		}
	}
}
