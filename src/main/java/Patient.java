import java.io.Serializable;

public class Patient implements Serializable {
    public String name;
    public int id;
    public String phoneNumber;
    public Patient(String n,int id_value, String phoneN) {
        name = n;
        id = id_value;
        phoneNumber = phoneN;
    }
}
