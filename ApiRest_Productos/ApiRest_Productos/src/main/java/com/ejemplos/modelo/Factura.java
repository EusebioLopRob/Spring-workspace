package com.ejemplos.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="facturas")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String descripcion;
	
	@NotEmpty
	private String observacion;

	@NotNull
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "factura")
	private List<ItemFactura> items;
	
	//bi-directional many-to-one association to Cliente	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	public void addItemFactura(ItemFactura item) {
		this.items.add(item);
	}
	
	public Double calculaTotal() {
		Double total = 0.0;
		int size = items.size();
		
		for (int i = 0; i < size; i++) {
			total += items.get(i).calcularImporte();
			}
			
		return total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<ItemFactura> getItemsFacturas() {
		return items;
	}

	public void setItemsFacturas(List<ItemFactura> itemsFacturas) {
		items = itemsFacturas;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	private static final long serialVersionUID = 1L;

}
