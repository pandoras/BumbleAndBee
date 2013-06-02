package nieuzywane;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ManagerKontaktow implements ContactListener {
	
		@Override
		public void beginContact (Contact contact) {
	        System.out.println("begin contact");
	        
		}
	
		@Override
		public void endContact (Contact contact) {
	        System.out.println("end contact");
		}
	
		@Override
		public void preSolve (Contact contact, Manifold oldManifold) {
			
	//Manifold.ManifoldType type = oldManifold.getType();
	//Vector2 localPoint = oldManifold.getLocalPoint();
	//Vector2 localNormal = oldManifold.getLocalNormal();
	//int pointCount = oldManifold.getPointCount();
	//ManifoldPoint[] points = oldManifold.getPoints();
	//System.out.println("pre solve, " + type +
	//", point: " + localPoint +
	//", local normal: " + localNormal +
	//", #points: " + pointCount +
	//", [" + points[0] + ", " + points[1] + "]");
		}
	
		@Override
		public void postSolve (Contact contact, ContactImpulse impulse) {
	//float[] ni = impulse.getNormalImpulses();
	//float[] ti = impulse.getTangentImpulses();
	//System.out.println("post solve, normal impulses: " + ni[0] + ", " + ni[1] + ", tangent impulses: " + ti[0] + ", " + ti[1]);
		}
}
