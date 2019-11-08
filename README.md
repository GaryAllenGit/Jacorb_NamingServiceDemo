This is a demo of the Corba Naming Service written to use the Jacorb Orb.  There is also a Python version that uses the OmniOrb Orb.
Both demos actually use a remote 'count' object to calculate the average ping time of 1000 remote method calls.

Dr Gary Allen.
University of Huddersfield

If you don't already have it, download Jacorb from the Jacorb web site and unpack it somewhere.

Also download the library jboss-rmi-api_1.0_spec-1.0.6.Final.jar from the Maven Repository at:

    https://mvnrepository.com/artifact/org.jboss.spec.javax.rmi/jboss-rmi-api_1.0_spec/1.0.6.Final.

This is a workaround to fix an issue with Java 11 and above.

To run the demo:

1.  Add all the libraries from the Jacorb lib folder to the project (they are not all required, but I don't know the minmum set) **AND** add the jboss library too.

2.  Precompile the idl.  To do this use the terminal built into Intellij (or an external terminal if you prefer) and type:


    cd src
    <path_to_jacorb_dir>/bin/idl count.idl

e.g. I would type:

    cd src
    /spare/jacorb-3.9/bin/idl count.idl
    
3.  Start the Jacorb naming service by typing the following into the same (or a new) terminal:


    cd <path_to_jacorb_dir>/bin
    ns -Djacorb.naming.ior_filename=<path to file to contain IOR of the name service>

e.g. I would type:

    cd /spare/jacorb-3.9/bin
    ns -Djacorb.naming.ior_filename=/home/scomga/nameserver.ior


4.  Create Run Configurations for the CountPortableServer and CountPortableClient programs (the easy way to do this is to try to run them, knowing they will fail this first time) then edit the Run Configurations as follows:

4.1  To stop Jacorb displaying extensive logging information add the following VM args:

    -Djacorb.log.default.verbosity=2
    
4.2 To allow the programs to find the Naming Service add the following Program Argument:

    -ORBInitRef.NameService=file:<path to file to contain IOR of the name service>

e.g. mine would be:

    -ORBInitRef.NameService=file:/home/scomga/nameserver.ior

4.  Start the Server and then run the Client.  The server persists so has to be stopped externally.



ALTERNATIVE DEMO using IOR in a file trick to locate the naming service

The programs NamingDemoWithIORInFileServer and NamingDemoWithIORInFileClient are similar to the above but use the "IOR in a file" trick to locate the Naming Service.
Start the naming service just as in the demo above, then edit both programs so that the line following the comment "// Get a reference to the Naming service" contains the name of the file containing the IOR (in the demo above, this would be /home/scomga/nameserver.ior).
Run the server then the client.  The server persists so has to be stopped externally.  You will not need any Program Arguments, but you can add the same VM Args as above to surpress too much debugging info.


NOTE : Since the file nameserver.ior file holds an IOR to a Naming Service it can be the IOR for ANY name server, not necessarily the Jacorb one.  e.g. it could be the python OmniOrb one.

Look in the "Python_OmniOrb" folder for a Python version of this same demo.
There is a README file in there with instructions for how to run it.
Run the python demo, then see whether you can work out how to make these demos interoperable.
For example, the Java client could call the python server using the Jacorb (java) naming service.
The Java client could call the python server using the OmniOrb (python) naming service.
The Java client could call the Java server using the OmniOrb (python) naming service.
The Python client could call the Java server using either naming service.
etc.....

