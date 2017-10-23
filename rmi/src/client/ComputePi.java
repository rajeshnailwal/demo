package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import compute.Compute;

import java.math.BigDecimal;

public class ComputePi {
    public static void main(String args[]) {
    	System.setProperty("java.security.policy","resource/rmi.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            //Registry registry = LocateRegistry.getRegistry(args[0]);
          //If no url or port specified then rmi server uses localhost and 1099 port
            Registry registry = LocateRegistry.getRegistry();
            Compute comp = (Compute) registry.lookup(name);
            //Pi task = new Pi(Integer.parseInt(args[1]));
            Pi task = new Pi(45);
            BigDecimal pi = comp.executeTask(task);
            System.out.println(pi);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }    
}