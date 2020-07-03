package sbnz.projekat.nutritionadviser.event;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import sbnz.projekat.nutritionadviser.model.Meal;
import sbnz.projekat.nutritionadviser.model.User;


@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("6h")
public class EatingMealEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date executionTime;
	private User user;
	private Meal meal;
	
	public EatingMealEvent() {
		
	}
	
	public EatingMealEvent(Date executionTime, User user, Meal meal) {
		super();
		this.executionTime = executionTime;
		this.user = user;
		this.meal = meal;
	}
	public Date getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}