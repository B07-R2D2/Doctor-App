import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Doctor extends User{
    private String bio;
    private String uni;
    private int doctorId;
//    private AvailabilitySchedule availability;
    static HashMap<String, HashSet<Doctor>> specialization = new HashMap<String, HashSet<Doctor>>();

    /* Doctor class constructor */
    public Doctor(String firstName, String lastName, Date birthday, int sin, String bio, String uni, int doctorId, String specialization) {
        super(firstName, lastName, birthday, sin);
        this.bio = bio;
        this.uni = uni;
        this.doctorId = doctorId;
        /* initialized static field specialization */
        /* if the key is already in the HashMap, we add the doctor into its value, HashSet */
        /* else we insert an the new key and value into the HashMap*/
        if(this.specialization.containsKey(specialization)){
            this.specialization.get(specialization).add(this);
            this.specialization.put(specialization, this.specialization.get(specialization));
        }
        else{
            HashSet<Doctor> d = new HashSet<Doctor>();
            d.add(this);
            this.specialization.put(specialization, d);
        }
    }

    /* To find the available timeslots for the doctor */
//    public AvailabilitySchedule availability(){
//
//    }

    @Override
    public boolean equals(Object obj) {
//        if(obj == null)
//            return false;
//        if(obj.getClass() != this.getClass())
//            return false;
//        Doctor d = (Doctor)obj;
//        return this.getSin() != d.getSin();
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
        /* return doctorId; */
    }

    @Override
    public String toString() {
        return "Dr. " + super.toString();
    }

    /* Getters and Setters for all private variables. */
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
