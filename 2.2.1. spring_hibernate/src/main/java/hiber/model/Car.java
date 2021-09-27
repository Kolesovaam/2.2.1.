package hiber.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private  int series;

    @OneToOne(mappedBy = "car")
    private User user;

    public User getUser() {
        return user;
    }


    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }


    @Override
    public String toString() {
        return "id = " + id + ", model = " + model + ", series = " + series + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getSeries() == car.getSeries() && getModel().equals(car.getModel()) && getUser().equals(car.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModel(), getSeries(), getUser());
    }
}
