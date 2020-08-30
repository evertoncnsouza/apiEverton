package org.everton.domain.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "assunto", length = 128)
    private String assunto;

    @Column(name = "descricao", length = 1000)
   private String descricao;

    @Column(name = "url", length = 200)
    private String url;

    @OneToMany( mappedBy = "video" , fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<Comment> comments;


   public Video(Integer id, String nome) {
       this.id = id;
       this.nome = nome;

    }

}
