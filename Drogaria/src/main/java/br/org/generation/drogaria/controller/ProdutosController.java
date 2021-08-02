package br.org.generation.drogaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.drogaria.model.Produtos;
import br.org.generation.drogaria.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*",  allowedHeaders = "*")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> getAll(){
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtosRepository.findAllByNome(nome));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Produtos> getById(@PathVariable long id ){
		return produtosRepository.findById(id)
		.map(resp ->ResponseEntity.ok(resp))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Produtos> postProdutos(@RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(produtosRepository.save(produtos));
	}
	
	@PutMapping
	public ResponseEntity<Produtos> putProdutos(@RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.OK)
				.body(produtosRepository.save(produtos));
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		produtosRepository.deleteById(id);
	}
}