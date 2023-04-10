package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
        {@NamedQuery(name = "findPersonByName", query = "from Person pers where pers.name = :name"),
                @NamedQuery(name = "findPersonByEmailAndPassword", query = "from Person pers where pers.email = :email and pers.password=:password"),
                @NamedQuery(name = "findPersonById", query = "from Person pers where pers.id = :id"),
                @NamedQuery(name = "findAllPersons", query = "from Person"),
                @NamedQuery(name = "findPersonByEmail", query = "from Person pers where pers.email = :email")
        }
)
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private Integer tokens;

    @Column
    private String email;

    @Column
    private Integer noOfQuest = 0;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Badge> badges;
}
