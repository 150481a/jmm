package transienttest;

import java.io.Serializable;
import java.util.Objects;

public class User  implements Serializable {

    public  int id;

    public  static transient String  name;

    public  transient String pasword;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(pasword, user.pasword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pasword);
    }
}
