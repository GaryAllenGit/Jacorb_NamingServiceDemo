This is a demo of the Python OmniOrb naming service, omniNames.  Follow the instructions very carefully to make it work.

1. In a terminal (inside your IDE or stand-alone) 'cd' to the folder containing this demo.  For example I would type:


    cd IntelliJProjects/Jacorb_NamingServiceDemo/Python_OmniOrb/

2.  Precompile the idl:


	omniidl -bpython count.idl


3. Run up an OmniOrb Naming Service server with the command:


	omniNames -start 1050 -always -logdir /tmp

As the naming server starts it will display its IOR on the screen.  You will need to copy and paste this IOR into some of the following commands.


4. In another terminal, run the demo server:


	python server.py -ORBInitRef NameService=<<the IOR of the name service>>

for example:

	python server.py -ORBInitRef NameService=IOR:000000000000002b49444c3a6f6d672e6f72672f436f734e616d696e672f4e616d696e67436f6e746578744578743a312e300000000000010000000000000096000102000000000a3132372e302e312e3100041a00000045afabcb0000000020000f424000000001000000000000000200000008526f6f74504f41000000000d544e616d65536572766963650000000000000008000000010000000114000000000000020000000100000020000000000001000100000002050100010001002000010109000000010001010000000026000000020002


This will produce output something like:

	Server created and accepting requests...


5. Start client in another terminal:


	python client.py -ORBInitRef NameService=<<the IOR of the name service>>

for example:

	python client.py -ORBInitRef NameService=IOR:000000000000002b49444c3a6f6d672e6f72672f436f734e616d696e672f4e616d696e67436f6e746578744578743a312e300000000000010000000000000096000102000000000a3132372e302e312e3100041a00000045afabcb0000000020000f424000000001000000000000000200000008526f6f74504f41000000000d544e616d65536572766963650000000000000008000000010000000114000000000000020000000100000020000000000001000100000002050100010001002000010109000000010001010000000026000000020002

This will produce output something like:
	Setting sum to 0
	Incrementing
	Avg Ping =  0.661131858826  msecs
	Sum =  1000


REMEMBER to replace the IOR in the above examples with the one generated when you started up the omniNames naming service.



NOTE : The IOR to the Naming Service can be the IOR for ANY name server, not necessarily the OmniOrb one.  e.g. it could be the Java Jacorb one.

Look in the main "src" folder of this demo for a Java/Jacorb version of this same demo.
There is a README file in there with instructions for how to run it.
Run the Java demo, then see whether you can work out how to make these demos interoperable.
For example, the Java client could call the python server using the Jacorb (java) naming service.
The Java client could call the python server using the OmniOrb (python) naming service.
The Java client could call the Java server using the OmniOrb (python) naming service.
The Python client could call the Java server using either naming service.
etc.....



Remember to kill the server AND the naming service when you have finished with them by pressing ctrl-c.
