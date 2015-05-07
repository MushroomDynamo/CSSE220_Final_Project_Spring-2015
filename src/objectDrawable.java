
public class objectDrawable {
	public String objectType;
	
	public objectDrawable(String objectTypeToSet) {
		this.objectType = objectTypeToSet;
	}
	
	public boolean setObjectType(String objectTypeToSet) {
		this.objectType = objectTypeToSet;
		return true;
	}
	
	public String getObjectType() {
		return objectType;
	}
}
