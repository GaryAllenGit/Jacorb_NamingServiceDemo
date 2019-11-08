import CounterPortable.*;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.io.*;


class NamingDemoWithIORInFileServer {
	static public void main(String[] args) {
		try {
			// Initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// Create the Count object
			CountPortableImpl count = new CountPortableImpl();

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(count);
			Count cref = CountHelper.narrow(ref);

			// Get a reference to the Naming service
			BufferedReader in = new BufferedReader(new FileReader("/home/scomga/nameserver.ior"));
			String stringified_ior = in.readLine();
			System.out.println("stringified_ior = " + stringified_ior);
			org.omg.CORBA.Object nameServiceObj =
					orb.string_to_object(stringified_ior);

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

			// bind the Count object in the Naming service
			String name = "countName";
			NameComponent[] countName = nameService.to_name(name);
			nameService.rebind(countName, cref);

			//  wait for invocations from clients
			orb.run();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

