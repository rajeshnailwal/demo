package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import compute.Compute;
import compute.Task;

public class ComputeEngine implements Compute {

    public ComputeEngine() {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }

    public static void main(String[] args) {
        try {
        	
        	
        	/**
        	 * Need to provide resource policy to to override security
        	 */
        	System.setProperty("java.security.policy","resource/rmi.policy");
        	//System.setProperty("java.rmi.server.useCodebaseOnly", "false");
        	
        	/**
        	 * Before proceeding we need to start rmiregistry (jre/bin/rmiregistry.exe)
        	 * If you don't want to use java.rmi.server.codebase property
        	 * then start rmiregistry from the location of your codebase (/C:/Development/eclipse_neon/workspace/rmi/bin/)
        	 * otherwise use System.setProperty("java.rmi.server.codebase", "file:/C:/Development/eclipse_neon/workspace/rmi/bin/");
        	 */
        	System.setProperty("java.rmi.server.codebase", "file:/C:/Development/eclipse_neon/workspace/rmi/bin/");
        	startServer();
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
    
    private static void startServer() throws RemoteException{
    	if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        String name = "Compute";
        Compute engine = new ComputeEngine();
        Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
        //If no url or port specified in getRegistry then rmi server uses localhost and 1099 port
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind(name, stub);
        System.out.println("ComputeEngine bound");
    }
    
    private static boolean isWindows() {
        String os = System.getProperty("os.name");
        if (os == null) {
            throw new IllegalStateException("os.name");
        }
        System.out.println(os);
        os = os.toLowerCase();
        return os.startsWith("windows");
    }
    
    public static File getRMIRegistryExecutable() throws FileNotFoundException {
        String jreDirectory = System.getProperty("java.home");
        System.out.println(jreDirectory);
        if (jreDirectory == null) {
            throw new IllegalStateException("java.home");
        }
        File exe;
        if (isWindows()) {
            exe = new File(jreDirectory, "bin/rmiregistry.exe");
        } else {
            exe = new File(jreDirectory, "bin/rmiregistry");
        }
        if (!exe.isFile()) {
            throw new FileNotFoundException(exe.toString());
        }
        return exe;
    }
    
    public static void addPath(File f) throws Exception {
        URL u = f.toURL();
        System.out.println("++"+u.toExternalForm());
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class urlClass = URLClassLoader.class;
        Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(urlClassLoader, new Object[]{u});
    }
    
    public static int launch(List<String> cmdarray) throws IOException, InterruptedException {
    	byte[] buffer = new byte[1024];

    	ProcessBuilder processBuilder = new ProcessBuilder(cmdarray);
    	processBuilder.redirectErrorStream(true);
    	Process process = processBuilder.start();
    	InputStream in = process.getInputStream();
		while (true) {
		    int r = in.read(buffer);
		    if (r <= 0) {
		        break;
		    }
		    System.out.write(buffer, 0, r);
		}
		return process.waitFor();
	}
}