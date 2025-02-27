import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import api.Admin;
import api.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AdminImpl;
import service.PatientImpl;

public class Main {

  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    System.setProperty("java.rmi.server.hostname", "localhost");
    try {
      logger.info("Starting the server");
      Admin adminStub = (Admin) UnicastRemoteObject.exportObject(AdminImpl.getInstance(), 0);
      Patient patientStub = (Patient) UnicastRemoteObject.exportObject(PatientImpl.getInstance(), 0);
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind("AdminMTL", adminStub);
      registry.rebind("PatientMTL", patientStub);

      logger.info("Server is ready");

    } catch (RemoteException e) {
      e.printStackTrace();
    }

  }
}
