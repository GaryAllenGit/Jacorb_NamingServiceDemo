# Client.py  
# client for the CounterPortable demo

# Standard/built-in modules.
import sys
from time import time

# Omniorb modules.
from omniORB import CORBA, PortableServer

# COS Naming modules.
import CosNaming

# Need to import client side package
import CounterPortable


def main(argv):
	# Initialize the ORB
    	orb = CORBA.ORB_init(sys.argv, CORBA.ORB_ID)

        # Get a reference to the Naming service                 
	ns = orb.resolve_initial_references ("NameService");
        rootContext = ns._narrow(CosNaming.NamingContext)

	if rootContext == None : 
		print "Failed to narrow the root naming context"
                sys.exit(1)


	# resolve the Count object in the Naming service
	countName =  [CosNaming.NameComponent("countName", "")]
	counter = rootContext.resolve(countName)  

	#Set sum to initial value of 0
	print "Setting sum to 0"
	  
	counter._set_sum(0)  

	# Calculate Start time
	startTime = time() 

	# Increment 1000 times
	print "Incrementing"  
	for i in range(1000) :
		counter.increment()  
       
	# Calculate stop time   print out statistics
	stopTime = time()
	print "Avg Ping = ", stopTime - startTime , " msecs"  
	print "Sum = ", counter._get_sum()  
     
   
 
if __name__ == '__main__':
    sys.exit(main(sys.argv))


