import java.sql.Connection;

public class Animal {

    public String name;
    public  Integer Id;

    public Animal(String name, Integer id) {
        this.name = name;
        Id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return Id;
    }
    @Override
    public  boolean equals(Object otherAnimal) {
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (newAnimal).otherAnimal;
            return this.getId().equals(newAnimal.getId()) &&
                    this.getName().equals(newAnimal.getName());
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO persons (name, email) VALUES (:name, :email)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("Id", this.Id)
                    .executeUpdate();
        }
    }


}