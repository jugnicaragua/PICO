package org.jugnicaragua.app.pico.data.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.jugnicaragua.app.pico.data.modelos.TipoMenu;

@Table(name = "menu")
@Entity(name = "menu")
public class Menu implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String label;

  @Column(name = "tipo_menu")
  @Enumerated(EnumType.STRING)
  private TipoMenu tipoMenu;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public TipoMenu getTipoMenu() {
    return tipoMenu;
  }

  public void setTipoMenu(TipoMenu tipoMenu) {
    this.tipoMenu = tipoMenu;
  }
}
