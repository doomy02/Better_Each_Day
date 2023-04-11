package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
            {@NamedQuery(name = "findQuestByName", query = "from Quest q where q.name = :name"),
                @NamedQuery(name = "findQuestById", query = "from Quest q where q.id = :id"),
                @NamedQuery(name = "findAllQuests", query = "from Quest"),
        }
)

public class Quest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    @Column
    private Boolean availability;

    @Column
    private Integer tokens;

    @Column
    private String firstPolynomial;

    @Column
    private String lastPolynomial;

    @OneToOne
    private Person owner;

    @Column
    private String result;

    @Column
    private String operation;

}
