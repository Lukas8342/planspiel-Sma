package facade;

import java.util.ArrayList;

import domain.Roles;
import domain.Rolle;
import domain.Teilszenarien;

public class gamecontroller {
	Roles roles = new Roles();
	Teilszenarien szen = new Teilszenarien();

	public void asignRoles(String name, int i) {
		roles.asign(i, name);
	}

	public ArrayList<Rolle> getRoles() {
		return roles.getRoles();
	}

	public void createRoles() {
		roles.createAllRoles();
	}

	public String[] getScenario(int id) {
		return szen.getScenario(id);
	}

}
