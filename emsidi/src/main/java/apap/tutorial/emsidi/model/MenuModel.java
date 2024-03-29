package apap.tutorial.emsidi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "menu")
public class MenuModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noMenu;

    @NotNull
    @Size(max=30)
    @Column(name ="nama_menu", nullable = false)
    private String namaMenu;

    @Column(name="is_available", nullable = false)
    private boolean isAvailable;

    // Relasi dgn CabangModel
    @ManyToMany(mappedBy = "listMenu")
    List<CabangModel> listCabang;

    @NotNull
    @Size(max=50)
    @Column(nullable = false)
    private String deskripsiMenu;
}
